import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.*;

import static utils.ReadTxtFileUtil.readTxtFile;

/**
 * @Author HeJie
 * @Date 2022/12/3
 * @Time 14:47
 */
public class StartDownload {
    final static String COOKIESTR = "cloudreve-session=MTY3MDA1MjA0NXxOd3dBTkZWTVZGTlpVazFIU2xSTE4wbzJTVnBXVUVKTFEwWXpUMDlHVlZsWFYxcE9XRFZWVUZGUVVVNDJUREpUVGpSU1YwSktURkU9fNoasHDen8YLJMuEsBsjB_2m1jfnDhED6OQp6b0Bc4zk";
    private static String readPath = "D:\\PUA\\1.txt";
    private static String downloadPath = "G:\\PUA2022\\ALL";
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5
            , 50
            , 10
            , TimeUnit.SECONDS
            , new LinkedBlockingQueue<>(100)
            , Executors.defaultThreadFactory()
            , new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        String s = readTxtFile(readPath);
        String[] split = s.split("\n\n");
        for (String s1 : split) {
            if (!s1.equals("")) {
                downFile(s1,COOKIESTR);
            }
        }
    }

    public static void downFile(String baseUrl,String COOKIESTR) throws ExecutionException, InterruptedException, IOException {

        CompletableFuture<Long> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            long start = System.currentTimeMillis();
            ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrlWithCookie(baseUrl,COOKIESTR), downloadPath + "\\" + UUID.randomUUID().toString().substring(0,7));
            return start;
        }, executor);
    }
}
