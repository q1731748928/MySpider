package Two048;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/21
 * @Time 10:50
 */
public class GetImageFrom2048Test {
    public static void main(String[] args) throws IOException {
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        String cookie = "zh_choose=n; PPA_CI=743200b127d07d07b32d54a44a06e4df; a22e7_ol_offset=221742; a22e7_lastpos=F26; a22e7_threadlog=%2C3%2C75%2C280%2C92%2C76%2C72%2C43%2C23%2C26%2C; a22e7_lastvisit=921%091674269498%09%2F2048%2Fthread.php%3Ffid-26-page-2.html";
        String url = "http://bbs2023.cancansong.com/2048/state/p/26/2301/9150944.html";
        Document document = Jsoup.connect(url).proxy("localhost", 1084).userAgent(us).cookie("cookie",cookie).ignoreContentType(true).get();
        Elements img = document.select("img");
        for (Element element : img) {
            String src = element.attr("src");
            System.out.println("src = " + src);
        }
    }
}
