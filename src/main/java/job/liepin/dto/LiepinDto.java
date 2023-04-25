package job.liepin.dto;

import cn.hutool.core.date.DateTime;
import lombok.Data;

/**
 * @Author HeJie
 * @Date 2023/2/7
 * @Time 20:15
 */
@Data
public class LiepinDto {
    private Integer id;
    private String companyName;
    private String title;
    private String dq;
    private String salary;
    private String recruiterName;
    private String recruiterTitle;
    private String stateDetail;
    private String jobIntroContent;
    private String jobDetailArea;
    private String requireWorkYears;
    private String requireEduLevel;
    private DateTime gmtCreate;
    private DateTime gmtModified;
    private Integer isDeliver;
}
