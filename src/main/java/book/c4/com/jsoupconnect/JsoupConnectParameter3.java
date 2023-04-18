package book.c4.com.jsoupconnect;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupConnectParameter3 {

	public static void main(String[] args) throws IOException {
		Connection connect = Jsoup.connect("http://www.*****.com/ems.php");
		//添加参数
		connect.data("wen", "EH629625211CS", "action", "ajax");
		Response response = connect.method(Method.GET).ignoreContentType(true).execute();  
		//获取数据,处理成HTML
		Document document = response.parse();
		System.out.println(document);
	}

}
