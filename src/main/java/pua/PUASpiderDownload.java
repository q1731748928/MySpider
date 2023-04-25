package pua;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @Author HeJie
 * @Date 2022/11/8
 * @Time 21:22
 */
public class PUASpiderDownload {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5
            , 50
            , 10
            , TimeUnit.SECONDS
            , new LinkedBlockingQueue<>(100)
            , Executors.defaultThreadFactory()
            , new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        final String COOKIESTR = "cloudreve-session=MTY2OTk2NTc3OXxOd3dBTkZWTVZGTlpVazFIU2xSTE4wbzJTVnBXVUVKTFEwWXpUMDlHVlZsWFYxcE9XRFZWVUZGUVVVNDJUREpUVGpSU1YwSktURkU9fFKuONa3sDskxoy5BjCnpHwYoe4GA5pi-U-RBsabTh6S";
        ;
        String[] split = COOKIESTR.split("=");
        HashMap<String, String> map = new HashMap<>();
        map.put(split[0], split[1]);
        Scanner scanner = new Scanner(System.in);
        // https://pan.pua333.cn/api/v3/share/list/y2sk%2F%E3%80%902022%E3%80%91%E5%B9%B4%E6%96%B0%E8%AF%BE%EF%BC%88%E6%9B%B4%E6%96%B0%E4%B8%AD%EF%BC%89%2F10%E3%80%8111%E3%80%8112%E6%9C%88%E8%AF%BE%E7%A8%8B%E5%8C%85%2F%E6%8A%96%E9%9F%B3%C2%B7h%E5%9D%8F%E5%B0%8FK%E8%81%8A%E5%A4%A9%E6%83%85%E5%95%86%E8%AF%BE
        String url = scanner.next();
        /*
         * https://pan.pua333.cn/api/v3/share/list/y2sk%2F%E3%80%902020%E3%80%91%E5%B9%B4%E4%B9%8B%E5%89%8D%E8%AF%BE%E7%A8%8B%2F01%E3%80%81%E6%B5%AA%E8%BF%B9%E6%95%99%E8%82%B2%EF%BC%88%E5%B0%8F%E5%AE%87%E5%AD%A6%E9%95%BF%EF%BC%89%2F5%E6%9C%881%E6%97%A5%E8%93%89%E5%9F%8E%E8%AE%A1%E5%88%92
         * */
        String loadJson = ReadTxtFileUtil.loadJson(url, COOKIESTR);
        JSONObject jsonObject = new JSONObject(loadJson);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray objects = data.getJSONArray("objects");
        for (int i = 0; i < objects.length(); i++) {
            String baseUrl = " https://pan.pua333.cn/api/v3/share/download/y2sk?path=";
            JSONObject jsonObject1 = objects.getJSONObject(i);
            String path = jsonObject1.getString("path");
            String name = jsonObject1.getString("name");
//            System.out.println("path = " + path);
            baseUrl += URLEncoder.encode(path + "/" + name, "UTF-8");
            if (baseUrl.toLowerCase().endsWith(".pdf")
                    || baseUrl.toLowerCase().endsWith(".mp4")
                    || baseUrl.toLowerCase().endsWith(".mp3")
                    || baseUrl.toLowerCase().endsWith(".m4a")
                    || baseUrl.toLowerCase().endsWith(".mp4")
                    || baseUrl.toLowerCase().endsWith(".jpg")
                    || baseUrl.toLowerCase().endsWith(".jpeg")
                    || baseUrl.toLowerCase().endsWith(".png")
                    || baseUrl.toLowerCase().endsWith(".docx")) {
                downFile(baseUrl, COOKIESTR, name);
            }
        }
        System.out.println("全部任务已顺利完成...");
        executor.shutdown();
    }

    public static void downFile(String baseUrl, String COOKIESTR, String name) throws ExecutionException, InterruptedException {
        String loadJson = ReadTxtFileUtil.loadJsonByPost(baseUrl, COOKIESTR);
        JSONObject jsonObject = new JSONObject(loadJson);
        String data = jsonObject.getString("data");
        CompletableFuture<Long> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            long start = System.currentTimeMillis();
            ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(data),"D:\\PUA\\ALl\\" + name);
            return start;
        }, executor);
        Long start = stringCompletableFuture.get();
        long end = System.currentTimeMillis();
        System.out.println(name + "，下载花费时间为：" + (end - start)   + "毫秒");
    }
}
