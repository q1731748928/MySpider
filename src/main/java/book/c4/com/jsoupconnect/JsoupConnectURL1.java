package book.c4.com.jsoupconnect;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupConnectURL1 {

	public static void main(String[] args) throws IOException {
		//创建连接
		Connection connect = Jsoup.connect("https://searchcustomerexperience.techtarget.com/info/news");
		//请求网页
		Document document = connect.get();
		//输出HTML
		System.out.println(document.html());
	}

}
