package job.liepin;

import job.boss.JobDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author HeJie
 * @Date 2023/1/29
 * @Time 10:12
 */
public class LiepinSpider {
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<JobDetail> jobDetails = new ArrayList<>();
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\PythonDemo\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(option);
        String url = "https://c.liepin.com/?time=1674958288889";
        driver.get(url);
        Thread.sleep(5000);
        String pageSource = driver.getPageSource();
        Document parse = Jsoup.parse(pageSource);
        Elements select = parse.select("li.pull-up-li");
        for (Element element : select) {
            Elements select1 = element.select("div.jsx-2297469327.job-card-left-box > div.jsx-2693574896.job-detail-box");
            for (Element element1 : select1) {
                Elements select2 = element1.select("div.jsx-2693574896 ellipsis-1");
                System.out.println(select2);

            }
        }
    }
}
