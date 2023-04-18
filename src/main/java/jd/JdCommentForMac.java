package jd;

import utils.ReadTxtFileUtil;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/18
 * @Time 14:11
 */
public class JdCommentForMac {
    public static void main(String[] args) throws IOException, InterruptedException {
        final String cookieStr = "cookie";
        final String cookie = "__jdv=76161171|direct|-|none|-|1673583437372; __jdu=16735834373711865968510; areaId=15; shshshfpa=8c466ace-154f-3bfc-dfcb-c7c31a5b1471-1673583439; shshshfpb=zUXI-hPwsX4gnCZF6Y2T2yQ; ipLoc-djd=15-1213-3038-59931; shshshfp=a32a5b04fa064d7ac546c976d5895cf9; jsavif=1; __jda=122270672.16735834373711865968510.1673583437.1673686230.1674021904.5; __jdc=122270672; jsavif=1; ip_cityCode=1213; wlfstk_smdl=16inv1ipupm2gzhnibk6y2dtjz05k45l; 3AB9D23F7A4B3C9B=ORR6GR6NDPUEW3SHZJXZQC53ORTXJKUT3MO64BAK3NHQJLJTCHPUOG3432CB3KPESM72CGRJ2Y4B5HFRQ5RI7RNNYU; TrackID=1kKx-ewnirGGL4xJYbEnI2jt5Sus_7fgr4FORKH4ssYrrZXPzPLXszt0DlQi2PRWmM3ppat4FIKt2E8WMDDpDkVLSdyUvvw_OKZOhMFZDSLQ; thor=A52583A2FC1EF2D06EB731D194E76FE0A14871421DA771B2A46E7DB83AB96D6F801206DA632EAA6340DC29B99CA072A1710D6EC95A3D77C22CFCC8A54593EA2178267AB257F7167EF077026C5D12A659D515844959CF73A4AD1A1CD1BAA7817536E75DA6D82EBDB4FD250CA66234F78691BE368AC3EE99768A35F7601F264FC5583531997EEF0A65B0748C3EBA8937B7; pinId=0ltYxmES3xw; pin=gohejie; unick=gohejie; ceshi3.com=000; _tp=QBXn2UMaj2LvAdT9uHpmMQ%3D%3D; _pst=gohejie; token=fd29f4b5b22928e618d0a3760c96f7ac,3,930012; __tk=Zsuzu3uwvsaEYUXxuphwvUqFvp2wuSb3uSGEYUk0uslSuDX0YcuzY2,3,930012; shshshsID=fc7dd94c58c60fc93b0a76443dcefb91_7_1674022514686; __jdb=122270672.10.16735834373711865968510|5.1674021904";
        String us = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36";
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 100; i++) {
            String url = "https://club.jd.com/comment/productPageComments.action?callback=fetchJSON_comment98&productId=100026923531&score=0&sortType=5&page=" + i +"&pageSize=10&isShadowSku=0&rid=0&fold=1";
            String s = ReadTxtFileUtil.loadJson(url, cookie);
            Thread.sleep(1000);
            ReadTxtFileUtil.toFile("当前是第"  + i + "页" + "\n\n","C:\\Users\\hj\\Desktop\\mac.txt",true);
            ReadTxtFileUtil.toFile(s + "\n\n","C:\\Users\\hj\\Desktop\\mac.txt",true);
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间为：" + (end -start) / 1000 + "秒");
//        Document document = Jsoup.connect(url).cookie(cookieStr,cookie).userAgent(us).get();
//        System.out.println("document = " + document);
    }
}
