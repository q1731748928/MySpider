package sis;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2022/11/20
 * @Time 22:44
 */
public class SisTempTest {
    public static void main(String[] args) throws IOException {
        String url = "http://23.225.255.95/forum/";
        url += "thread-11151428-1-42.html";
        Document document = Jsoup.connect(url).ignoreContentType(true).get();
        Elements a = document.getElementsByTag("a");
        System.out.println(a);
    }
}
