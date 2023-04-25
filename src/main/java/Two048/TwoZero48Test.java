package Two048;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import utils.ReadTxtFileUtil;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2022/11/8
 * @Time 21:22
 */
public class TwoZero48Test {
    public static void main(String[] args) throws IOException {
        String url = "";
        int start = 81;
        int end = 500;
        String[] key = {"空姐偷拍", "飞机", "天空", "空姐"};
        String baseUrl = "";
        for (int i = start; i < end; i++) {
            //http://bbs.xiuxianche.com/2048/thread.php?fid-76-page-3.html
            //http://bbs.xiuxianche.com/2048/thread.php?fid-15-
            url = "http://bbs.xiuxianche.com/2048/thread.php?fid-15-page-" + i + ".html";
            Document document = Jsoup.connect(url).proxy("localhost",1084).cookie("cookie","zh_choose=n; a22e7_threadlog=%2C76%2C72%2C15%2C; a22e7_lastpos=index; a22e7_ol_offset=518368; a22e7_lastvisit=202%091669556506%09%2F2048%2Findex.php").ignoreContentType(true).get();
            Elements subject = document.getElementsByClass("subject");
            ReadTxtFileUtil.toFile(subject.toString() +"\n"+ baseUrl + "\n", "C:\\users\\hj\\desktop\\2048.txt",true);
            /*for (int j = 0; j < subject.size(); j++) {
                Element element = subject.get(j);
                String ele = element.toString();
                ele = ele.replaceAll("target=\"_blank\" id=\"a_ajax_[0-9]*\" class=\"subject\"", "");
                if (ele.contains("<a href=\"state/p/")) {
                    if (ele.contains(key[0]) || ele.contains(key[1]) || ele.contains(key[2]) || ele.contains(key[3])) {
                        Pattern compile = Pattern.compile("state/p/[0-9]{2}/[0-9]{4}/[0-9]{7}.html");
                        Matcher matcher = compile.matcher(ele);
                        while (matcher.find()) {
                            String group = matcher.group();
                            baseUrl = "http://bbs.xiuxianche.com/2048/";
                            baseUrl += group;
                            Document document1 = Jsoup.connect(baseUrl).ignoreContentType(true).get();
                            Elements select = document1.select("img[src]");
                            group = group.replaceAll("state/p/[0-9]{2}/[0-9]{4}/", "").replace(".html", "");
                            for (Element element1 : select) {
                                String[] s = element1.toString().split(" ");
                                for (String s1 : s) {
                                    if (s1.contains("https://")) {
                                        String replace = s1.replace("src=", "").replace("\"", "");
                                        ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(replace), "d:\\2048\\" + group + "\\" + UUID.randomUUID().toString() + ".jpg");
                                        ReadTxtFileUtil.toFile(s1+"\n"+ baseUrl + "\n", "d:\\2048\\" + group + "\\" + group + ".txt", true);
                                        ReadTxtFileUtil.toFile(ele +"\n"+ baseUrl + "\n", "d:\\2048\\" + group + "\\" + group + ".txt", true);
                                    }
                                }
                            }
                        }
                    }
                }
            }*/
        }
    }
}
