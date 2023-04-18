package jd;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.ReadTxtFileUtil;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/6
 * @Time 14:53
 */
public class JDCommentForTT {
    public static void main(String[] args) throws IOException {
        int count = 0;
        String s = ReadTxtFileUtil.readTxtFile("C:\\Users\\hj\\Desktop\\tt.txt");
        String[] split = s.split("\n\n");
        for (int i = 0; i < split.length; i++) {
            JSONObject jsonObject = new JSONObject(split[i]);
            JSONArray comments = jsonObject.getJSONArray("comments");
            for (int j = 0; j <= comments.length() - 1; j++) {
                JSONObject jsonObject1 = comments.getJSONObject(j);
                if (jsonObject1.has("productColor")) {
                    String productColor = jsonObject1.getString("productColor");
                    System.out.println("productColor = " + productColor);
                }
                if (jsonObject1.has("productSize")) {
                    String productSize = jsonObject1.getString("productSize");
                    System.out.println("productSize = " + productSize);
                }
                if (jsonObject1.has("referenceName")) {
                    String referenceName = jsonObject1.getString("referenceName");
                    System.out.println("referenceName = " + referenceName);
                }
                count++;
                String content = jsonObject1.getString("content");
                System.out.println("content = " + content);
            }
        }
        System.out.println("共" + count + "条数据");
    }
}
