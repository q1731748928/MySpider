package jd;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @Author HeJie
 * @Date 2023/1/6
 * @Time 14:53
 */
public class JDCommentForBra1 {
    public static void main(String[] args) throws IOException {
        int count = 0;
        ArrayList<String> list = new ArrayList<>();
        TreeSet<String> treeSet = new TreeSet<>();
        HashMap<String, Integer> map = new HashMap<>();
        String s = ReadTxtFileUtil.readTxtFile("C:\\Users\\hj\\Desktop\\bra_1.txt");
        String[] split = s.split("\n\n");
        for (int i = 0; i < split.length; i++) {
            JSONObject jsonObject = new JSONObject(split[i]);
            JSONArray comments = jsonObject.getJSONArray("comments");
            for (int j = 0; j <= comments.length() - 1; j++) {
                JSONObject jsonObject1 = comments.getJSONObject(j);
                if (jsonObject1.has("productColor")) {
                    String productColor = jsonObject1.getString("productColor");
//                    Pattern pattern = Pattern.compile("[A-Z]-[A-Z]杯");
//                    Matcher matcher = pattern.matcher(productColor);
//                    String dateStr = null;
//                    if (matcher.find()) {
//                        dateStr = matcher.group(0);
//                        list.add(dateStr);
//                        treeSet.add(dateStr);
////                        System.out.println(dateStr);
//                    }
                    list.add(productColor);
                    treeSet.add(productColor);
                }
                if (jsonObject1.has("productSize")) {
                    String productSize = jsonObject1.getString("productSize");
//                    System.out.println("productSize = " + productSize);
                }
                if (jsonObject1.has("referenceName")) {
                    String referenceName = jsonObject1.getString("referenceName");
//                    System.out.println("referenceName = " + referenceName);
                }
                count++;
                String content = jsonObject1.getString("content");
//                System.out.println("content = " + content);
            }
        }
        for (String s1 : treeSet) {
            int frequency = Collections.frequency(list, s1);
            System.out.println(s1);
            System.out.println("frequency = " + frequency);
        }
    }
}
