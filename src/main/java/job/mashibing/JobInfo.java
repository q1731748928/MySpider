package job.mashibing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/31
 * @Time 9:32
 */
public class JobInfo {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i <= 14; i++) {
            String url = "https://www.mashibing.com/job?city=%E6%9D%AD%E5%B7%9E&pageIndex=" + i + "&classification=237";
            Document document = Jsoup.connect(url).ignoreContentType(true).get();
            Elements select = document.select("div.info");
            for (Element element : select) {
                String text = element.text();
                System.out.println(text);
            }
        }
    }
}
