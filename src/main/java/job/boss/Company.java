package job.boss;

import lombok.Data;
import lombok.ToString;

/**
 * @Author HeJie
 * @Date 2023/1/10
 * @Time 10:24
 */
@Data
@ToString
public class Company {
    String companyName;//职位名称
    String companyType;//工作地区
    String benefits ;//发布时间
}
