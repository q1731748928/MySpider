package job.boss;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.ReadTxtFileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @Author HeJie
 * @Date 2023/1/9
 * @Time 15:39
 */
public class Boss {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
//            String urlSend = "https://www.zhipin.com/wapi/zpgeek/history/joblist.json";
//            String data = "jobIds=3b2aa6ef6609a6ed1XZ40tW4FVpX%2C7603d2730cd147261XJ72dy-E1ZQ%2C033e06ee2c0d84251XV_0tm4F1RY%2C729ce5006062405d1XJ60ty-GFdR%2Cc06478b526effa2c1XF_2ti1ElBY%2C30cef1da94817a411XV-0tu9FVRW%2C3e4cd61d6e86e37a1XF829i4EFRX%2C880461ce5cd574881nd42dm5ElRQ%2C1ee526950db05adc0Hd-3ty4E1M~%2C5cf6a416f5d260321XV93dq1FlZU&page=" + i;
//            StringBuffer stringBuffer = sendPostRequest(data,urlSend);
//            ReadTxtFileUtil.toFile(stringBuffer + "\n\n","C:\\users\\hj\\Desktop\\job.boss.txt",true);
            getDetail();
        }
    }
    public static void getDetail() {
        String s = ReadTxtFileUtil.readTxtFile("C:\\users\\hj\\Desktop\\job.boss.txt");
        ArrayList<String> list = new ArrayList<>();
        String[] split = s.split("\n\n");
        for (int j = 0; j <= split.length - 1; j++) {
            JSONObject jsonObject = new JSONObject(split[j]);
            if (jsonObject.has("zpData")) {
                JSONObject zpData = jsonObject.getJSONObject("zpData");
                JSONArray jobList = zpData.getJSONArray("jobList");
                for (int k = 0; k <= jobList.length() - 1; k++) {
                    JSONObject jsonObject1 = jobList.getJSONObject(k);
                    String jobName = jsonObject1.getString("jobName");
                    String jobSalary = jsonObject1.getString("jobSalary");
                    String districtName = jsonObject1.getString("districtName");
                    String brandName = jsonObject1.getString("brandName");
                    list.add(brandName);
                    String cityName = jsonObject1.getString("cityName");
//                    System.out.println("jobName = " + jobName);
//                    System.out.println("jobSalary = " + jobSalary);
//                    System.out.println("districtName = " + districtName);
                    System.out.println("brandName = " + brandName);
//                    System.out.println("cityName = " + cityName);
                    System.out.println("\n\n");
                }
            }
        }
    }
    public static StringBuffer sendPostRequest(String data,String urlSend) {
        StringBuffer answer = null;
        //Build parameter string
        try {
            URL url = new URL(urlSend);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

            //write parameters
            writer.write(data);
            writer.flush();

            // Get the response
            answer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                answer.append(line);
            }
            writer.close();
            reader.close();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return answer;
    }
}
