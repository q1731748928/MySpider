package t66y;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author HeJie
 * @Date 2022/11/8
 * @Time 21:22
 */
public class TestT66yDownImg {
    public static void main(String[] args) throws IOException, InterruptedException {
        int countThread = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(countThread);
        String url = "";
        int start = 1;
        int end = 2;
        String[] key = {"空姐偷拍", "飞机", "空姐", "天空"};
        String baseUrl = "";
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        for (int i = start; i <= end; i++) {
            //http://bbs.xiuxianche.com/2048/thread.php?fid-76-page-3.html
            //http://bbs.xiuxianche.com/2048/thread.php?fid-15-
            url = "https://www.t66y.com/thread0806.php?fid=16&page=" + i;
            Document document = Jsoup.connect(url).proxy("localhost", 1085).userAgent(us).ignoreContentType(true).get();
            Elements h3Href = document.select("h3>a");
            for (Element element : h3Href) {
                String href = element.attr("href");
                Pattern compile = Pattern.compile("^htm_data/[0-9]*/[0-9]*/[0-9]*.html");
                Matcher matcher = compile.matcher(href);
                if (matcher.find()) {
                    String group = matcher.group();
                    String newUrl = "https://www.t66y.com/" + group;
                    Document document1 = Jsoup.connect(newUrl).proxy("localhost", 1085).userAgent(us).ignoreContentType(true).get();
                    Thread.sleep(7000);
                    Elements img = document1.select("img");
                    for (Element element1 : img) {
                        String src = element1.attr("ess-data");
                        if (StringUtils.isNotBlank(src) && src.endsWith(".jpg")) {
                            String title = document1.select("h4").text();
                            String substring = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
                            executorService.submit(() -> {
                                try {
                                    ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(src), "D:\\t66y\\" + title + "\\" + substring + ".jpg");
                                    String name = Thread.currentThread().getName();
                                    System.out.println("线程：" + name + "正在执行下载任务");
                                    Thread.sleep(7000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                }
            }
        }
    }
}
