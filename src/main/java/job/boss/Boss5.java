package job.boss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author HeJie
 * @Date 2023/1/10
 * @Time 10:27
 */
public class Boss5 {
    public static void main(String[] args) throws IOException, InterruptedException {
        int count = 0;
        ArrayList<JobDetail> jobDetails = new ArrayList<JobDetail>();
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\PythonDemo\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(option);
        String query = "java";
        String cityCode = "101210100";
       /* for (int i = 1; i <= 10; i++) {
            driver.get("https://www.zhipin.com/web/geek/job?query=" + query + "+&city=" + cityCode + "&page=" + i);
            Thread.sleep(7000);
            String pageSource = driver.getPageSource();
            Document parse = Jsoup.parse(pageSource);
            Elements select = parse.select("ul.job-list-box>li");*/

            /*for (int j = 0; j < select.size(); j++) {
                JobDetail jobDetail = new JobDetail();
                Element element = select.get(j);
                Elements jobCardBody = element.select("div.job-card-body");
                Elements jobCardBodyA = jobCardBody.select("div.job-card-body>a");
                String href = jobCardBodyA.attr("href");*/
        //String url = "https://www.zhipin.com/job_detail/3e4cd61d6e86e37a1XF829i4EFRX.html?lid=4FFtvHOd5dU.search.1&securityId=31bPlxsxF1lr3--1nyJ1cqs1jd5oip6Fa7pdk9vtT2m1NRz0He12tWcPJbNFeVkUdAFvTsAChA2BU0MgFj6u4iSYQi6TnL2RTWuodpfoBhQP2FMXjOTyFUyIt1ZvMPZoGZyoOsiaMI_h&sessionId=";
        String url = new Scanner(System.in).next();
        driver.get(url);
        //Thread.sleep(1000);
        String pageSource = driver.getPageSource();
        Document parse = Jsoup.parse(pageSource);
        String jobSecText = parse.select("div.job-detail-section>div.job-sec-text").text();
        System.out.println("jobSecText = " + jobSecText);
//            }
    }
}
