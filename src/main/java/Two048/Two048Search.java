package Two048;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author HeJie
 * @Date 2022/12/11
 * @Time 14:26
 */
public class Two048Search {

    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "";
        int start = 1;
        int end = 500;
//        String[] key = {"戴璐", "张津瑜", "公安", "警察"};
        String[] key = {""};
        String baseUrl = "";
        ArrayList<String> list = new ArrayList<>();
        HashMap map = new HashMap<>();
        map.put("cookies","'zh_choose': 'n',\n" +
                "    'cf_chl_2': 'd8a0bd44f5b8145',\n" +
                "    'PPA_CI': 'a054734d67da69c90b28a2dda61d840f',\n" +
                "    'cf_clearance': 'SSsHLhbrwt1lVNhIyjxy_Ykn6OYxO4eeQ7Okv5FOXYA-1682430502-0-150',\n" +
                "    'a22e7_lastpos': 'F15',\n" +
                "    'a22e7_threadlog': '%2C15%2C',\n" +
                "    'a22e7_ol_offset': '51701',\n" +
                "    'a22e7_lastvisit': '130%091682430650%09%2F2048%2Fthread.php%3Ffid-15-page-1.html',");
        map.put("headers","'authority': 'bbs.1vlqg.com',\n" +
                "    'accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7',\n" +
                "    'accept-language': 'zh-CN,zh;q=0.9',\n" +
                "    'cache-control': 'no-cache',\n" +
                "    # 'cookie': 'zh_choose=n; cf_chl_2=d8a0bd44f5b8145; PPA_CI=a054734d67da69c90b28a2dda61d840f; cf_clearance=SSsHLhbrwt1lVNhIyjxy_Ykn6OYxO4eeQ7Okv5FOXYA-1682430502-0-150; a22e7_lastpos=F15; a22e7_threadlog=%2C15%2C; a22e7_ol_offset=51701; a22e7_lastvisit=130%091682430650%09%2F2048%2Fthread.php%3Ffid-15-page-1.html',\n" +
                "    'pragma': 'no-cache',\n" +
                "    'sec-ch-ua': '\"Chromium\";v=\"112\", \"Google Chrome\";v=\"112\", \"Not:A-Brand\";v=\"99\"',\n" +
                "    'sec-ch-ua-mobile': '?0',\n" +
                "    'sec-ch-ua-platform': '\"Windows\"',\n" +
                "    'sec-fetch-dest': 'document',\n" +
                "    'sec-fetch-mode': 'navigate',\n" +
                "    'sec-fetch-site': 'none',\n" +
                "    'sec-fetch-user': '?1',\n" +
                "    'upgrade-insecure-requests': '1',\n" +
                "    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36',");
        for (int i = start; i < end; i++) {
            url = "https://bbs.1vlqg.com/2048/thread.php?fid-15-page-" + i + ".html";
            Document document = Jsoup.connect(url).headers(map).proxy("localhost", 1085).ignoreContentType(true).get();
            Elements subject = document.getElementsByClass("subject");
            String str = subject.toString();
            String[] split = str.split("\n");
            for (String s1 : split) {
                if (s1.contains(key[0]) || s1.contains(key[1]) || s1.contains(key[2]) || s1.contains(key[3])) {
                    ReadTxtFileUtil.toFile(s1+"\n\n","C:\\Users\\hj\\Desktop\\2048.txt",true);
                    Thread.sleep(5000);
                    /*if (StringUtils.isNotBlank(s1)) {
                        list.add(s1);
                    }
                    getExcel(list);*/
                }

            }
        }

    }

    private static void getExcel(ArrayList list) {
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\hj\\Desktop\\2048.xlsx");
        ArrayList head = CollUtil.newArrayList("信息");
        ArrayList rows = CollUtil.newArrayList(list);
        writer.writeHeadRow(head);
        writer.write(rows, false);
        writer.close();
    }
}
