package sehuatang;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/23
 * @Time 20:47
 */
public class SeHuaTangTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "https://www.sehuatang.org/forum-155-1.html";
        String cookie = "cPNj_2132_saltkey=sxL43UUL; cPNj_2132_lastvisit=1674474308; PPA_CI=a52f045b7e00f81d8fd1604caef06ae7; cPNj_2132_lastfp=d9df92e5fc7dfdb7f550ef454663be31; cPNj_2132_st_t=0%7C1674477936%7C773361b0f3cc0f1e290ac5e183985103; cPNj_2132_atarget=1; cPNj_2132_forum_lastvisit=D_155_1674477936; cPNj_2132_visitedfid=155; cPNj_2132_st_p=0%7C1674478436%7Ceea433e39aaaf21c26d5839facc548c2; cPNj_2132_viewid=tid_890264; cPNj_2132_lastact=1674478437%09home.php%09misc; cPNj_2132_sendmail=1";
        String cookieStr = "cookie";
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        Document document = Jsoup.connect(url).proxy("localhost", 1084).userAgent(us).ignoreContentType(true).get();
        Elements a_s_xst = document.select("th.new a.s.xst");
        System.out.println("a_s_xst = " + a_s_xst);
//        for (Element element : a_s_xst) {
//            String href = element.attr("href");
//            String newUrl = "https://www.sehuatang.org/" + href;
//            System.out.println("newUrl = " + newUrl);
//            Document document1 = Jsoup.connect(newUrl).cookie(cookieStr,cookie).proxy("localhost", 1084).userAgent(us).ignoreContentType(true).get();
//            Thread.sleep(5000);
//            System.out.println("document1 = " + document1);
//        }
    }
}
