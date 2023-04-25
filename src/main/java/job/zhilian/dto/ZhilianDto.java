package job.zhilian.dto;

import cn.hutool.core.date.DateTime;
import lombok.Data;

/**
 * @Author HeJie
 * @Date 2023/2/8
 * @Time 16:40
 */
@Data
public class ZhilianDto {
    private Integer id;
    private String jobName;
    private String companyName;
    private String salary;
    private String jobDescDemand;
    private String compDesc;
    private String welfare;
    private String status;
    private String companyDetail;
    private String summaryPlaneLeft;
    private String describtion;
    private String highlights;
    private String jobAddressContent;
    private DateTime gmtCreate;
    private DateTime gmtModified;
    private Integer isDeliver;

}
