package netease;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/13
 * @Time 17:06
 */
public class WangYiCloudMusic {
    public static void main(String[] args) throws InterruptedException, IOException {
        /*System.getProperties().setProperty("webdriver.chrome.driver", "D:\\PythonDemo\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(option);
        driver.get("https://music.163.com/discover/toplist");
        Thread.sleep(7000);
        String pageSource = driver.getPageSource();
        System.out.println("pageSource = " + pageSource);*/
        String url = "https://music.163.com/#/user/home?id=8020796333";
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        final String cookieStr = "cookie";
        String cookie = "_ntes_nnid=922a795f5a8c3870e103821b7862cb0a,1673600336864; _ntes_nuid=922a795f5a8c3870e103821b7862cb0a; NMTID=00OvXExU4yrbkFILEDusv6eRVApG4wAAAGFqlqEwQ; WEVNSM=1.0.0; WNMCID=tdtglb.1673600337172.01.0; WM_TID=Zd4cexBbnidFBVVBEVeAJ8zdN5yVenat; __snaker__id=ftDtUmt5gIvhAljT; gdxidpyhxdE=hjfaCnXPkW40meokJXkRzdLES3QLJZnvgGoWp4aTaE%2F3ZH%5CKyyGuBbYbpm2xJ7jxKDg2WGV7Yi3gjhWetLJHTtBzr%5Cc50nTWrshdZVe6SpuSmvsaxuAQ4Y9reI5KCZ6UEudp3IYIxkzGZCqG%5CbLCmLRklY7YeY7ZXup4TksMwM3i46ye%3A1673601242915; YD00000558929251%3AWM_NI=6%2F5U05RJ2gVyzHzfE13K42BUFEDrqtxqkAw6dEMAP5vHKBh46T3j7lw8rr%2BvQLfpkF0mEKsuyR9I9zuFJci4fJtYgwdOtEZ7clPIzBK%2F1mTRhKMSpHnSDTc2ZMXnMFhrR3k%3D; YD00000558929251%3AWM_NIKE=9ca17ae2e6ffcda170e2e6eea4c770a8ee81b2e85aa6e78bb6d44b829e9e83c546ada697bbf33991eb96b9d22af0fea7c3b92af6f1fbb6b33f9296fe88d573ba98b687c83bf195fcd3c15f869b8c93f480bcafa5a2f63ea89b83a2d33fb69885a3eb40f68cfd8bb668b5e8be93aa7e9394aa91ea4ef4b38bacf27fbb8ef7aabc72b79a8684ec40a696bcbae67da891bca6d93da1b4a0aaf94a89f1ff8ed75aac9583b4f344ae8d8cb5e23a9a93988fd04eacae9ed4ea37e2a3; YD00000558929251%3AWM_TID=UlcOywnm9AREUQQVRAPEd4iINojH4dTW; __csrf=338c8d19f0fbeb9327eb1be72c4cf0ce; MUSIC_U=d5b4655d8a17963e52d97296c5327f98ab1bd8eb68a03fbcf3b0e900c804ec23993166e004087dd373bbf842cf380dbc01a9cc885fe07f4ad13fea7a34b45c5a273d03a5812e9724d4dbf082a8813684; ntes_kaola_ad=1; _iuqxldmzr_=32; WM_NI=jA%2FmEdYAB%2BQFMx974h8%2FLm1Tyq9kK%2FJAPOSc9N0WQNZaoD7s%2FD%2FsJOLQeaSRYDSPV4lgOKl%2Bn8KX%2F93h9k8s3kPovi4WNXXvCOrOiKncz0pYfV8KAJJyy1cspDBXvKamSlc%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee8cea619c959aa4b23bba968eb6d15f838e8e83c453adaca991f05c81e800d9bb2af0fea7c3b92aaa9fe18ec8709b9f8eccd44e8fa8f7d8b764fcf1848eb460bbaff78cca258a9fa0b0d269fb9200b8d4469abfa1a5b679ed9ce194c945908fab82d25a9b9c9db1d540b0b981acef21aee885b9ae33f1b8f78cbb79f1b5a090f563f88982b9db7e8191beb0ec41aebdb6d1f353a68af8acc4428aa7b7d2e279b49c8eb9f541a3b39d8de237e2a3; JSESSIONID-WYYY=TSZw4KTb0pwvYa30Ruhr59q9E48Npjt03zsy2Y%2BJASflklb04y7ZNKCglw1XknDC0s4YAz%2FbhYDBnUbpcZ5YT1WHz0GKhdhXXAhaGNQI%5C28vnEgQO8pDzBH497vsM2%5Cq07cPpaVsffQUc7KbDJJihBxH2%2Fcn5n%2BGB7SbUw3hvJzG4tW%2B%3A1673835748958";
        Document document = Jsoup.connect(url).cookie(cookieStr,cookie).userAgent(us).get();
        Elements tr = document.select("li");
        System.out.println("tr = " + tr);
        /*Document parse = Jsoup.parse(pageSource);
        Elements select = parse.select("div.m-record");
        System.out.println("select = " + select);*/
    }
}
