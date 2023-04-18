import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author HeJie
 * @Date 2022/11/13
 * @Time 20:23
 */
public class Test {
    public static void main(String[] args) throws IOException {
        int start = 1;
        int end = 10;
        String url = "http://bbs.xiuxianche.com/2048/thread.php?fid-26.html";
        String path = "C:\\Users\\hj\\Desktop\\sc.txt";
        Document document = Jsoup.connect(url).ignoreContentType(true).get();
        Elements select = document.getElementsByClass("subject");
        for (Element element : select) {
            // <a href="state/p/26/2211/8162395.html"
            Pattern compile = Pattern.compile("<a href=\"state/p/[0-9]{2}/[0-9]{4}/[0-9]{7}.html");
            Matcher matcher = compile.matcher(select.toString());
            while (matcher.find()) {
                String group = matcher.group();
                String[] split = group.split("\n");
                for (String s : split) {
                    s = s.replace("<a href=\"","");
                    url = "http://bbs.xiuxianche.com/2048/" + s;
                    Document document1 = Jsoup.connect(url).ignoreContentType(true).get();
                    Elements select1 = document1.select("img[src]");
                    for (Element element1 : select1) {
                        // https://img.picelsb.com/i/2022/11/05/r2a0rp.png
                        compile = Pattern.compile("https://img.picelsb.com/i/2022/[0-9]{2}/[0-9]{2}/\\w.png");
                        while (compile.matcher(element1.toString()).find()) {
                            String group1 = matcher.group();
                            group1 = group1.replace("a href=\"","");
                            System.out.println("group1 = " + group1);
                        }
                    }
                }
            }
        }
    }
}