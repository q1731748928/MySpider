package book.c4.com.jsoupconnect;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.*;
//实现图片的下载
public class JsoupConnectInputstream {
	
	public static void main(String[] args) throws IOException {
		String imageUrl = "https://xiaorui-my.sharepoint.com/personal/xiaorui_xiaorui_onmicrosoft_com/_layouts/15/download.aspx?UniqueId=e0ce615a-4fe5-406a-b453-da873f3d4df3&Translate=false&tempauth=eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAveGlhb3J1aS1teS5zaGFyZXBvaW50LmNvbUBlNDU4NzBmMS03ZGYyLTRmMmItYmZlMC04MGVhNDY1YWRlODQiLCJpc3MiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAiLCJuYmYiOiIxNjcwODQ3MzY0IiwiZXhwIjoiMTY3MDg1MDk2NCIsImVuZHBvaW50dXJsIjoiS3F2cDVndzlnS2kvTTZWdjdmRlRueDFEM29rY2pLOWhTdCszeCs2SytEOD0iLCJlbmRwb2ludHVybExlbmd0aCI6IjE2MiIsImlzbG9vcGJhY2siOiJUcnVlIiwiY2lkIjoiT1dZd1l6a3laVFV0Tm1NMk5DMDBabUUxTFdFM05tUXRZekl5TURNNE9UTmpPRGM0IiwidmVyIjoiaGFzaGVkcHJvb2Z0b2tlbiIsInNpdGVpZCI6IlpXTmpNR0l5TlRrdE1qbG1ZUzAwTlRJeExXRXpOamN0WXprNU9UWm1OV1kxWlRsaSIsImFwcF9kaXNwbGF5bmFtZSI6IkNsb3VkcmV2ZeaKiuWmueivvueoiyIsImdpdmVuX25hbWUiOiJzb25nIiwiZmFtaWx5X25hbWUiOiJrZWphaW5nIiwic2lnbmluX3N0YXRlIjoiW1wia21zaVwiXSIsImFwcGlkIjoiMGE5MGMxZmQtNWNlYy00MThhLWE3ZmUtMDM3MTQ1YTQ3YzVhIiwidGlkIjoiZTQ1ODcwZjEtN2RmMi00ZjJiLWJmZTAtODBlYTQ2NWFkZTg0IiwidXBuIjoieGlhb3J1aUB4aWFvcnVpLm9ubWljcm9zb2Z0LmNvbSIsInB1aWQiOiIxMDAzMjAwMDgzQjU0ODFEIiwiY2FjaGVrZXkiOiIwaC5mfG1lbWJlcnNoaXB8MTAwMzIwMDA4M2I1NDgxZEBsaXZlLmNvbSIsInNjcCI6ImFsbGZpbGVzLndyaXRlIiwidHQiOiIyIiwidXNlUGVyc2lzdGVudENvb2tpZSI6bnVsbCwiaXBhZGRyIjoiMjAuMTkwLjE0NC4xNzIifQ.TU4vQk9TQUk2RUJXdFRMMzZBTTc3cXZjcG92Vm52c2daMmV6R3lFOVlXZz0&ApiVersion=2.0";
		Connection connect = Jsoup.connect(imageUrl);
		Response response = connect.method(Method.GET).ignoreContentType(true).execute();  
		System.out.println("文件类型为:" + response.contentType());
		//如果响应成功，则执行下面的操作
		if (response.statusCode() ==200) {
			//响应转化成输出流
			BufferedInputStream bufferedInputStream = response.bodyStream();
			//保存图片
			saveImage(bufferedInputStream,"d://image//1.mp4");
		}
	}
	
	/**
     * 保存图片操作
     * @param
     * @param
	 * @throws IOException
     */
	static void saveImage(BufferedInputStream inputStream, String savePath) throws IOException  {
		
		byte[] buffer = new byte[1024];
		int len = 0;
		//创建缓冲流
		FileOutputStream fileOutStream = new FileOutputStream(new File(savePath));
		BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOutStream);
		//图片写入
		while ((len = inputStream.read(buffer)) != -1) {
			bufferedOut.write(buffer, 0, len);
		}
		//缓冲流释放与关闭
		bufferedOut.flush();
		bufferedOut.close();
	}
}
