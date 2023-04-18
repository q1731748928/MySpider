package haijiao;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.ReadTxtFileUtil;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2022/11/20
 * @Time 17:23
 */
public class HaijiaoTest2 {
    public static void main(String[] args) throws IOException {
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        for (int i = 1; i <= 100; i++) {
            String url = "https://hjp81a.com/api/topic/hot/topics?page=" + i;
            String s = ReadTxtFileUtil.loadJson(url, "");
            JSONObject jsonObject = new JSONObject(s);
            JSONObject data = jsonObject.getJSONObject("data");
            if (data.toString().contains("\"results\":null")) {
                continue;
            }
            else {
                if (data.has("results")) {
                    JSONArray results = data.getJSONArray("results");
                    for (int j = 0; j < results.length(); j++) {
                        JSONObject jsonObject1 = results.getJSONObject(j);
                        String liteContent = jsonObject1.getString("liteContent");
                        String title = jsonObject1.getString("title");
                        int topicId = jsonObject1.getInt("topicId");
                        ReadTxtFileUtil.toFile("第" + i + "页" + "\n" + "https://hjdf34.com/post/details?pid=" + topicId + "\n" + title + "\n" + liteContent + "\n\n", "C:\\Users\\hj\\Desktop\\haijiao.txt", true);
                    }
                }
            }
        }
    }
}
