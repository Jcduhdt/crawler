package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Set;

/**
 * @author ZhangXiong
 * @version v12.0.1
 * @date 2020-01-04
 */
public class JsoupFirstTest {
    @Test
    public void testUrl() throws Exception{
        //解析url地址，第一个参数是访问的url，第二个参数是设置的超时时间
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);
        //使用标签选择器，获取第一个title标签中的内容
        Element title = doc.getElementsByTag("title").first();
        //打印
        System.out.println("title = " + title.text());
    }
    
    @Test
    public void testString() throws Exception{
        //使用工具类读取文件，获取字符串
        String content = FileUtils.readFileToString(new File("D:\\Java\\JavaProject\\crawler\\src\\main\\java\\test.html"),"utf-8");
        //解析字符串
        Document doc = Jsoup.parse(content);
        Element title = doc.getElementsByTag("title").first();
        System.out.println("title = " + title.text());
    }

    @Test
    public void testFile() throws Exception{
        //解析文件
        Document doc = Jsoup.parse(new File("D:\\Java\\JavaProject\\crawler\\src\\main\\java\\test.html"), "utf-8");
        Element title = doc.getElementsByTag("title").first();
        System.out.println("title = " + title.text());
    }

    @Test
    public void testDOM() throws Exception{
        //解析文件，获取Document文件
        Document doc = Jsoup.parse(new File("D:\\Java\\JavaProject\\crawler\\src\\main\\java\\test.html"), "utf-8");
        //获取元素
        //1.根据id查询元素getElementById
        Element element = doc.getElementById("city_bj");
        //2.根据标签获取元素getElementsByTag
        Element element1 = doc.getElementsByTag("span").first();
        //3.根据class获取元素getElementsByClass
        Elements element2 = doc.getElementsByClass("class_a");
        //4.根据属性获取元素getElementsByAttribute
        Element element3 = doc.getElementsByAttribute("abc").first();
        Element element4 = doc.getElementsByAttributeValue("href", "http://sh.itcast.cn").first();

        System.out.println("获取到的元素内容：" + element4.text());
    }

    @Test
    public void testData() throws Exception{
        //解析文件，获取Document文件
        Document doc = Jsoup.parse(new File("D:\\Java\\JavaProject\\crawler\\src\\main\\java\\test.html"), "utf-8");
        //根据id获取元素
        Element element = doc.getElementById("test");
        String str = "";
        //1.从元素中获取id
        str = element.id();
        //2.从元素中获取className
        str = element.className();
        /*Set<String> classSet = element.classNames();
        for (String s : classSet) {
            System.out.println(s);
        }*/
        //3.从元素中获取属性的值attr
        str = element.attr("class");
        //4.从元素中获取所有属性attributes
        Attributes attributes = element.attributes();
        System.out.println(attributes.toString());
        //5.从元素中获取文本内容text
        str = element.text();

        System.out.println("获取到的数据时： " + str);
    }
}
