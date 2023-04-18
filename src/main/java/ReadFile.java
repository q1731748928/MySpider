import java.io.*;

/**
 * @Classname: ReadFile
 * @Description: 读取文件并输出到另一个文件
 * @author: 流泪兔兔头
 * @date: 2022/1/12 9:33
 */
public class ReadFile {

    public static void main(String[] args) throws Exception {
        readFileContent("D:\\马士兵\\资料\\MAC高级架构师\\2023金三银四面试突击班\\并发编程面试题123.md", "D:\\马士兵\\资料\\MAC高级架构师\\2023金三银四面试突击班\\并发编程面试题123.txt");
    }

    /**
     * @Title: readFileContent
     * @Description: 按行读取文本文件的内容
     * @author: 读取文件并输出到另一个文件
     * @date: 2022/1/13 11:25
     */
    public static void readFileContent(String fileName, String fileName2) throws IOException {
        // 1、获取到文件对象
        File file = new File(fileName);
        File outFile = new File(fileName2);

        // 2、判断文件是否存在
        if(!file.exists()) {
            return;
        } else if (!outFile.exists()) {
            // 输出文件不存在文件且创建失败
            if(!outFile.createNewFile()) {
                return;
            }
        }

        // 因为读取的是文本，所以使用字符流。如果是其他二进制文件，可以使用字节流。在try中创建可以自动关闭
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        ) {
            String readStr;
            // 读一行写一行
            while ((readStr = reader.readLine()) != null) {

                if(readStr.contains("#") || readStr.contains("##") || readStr.contains("###")) {
                    writer.write(readStr + "\n\n\n\n\n");
                    writer.newLine();
                }
                // 输出流在close之前会自动执行flush，也可以根据情况手动执行
//				writer.flush();
            }
        }
    }

}