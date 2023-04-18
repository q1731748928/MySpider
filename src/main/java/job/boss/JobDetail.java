package job.boss;

import lombok.Data;

/**
 * @Author HeJie
 * @Date 2023/1/11
 * @Time 15:09
 */
@Data
public class JobDetail {
    String jobName; // 岗位名称
    String jobArea; // 上班地点
    String salary; // 薪资
    String companyDetail; // 公司详情主页
    String jobRequire; // 岗位要求
    String em; // 招聘人职位
    String companyName; // 公司名称
    String companyTagLi; // 公司概览
    String tagListFooterLi; // 技术栈
    String infoDesc; // 福利待遇
    String jobSecText; // 岗位介绍
    String companyLogo; // 公司logo
}
