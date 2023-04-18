package job.boss;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import job.boss.dto.BossDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author HeJie
 * @Date 2023/2/7
 * @Time 20:59
 */
public class BossToExcel {
    public static void main(String[] args) {
        queryCompanyInfo();
    }

    public static void queryCompanyInfo() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/jobinfo?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "123456";
        // 查询名字中包含字母A的员工信息
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<BossDto> list = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from bosszhipin";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            list = new ArrayList<>();
            while (resultSet.next()) {
                BossDto dto = new BossDto();
                int id = resultSet.getInt("id");
                String jobName = resultSet.getString("job_name");
                String jobArea = resultSet.getString("job_area");
                String salary = resultSet.getString("salary");
                String companyName = resultSet.getString("company_name");
                String companyDetail = resultSet.getString("company_detail");
                String jobReq = resultSet.getString("job_req");
                String em = resultSet.getString("em");
                String bossDetail = resultSet.getString("boss_detail");
                String companyTagLi = resultSet.getString("company_tag_li");
                String tagListFooterLi = resultSet.getString("tag_list_footer_li");
                String infoDesc = resultSet.getString("info_desc");
                String jobSecText = resultSet.getString("job_sec_text");
                String companyLogo = resultSet.getString("company_logo");
                String locationAddress = resultSet.getString("location_address");
                Date gmtCreate = resultSet.getTimestamp("gmt_create");
                Date gmtModified = resultSet.getTimestamp("gmt_modified");
                Integer isDeliver = resultSet.getInt("is_deliver");
                dto.setId(id);
                dto.setJobName(jobName);
                dto.setJobArea(jobArea);
                dto.setSalary(salary);
                dto.setCompanyName(companyName);
                dto.setCompanyDetail(companyDetail);
                dto.setJobReq(jobReq);
                dto.setEm(em);
                dto.setBossDetail(bossDetail);
                dto.setCompanyTagLi(companyTagLi);
                dto.setTagListFooterLi(tagListFooterLi);
                dto.setInfoDesc(infoDesc);
                dto.setJobSecText(jobSecText);
                dto.setCompanyLogo(companyLogo);
                dto.setLocationAddress(locationAddress);
                dto.setGmtCreate(new DateTime(gmtCreate));
                dto.setGmtModified(new DateTime(gmtModified));
                dto.setIsDeliver(isDeliver);
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\hj\\Desktop\\BossZhipinToExcel0208.xlsx");
        ArrayList rows = CollUtil.newArrayList(list);
        writer.write(rows, true);
        writer.close();
    }
}
