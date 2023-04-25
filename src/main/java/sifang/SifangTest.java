package sifang;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Author HeJie
 * @Date 2022/11/20
 * @Time 23:14
 */
public class SifangTest {
    public static void main(String[] args) throws IOException {
        String url = "";
        int start = 1;
        int end = 500;
        String COOKIE = "sMPh_2132_saltkey=cz7mK78E; sMPh_2132_lastvisit=1668953780; sMPh_2132_sid=f6EAGi; sMPh_2132_sendmail=1; sMPh_2132_atarget=1; sMPh_2132_visitedfid=94; sMPh_2132_lastact=1668957430%09forum.php%09forumdisplay; sMPh_2132_st_t=0%7C1668957430%7C445be594abc7ece329288c5010d1691d; sMPh_2132_forum_lastvisit=D_94_1668957430";
        HashMap<String, String> map = new HashMap<>();
        for (int i = start; i <end; i++) {
            url = "http://23.225.213.2/forum-94-" + i + ".html";
            Document document = Jsoup.connect(url).cookie("cookie",COOKIE).ignoreContentType(true).get();
            Elements s_xst = document.getElementsByClass("s xst");
            System.out.println("s_xst = " + s_xst);
            ReadTxtFileUtil.toFile(s_xst.toString()+"\n","C:\\Users\\hj\\Desktop\\sifang-xique.txt",true);
        }
    }
}
