package job.zhilian;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import job.zhilian.dto.ZhilianDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author HeJie
 * @Date 2023/2/8
 * @Time 17:06
 */
public class ZhilianToExcel {
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
        List<ZhilianDto> list = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from zhilian";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            list = new ArrayList<>();
            while (resultSet.next()) {
                ZhilianDto dto = new ZhilianDto();
                int id = resultSet.getInt("id");
                String jobName = resultSet.getString("job_name");
                String companyName = resultSet.getString("company_name");
                String salary = resultSet.getString("salary");
                String jobDescDemand = resultSet.getString("job_desc_demand");
                String compDesc = resultSet.getString("comp_desc");
                String welfare = resultSet.getString("welfare");
                String status = resultSet.getString("status");
                String companyDetail = resultSet.getString("company_detail");
                String summaryPlaneLeft = resultSet.getString("summary_plane_left");
                String describtion = resultSet.getString("describtion");
                String highlights = resultSet.getString("highlights");
                String jobAddressContent = resultSet.getString("job_address_content");
                Date gmtCreate = resultSet.getTimestamp("gmt_create");
                Date gmtModified = resultSet.getTimestamp("gmt_modified");
                Integer isDeliver = resultSet.getInt("is_deliver");
                dto.setId(id);
                dto.setJobName(jobName);
                dto.setCompanyName(companyName);
                dto.setSalary(salary);
                dto.setJobDescDemand(jobDescDemand);
                dto.setCompDesc(compDesc);
                dto.setWelfare(welfare);
                dto.setStatus(status);
                dto.setCompanyDetail(companyDetail);
                dto.setSummaryPlaneLeft(summaryPlaneLeft);
                dto.setDescribtion(describtion);
                dto.setHighlights(highlights);
                dto.setJobAddressContent(jobAddressContent);
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
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\hj\\Desktop\\Zhilian_Java0208.xlsx");
        ArrayList rows = CollUtil.newArrayList(list);
        writer.write(rows, true);
        writer.close();
    }
}
