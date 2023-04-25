package utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author HeJie
 * @Date 2022/11/8
 * @Time 15:04
 */
public class ReadTxtFileUtil {
    public static String readTxtFile(String filePath) {
        String tmp = "";
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    tmp += lineTxt + "\n";
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return tmp;
    }

    /**
     * @param str 写入的字符串
     * @param path 存储的路径
     * @throws IOException
     */
    public static void toFile(String str, String path, Boolean isAppend) throws IOException {
        File f = new File(path);//新建一个文件对象，如果不存在则创建一个该文件
        FileWriter fw;
        try {
            fw = new FileWriter(f, isAppend);
            fw.write(str);//将字符串写入到指定的路径下的文件中
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            System.out.println("课程编号" + (count - 5));
        }
        return count;
    }

    public static Boolean isExistFile(File file,String fileName) {
        // 在方法中,获取该目录下的所有子文件和子目录
        File[] files = file.listFiles();

        // 在方法中,循环遍历获取到的所有字文件和子目录
        if (files != null) {
            // 在方法中,遍历的时候,需要判断遍历出来的是文件还是目录
            for (File file1 : files) {
                // 如果是文件夹,就递归调用该方法
                if (file1.isDirectory()) {
                    isExistFile(file1);
                }
                if (file1.isFile()) {
                    if (file1.toString().equals(fileName)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static Boolean isExistFile(File file) {
        // 在方法中,获取该目录下的所有子文件和子目录
        File[] files = file.listFiles();

        // 在方法中,循环遍历获取到的所有字文件和子目录
        if (files != null) {
            // 在方法中,遍历的时候,需要判断遍历出来的是文件还是目录
            for (File file1 : files) {
                // 如果是文件夹,就递归调用该方法
                if (file1.isDirectory()) {
                    isExistFile(file1);
                }
                if (file1.isFile()) {

                }
            }
        }
        return false;
    }

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

    /**
     * 将获取的字节数组保存为文件写入硬盘
     *
     * @param data
     * @param fileName
     */
    public static void writeImageToDisk(byte[] data, String fileName) {
        try {
            File file = new File(fileName); // 本地目录
            File fileParent = file.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
                file.createNewFile();
            }
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(data);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取远程http地址视图片
     *
     * @param strUrl
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36");
            conn.setConnectTimeout(6 * 10000);
            conn.setRequestProperty("Accept", "*/*");
//            conn.setRequestProperty("Host", "http://23img.com/");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            conn.setRequestProperty("Connection", "keep-alive");
            int contentLength = conn.getContentLength();
            if (contentLength <= 1024 * 1024) {
                System.out.println("文件总大小为：" + String.format("%.1f",contentLength / 1024.0) + "KB");
            }else {
                System.out.println("文件总大小为：" + String.format("%.1f",contentLength / 1024.0 / 1024) + "MB");
            }
            InputStream inStream = conn.getInputStream();
            byte[] btData = readInputStream(inStream);
            return btData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getImageFromNetByUrlWithName(String strUrl,String name) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,SSLv3");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.54");
            conn.setConnectTimeout(6 * 10000);
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Host", "cl.5297x.xyz");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            conn.setRequestProperty("Connection", "keep-alive");
//            conn.setRequestProperty("accept-ranges:","1-20");
            int contentLength = conn.getContentLength();
            if (contentLength <= 1024 * 1024) {
                ReadTxtFileUtil.toFile(name + "，文件总大小为：" + String.format("%.1f",contentLength / 1024.0) + "KB" + "\n\n","G:\\PUA2022\\ALL\\pua.txt",true );
            }else {
                ReadTxtFileUtil.toFile(name + "，文件总大小为：" + String.format("%.1f",contentLength / 1024.0 / 1024) + "MB" + "\n\n","G:\\PUA2022\\ALL\\pua.txt",true );
            }
            InputStream inStream = conn.getInputStream();
            byte[] btData = readInputStreamWithName(inStream,name);
            return btData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取远程http地址视图片
     *
     * @param strUrl
     * @return
     */
    public static byte[] getImageFromNetByUrlWithCookie(String strUrl,String cookie) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,SSLv3");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.54");
            conn.setConnectTimeout(6 * 10000);
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Host", "cl.5297x.xyz");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("cookie", cookie);
            InputStream inStream = conn.getInputStream();
            byte[] btData = readInputStream(inStream);
            return btData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 读取流
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        int size = 0;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[16384];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
            size += len;
            if (size <= 1024 * 1024) {
                System.out.println("已预写入：" + String.format("%.1f",size / 1024.0) + "KB");
            }else {
                System.out.println("已预写入：" + String.format("%.1f",size / 1024.0 / 1024) + "MB");
            }
        }
        inStream.close();
        if (size <= 1024 * 1024) {
            System.out.println("已完成，文件大小为：" + String.format("%.1f",size / 1024.0) + "KB");
        }else {
            System.out.println("已完成，文件大小为：" + String.format("%.1f",size / 1024.0 / 1024) + "MB");
        }
        return outStream.toByteArray();
    }

    public static byte[] readInputStreamWithName(InputStream inStream,String name) throws Exception {
        int size = 0;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[16384];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
            size += len;
            if (size <= 1024 * 1024) {
                System.out.println(name + "：已预写入：" + String.format("%.1f",size / 1024.0) + "KB");
            }else {
                System.out.println(name + "：已预写入：" + String.format("%.1f",size / 1024.0 / 1024) + "MB");
            }
        }
        inStream.close();
        if (size <= 1024 * 1024) {
            System.out.println("已完成，文件大小为：" + String.format("%.1f",size / 1024.0) + "KB");
        }else {
            System.out.println("已完成，文件大小为：" + String.format("%.1f",size / 1024.0 / 1024) + "MB");
        }
        return outStream.toByteArray();
    }

    public static String loadJson(String url, String cookie) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlStr = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlStr.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("cookie", cookie);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.54");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
    public static String loadJsonForJD(String url, String cookie) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlStr = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlStr.openConnection();
            conn.setRequestProperty("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept-encoding","gzip, deflate, br");
//            conn.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
            conn.setRequestProperty("cache-control","max-age=0");
            conn.setRequestProperty("upgrade-insecure-requests","1");
            conn.setRequestProperty("cookie", cookie);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.54");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
    public static String loadJsonByPut(String url, String cookie) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlStr = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlStr.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("cookie", cookie);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.54");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static String loadJsonByPost(String url, String cookie) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlStr = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlStr.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("cookie", cookie);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.54");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static String loadJsonByPostToBoss(String url, String cookie,String jobIds) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlStr = new URL(url+"?jobIds="+jobIds);
            //https://www.zhipin.com/wapi/zpgeek/history/joblist.json?jobIds=3b2aa6ef6609a6ed1XZ40tW4FVpX%252C7603d2730cd147261XJ72dy-E1ZQ%252C033e06ee2c0d84251XV_0tm4F1RY%252C729ce5006062405d1XJ60ty-GFdR%252Cc06478b526effa2c1XF_2ti1ElBY%252C30cef1da94817a411XV-0tu9FVRW%252C3e4cd61d6e86e37a1XF829i4EFRX%252C880461ce5cd574881nd42dm5ElRQ%252C1ee526950db05adc0Hd-3ty4E1M%7E%252C5cf6a416f5d260321XV93dq1FlZU
            System.out.println("urlStr = " + urlStr);
            HttpURLConnection conn = (HttpURLConnection) urlStr.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("cookie", cookie);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.54");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public static void toNullFile(String str) throws IOException {
        File f = new File("F:\\PUA2023\\ALL\\temp.txt");//新建一个文件对象，如果不存在则创建一个该文件
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            fw.write("");//将字符串写入到指定的路径下的文件中
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void toNullFileWithParam(String file,String str) throws IOException {
        File f = new File(file);//新建一个文件对象，如果不存在则创建一个该文件
        FileWriter fw;
        try {
            fw = new FileWriter(f);
            fw.write("");//将字符串写入到指定的路径下的文件中
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 盖章文件Pdf下载
     *
     * @Param url 请求下载地址
     * @Param accessToken token
     */
    public static HttpEntity pdfDownload(String url) {
        HttpEntity entity = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");
//            httpGet.setHeader("Authorization", "Bearer " + accessToken);
            HttpResponse response = httpClient.execute(httpGet);
            entity = response.getEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }


    /**
     * 输出pdf
     *
     * @Param entity 请求返回内容
     * @Param filePath 文件保存地址
     */
    public static long writeToPdf(HttpEntity entity, String filePath) throws Exception {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        int size = 0;
        try {
            byte[] bytes = EntityUtils.toByteArray(entity);
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if (!path.exists()) {
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while (length != -1) {
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
            return bis.available();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                fos.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return size;
    }
}
