package book.c4.com.crawler;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpclientTimeout1 {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		//全部设置为10秒
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(10000)
				.setConnectTimeout(10000)
				.setConnectionRequestTimeout(10000)
				.build();
		//配置httpClient
		HttpClient httpClient = HttpClients.custom()
				.setDefaultRequestConfig(requestConfig)
				.build();
		HttpGet httpGet = new HttpGet("https://searchcustomerexperience.techtarget.com/info/news");
		HttpResponse response = null;  
		try { 
			response = httpClient.execute(httpGet);  //执行请求
		}catch (Exception e){  
			e.printStackTrace();  
		} 
		String result = EntityUtils.toString(response.getEntity(),"gbk");  //获取结果，html
		System.out.println(result);   //输出结果
	}
}
