package book.c4.com.httpclient.thread;

import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        //添加连接参数
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(Consts.UTF_8)
                .build();
        //添加socket参数
        SocketConfig socketConfig = SocketConfig.custom()
                .setTcpNoDelay(true)
                .build();
        //配置连接池管理器
        PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        pcm.setMaxTotal(100);
        // 设置每个连接的路由数
        pcm.setDefaultMaxPerRoute(10);
        //设置连接信息
        pcm.setDefaultConnectionConfig(connectionConfig);
        //设置socket信息
        pcm.setDefaultSocketConfig(socketConfig);
        //设置全局请求配置,包括Cookie规范,HTTP认证,超时
        RequestConfig defaultConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays
                        .asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .setConnectionRequestTimeout(30 * 1000)
                .setConnectTimeout(30 * 1000)
                .setSocketTimeout(30 * 1000)
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(pcm)
                .setDefaultRequestConfig(defaultConfig)
                .build();
        // 请求的URL
        String[] urlArr = {
                "https://xiaorui-my.sharepoint.com/personal/xiaorui_xiaorui_onmicrosoft_com/_layouts/15/download.aspx?UniqueId=da880a22-f14d-465c-a772-f61a950c62d3&Translate=false&tempauth=eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAveGlhb3J1aS1teS5zaGFyZXBvaW50LmNvbUBlNDU4NzBmMS03ZGYyLTRmMmItYmZlMC04MGVhNDY1YWRlODQiLCJpc3MiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAiLCJuYmYiOiIxNjcwODM4MDUxIiwiZXhwIjoiMTY3MDg0MTY1MSIsImVuZHBvaW50dXJsIjoiVEhSMTFIVGdvWndVUEhBaGh3TmNkM3JibzFNVlFoS1YwcFFEbE5PZ1ZZVT0iLCJlbmRwb2ludHVybExlbmd0aCI6IjE2MiIsImlzbG9vcGJhY2siOiJUcnVlIiwiY2lkIjoiT1dRNE1HVXhNV1F0T1dWbVppMDBOakV3TFRnMllqRXROVEkyWTJFME16SXlaVFZsIiwidmVyIjoiaGFzaGVkcHJvb2Z0b2tlbiIsInNpdGVpZCI6IlpXTmpNR0l5TlRrdE1qbG1ZUzAwTlRJeExXRXpOamN0WXprNU9UWm1OV1kxWlRsaSIsImFwcF9kaXNwbGF5bmFtZSI6IkNsb3VkcmV2ZeaKiuWmueivvueoiyIsImdpdmVuX25hbWUiOiJzb25nIiwiZmFtaWx5X25hbWUiOiJrZWphaW5nIiwic2lnbmluX3N0YXRlIjoiW1wia21zaVwiXSIsImFwcGlkIjoiMGE5MGMxZmQtNWNlYy00MThhLWE3ZmUtMDM3MTQ1YTQ3YzVhIiwidGlkIjoiZTQ1ODcwZjEtN2RmMi00ZjJiLWJmZTAtODBlYTQ2NWFkZTg0IiwidXBuIjoieGlhb3J1aUB4aWFvcnVpLm9ubWljcm9zb2Z0LmNvbSIsInB1aWQiOiIxMDAzMjAwMDgzQjU0ODFEIiwiY2FjaGVrZXkiOiIwaC5mfG1lbWJlcnNoaXB8MTAwMzIwMDA4M2I1NDgxZEBsaXZlLmNvbSIsInNjcCI6ImFsbGZpbGVzLndyaXRlIiwidHQiOiIyIiwidXNlUGVyc2lzdGVudENvb2tpZSI6bnVsbCwiaXBhZGRyIjoiMjAuMTkwLjE0NC4xNzAifQ.NXlPYi85bmVEVm04NzlZbkozb3RyY0R0TStQQUR6MDV1cGJiVU1JTDJmTT0&ApiVersion=2.0",
                "https://xiaorui-my.sharepoint.com/personal/xiaorui_xiaorui_onmicrosoft_com/_layouts/15/download.aspx?UniqueId=ea04340f-5806-4425-8692-fd421ddd6db4&Translate=false&tempauth=eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAveGlhb3J1aS1teS5zaGFyZXBvaW50LmNvbUBlNDU4NzBmMS03ZGYyLTRmMmItYmZlMC04MGVhNDY1YWRlODQiLCJpc3MiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAiLCJuYmYiOiIxNjcwODM4MDUyIiwiZXhwIjoiMTY3MDg0MTY1MiIsImVuZHBvaW50dXJsIjoidTYrUTBPenlZZGh5eEpxemdqNFd2T2x2ejkvZWd3TWFCSUZpTnhUczF1MD0iLCJlbmRwb2ludHVybExlbmd0aCI6IjE2MiIsImlzbG9vcGJhY2siOiJUcnVlIiwiY2lkIjoiWTJNNFpESm1PR1F0T0daaU55MDBNbU15TFRneE5qQXRPVFV6WXpFelpHRmtZV0V5IiwidmVyIjoiaGFzaGVkcHJvb2Z0b2tlbiIsInNpdGVpZCI6IlpXTmpNR0l5TlRrdE1qbG1ZUzAwTlRJeExXRXpOamN0WXprNU9UWm1OV1kxWlRsaSIsImFwcF9kaXNwbGF5bmFtZSI6IkNsb3VkcmV2ZeaKiuWmueivvueoiyIsImdpdmVuX25hbWUiOiJzb25nIiwiZmFtaWx5X25hbWUiOiJrZWphaW5nIiwic2lnbmluX3N0YXRlIjoiW1wia21zaVwiXSIsImFwcGlkIjoiMGE5MGMxZmQtNWNlYy00MThhLWE3ZmUtMDM3MTQ1YTQ3YzVhIiwidGlkIjoiZTQ1ODcwZjEtN2RmMi00ZjJiLWJmZTAtODBlYTQ2NWFkZTg0IiwidXBuIjoieGlhb3J1aUB4aWFvcnVpLm9ubWljcm9zb2Z0LmNvbSIsInB1aWQiOiIxMDAzMjAwMDgzQjU0ODFEIiwiY2FjaGVrZXkiOiIwaC5mfG1lbWJlcnNoaXB8MTAwMzIwMDA4M2I1NDgxZEBsaXZlLmNvbSIsInNjcCI6ImFsbGZpbGVzLndyaXRlIiwidHQiOiIyIiwidXNlUGVyc2lzdGVudENvb2tpZSI6bnVsbCwiaXBhZGRyIjoiMjAuMTkwLjE0NC4xNzAifQ.ZWRXZHdkZEFGSVU0bkN0b3g3Rm1DSFZpMXI2aTgvMm40bVJINUhpNnRVbz0&ApiVersion=2.0",
                "https://xiaorui-my.sharepoint.com/personal/xiaorui_xiaorui_onmicrosoft_com/_layouts/15/download.aspx?UniqueId=70c121fa-b802-44b7-95e2-54e16fe9bba2&Translate=false&tempauth=eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJhdWQiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAveGlhb3J1aS1teS5zaGFyZXBvaW50LmNvbUBlNDU4NzBmMS03ZGYyLTRmMmItYmZlMC04MGVhNDY1YWRlODQiLCJpc3MiOiIwMDAwMDAwMy0wMDAwLTBmZjEtY2UwMC0wMDAwMDAwMDAwMDAiLCJuYmYiOiIxNjcwODM4MDUyIiwiZXhwIjoiMTY3MDg0MTY1MiIsImVuZHBvaW50dXJsIjoiNkxuSVp6d1pkUE9TbG9tYjBNQjRLWlpDUG5Tcmo3dXVXMUhHaGFmcEcyaz0iLCJlbmRwb2ludHVybExlbmd0aCI6IjE2MiIsImlzbG9vcGJhY2siOiJUcnVlIiwiY2lkIjoiTm1Oa01HSmxaR010TXpGbU5DMDBNbVF5TFRoaU0ySXRNR016WmpSbE9UWmtPV1UwIiwidmVyIjoiaGFzaGVkcHJvb2Z0b2tlbiIsInNpdGVpZCI6IlpXTmpNR0l5TlRrdE1qbG1ZUzAwTlRJeExXRXpOamN0WXprNU9UWm1OV1kxWlRsaSIsImFwcF9kaXNwbGF5bmFtZSI6IkNsb3VkcmV2ZeaKiuWmueivvueoiyIsImdpdmVuX25hbWUiOiJzb25nIiwiZmFtaWx5X25hbWUiOiJrZWphaW5nIiwic2lnbmluX3N0YXRlIjoiW1wia21zaVwiXSIsImFwcGlkIjoiMGE5MGMxZmQtNWNlYy00MThhLWE3ZmUtMDM3MTQ1YTQ3YzVhIiwidGlkIjoiZTQ1ODcwZjEtN2RmMi00ZjJiLWJmZTAtODBlYTQ2NWFkZTg0IiwidXBuIjoieGlhb3J1aUB4aWFvcnVpLm9ubWljcm9zb2Z0LmNvbSIsInB1aWQiOiIxMDAzMjAwMDgzQjU0ODFEIiwiY2FjaGVrZXkiOiIwaC5mfG1lbWJlcnNoaXB8MTAwMzIwMDA4M2I1NDgxZEBsaXZlLmNvbSIsInNjcCI6ImFsbGZpbGVzLndyaXRlIiwidHQiOiIyIiwidXNlUGVyc2lzdGVudENvb2tpZSI6bnVsbCwiaXBhZGRyIjoiMjAuMTkwLjE0NC4xNzAifQ.VTRFQTJhSEYvby9rNVVVQmliM1hpZVhXdm80ejdYZytUWUlNWGpMSXNDST0&ApiVersion=2.0"

        };
        //创建固定大小的线程池
        ExecutorService exec = Executors.newFixedThreadPool(3);
        for (int i = 0; i < urlArr.length; i++) {
            String filename = UUID.randomUUID().toString().substring(0, 5);
            //创建HTML文件输出目录
            OutputStream out = new FileOutputStream("d:\\file\\" + filename);
            HttpGet httpget = new HttpGet(urlArr[i]);
            //启动线程执行请求
            exec.execute(new DownHtmlFileThread(httpClient, httpget, out));
        }
        //关闭线程
        exec.shutdown();
    }

    static class DownHtmlFileThread extends Thread {
        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpGet httpget;
        private final OutputStream out;

        //输入的参数
        public DownHtmlFileThread(CloseableHttpClient httpClient,
                                  HttpGet httpget, OutputStream out) {
            this.httpClient = httpClient;
            this.context = HttpClientContext.create();
            this.httpget = httpget;
            this.out = out;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() +
                    "线程请求的URL为:" + httpget.getURI());
            try {
                CloseableHttpResponse response = httpClient.execute(
                        httpget, context);  //执行请求
                try {
                    //HTML文件写入文档
                    out.write(EntityUtils.toString(response.getEntity(), "utf-8")
                            .getBytes());
                    out.close();
                    //消耗实体
                    EntityUtils.consume(response.getEntity());
                } finally {
                    response.close(); //关闭响应
                }
            } catch (ClientProtocolException ex) {
                ex.printStackTrace(); // 处理 protocol错误
            } catch (IOException ex) {
                ex.printStackTrace(); // 处理I/O错误
            }
        }
    }
}
