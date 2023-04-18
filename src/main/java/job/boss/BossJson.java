package job.boss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import utils.ReadTxtFileUtil;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/29
 * @Time 15:05
 */
public class BossJson {
    public static void main(String[] args) throws IOException {
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        String cookie = "lastCity=101210100; wd_guid=a5bdbfc0-4268-4c1c-8caa-584173ff80c1; historyState=state; wbg=0; _bl_uid=0tl8ycddrRn9eO22yxXw8d6lbt0v; wt2=Dr3S8XD87bi-JCNdSLRsOHG2Vzrtl2noojZNsoI5YdYRFg_NGA2MGimA6Z8cKyiRuYwuIuc51z5L3sx6hQmsh0Q~~; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1674894982,1674896283,1674957740,1674972480; PPA_CI=130e67c1830b9946381d73efeb561476; __g=-; __c=1674975618; __l=l=%2Fwww.zhipin.com%2Fweb%2Fgeek%2Fjob%3Fquery%3Djava%26page%3D10&r=&g=&s=3&friend_source=0&s=3&friend_source=0; __a=41532580.1673416660.1674974654.1674975618.3975.33.12.3972; geek_zp_token=V1QNsmGOL82l5vVtRvyBkRLi2y7zPXxC4~; __zp_stoken__=4ae5efGBiRyJPaUdvfjETdlErP2oFAitWRWx8S3ZSSHNbZGlxP25bRXUubVI0NXhSHmQHem8TJSVNKRg3JTx3WHY2bDsCEQcLGR8LeBllfRdqA0cqAmYTIVUJVxA7HyAdXF01G2BsT1hybB0%3D; __zp_sname__=844da516; __zp_sseed__=fOvhrD2rm4oHuSllBxjPA5sowADCoQw1wdDIA8AHs/4=; __zp_sts__=1674975783200";
        String url = "https://www.zhipin.com/wapi/zpgeek/search/joblist.json?scene=1&query=java&city=101210100&experience=&degree=&industry=&scale=&stage=&position=&jobType=&salary=&multiBusinessDistrict=&multiSubway=&page=1&pageSize=30";
        /*for (int i = 1; i <= 10; i++) {
            Document document = Jsoup.connect(url).userAgent(us).cookie("cookie",cookie).ignoreContentType(true).get();
        }*/
        Document document = Jsoup.connect(url).userAgent(us).cookie("cookie",cookie).ignoreContentType(true).get();
        String s = ReadTxtFileUtil.loadJson(url, cookie);
        System.out.println(s);
    }
}
