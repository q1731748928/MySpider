package knowPlanet;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.ReadTxtFileUtil;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/30
 * @Time 9:47
 */
public class KnowPlanetSpdier {
    public static void main(String[] args) throws IOException, InterruptedException {
//        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\PythonDemo\\chromedriver.exe");
//        ChromeOptions option = new ChromeOptions();
//        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
//        ChromeDriver driver = new ChromeDriver(option);
        String[] urlArr = {
                "https://api.zsxq.com/v2/hashtags/15281542428242/topics?count=20",
                "https://api.zsxq.com/v2/hashtags/15281542428242/topics?count=20&end_time=2022-09-05T21%3A40%3A46.164%2B0800",
                "https://api.zsxq.com/v2/hashtags/15281542428242/topics?count=20&end_time=2022-08-05T16%3A12%3A40.529%2B0800",
                "https://api.zsxq.com/v2/hashtags/15281542428242/topics?count=20&end_time=2022-05-29T22%3A35%3A44.808%2B0800",
                "https://api.zsxq.com/v2/hashtags/15281542428242/topics?count=20&end_time=2022-04-14T07%3A59%3A15.935%2B0800",
                "https://api.zsxq.com/v2/hashtags/15281542428242/topics?count=20&end_time=2022-03-18T21%3A11%3A34.488%2B0800",
                "https://api.zsxq.com/v2/hashtags/15281542428242/topics?count=20&end_time=2021-06-02T21%3A16%3A39.540%2B0800",
                "https://api.zsxq.com/v2/hashtags/15281542428242/topics?count=20&end_time=2021-03-10T21%3A03%3A25.508%2B0800"
        };
        String cookie = "PPA_CI=5638e3e1bc8f3d93ad1d84d01fa38229; zsxq_access_token=A4CF28FD-1953-7AD7-F1C2-E4056BE269A2_B25040F4FAF64CEA; zsxqsessionid=0dd0913b2347774689e17fdb9c39cf97; abtest_env=product";
        for (int i = 0; i < urlArr.length; i++) {
            String url = urlArr[i];
            String data = ReadTxtFileUtil.loadJson(url, cookie);
            JSONObject jsonObject = new JSONObject(data);
            JSONObject respData = jsonObject.getJSONObject("resp_data");
            if (respData.has("topics")) {
                JSONArray topics = respData.getJSONArray("topics");
                for (int j = 0; j < topics.length(); j++) {
                    JSONObject jsonObject1 = topics.getJSONObject(j);
                    if (jsonObject1.has("talk")) {
                        JSONObject talk = jsonObject1.getJSONObject("talk");
                        JSONObject owner = talk.getJSONObject("owner");
                        String name = owner.getString("name");
                        ReadTxtFileUtil.toFile(name + "\n", "C:\\Users\\hj\\Desktop\\知识星球.txt", true);
                        String text = talk.getString("text");
                        ReadTxtFileUtil.toFile(text + "\n", "C:\\Users\\hj\\Desktop\\知识星球.txt", true);
                        if (talk.has("article")) {
                            JSONObject article = talk.getJSONObject("article");
                            String title = article.getString("title");
                            String article_url = article.getString("article_url");
//                            driver.get(article_url);
//                            Thread.sleep(7000);
//                            String pageSource = driver.getPageSource();
//                            Document parse = Jsoup.parse(pageSource);
//                            Elements select = parse.select("div.ql-snow p");
//                            for (Element element : select) {
//                                String detail = element.text();
//                                ReadTxtFileUtil.toFile(detail + "\n", "C:\\Users\\hj\\Desktop\\知识星球.txt", true);
//                            }
                            ReadTxtFileUtil.toFile(title + "\n", "C:\\Users\\hj\\Desktop\\知识星球.txt", true);
                            ReadTxtFileUtil.toFile(article_url + "\n\n\n", "C:\\Users\\hj\\Desktop\\知识星球.txt", true);
                        }
                    }
                }
            }
        }
    }
}
