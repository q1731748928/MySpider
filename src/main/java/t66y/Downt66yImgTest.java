package t66y;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/23
 * @Time 19:27
 */
public class Downt66yImgTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://www.t66y.com/htm_data/2301/16/5481012.html";
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        Document document = Jsoup.connect(url).proxy("localhost", 1085).userAgent(us).ignoreContentType(true).get();
        Thread.sleep(7000);
        Elements img = document.select("img");
        for (Element element : img) {
            String src = element.attr("ess-data");
            System.out.println("src = " + src);
//            if (StringUtils.isNotBlank(src) && src.endsWith(".jpg")) {
//                System.out.println("src = " + src);
//            }
        }
    }
}
