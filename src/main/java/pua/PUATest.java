package pua;

import utils.ReadTxtFileUtil;

import java.io.IOException;

/**
 * @Author HeJie
 * @Date 2023/1/28
 * @Time 13:40
 */
public class PUATest {
    public static void main(String[] args) throws IOException {
        String cookie = "PPA_CI=81cd45296eb897c54fbb5eba5d783612; cloudreve-session=MTY3NDg4NDQ4OXxOd3dBTkUxUlNWRlFVMUpGU0VKUU0xRTJOMGhhUWxKUFNFbENTVXRDTjBwRVNFOVVVbHBXVlZWTlVEVlRTRXhPUTBsUE1qZEtSMEU9fKeDfgJP-xir7iSFNnJREqhSo0XuTzA-CimEN09S_MYK";
        String url = "https://pan.pua333.cn/api/v3/share/list/y2sk%2F%E3%80%902023%E3%80%91%E5%B9%B4%E8%AF%BE%E7%A8%8B%EF%BC%88%E6%9B%B4%E6%96%B0%E4%B8%AD%EF%BC%89%2F1%E3%80%812%E3%80%813%E6%9C%88%E8%AF%BE%E7%A8%8B";
        String s = ReadTxtFileUtil.loadJson(url, cookie);
        System.out.println(s);
    }
}
