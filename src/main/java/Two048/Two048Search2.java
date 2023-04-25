package Two048;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author HeJie
 * @Date 2022/12/11
 * @Time 14:26
 */
public class Two048Search2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "";
        int start = 1;
        int end = 500;
//        String[] key = {"卫生巾", "公安", "民警", "警察"};
        String[] key = {""};
        String baseUrl = "";
//        for (int i = start; i < end; i++) {
//            url = "http://bbs.xiuxianche.com/2048/thread.php?fid-15-page-" + i + ".html";
        url = "http://bbs.xiuxianche.com/2048/state/p/15/2301/9021972.html";
        Document document = Jsoup.connect(url).ignoreContentType(true).get();
        Elements img = document.select("img");
        for (Element element : img) {
            Elements src = element.getElementsByAttribute("src");
            String s1 = src.toString();
            Pattern compile1 = Pattern.compile("(https?:[^:<>\"]*\\/)([^:<>\"]*)(\\.((png!thumbnail)|(png)|(jpg)|(webp)))");
            Matcher matcher1 = compile1.matcher(s1);
            while (matcher1.find()) {
                String group1 = matcher1.group().trim();
                System.out.println("group1 = " + group1);
                String substring = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
                ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(group1), "D:\\2048\\" + substring + ".jpg");
                Thread.sleep(7000);
            }
        }
            /*Elements subject = document.getElementsByClass("subject");
            System.out.println("subject = " + subject);
            String str = subject.toString();
            String[] split = str.split("\n");
            for (String s1 : split) {
                if (s1.contains(key[0]) || s1.contains(key[1])|| s1.contains(key[2]) || s1.contains(key[3])) {
                    ReadTxtFileUtil.toFile(s1 + "\n\n","C:\\Users\\hj\\Desktop\\2048.txt",true);
                }
            }*/
//        }

    }
}
