import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

import java.io.UnsupportedEncodingException;

/**
 * @Author HeJie
 * @Date 2023/1/23
 * @Time 15:38
 */
public class NetEaseMusicRecord {
    public static void main(String[] args) {


        String url = "https://music.163.com/weapi/v1/play/record?csrf_token=338c8d19f0fbeb9327eb1be72c4cf0ce";
        String f = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97dpublic static String 52741d546b8e289dc6935b3ece0462db0a22b8e7";
        String g = "0CoJUm6Qyw8W8jud";
        String e = "010001";
        String i = "uh5BqvAmkf9Ekwp8";

        String data = "{\n" +
                "                \"limit\": \"1000\",\n" +
                "                \"offset\": \"0\",\n" +
                "                \"total\": \"true\",\n" +
                "                \"type\": \"-1\",\n" +
                "                \"uid\": \"8020796333\"\n" +
                "}";


    }

    public static String getEncSecKey() {
        return "0a78ae5f592a0b586d8f2546f21e0674c1b476e9e575f307ec9af15c7440b44737154e281ad0f6a902a27ee0b79103b72e8479ff1531c9659b62406b7b350b9f07c4884396c944984a028a31103d2a9fb55d73e8581d9f255569e04da32533ae6542669598aba893e6d92bae87595ba785939d24ffac664fa2d8bbb8d3a728bc";
    }

    public static String getParam(String data) throws UnsupportedEncodingException {
        String i = "uh5BqvAmkf9Ekwp8";
        String g = "0CoJUm6Qyw8W8jud";
        String first = encParams(data, g);
        String second = encParams(first, i);
        return second;
    }

    public static String to_16(String data) {
        int pad = 16 - data.length() % 16;
        data += (char) (pad) * pad;
        return data;
    }

    public static String encParams(String data, String key) throws UnsupportedEncodingException {
        String iv = "0102030405060708";
        data = to_16(data);
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
        byte[] bs = aes.encrypt(data.getBytes("utf-8"));
        return "";
    }
}







