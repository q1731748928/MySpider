package mashibing;

import org.json.JSONArray;
import org.json.JSONObject;
import utils.ReadTxtFileUtil;

/**
 * @Author HeJie
 * @Date 2023/1/9
 * @Time 10:11
 */
public class MashibingDownRe {
    public static void main(String[] args) {
        String s = ReadTxtFileUtil.readTxtFile("C:\\Users\\hj\\Desktop\\msb.txt");
        JSONObject jsonObject = new JSONObject(s);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray dataLibraryList = data.getJSONArray("dataLibraryList");
        for (int i = 0; i < dataLibraryList.length(); i++) {
            JSONObject jsonObject1 = dataLibraryList.getJSONObject(i);
            String accessoryUrl = jsonObject1.getString("accessoryUrl");
            String accessoryName = jsonObject1.getString("accessoryName");
            ReadTxtFileUtil.writeImageToDisk(ReadTxtFileUtil.getImageFromNetByUrl(accessoryUrl),"D:\\马士兵\\资料\\MAC高级架构师\\30MySQL性能调优与架构设计(直播)\\" + accessoryName);
        }
    }
}
