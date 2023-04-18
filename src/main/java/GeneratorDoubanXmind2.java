import cn.hutool.system.SystemUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.xmind.core.*;
import utils.ReadTxtFileUtil;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author buhao
 * @version GeneratorDoubanXmind.java, v 0.1 2019-12-02 22:54 buhao
 */
public class GeneratorDoubanXmind2 {

    /**
     * 当前类路径
     */
    public static final String CLASS_PATH = GeneratorDoubanXmind2.class.getResource("/").getPath();
    /**
     * 文件分隔符
     */
    public static final String FILE_SEPARATOR = SystemUtil.getOsInfo().getFileSeparator();

    public static void main(String[] args) throws IOException, CoreException {
        // 创建思维导图的工作空间
        IWorkbookBuilder workbookBuilder = Core.getWorkbookBuilder();
        IWorkbook workbook = workbookBuilder.createWorkbook();
        // 获得默认sheet
        ISheet primarySheet = workbook.getPrimarySheet();
        // 获得根主题
        ITopic rootTopic = primarySheet.getRootTopic();
        // 读取目录
        String bookName = "MySQL中的索引";
        // 设置根主题的标题
        rootTopic.setTitleText(bookName);
        ITopic topic1 = null;
        ITopic topic1Other = null;
        ITopic topic2 = null;
        ITopic topic2Other = null;
        ITopic topic3 = null;
        ITopic topic3Other = null;
        ITopic topic4 = null;
        ITopic topic4Other = null;
        // 章节 topic 的列表
        ArrayList<ITopic> chapterTopics = Lists.newArrayList();
        String s = ReadTxtFileUtil.readTxtFile("D:\\个人爬虫\\spider\\src\\main\\java\\MySQL中的索引.txt");
        String[] split = s.split("\n");
        for (String s1 : split) {
            if (StringUtils.isNotBlank(s1)) {
                if (s1.startsWith("## ")) {
                    // 创建章节节点
                    topic1 = workbook.createTopic();
                    topic1.setTitleText(s1);
                    rootTopic.add(topic1);
                }
                if (s1.startsWith("### ")) {
                    // 创建章节节点
                    topic2 = workbook.createTopic();
                    topic2.setTitleText(s1);
                    topic1.add(topic2);
                }
                if (s1.startsWith("#### ")) {
                    // 创建章节节点
                    topic3 = workbook.createTopic();
                    topic3.setTitleText(s1);
                    topic2.add(topic3);
                }else {
                    topic1Other = workbook.createTopic();
                    topic1Other.setTitleText(s1);
                    rootTopic.add(topic1Other);
                }
            }

        }
        // 保存
        workbook.save("C:\\Users\\hj\\Desktop\\" + bookName + ".xmind");
    }
}
