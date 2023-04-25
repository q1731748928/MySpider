package knowPlanet;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @Author HeJie
 * @Date 2023/2/1
 * @Time 11:24
 */
public class DownFile {
    public static void main(String[] args) throws InterruptedException {
        String url = "https://wx.zsxq.com/dweb2/index/files";
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\PythonDemo\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(option);
        String js="var q=document.documentElement.scrollTop=10000";
        driver.get(url);
        driver.executeScript(js);
    }
}
