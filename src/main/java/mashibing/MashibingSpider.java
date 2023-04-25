package mashibing;

import net.sf.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;

import static utils.ReadTxtFileUtil.*;

/**
 * @Author HeJie
 * @Date //6
 * @Time :
 */
public class MashibingSpider {
    public static void main(String[] args) throws JSONException, IOException, InterruptedException, URISyntaxException {
        // 课程进度表
        toSchedule();
    }

    private static void toSchedule() throws JSONException, IOException, URISyntaxException {
        String filePath = "D:\\PUA\\pua.txt";
        String str = readTxtFile(filePath);
        JSONObject jsonObject = new JSONObject(str);
        JSONObject data = jsonObject.getJSONObject("data");
        String chapterList = data.getString("chapterList");
        String courseName = data.getString("courseName");

        File file1 = new File("D:\\马士兵\\资料\\MCA高级架构师");
        Integer file2 = findFile(file1);
        File file = new File("D:\\马士兵\\资料\\MCA高级架构师\\" + (file2 - 5) +courseName);
        if (!file.exists()) {
            file.mkdir();
        }
        String path = file.toString().trim();
        String txt = path + "\\" + courseName + ".txt";
        JSONArray jsonArray = JSONArray.fromObject(chapterList);
        String sectionName = "";
        Integer sectionSequence = 1;
        // toNullFile(sectionName);
        toFile(courseName + " 课程进度表" + "\n", txt,true);
        Integer chapterSequence = 0;
        String chapterInfo = "";
        for (int i = 0; i < jsonArray.size(); i++) {
            net.sf.json.JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String sectionList = jsonObject1.getString("sectionList");
            String chapterName = jsonObject1.getString("chapterName");
            // chapterSequence = jsonObject1.getInt("chapterSequence");
            chapterSequence++;
            int chapterCount = jsonObject1.getInt("chapterCount");
            int chapterDurationTimeCount = jsonObject1.getInt("chapterDurationTimeCount");
            JSONArray jsonArray1 = JSONArray.fromObject(sectionList);
            chapterInfo = "章节" + chapterSequence + ":" + chapterName + chapterCount
                    + "节" + " **| " + TimeToMH(chapterDurationTimeCount) + "**"
                    + "\n\n";
            toFile(chapterInfo, txt,true);
            for (int j = 0; j < jsonArray1.size(); j++) {
                String sectionSequenceStr = "";
                net.sf.json.JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                sectionName = jsonObject2.getString("sectionName");
                // sectionSequence = jsonObject2.getInt("sectionSequence");
                String durationTime = jsonObject2.getString("durationTime");
                int allTime = Integer.parseInt(durationTime);
                if (sectionSequence <=9) {
                    sectionSequenceStr += "0";
                }
                toFile("\t"+ "课时" + sectionSequenceStr + sectionSequence + "\n" +"\t\t" + sectionName + "\n" + "\t\t" + TimeToMH(allTime) + "\n\n", txt,true);
                sectionSequence++;
            }
        }
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(txt));
        desktop.open(new File("C:\\Program Files (x86)\\toketaWare\\iThoughts\\iThoughts.exe"));
        System.out.println(courseName + " 课程进度表");
        System.out.println("章节总数：" + chapterSequence);
//        String[] url = {"http://www.mashibing.com/","http://youtube.com"};
//        for (String s : url) {
//            java.net.URI uri = java.net.URI.create(s);
//            desktop.browse(uri);
//        }
    }

    /**
     * * 功能：Java读取txt文件的内容
     * * 步骤：1：先获得文件句柄
     * * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * * 3：读取到输入流后，需要读取生成字节流
     * * 4：一行一行的输出。readline()。
     * * 备注：需要考虑的是异常情况
     * * @param filePath
     */


    /*public static void toFile(String str) throws IOException {
        File f = new File("d:\\PUA\\puaOpt.txt");//新建一个文件对象，如果不存在则创建一个该文件
        FileWriter fw;
        try {
            fw = new FileWriter(f, true);
            fw.write(str);//将字符串写入到指定的路径下的文件中
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

//    public static void toNullFile(String str) throws IOException {
//        File f = new File("d:\\PUA\\puaOpt.txt");//新建一个文件对象，如果不存在则创建一个该文件
//        FileWriter fw;
//        try {
//            fw = new FileWriter(f);
//            fw.write("");//将字符串写入到指定的路径下的文件中
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



}
