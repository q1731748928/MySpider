import utils.ReadTxtFileUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2022/11/22
 * @Time 21:47
 */
public class ComTwoPic {
    public static void main(String[] args) throws IOException, InterruptedException {
        long start = System.currentTimeMillis();
        File file = new File("D:\\相册\\Camera");
        findFile(file);
        long end = System.currentTimeMillis();
        System.out.println("共计用时：" + (end -start)/1000/60+"分钟");
    }

    /**
     * 定义一个方法,用来获取一个目录中所有符合条件的文件(子文件,子子文件,子子子文件...)
     *
     * @param file
     */
    public static void findFile(File file) throws IOException, InterruptedException {

        // 在方法中,获取该目录下的所有子文件和子目录
        File file3 = new File("D:\\相册\\DCIM");
        File[] files = file.listFiles();
        File[] files2 = file3.listFiles();
        Desktop desktop = Desktop.getDesktop();
        String result = null;
        // 在方法中,循环遍历获取到的所有字文件和子目录
        if (files != null) {
            // 在方法中,遍历的时候,需要判断遍历出来的是文件还是目录
            for (File file1 : files) {
                // 如果是文件,就判断该文件是否以.java结尾,如果是就获取其绝对路径打印输出
                if (file1.isFile() && file1.getName().toLowerCase().endsWith(".png") ||  file1.getName().toLowerCase().endsWith(".jpeg") || file1.getName().toLowerCase().endsWith(".jpg") /* || file1.getName().toLowerCase().endsWith(".mp4") || file1.getName().toLowerCase().endsWith(".mov")||file1.getName().toLowerCase().endsWith(".txt")*/) {
                    BufferedImage sourceImg = ImageIO.read(new FileInputStream(file1));
                    int width1 = sourceImg.getWidth();
                    int height1 = sourceImg.getHeight();
                    long length1 = file1.length();
                    for (File file2 : files2) {
                        if (file2.isFile() && file2.getName().toLowerCase().endsWith(".png") || file2.getName().toLowerCase().endsWith(".jpeg") || file2.getName().toLowerCase().endsWith(".jpg")) {
                            sourceImg = ImageIO.read(new FileInputStream(file2));
                            int width2 = sourceImg.getWidth();
                            int height2 = sourceImg.getHeight();
                            long length2 = file2.length();
                            if ( width1 == width2 && height1 == height2 && length1 == length2) {
                                result = "文件夹file1的路径为：" + file1.getAbsolutePath()+ "===============" + "文件夹file2的路径为：" + file2.getAbsolutePath();
                                ReadTxtFileUtil.toFile(result + "\n","C:\\Users\\hj\\Desktop\\result.txt",true);
                                /*desktop.open(file1);
                                desktop.open(file2);*/
                            }
                        }
                    }
                }
                // 如果是文件夹,就递归调用该方法
                if (file1.isDirectory()) {
                    findFile(file1);
                }
            }
        }

    }
    /**
     * 本地获取
     */
    public static void testImg2() throws IOException {
        File picture = new File("D:\\相册\\Camera\\IMG_20210629_085848.jpg");
        BufferedImage sourceImg = ImageIO.read(new FileInputStream(picture));
        System.out.println(String.format("%.1f", picture.length() / 1024.0));// 源图大小
        System.out.println(sourceImg.getWidth()); // 源图宽度
        System.out.println(sourceImg.getHeight()); // 源图高度

    }
}

