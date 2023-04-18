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
public class Test_FindFile {
    public static void main(String[] args) throws IOException, InterruptedException {
        File file = new File("F:\\斩匪");

        findFile(file);
    }

    /**
     * 定义一个方法,用来获取一个目录中所有符合条件的文件(子文件,子子文件,子子子文件...)
     *
     * @param file
     */
    public static void findFile(File file) throws IOException, InterruptedException {
        Desktop desktop = Desktop.getDesktop();
        // 在方法中,获取该目录下的所有子文件和子目录
        File[] files = file.listFiles();
        // 在方法中,循环遍历获取到的所有字文件和子目录
        if (files != null) {
            // 在方法中,遍历的时候,需要判断遍历出来的是文件还是目录
            for (File file1 : files) {
                // 如果是文件,就判断该文件是否以.java结尾,如果是就获取其绝对路径打印输出
                if (file1.isFile() /*&& file1.getName().toLowerCase().endsWith(".jpeg") || file1.getName().toLowerCase().endsWith(".jpg")*//* || file1.getName().toLowerCase().endsWith(".mp4") */|| file1.getName().toLowerCase().endsWith(".mov")  || file1.getName().toLowerCase().endsWith(".txt")) {
                    desktop.open(file1);
                    Thread.sleep(30000);
                }
//                }
                /*if (file1.isFile() && file1.getName().toLowerCase().endsWith(".itmz")){
                    String absolutePath = file1.getAbsolutePath();
                    System.out.println("文件路径为：" + absolutePath);
                    desktop.open(file1);
                    Thread.sleep(5000);
                }*/
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

