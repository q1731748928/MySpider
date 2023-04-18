package haijiao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/23
 * @Time 13:31
 */
public class HaijiaoDetail {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\PythonDemo\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(option);
        driver.get("https://hjdf34.com/post/details?pid=646581");
        Thread.sleep(14000);
        String pageSource = driver.getPageSource();
        Document parse = Jsoup.parse(pageSource);
        Elements select = parse.select("div.article ql-editor");
        System.out.println("select = " + select);
    }
}
