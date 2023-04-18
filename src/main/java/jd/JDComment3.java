package jd;

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
public class JDComment3 {
    public static void main(String[] args) throws IOException {
        int count = 0;
        ArrayList<String> list = new ArrayList<>();
        TreeSet<String> treeSet = new TreeSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        String s = readTxtFile("C:\\Users\\hj\\Desktop\\bra.txt");
        String[] split = s.split("\n\n");
        for (int i = 0; i < split.length; i++) {
            JSONObject jsonObject = new JSONObject(split[i]);
            JSONArray comments = jsonObject.getJSONArray("comments");
            for (int j = 0; j <= comments.length() - 1; j++) {
                JSONObject jsonObject1 = comments.getJSONObject(j);
                if (jsonObject1.has("productColor")) {
                    String productColor = jsonObject1.getString("productColor");
                    list.add(productColor);
                    treeSet.add(productColor);
//                    System.out.println("productColor = " + productColor);
                }
                if (jsonObject1.has("productSize")) {
                    String productSize = jsonObject1.getString("productSize");
//                    System.out.println("productSize = " + productSize);
                }
                if (jsonObject1.has("referenceName")) {
                    String referenceName = jsonObject1.getString("referenceName");
//                    System.out.println("referenceName = " + referenceName);
                }
            }
        }
        for (String s1 : treeSet) {
            int frequency = Collections.frequency(list, s1);
            System.out.println(frequency + ",");
        }
    }
}
