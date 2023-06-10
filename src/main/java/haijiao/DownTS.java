package haijiao;

import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author HeJie
 * @Date 2023/1/31
 * @Time 14:13
 */
public class DownTS {
    public static void main(String[] args) throws IOException {
        int countThread = 24;
        ExecutorService executorService = Executors.newFixedThreadPool(countThread);
        for (int i = 0; i <= 2061 ; i++) {
            // 合并mp4不能有中文路径,且 /
            String url = "https://hjvdo.139592.com/hjstore/video/20230505/2509404e228dd83b35c7fe5728087611/4985794sq4PvPCO_i" + i + ".ts";
            int l = i;
            executorService.execute(()->{
                try {
                    ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(url), "D:\\haijiao\\20230610\\" + l + ".ts");
                    Thread.sleep(5);
                    String name = Thread.currentThread().getName();
                    System.out.println("线程：" + name + "正在执行下载任务");
                } catch (Exception e) {
                    System.exit(0);
                }


            });
        }
//        for (int i = 29; i <= 1584; i++) {
//            String s1 = "#EXTINF:1.250000,";
//            String s2 = "D://haijiao//152394//" + i + ".ts";
//            ReadTxtFileUtil.toFile(s1 + "\n" + s2 + "\n","D:\\haijiao\\152394\\index.m3u8",true);
//        }
    }
}
