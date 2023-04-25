package pua;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Scanner;

import static utils.ReadTxtFileUtil.*;

/**
 * @Author HeJie
 * @Date 2022/11/8
 * @Time 21:22
 */
public class PUA2023ForSingle {
    public static void main(String[] args) throws IOException {
        int count = 0;
        final String COOKIESTR ="PPA_CI=e2e51ad7930a850135a700fcac53f9ed; cloudreve-session=MTY4MjM0NTc2NXxOd3dBTkVaVVNVaEZSMVZDVmxnMlZrVlFSVlJLVUZWUVJqUTFXa3BGUkZKSFNVTlpWVnBWVGtoSE56ZE5WVTlMTlRSV1UweEVRbEU9fKhu4q2n0XsJfuFCaeylOHMueH8Zfmj3_4ODP3fC4L_3";
        String txt = "F:\\PUA2023\\ALL";
        String[] split = COOKIESTR.split("=");
        HashMap<String, String> map = new HashMap<>();
        map.put(split[0], split[1]);
        String baseUrl = new Scanner(System.in).nextLine();
        String path = "";
//        ReadTxtFileUtil.toNullFileWithParam(txt,"");
        if (baseUrl.contains("%2F%2F")) {
            baseUrl = baseUrl.replaceAll("%2F%2F", "%2F");
        }
        String json = loadJson(baseUrl, COOKIESTR);
        JSONObject jsonObject = new JSONObject(json);
        if (!baseUrl.endsWith(".mp4") || !baseUrl.endsWith(".mp3") || !baseUrl.endsWith(".m4a") || !baseUrl.endsWith(".mp4") || !baseUrl.endsWith(".jpg")) {
            if (json.contains("data")) {
                JSONObject data = jsonObject.getJSONObject("data");
                JSONArray objects = data.getJSONArray("objects");
//                toNullFile("");
                for (int i = 0; i < objects.length(); i++) {
                    baseUrl = "https://pan.pua333.cn/api/v3/share/list/y2sk";
                    JSONObject jsonObject1 = objects.getJSONObject(i);
                    String name = jsonObject1.getString("name");
                    count++;
                    path = jsonObject1.getString("path");
                    baseUrl += URLEncoder.encode(path + "/" + name, "utf-8");
                    if (baseUrl.contains("%2F%2F")) {
                        baseUrl = baseUrl.replaceAll("%2F%2F", "%2F");
                    }
                    if (baseUrl.endsWith(".md")) {
                        System.out.println("文件不存在" + baseUrl);
                        continue;
                    } else {
                        System.out.println(name);
                        toFile("\n" + name + "\n", "F:\\PUA\\name2023.txt", true);
                    }
                }
                Desktop.getDesktop().open(new File(txt));
            }
        }
        System.out.println(count);

    }
}
