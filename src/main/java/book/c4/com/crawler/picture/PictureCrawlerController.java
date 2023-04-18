package book.c4.com.crawler.picture;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.apache.http.message.BasicHeader;

import java.net.URLEncoder;
import java.util.HashSet;
public class PictureCrawlerController {
	public static void main(String[] args) throws Exception {
		//爬虫状态存储文件夹
		String crawlStorageFolder = "D:\\image";
		String storageFolder = "D:\\picture\\";
		int numberOfCrawlers = 5;  //线程数
		CrawlConfig config = new CrawlConfig();
		config.setMaxDepthOfCrawling(3);   //只采集第三层页面
		config.setFollowRedirects(false);  //是否允许重定向
		config.setCrawlStorageFolder(crawlStorageFolder);
		//设置头信息
		HashSet<BasicHeader> collections = new HashSet<BasicHeader>();
		collections.add(new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36"));
		collections.add(new BasicHeader("Accept","text/html,application/xhtml+xml,"
				+ "application/xml;"
				+ "q=0.9,image/webp,image/apng,*/*;q=0.8"));
		collections.add(new BasicHeader("Accept-Encoding", "gzip, deflate"));
		collections.add(new BasicHeader("Accept-Language", "zh-CN,zh;q=0.9"));
		collections.add(new BasicHeader("Connection", "keep-alive"));
		collections.add(new BasicHeader("cookie","__cf_bm=WDNKddmkXYp0S45kHq7t6oCBYgSolMZ7dqw_peVDvkg-1670850900-0-ATJsfAJe992i3iHKiAISGfBj8h/gAmFQpaw3N+wSOKi/ee8HYAIQ3cgmVWnu7ptyzPXODhVyHTmolN5O+THk4c8=; _ga=GA1.2.1951526550.1670850901; _gid=GA1.2.1367056845.1670850901; _hjFirstSeen=1; _hjIncludedInSessionSample=0; _hjSession_920442=eyJpZCI6Ijk2ZGNhZGRmLTZiZDQtNGI3MC05OTQ2LTc2Mjg0ZDM5ZmY5MCIsImNyZWF0ZWQiOjE2NzA4NTA5MDIzMjksImluU2FtcGxlIjpmYWxzZX0=; _hjAbsoluteSessionInProgress=0; is_human=1; _hjSessionUser_920442=eyJpZCI6IjdlZTM1YjRiLWZiNTgtNWViNi1hNjM4LTE3NjNkNGJkN2JjOCIsImNyZWF0ZWQiOjE2NzA4NTA5MDIyODcsImV4aXN0aW5nIjp0cnVlfQ==; anonymous_user_id=97926bc4c6c64d0a8c4349289931cf63; csrftoken=eLmZPZifp1DqKwVPK6Lx4bVZ96CHYSYqXhbR1bimNBYOokpcNPrjuEkxDqD2MU8I; lang=zh; client_width=1519; OptanonConsent=isGpcEnabled=0&datestamp=Mon+Dec+12+2022+21%3A19%3A47+GMT%2B0800+(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)&version=6.31.0&isIABGlobal=false&hosts=&consentId=0125c8c3-ca88-47ca-b520-28eb85d8127a&interactionCount=0&landingPath=NotLandingPage&groups=C0001%3A1%2CC0002%3A1%2CC0003%3A1%2CC0004%3A1&AwaitingReconsent=false"));
		config.setDefaultHeaders(collections);
		config.setPolitenessDelay(2000); //礼貌采集
		//是否运行采集二进制文件
		config.setIncludeBinaryContentInCrawling(true); 
		config.setSocketTimeout(10000); //超时设置
		config.setConnectionTimeout(10000); //超时设置
		// 配置信息
		PageFetcher pageFetcher = new PageFetcher(config);
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); //robots协议
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
		CrawlController controller = new CrawlController(config,
				pageFetcher, robotstxtServer);
		//添加种子URL
		controller.addSeed("https://pixabay.com/zh/images/search/"
				+ URLEncoder.encode("深林", "utf-8") + "/");
		controller.addSeed("https://pixabay.com/zh/images/search/" 
				+ URLEncoder.encode("汽车", "utf-8") + "/");
		controller.addSeed("https://pixabay.com/zh/images/search/" 
				+ URLEncoder.encode("动物", "utf-8") + "/");
		controller.addSeed("https://pixabay.com/zh/images/search/" 
				+ URLEncoder.encode("文字", "utf-8") + "/");
		PictureCrawler.configure(storageFolder); // 配置存储位置
		//运行网络爬虫
		controller.start(PictureCrawler.class, numberOfCrawlers);
	}
}