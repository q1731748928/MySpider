package book.c4.com.crawler;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
public class HttpGetInit {
	public static void main(String[] args) throws URISyntaxException {
		//第一种方式
		String personalUrl = "https://searchcustomerexperience.techtarget.com/info/news";
		URI uri = new URIBuilder(personalUrl).build();  //创建URI
		HttpGet getMethod = new HttpGet();  //  get方法请求
		getMethod.setURI(uri);  //设置
		System.out.println(getMethod);
		//第二种方式
		HttpGet httpGetUri = new HttpGet(uri);
		System.out.println(httpGetUri);
		//第三种
		HttpGet httpGetStr = new HttpGet(personalUrl);
		System.out.println(httpGetStr);

		
	}
}
