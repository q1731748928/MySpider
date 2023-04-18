package job.boss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/15
 * @Time 13:54
 */
public class TestJsoup {
    public static void main(String[] args) throws IOException {
        String url = "https://www.zhipin.com/web/geek/job?query=Java&city=101210100";
        Document document = Jsoup.connect(url).proxy("localhost",1084).cookie("cookie","zh_choose=n; a22e7_threadlog=%2C76%2C72%2C15%2C; a22e7_lastpos=index; a22e7_ol_offset=518368; a22e7_lastvisit=202%091669556506%09%2F2048%2Findex.php").ignoreContentType(true).get();
        Elements html = document.select("html");
        System.out.println("html = " + html);
    }
}
