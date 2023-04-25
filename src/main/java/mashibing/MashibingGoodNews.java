package mashibing;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MashibingGoodNews {

    public static void main(String[] args) throws IOException {
        int countThread = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(countThread);
        String baseUrl = "https://edu.mashibing.com/api/edu-entrance/entrance/goodnewsCategory?code=MCA&tagId=15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33&pageIndex=1&length=9999";
        String s = ReadTxtFileUtil.loadJson(baseUrl, "");
        JSONObject jsonObject = new JSONObject(s);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray goodnewsVo = data.getJSONArray("goodnewsVo");
        for (int i = 0; i < goodnewsVo.length(); i++) {
            JSONObject jsonObject1 = goodnewsVo.getJSONObject(i);
            String title = jsonObject1.getString("title");
            if (title.contains("*") || title.contains("?") || title.contains("，") || title.contains(" ")) {
                title = title.replace("*", "").replace("?", "").replace("，", "").replace(" ", "");
            }
            String newTitle = title;
            String imgUrl = jsonObject1.getString("imgUrl");
            String replace = UUID.randomUUID().toString().substring(0, 7).replace("-", "");
            executorService.submit(() -> {
                try {
                    ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(imgUrl), "D:\\马士兵\\资料\\喜报\\" + newTitle + "\\" + replace + ".jpg");
                    String name = Thread.currentThread().getName();
                    System.out.println("线程：" + name + "正在执行下载任务");
                    Thread.sleep(7000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}