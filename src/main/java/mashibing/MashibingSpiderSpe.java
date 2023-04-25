package mashibing;

import net.sf.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static utils.ReadTxtFileUtil.readTxtFile;
import static utils.ReadTxtFileUtil.toFile;

/**
 * @Author HeJie
 * @Date //6
 * @Time :
 */
public class MashibingSpiderSpe {
    public static void main(String[] args) throws JSONException, IOException, InterruptedException, URISyntaxException {
        // 课程进度表
        toSchedule();
    }

    private static void toSchedule() throws JSONException, IOException {
        String filePath = "D:\\马士兵\\资料\\MCA高级架构师2022版\\json.txt";
        String str = readTxtFile(filePath);
        JSONObject jsonObject = new JSONObject(str);
        JSONObject data = jsonObject.getJSONObject("data");
        org.json.JSONArray chapterList = data.getJSONArray("chapterList");
        String courseName = data.getString("courseName");
        String chapterInfo = "";

        File file1 = new File("D:\\马士兵\\资料\\MCA高级架构师");
        Integer file2 = findFile(file1);
        File file = new File("D:\\马士兵\\资料\\MCA高级架构师\\" + (file2) + courseName);
        Integer sectionSequence = 1;
        String path = file.toString().trim();
        String txt = "D:\\马士兵\\资料\\数据结构与算法大师课\\" + "02算法数据结构体系学习班\\算法数据结构体系学习班.txt";
        toFile(courseName + " 课程进度表" + "\n", txt,true);
        Integer chapterSequence = 0;
        for (int i = 0; i < chapterList.length(); i++) {
            JSONObject jsonObject1 = chapterList.getJSONObject(i);
//            System.out.println("jsonObject1 = " + jsonObject1);
            JSONArray sectionList = jsonObject1.getJSONArray("sectionList");
            String chapterName = jsonObject1.getString("chapterName");
//            chapterSequence = jsonObject1.getInt("chapterSequence");
            ++chapterSequence;
            int chapterDurationTimeCount = jsonObject1.getInt("chapterDurationTimeCount");
            int chapterCount = jsonObject1.getInt("chapterCount");
            chapterInfo = "章节" + chapterSequence + ":" + chapterName + chapterCount
                    + "节" + " **| " + TimeToMH(chapterDurationTimeCount) + "**"
                    + "\n\n";
            toFile(chapterInfo, txt,true);
            for (int j = 0; j <= sectionList.length() - 1; j++) {
                String sectionSequenceStr = "";
                JSONObject jsonObject2 = sectionList.getJSONObject(j);
                String sectionName = jsonObject2.getString("sectionName");
                int durationTime = jsonObject2.getInt("durationTime");
                if (sectionSequence <=9) {
                    sectionSequenceStr += "0";
                }
                toFile("\t"+ "课时" + sectionSequenceStr + sectionSequence + "\n" +"\t\t" + sectionName + "\n" + "\t\t" + TimeToMH(durationTime) + "\n\n", txt,true);
                sectionSequence++;
            }
        }
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File(txt));
        desktop.open(new File("C:\\Program Files (x86)\\toketaWare\\iThoughts\\iThoughts.exe"));
        System.out.println(courseName + " 课程进度表");
        System.out.println("章节总数：" + chapterSequence);
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
    public static String TimeToMH(Integer allTime) {
        Integer hours = allTime / 3600;
        Integer min = allTime / 60;
        Integer seconds = allTime - min * 60;
        String time = "";
        if (hours >= 1) {
            time = hours + "小时" + (min - hours * 60) + "分" + seconds + "秒";
        } else {
            time = min + "分" + seconds + "秒";
        }
        return time;
    }

    public static Integer findFile(File file) {
        // 在方法中,获取该目录下的所有子文件和子目录
        File[] files = file.listFiles();
        int count = 0;
        // 在方法中,循环遍历获取到的所有字文件和子目录
        if (files != null) {
            // 在方法中,遍历的时候,需要判断遍历出来的是文件还是目录
            for (File file1 : files) {
                // 如果是文件夹,就递归调用该方法
                if (file1.isDirectory()) {
                    count++;
                }
                if (file1.isFile()) {
                    count++;
                }
            }
            System.out.println("文件+文件夹总数" + count);
            System.out.println("课程编号" + (count-2));
        }
        return count;
    }
}
