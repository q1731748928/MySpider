package book.c4.com.jsoupconnect;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import org.jsoup.*;
import org.jsoup.Connection.*;
public class JsoupConnectProxy1 {
	public static void main(String[] args) throws IOException {
		//第一种方式设置代理
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("122.5.108.21", 9999));  
		Connection connection = Jsoup.connect("https://searchcustomerexperience.techtarget.com/info/news").proxy(proxy);
		Response response = connection.method(Method.GET).timeout(10*1000).execute();
		//获取响应状态码
		int statusCode = response.statusCode();  
		System.out.println("响应状态码为:" + statusCode);  
	}
}
