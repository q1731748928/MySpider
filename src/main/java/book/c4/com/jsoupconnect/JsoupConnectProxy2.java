package book.c4.com.jsoupconnect;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
public class JsoupConnectProxy2 {
	public static void main(String[] args) throws IOException {
		//第二种方式设置代理
		Connection connection = Jsoup.connect("https://searchcustomerexperience.techtarget.com/info/news")
				.proxy("163.204.240.107",9999);
		Response response = connection.method(Method.GET).timeout(20*1000).execute();
		//获取响应状态码
		int statusCode = response.statusCode();  
		System.out.println("响应状态码为:" + statusCode);  
	}
}
