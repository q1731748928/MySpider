package job.boss.dto;

import cn.hutool.core.date.DateTime;
import lombok.Data;

/**
 * @Author HeJie
 * @Date 2023/2/7
 * @Time 20:55
 */
@Data
public class BossDto {
    private Integer id;
    private String jobName;
    private String jobArea;
    private String salary;
    private String companyName;
    private String companyDetail;
    private String jobReq;
    private String em;
    private String bossDetail;
    private String CompanyTagLi;
    private String tagListFooterLi;
    private String infoDesc;
    private String jobSecText;
    private String companyLogo;
    private String locationAddress;
    private DateTime gmtCreate;
    private DateTime gmtModified;
    private Integer isDeliver;

}
