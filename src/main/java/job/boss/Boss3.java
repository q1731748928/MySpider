package job.boss;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author HeJie
 * @Date 2023/1/10
 * @Time 10:27
 */
public class Boss3 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ArrayList<JobDetail> jobDetails = new ArrayList<>();
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\PythonDemo\\chromedriver.exe");
        ChromeOptions option = new ChromeOptions();
        option.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        ChromeDriver driver = new ChromeDriver(option);
        String query = "Java";
        String cityCode = "101210100";
        Long start = System.currentTimeMillis();
        for (int i = 1; i <= 10; i++) {
            driver.get("https://www.zhipin.com/web/geek/job?query=" + query + "+&city=" + cityCode + "&page=" + i);
            Thread.sleep(10000);
            String pageSource = driver.getPageSource();
            Document parse = Jsoup.parse(pageSource);
            Elements select = parse.select("ul.job-list-box>li");
            for (int j = 0; j < select.size(); j++) {
                JobDetail jobDetail = new JobDetail();
                Element element = select.get(j);
                Elements jobCardBody = element.select("div.job-card-body");
                Elements jobCardBodyA = jobCardBody.select("div.job-card-body>a");

                Elements jobTitle = jobCardBodyA.select("div.job-title");
                String jobName = jobTitle.select("span.job-name").text();
                String jobArea = jobTitle.select("span.job-area").text();
//            System.out.println("岗位名称 = " + jobName);
                jobDetail.setJobName(jobName);
//            System.out.println("上班地点 = " + jobArea);
                jobDetail.setJobArea(jobArea);
                Elements jobInfo = jobCardBody.select("div.job-info.clearfix");
                String salary = jobInfo.select("span.salary").text();
//            System.out.println("薪资 = " + salary);
                jobDetail.setSalary(salary);
                Elements tagList = jobInfo.select("ul.tag-list");
                Elements li = tagList.select("li");
                String text1 = li.text();
                jobDetail.setJobRequire(text1);
                for (Element element1 : li) {
                    String text = element1.text();
//                System.out.println("岗位要求 = " + text);
                }
                Elements infoPublic = jobInfo.select("div.info-public");
                String em = infoPublic.select("em").text();
//            System.out.println("招聘人职位 = " + em);
                jobDetail.setEm(em);
                Elements jobCardRight = jobCardBody.select("div.job-card-right");
                Elements companyLogoImg = jobCardRight.select("div.company-logo>a>img");
                String logo = companyLogoImg.attr("src");
//            System.out.println("公司logo = " + logo);
                jobDetail.setCompanyLogo(logo);
                String companyName = jobCardRight.select("div.company-info>h3.company-name>a").text();
//            System.out.println("公司名称 = " + companyName);
                jobDetail.setCompanyName(companyName);
                Elements companyTagList = jobCardRight.select("div.company-info>ul.company-tag-list");
                Elements companyTagLi = companyTagList.select("li");
                String text2 = companyTagLi.text();
                jobDetail.setCompanyTagLi(text2);
                for (Element element1 : companyTagLi) {
                    String text = element1.text();
//                System.out.println("公司概览 = " + text);
                }
                Elements jobCardFooter = element.select("div.job-card-footer");
                Elements tagListFooter = jobCardFooter.select("ul.tag-list");
                Elements tagListFooterLi = tagListFooter.select("li");
                String text3 = tagListFooterLi.text();
                jobDetail.setTagListFooterLi(text3);
                for (Element element1 : tagListFooterLi) {
                    String text = element1.text();
//                System.out.println("技术栈 = " + text);
                }
                String infoDesc = element.select("div.info-desc").text();
//            System.out.println("福利待遇 = " + infoDesc);
                jobDetail.setInfoDesc(infoDesc);

                String href = jobCardBodyA.attr("href");
                String url = "https://www.zhipin.com" + href;
                driver.get(url);
                Thread.sleep(5000);
                pageSource = driver.getPageSource();
                parse = Jsoup.parse(pageSource);
                String jobSecText = parse.select("div.job-detail-section>div.job-sec-text").text();
                jobDetail.setCompanyDetail(url);
                jobDetail.setJobSecText(jobSecText);
                jobDetails.add(jobDetail);
                System.out.println("当前是第" + i + "页的数据的第" + (j + 1) + "条数据");
//            System.out.println("\n\n");
            }

            /*for (JobDetail jobDetail : jobDetails) {
            }*/
            ExcelWriter writer = ExcelUtil.getWriter("d:/Boss直聘-Java.xlsx");
            ArrayList head = CollUtil.newArrayList("岗位名称", "上班地点", "薪资", "公司详情主页", "岗位要求", "招聘人职位", "公司名称", "公司概览", "技术栈", "福利待遇", "岗位介绍", "公司logo");
            ArrayList rows = CollUtil.newArrayList(jobDetails);
            writer.writeHeadRow(head);
            writer.write(rows, true);
            writer.close();
        }
        Long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000 / 60 + "分钟");
    }
}
