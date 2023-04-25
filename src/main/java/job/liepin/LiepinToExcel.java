package job.liepin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import job.liepin.dto.LiepinDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author HeJie
 * @Date 2023/2/7
 * @Time 20:10
 */
public class LiepinToExcel {
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
        List<LiepinDto> list = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            String sql = "select * from liepin_json";
            preparedStatement = connection.prepareStatement(sql);//这里已经传入SQL语句
            //设置参数
            //执行CURD
            resultSet = preparedStatement.executeQuery();// 这里不需要再传入SQL语句
            list = new ArrayList<>();
            while (resultSet.next()) {
                LiepinDto dto = new LiepinDto();
                Integer id = resultSet.getInt("id");
                String companyName = resultSet.getString("company_name");
                String title = resultSet.getString("title");
                String dq = resultSet.getString("dq");
                String salary = resultSet.getString("salary");
                String recruiterName = resultSet.getString("recruiter_name");
                String recruiterTitle = resultSet.getString("recruiter_title");
                String stateDetail = resultSet.getString("state_detail");
                String jobIntroContent = resultSet.getString("job_intro_content");
                String jobDetailArea = resultSet.getString("job_detail_area");
                String requireWorkYears = resultSet.getString("require_work_years");
                String requireEduLevel = resultSet.getString("require_edu_level");
                Date gmtCreate = resultSet.getTimestamp("gmt_create");
                Date gmtModified = resultSet.getTimestamp("gmt_modified");
                Integer isDeliver = resultSet.getInt("is_deliver");
                dto.setId(id);
                dto.setCompanyName(companyName);
                dto.setTitle(title);
                dto.setDq(dq);
                dto.setSalary(salary);
                dto.setRecruiterName(recruiterName);
                dto.setRecruiterTitle(recruiterTitle);
                dto.setStateDetail(stateDetail);
                dto.setJobIntroContent(jobIntroContent);
                dto.setJobDetailArea(jobDetailArea);
                dto.setRequireWorkYears(requireWorkYears);
                dto.setRequireEduLevel(requireEduLevel);
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
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\hj\\Desktop\\Liepin_Java0208.xlsx");
        ArrayList rows = CollUtil.newArrayList(list);
        writer.write(rows, true);
        writer.close();
    }
}
