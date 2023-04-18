package jd;

import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Author HeJie
 * @Date 2023/1/6
 * @Time 14:53
 */
public class JDComment {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            String path = "C:\\Users\\hj\\Desktop\\paper.txt";
            ReadTxtFileUtil.toFile("当前是第" + (i + 1) + "页" + "\n",path,true);
            String url = "https://club.jd.com/comment/productPageComments.action?callback=fetchJSON_comment98&productId=5587441&score=0&sortType=5&page=" + i + "&pageSize=50&isShadowSku=0&fold=1";
            HashMap<String, String> map = new HashMap<>();
            map.put("cookie","shshshfpa=eda057db-3323-e252-210e-5b0981da0bb7-1664596889; shshshfpb=gwvpvQFZCfJ2Vh9HwAbmgcw; __jdu=1669099569911595716603; unpl=JF8EALpnNSttChxWVU9SThNHS14EW10IGEQAaWIFXV1QSVwNG1UaFBN7XlVdXhRLFB9vZBRUWlNOVg4eACsSEXtdVV9fD0oeBm5vNWQJUR5VARMKEhp-SzNcIxx3IkMCbmFfCm1bS2QEKwIcFRhKVVxaWgBDFwNrbwFSXV9MVQcrAysSGE9tZG5YCEoWAmdgBFNcaEpkBxoDHhYRTFxXWW1DJRZOb2ACXFxQQ1ACEwobEhRDWVJeWg9KFTNuVwY; areaId=31; ipLoc-djd=31-2652-36685-36776; pinId=0ltYxmES3xw; pin=gohejie; unick=gohejie; _tp=QBXn2UMaj2LvAdT9uHpmMQ%3D%3D; _pst=gohejie; user-key=43e21b9b-05b1-4962-a6fa-9caad19d275e; cn=0; __jdv=76161171|direct|-|none|-|1672377180473; TrackID=1Pa2GyV_opuHvUrlTqN2jTEX9KtFufg9_nUzTbBV_COq2xgFg4AWJQzMV7RR5DnOoMT-hTxfJLH36oce5335GFOg7GvSg9KGwglR6i6Ra2mQ; ceshi3.com=000; jsavif=1; __jda=122270672.1669099569911595716603.1669099570.1672976226.1672985426.23; __jdc=122270672; token=740dfcf1ac4ad9259e011c5aacac521b,3,929437; __tk=a2f62098cb90fe22180cd35292f8df08,3,929437; shshshfp=c1824f7605280b6df9d4e194e56a5b3d; ip_cityCode=1213; jwotest_product=99; thor=A52583A2FC1EF2D06EB731D194E76FE0317E0241932D66F3EDD0713AB2BF3A0029BFC7E82DE4D68D66AAF44F36BB7BD1700306DEC60C31FF603FED5C71110C4E627699D1434ED4AEF8A62A6A56BEF0C3ACAF98754F9C3FA5C00651D0C99EFDD8D4072D8E2220923F3FCD9C50135529F5A3C6E6F1BFD5470AFDB855733ACFFBCCC7DE3B810D04A814FC071428EB52E1BB; shshshsID=f0cd6376d9117bbcf1ff81eca232ef36_16_1672987749412; __jdb=122270672.24.1669099569911595716603|23.1672985426; 3AB9D23F7A4B3C9B=ZU5NQ53RTK6T5SZC6TITMYYAMJD5IEBCYAK6ONYUL7627L2YY7XE77OII6IZ2O347WGBYPIZ5H4QP5T4TUNRLIAXXA; JSESSIONID=99761017BDA0004D86C1B9988AADAED1");
            String loadJson = ReadTxtFileUtil.loadJson(url, map.get("cookie"));
            Thread.sleep(1000);
            ReadTxtFileUtil.toFile(loadJson + "\n\n",path,true);
        }
    }
}


