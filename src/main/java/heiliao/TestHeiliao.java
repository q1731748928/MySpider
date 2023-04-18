package heiliao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.ReadTxtFileUtil;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2022/11/20
 * @Time 16:01
 */
public class TestHeiliao {
    public static void main(String[] args) throws IOException {
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        String url = "";
        String[] key = {"戴璐", "张津瑜", "公安", "警察"};
        String cookie = "_ga_BFSNDK2J48=GS1.1.1674268497.1.0.1674268497.60.0.0; _ga=GA1.1.1171515865.1674268497; PPA_CI=9b109810b91da77ab97fdd619bdf1b26";
        for (int i = 1; i <= 500; i++) {
            url = "https://zztt15.com/category/1.html/" + i +"/";
            Document document = Jsoup.connect(url).proxy("localhost", 1084).cookie("cookie",cookie).userAgent(us).ignoreContentType(true).get();
            Elements h2 = document.getElementsByTag("h2");
            for (Element element : h2) {
                ReadTxtFileUtil.toFile("第:" + i + "页" +"\n","C:\\Users\\hj\\Desktop\\115.txt",true);
                ReadTxtFileUtil.toFile(element.toString()+"\n","C:\\Users\\hj\\Desktop\\115.txt",true);
            }
        }
    }
}
