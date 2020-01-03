package com.zx.crawler.test;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author ZhangXiong
 * @version v12.0.1
 * @date 2020-01-03
 */
public class HttpConfigTest {
    public static void main(String[] args) {
        //1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.创建HttpGet请求，设置url访问地址
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");
        //配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) //创建链接的最长时间，单位是ms
                .setConnectionRequestTimeout(500) //设置获取链接的最长时间，单位是ms
                .setSocketTimeout(10*1000) //设置数据传输的最长时间，单位是ms
                .build();
        //给请求设置请求的信息
        httpGet.setConfig(config);
        CloseableHttpResponse response = null;
        try{
            //3.使用HttpClient发起请求，获取response
            response = httpClient.execute(httpGet);
            //4.解析响应
            if (response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(),"utf-8");
                System.out.println(content.length());
            }
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            //关闭response
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
