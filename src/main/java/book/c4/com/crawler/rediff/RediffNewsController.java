package book.c4.com.crawler.rediff;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class RediffNewsController {

	public static void main(String[] args) throws Exception {
		//爬虫状态存储文件夹
        String crawlStorageFolder = "F:/program_work/java_work"
        		+ "/CSDNCourse/Crawler4j/data/craw/root";
        int numberOfCrawlers = 5;  //线程数
        CrawlConfig config = new CrawlConfig();
        config.setMaxDepthOfCrawling(1);   //只采集第一层页面
        config.setMaxPagesToFetch(10);  //最多采集10个页面
        config.setFollowRedirects(false);  //是否允许重定向
        config.setCrawlStorageFolder(crawlStorageFolder);
       // 配置信息
        PageFetcher pageFetcher = new PageFetcher(config);
        //robots协议
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config,
        		pageFetcher, robotstxtServer);
        //添加种子URL
        controller.addSeed("https://www.rediff.com");
        //运行网络爬虫
        controller.start(RediffCrawler.class, numberOfCrawlers);
    }
}