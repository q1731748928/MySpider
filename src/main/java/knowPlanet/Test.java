package knowPlanet;

import org.apache.commons.lang.StringEscapeUtils;
import utils.ReadTxtFileUtil;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/30
 * @Time 17:28
 */
public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {
        /*String url = "https://wx.zsxq.com/dweb2/index/group/48418884588288";
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\PythonDemo\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(option);
        driver.get(url);
        Thread.sleep(7000);
        String pageSource = driver.getPageSource();
        Document parse = Jsoup.parse(pageSource);
        Elements select = parse.select("div.content");
        for (Element element : select) {
            String text = element.text();
            System.out.println(text);
        }*/

        String url = "https://api.zsxq.com/v2/topics/118811814512212/info";
        String s = ReadTxtFileUtil.loadJson(url, "zsxq_access_token=A4CF28FD-1953-7AD7-F1C2-E4056BE269A2_B25040F4FAF64CEA; PPA_CI=3e9f42c6ace2d62516c55d8232c8cf02; abtest_env=product; zsxqsessionid=f687329251e4c4f573d4d9b98d36a007; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%221860aef77c1390-0520729ede13ce4-26021051-1327104-1860aef77c297d%22%2C%22first_id%22%3A%22%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2MGFlZjc3YzEzOTAtMDUyMDcyOWVkZTEzY2U0LTI2MDIxMDUxLTEzMjcxMDQtMTg2MGFlZjc3YzI5N2QifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%22%2C%22value%22%3A%22%22%7D%2C%22%24device_id%22%3A%221860aef77c1390-0520729ede13ce4-26021051-1327104-1860aef77c297d%22%7D");
        String s1 = StringEscapeUtils.unescapeJava(s);
        ReadTxtFileUtil.toFile(s1,"D:\\知识星球\\《Java面试指北》.txt",true);
    }
}
