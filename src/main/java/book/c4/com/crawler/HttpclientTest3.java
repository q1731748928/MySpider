package book.c4.com.crawler;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpclientTest3 {
	public static void main(String[] args) throws ParseException, IOException  {
		CloseableHttpClient httpClient = HttpClients.createDefault(); 
		HttpGet httpGet=new HttpGet("https://searchcustomerexperience.techtarget.com/info/news");
		CloseableHttpResponse response = null;//请求响应
		try {
			response = httpClient.execute(httpGet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String entity = EntityUtils.toString (response.getEntity(),"gbk");
		System.out.println(entity);
		response.close();
	}
}

