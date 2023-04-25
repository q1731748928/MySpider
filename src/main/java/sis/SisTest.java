package sis;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.ReadTxtFileUtil;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2022/11/20
 * @Time 22:44
 */
public class SisTest {
    public static void main(String[] args) throws IOException {
        String url = "";
        int start = 1;
        int end = 500;
        String[] key = {"张津瑜", "吕总", "地铁", "迷奸"};
        for (int i = start; i < end; i++) {
            url = "http://23.225.255.95/forum/forum-544-" + i +".html";
            Document document = Jsoup.connect(url).ignoreContentType(true).get();
            Elements select = document.select("a[href]");
            for (Element element : select) {
                if (element.toString().startsWith("<a href=\"thread-")) {
//                    System.out.println("element = " + element);
                    ReadTxtFileUtil.toFile(element.toString()+ "\n","C:\\Users\\hj\\Desktop\\sis.txt",true);
                }
            }
        }
    }
}
