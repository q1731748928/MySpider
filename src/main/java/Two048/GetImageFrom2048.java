package Two048;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author HeJie
 * @Date 2023/1/21
 * @Time 10:50
 */
public class GetImageFrom2048 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Long startTime = System.currentTimeMillis();
        int countThread = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(countThread);
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        int start = 1;
        int end = 500;
        String cookie = "zh_choose=n; PPA_CI=7402bb22e8a398dd59ba66c97afe0cea; a22e7_ol_offset=43165; a22e7_lastpos=F26; a22e7_threadlog=%2C26%2C; a22e7_lastvisit=39%091674891339%09%2F2048%2Fthread.php%3Ffid-26-page-2.html";
        String[] key = {"空姐偷拍", "飞机", "空姐", "天空"};
        String baseUrl = "";
        for (int i = start; i <= end; i++) {
            String url = "http://bbs2023.cancansong.com/2048/thread.php?fid-26-page-" + i + ".html";
            Document document = Jsoup.connect(url).proxy("localhost", 1084).userAgent(us).cookie("cookie", cookie).ignoreContentType(true).get();
            Elements subject = document.getElementsByClass("subject");
            for (Element element : subject) {
                String href = element.attr("href");
                if (href.contains("state")) {
                    baseUrl = "http://bbs2023.cancansong.com/2048/" + href;
                    System.out.println("baseUrl = " + baseUrl);
                    document = Jsoup.connect(baseUrl).proxy("localhost", 1084).userAgent(us).cookie("cookie", cookie).ignoreContentType(true).get();
                    Elements img = document.select("img");
                    for (Element element1 : img) {
                        if (element1.toString().contains("https://img") && !element1.toString().contains(" [img]")) {
                            String title1 = document.getElementsByTag("title").text();
                            String substring = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
                            String src = element1.attr("src");
                            ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(src), "D:\\2048\\" + title1 + "\\" + substring + ".jpg");
                            Thread.sleep(3000);
                            executorService.submit(() -> {
                                try {
                                    ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(src), "D:\\2048\\" + title1 + "\\" + substring + ".jpg");
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                }
            }
        }
        Long endTime = new Date().getTime();
        Long time = endTime - startTime;
        System.out.println("总共耗时：" + (time) / 1000 / 60 + "分钟");
    }

}
