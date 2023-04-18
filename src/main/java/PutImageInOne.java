import java.awt.*;
import java.io.*;
import java.util.UUID;

/**
 * @Author HeJie
 * @Date 2022/11/25
 * @Time 20:55
 */
public class PutImageInOne {
    public static void main(String[] args) throws IOException, InterruptedException {
        copyImg();
//        findFile(file);
    }

    public static void findFile(File file) throws IOException, InterruptedException {
        // 在方法中,获取该目录下的所有子文件和子目录
        File[] files = file.listFiles();
        Desktop desktop = Desktop.getDesktop();
        // 在方法中,循环遍历获取到的所有字文件和子目录
        if (files != null) {
            // 在方法中,遍历的时候,需要判断遍历出来的是文件还是目录
            for (File file1 : files) {
                // 如果是文件,就判断该文件是否以.java结尾,如果是就获取其绝对路径打印输出
                if (file1.isFile() && file1.getName().toLowerCase().endsWith(".jpeg") || file1.getName().toLowerCase().endsWith(".jpg")) {

                }
                // 如果是文件夹,就递归调用该方法
                if (file1.isDirectory()) {
                    findFile(file1);
                }
            }
        }
    }

    public static void copyImg() throws IOException {
        //1.有一个源图片
        File f1 = new File("C:\\Users\\hj\\Desktop\\A\\1.png");
        //2.有一个目标图片：
        File f2 = new File("C:\\Users\\hj\\Desktop\\2\\" + UUID.randomUUID().toString());
        //3.有一个输入的管道 怼 到 源文件：
        FileInputStream fis = new FileInputStream(f1);
        //4.有一个输出的管道 怼到  目标文件上：
        FileOutputStream fos = new FileOutputStream(f2);
        //5.功能加强，在FileInputStream外面套一个管：BufferedInputStream:
        BufferedInputStream bis = new BufferedInputStream(fis);
        //6.功能加强，在FileOutputStream外面套一个管：BufferedOutputStream:
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //7.开始动作 ：
        long startTime = System.currentTimeMillis();
        byte[] b = new byte[1024];
        int len = bis.read(b);
        while (len != -1) {
            bos.write(b, 0, len);
            /* bos.flush(); 底层已经帮我们做了刷新缓冲区的操作，不用我们手动完成：底层调用flushBuffer()*/
            len = bis.read(b);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("复制完成的时间为：" + (endTime - startTime));
        //8.关闭流：
        //倒着关：
        //如果处理流包裹着节点流的话，那么其实只要关闭高级流（处理流），那么里面的字节流也会随之被关闭。
        bos.close();
        bis.close();
        /*fos.close();
        fis.close();*/
    }
}
