package pua;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.ReadTxtFileUtil;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @Author HeJie
 * @Date 2022/11/18
 * @Time 17:08
 */
public class PUA2022ForAll_1_3_downImg {
    int count = 0;
    private static String downloadPath = "G:\\PUA2022\\ALL";
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5
            , 50
            , 10
            , TimeUnit.SECONDS
            , new LinkedBlockingQueue<>(100)
            , Executors.defaultThreadFactory()
            , new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        final String COOKIESTR = "cloudreve-session=MTY3MjU0ODMyMnxOd3dBTkZFMldEUktRVnBFVFZKVFMxa3lTVTFIVmt4RVVGTTJVRkkzTmxWQ1Z6UTFVVVkwTXpWV00wWXpSa05CUVRSRU5rTkhXRkU9fP1ggZQQ-d6A2_7_yLu6qZizzRK2T-4z-JErokjM50kl";
        ;
        // 全转小写匹配
        String[] regx = {".jpeg", ".png", ".jpg", ".mp4", ".avi", ".docx", ".m4a", ".mp3", ".docx", ".doc", ".pptx", ".ppt", ".ts", ".flv", ".mkv", ".txt", ".f4v"};
        String url = "https://pan.pua333.cn/api/v3/share/list/y2sk%2F";
        String[] split = COOKIESTR.split("=");
        HashMap<String, String> map = new HashMap<>();
        map.put(split[0], split[1]);
        getName(COOKIESTR);
    }

    public static String getName(String COOKIESTR) throws IOException, ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String url = scanner.next();
        String json = ReadTxtFileUtil.loadJson(url, COOKIESTR);
        JSONObject jsonObject = new JSONObject(json);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray objects = data.getJSONArray("objects");
        for (int i = 0; i < objects.length(); i++) {
            url = "https://pan.pua333.cn/api/v3/share/list/y2sk";
            JSONObject jsonObject1 = objects.getJSONObject(i);
            String name = jsonObject1.getString("name");
            String path = jsonObject1.getString("path");
//            ReadTxtFileUtil.toFile(name + "\n\n", "G:\\PUA2022\\nameTmp.txt", true);
            url += URLEncoder.encode(path + "/" + name, "UTF-8");
            if (url.contains("%2F%2F")) {
                url = url.replace("%2F%2F", "%2F");
            }

//            System.out.println("url = " + url);
            if (!url.toLowerCase().endsWith(".pdf") && !url.toLowerCase().endsWith(".jpg") && !url.toLowerCase().endsWith(".jpeg") && !url.endsWith(".m4a") && !url.toLowerCase().endsWith(".png") && !url.toLowerCase().endsWith(".mp4") && !url.toLowerCase().endsWith(".mp3") && !url.toLowerCase().endsWith(".doc") && !url.toLowerCase().endsWith(".docx") && !url.toLowerCase().endsWith(".avi") && !url.toLowerCase().endsWith(".pptx")
                    && !url.toLowerCase().endsWith(".ppt") && !url.toLowerCase().endsWith(".txt") && !url.toLowerCase().endsWith(".rar") && !url.toLowerCase().endsWith(".mkv") && !url.toLowerCase().endsWith(".ts") && !url.toLowerCase().endsWith(".flv")) {
                getNName(url, COOKIESTR);
            } else if (name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")) {
                String baseUrl = " https://pan.pua333.cn/api/v3/share/download/y2sk?path=";
                url = url.replace("https://pan.pua333.cn/api/v3/share/list/y2sk", "");
                baseUrl += url;
                downFile(baseUrl, COOKIESTR, url);
            }
//            else {
//                String baseUrl = " https://pan.pua333.cn/api/v3/share/download/y2sk?path=";
//                url = url.replace("https://pan.pua333.cn/api/v3/share/list/y2sk", "");
//                baseUrl += url;
//                downFile(baseUrl, COOKIESTR, url);
//            }
        }
        return "";
    }

    public static String getNName(String url, String COOKIESTR) throws IOException, ExecutionException, InterruptedException {
        if (!url.toLowerCase().endsWith(".pdf") && !url.toLowerCase().endsWith(".jpg") && !url.toLowerCase().endsWith(".jpeg") && !url.endsWith(".m4a") && !url.toLowerCase().endsWith(".png") && !url.toLowerCase().endsWith(".mp4") && !url.toLowerCase().endsWith(".mp3") && !url.toLowerCase().endsWith(".doc") && !url.toLowerCase().endsWith(".docx") && !url.toLowerCase().endsWith(".avi") && !url.toLowerCase().endsWith(".pptx")
                && !url.toLowerCase().endsWith(".ppt") && !url.toLowerCase().endsWith(".txt") && !url.toLowerCase().endsWith(".rar") && !url.toLowerCase().endsWith(".mkv") && !url.toLowerCase().endsWith(".ts") && !url.toLowerCase().endsWith(".flv")) {
            String json = ReadTxtFileUtil.loadJson(url, COOKIESTR);
            JSONObject jsonObject = new JSONObject(json);
            if (json.contains("data")) {
                JSONObject data = jsonObject.getJSONObject("data");
                JSONArray objects = data.getJSONArray("objects");
                for (int i = 0; i < objects.length(); i++) {
                    url = "https://pan.pua333.cn/api/v3/share/list/y2sk";
                    JSONObject jsonObject1 = objects.getJSONObject(i);
                    String name = jsonObject1.getString("name");
                    String path = jsonObject1.getString("path");
//                    ReadTxtFileUtil.toFile(name + "\n\n", "G:\\PUA2022\\nameTmp.txt", true);
                    url += URLEncoder.encode(path + "/" + name, "UTF-8");
                    if (url.contains("%2F%2F")) {
                        url = url.replace("%2F%2F", "%2F");
                    }
                    if (!url.toLowerCase().endsWith(".pdf") && !url.toLowerCase().endsWith(".jpg") && !url.toLowerCase().endsWith(".jpeg") && !url.endsWith(".m4a") && !url.toLowerCase().endsWith(".png") && !url.toLowerCase().endsWith(".mp4") && !url.toLowerCase().endsWith(".mp3") && !url.toLowerCase().endsWith(".doc") && !url.toLowerCase().endsWith(".docx") && !url.toLowerCase().endsWith(".avi") && !url.toLowerCase().endsWith(".pptx")
                            && !url.toLowerCase().endsWith(".ppt") && !url.toLowerCase().endsWith(".txt") && !url.toLowerCase().endsWith(".rar") && !url.toLowerCase().endsWith(".mkv") && !url.toLowerCase().endsWith(".ts") && !url.toLowerCase().endsWith(".flv")) {
                        getNName(url, COOKIESTR);
                    } else if (name.toLowerCase().endsWith(".jpeg") || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")) {
                        String baseUrl = " https://pan.pua333.cn/api/v3/share/download/y2sk?path=";
                        url = url.replace("https://pan.pua333.cn/api/v3/share/list/y2sk", "");
                        baseUrl += url;
                        downFile(baseUrl, COOKIESTR, url);
                    }
//                    else {
//                        String baseUrl = " https://pan.pua333.cn/api/v3/share/download/y2sk?path=";
//                        url = url.replace("https://pan.pua333.cn/api/v3/share/list/y2sk", "");
//                        baseUrl += url;
//                        downFile(baseUrl, COOKIESTR, url);
//                    }
                }
            }
        }
//        else {
//            String baseUrl = " https://pan.pua333.cn/api/v3/share/download/y2sk?path=";
//            url = url.replace("https://pan.pua333.cn/api/v3/share/list/y2sk", "");
//            baseUrl += url;
//            downFile(baseUrl, COOKIESTR, url);
//        }
        return "程序正常结束";
    }

    public static void downFile(String baseUrl, String COOKIESTR, String name) throws ExecutionException, InterruptedException, IOException {
        String loadJson = ReadTxtFileUtil.loadJsonByPost(baseUrl, COOKIESTR);
        JSONObject jsonObject = new JSONObject(loadJson);
        if (loadJson.contains("data")) {
            String data = jsonObject.getString("data");
            String path = downloadPath;
            String decode = URLDecoder.decode(name, "UTF-8");
            decode = decode.replace("/", "\\");
            System.out.println(decode + " 开始下载了...");
            File file = new File(path + decode);
            if (!file.exists()) {
                CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(() -> {
                    String name1 = Thread.currentThread().getName();
                    System.out.println("name1 = " + name1 + "正在执行下载任务...");
                    long start1 = System.currentTimeMillis();
                    ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrlWithName(data, file.toString()), file.toString());
                    return start1;
                }, executor);
                Long start1 = future1.get();
                long end1 = System.currentTimeMillis();
                System.out.println(decode + " ，下载完成。");
                System.out.println(decode + " ，下载花费时间为：" + (end1- start1) + "毫秒");
                String over1 = decode + " 下载已完成";
                String time1 = decode + " ，下载花费时间为：" + (end1 - start1) + "毫秒";
//                ReadTxtFileUtil.toFile(over1 + "\n" + time1 + "\n" + "\n\n", downloadPath + "\\" + "pua.txt", true);

            } else {
                System.out.println(file + "已存在，跳过该文件");
            }
        }
    }
}
