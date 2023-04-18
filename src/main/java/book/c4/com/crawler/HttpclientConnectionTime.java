package book.c4.com.crawler;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class HttpclientConnectionTime {
	public static void main(String[] args) throws ClientProtocolException, IOException  {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet=new HttpGet("https://searchcustomerexperience.techtarget.com/info/news");//HTTP Get请求(POST雷同)
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
		httpGet.setConfig(requestConfig);
		httpClient.execute(httpGet);//执行请求
		
	}
}

