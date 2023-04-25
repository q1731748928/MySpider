package toutiao;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @Author HeJie
 * @Date 2023/1/12
 * @Time 16:28
 */
public class TouTiao {
    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        final String cookieStr = "cookie";
        String cookie = "__ac_signature=_02B4Z6wo00f01eiS6uAAAIDBaJAQoVvEsp3osu5AABmd42; tt_webid=7187680590329857591; ttcid=5ab1efbe7c50487abbabd94659ea3e3127; local_city_cache=%E5%AE%81%E6%B3%A2; csrftoken=1184042c3780e08e8b42796608ec39d2; s_v_web_id=verify_lcsu0tb6_SE8RZoGu_oT1D_4x2z_88UY_TYDFwZ14T4jU; passport_csrf_token=bfc5d452e61a83b56375c5b345514d07; passport_csrf_token_default=bfc5d452e61a83b56375c5b345514d07; n_mh=hEilrj6FXq7gwp2u0JSH5Ei2LDSVpKUnInPjijW_YdQ; sso_uid_tt=469250aee4849cdeeafb00d63a12cf13; sso_uid_tt_ss=469250aee4849cdeeafb00d63a12cf13; toutiao_sso_user=3ce75815ea4b83ab85c29a4db19303c5; toutiao_sso_user_ss=3ce75815ea4b83ab85c29a4db19303c5; sid_ucp_sso_v1=1.0.0-KDQ4YzgzZDEyZGYwMWE5YmJiZGZmNjg0OTdjNTlkOTg4NWY1NTM5MTgKHAi0z4f3kQMQsqT_nQYYGCAMMLrb798FOAZA9AcaAmxxIiAzY2U3NTgxNWVhNGI4M2FiODVjMjlhNGRiMTkzMDNjNQ; ssid_ucp_sso_v1=1.0.0-KDQ4YzgzZDEyZGYwMWE5YmJiZGZmNjg0OTdjNTlkOTg4NWY1NTM5MTgKHAi0z4f3kQMQsqT_nQYYGCAMMLrb798FOAZA9AcaAmxxIiAzY2U3NTgxNWVhNGI4M2FiODVjMjlhNGRiMTkzMDNjNQ; passport_auth_status=0865f194ed23dd1a614e9f57bfb31798%2C; passport_auth_status_ss=0865f194ed23dd1a614e9f57bfb31798%2C; sid_guard=9a3f73f15953df8ce10b487834a64386%7C1673515571%7C5183998%7CMon%2C+13-Mar-2023+09%3A26%3A09+GMT; uid_tt=9d03d0a63eeb580c1ee131b24a997d3f; uid_tt_ss=9d03d0a63eeb580c1ee131b24a997d3f; sid_tt=9a3f73f15953df8ce10b487834a64386; sessionid=9a3f73f15953df8ce10b487834a64386; sessionid_ss=9a3f73f15953df8ce10b487834a64386; sid_ucp_v1=1.0.0-KDliYTM5MTU2YjhiMzBiMWE2ZjY4YzJhYWYzYjM5NmYwYjI5NWI4ZTkKFgi0z4f3kQMQs6T_nQYYGCAMOAZA9AcaAmxmIiA5YTNmNzNmMTU5NTNkZjhjZTEwYjQ4NzgzNGE2NDM4Ng; ssid_ucp_v1=1.0.0-KDliYTM5MTU2YjhiMzBiMWE2ZjY4YzJhYWYzYjM5NmYwYjI5NWI4ZTkKFgi0z4f3kQMQs6T_nQYYGCAMOAZA9AcaAmxmIiA5YTNmNzNmMTU5NTNkZjhjZTEwYjQ4NzgzNGE2NDM4Ng; store-region=cn-zj; store-region-src=uid; msToken=0szwVLmeZ1zKD6t2xNlQB4PX2l_4ykruUq3fYvz3urm_q5G31he1cnlKEiI9ykp6DZarBJKgvQ17ciT0KuJi_HSiGxf9Vw2J25uL9r5qJHY=; odin_tt=971808fc74b7a0c3942ac350acff390a54905773f36117674b86f77f4ab4e0a836383bb1467675422590bcc0551125ad; tt_anti_token=AMcXXtfk2-1d9053a6a59de4e7301793864077dc2b0652f7bdc466aaabbb4b96f853ba6163; ttwid=1%7CDFSuLbkP79Gf0LSHREhFgYO062U4aiBZ2qAzeYYCkCw%7C1673572858%7Cbf4635e2d0ddaca6376d4a8ff415e41c265b150dd8d2111c403f495b1bbd9e9f; tt_scid=XDG0bbsOFX3.iwPGSHJ8eI5-HScRKU.V-xuDLcMbcGuatpQ6zDm2Uh3AR7kXUv0L2f44";
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\PythonDemo\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(option);
        driver.get("https://www.toutiao.com/c/user/token/MS4wLjABAAAApBIkpaRJOXwPcuBTYyORNFLMJbMlg8pIEO8-eXFulf0/?source=mine_home");
        Thread.sleep(7000);
        String pageSource = driver.getPageSource();
        Document parse = Jsoup.parse(pageSource);
        // 以下针对的是图片
        Elements profileWttCardWrapper = parse.select("div.profile-wtt-card-wrapper");
        for (Element element : profileWttCardWrapper) {
            Elements ahref = element.select("div.feed-card-cover>a");
            String content = element.select("p.content").text();
            String path = "D:\\相册\\记忆封存\\" + content + "\\" + content;
            String href = ahref.attr("href");
            driver.get(href);
            Thread.sleep(3000);
            Document document = Jsoup.connect(href).userAgent(us).cookie(cookieStr, cookie).get();
            Elements select = document.select("img.weitoutiao-img");
            for (Element element1 : select) {
                String src = "http:" + element1.attr("src");
                String replace = UUID.randomUUID().toString().substring(0, 7).replace("-", "");
                ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(src), "D:\\相册\\记忆封存\\头条动态\\" + content + "\\" + replace + ".webp");
                ReadTxtFileUtil.toFile(src + "\n" + replace + "\n\n", path +  ".txt", true);
            }
        }
        // 以下针对是视频
        Elements profileNormalVideoCardWrapper = parse.select("div.profile-normal-video-card-wrapper");
        for (Element element : profileNormalVideoCardWrapper) {
            Elements select = element.select("div.l-content>a");
            String href = select.attr("href");
            String content = select.text();
            driver.get(href);
            Thread.sleep(7000);
            String pageSource1 = driver.getPageSource();
            Document parse1 = Jsoup.parse(pageSource1);
            Elements select1 = parse1.select("div.ttp-xgplayer>video");
            String src = "http:" + select1.attr("src");
            String replace = UUID.randomUUID().toString().substring(0, 7).replace("-", "");
            String path = "D:\\相册\\记忆封存\\头条动态\\" + content + "\\" + content;
            ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(src), path + replace + ".mp4");
            ReadTxtFileUtil.toFile(src + "\n" + replace + "\n\n", path + ".txt", true);
        }
    }
}
