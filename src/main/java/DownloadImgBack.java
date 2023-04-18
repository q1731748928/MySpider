import redis.clients.jedis.Jedis;
import utils.ReadTxtFileUtil;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author HeJie
 * @Date 2023/1/8
 * @Time 16:05
 */
public class DownloadImgBack {
    public static void main(String[] args) throws Exception {
        Long startTime = System.currentTimeMillis();
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        int countThread = 24;
        ExecutorService executorService = Executors.newFixedThreadPool(countThread);
        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            String imgUrl = jedis.get(key);
//            System.out.println("imgUrl = " + imgUrl);
            Thread.sleep(7000);
            executorService.submit(() -> {
                try {
                    ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(imgUrl), "D:\\2048\\" + key + ".jpg");
                    String name = Thread.currentThread().getName();
                    System.out.println("线程：" + name + "正在执行下载任务");
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Long endTime = new Date().getTime();
        Long time = endTime - startTime;
        System.out.println("总共耗时：" + (time) / 1000 / 60 + "分钟");
    }
}