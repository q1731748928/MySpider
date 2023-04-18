package jd;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

import static utils.ReadTxtFileUtil.readTxtFile;

/**
 * @Author HeJie
 * @Date 2023/1/6
 * @Time 14:53
 */
public class JDComment2 {
    public static void main(String[] args) throws IOException {
        int count = 0;
        ArrayList<String> list = new ArrayList<>();
        TreeSet<String> treeSet = new TreeSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        String path = "C:\\Users\\hj\\Desktop\\mac.txt";
        String s = readTxtFile(path);
        String[] split = s.split("\n\n");
        for (int i = 0; i < split.length; i++) {
            if (StringUtils.isNotBlank(split[i])) {
                JSONObject jsonObject = new JSONObject(split[i]);
                JSONArray comments = jsonObject.getJSONArray("comments");
                for (int j = 0; j <= comments.length() - 1; j++) {
                    JSONObject jsonObject1 = comments.getJSONObject(j);
                    String productColor = jsonObject1.getString("productColor");
                    String content = jsonObject1.getString("content");
                    count++;
                    String productSize = jsonObject1.getString("productSize");
                    list.add(productColor);
                System.out.println("content = " + content);
//                System.out.println("productColor = " + productColor);
                    treeSet.add(productSize);
                    System.out.println("productSize = " + productSize);
                }
            }
        }
        for (String s1 : treeSet) {
            int frequency = Collections.frequency(list, s1);
            System.out.println("{name:" + "'" + s1);
        }
        System.out.println("共" + count + "条数据");
    }
}

