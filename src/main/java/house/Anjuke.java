package house;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/2/1
 * @Time 15:23
 */
public class Anjuke {
    public static void main(String[] args) throws IOException {
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        String url = "https://hangzhou.anjuke.com/sale/gongshu/";
        Document document = Jsoup.connect(url).ignoreContentType(true).userAgent(us).get();
        Elements select = document.select("div.property-content-title > h3");
        System.out.println(document);
    }
}
