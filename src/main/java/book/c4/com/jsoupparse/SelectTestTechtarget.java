package book.c4.com.jsoupparse;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SelectTestTechtarget {

	public static void main(String[] args) throws IOException {
		//获取URL对应的HTML内容
		Document doc = Jsoup.connect("https://searchcustomerexperience.techtarget.com/info/news").timeout(5000).get();
		//抽取网页中的新闻
		Elements elements = doc.select("li[class=infotype-news-item]");
		for (Element ele : elements) {
			//抽取新闻时间  <span class="news-mobile-date">November 01, 2019</span>
			String time = ele.select(".news-mobile-date").text();
			//抽取新闻标题  <h3 class="infotype-news-title"><a href="https://searchcustomerexperience.techtarget.com/news/252473339/Adobe-digital-experience-platform-adds-small-businesses-offerings">Adobe digital experience platform adds small businesses offerings</a></h3>
			String title = ele.select(".infotype-news-title").select("a").text();
			//抽取新闻链接
			String url = ele.select(".infotype-news-title").select("a").attr("href");
			//抽取新闻描述  <p class="infotype-news-description">Adobe is reaching out to small businesses with expansions to ...</p>
			String description = ele.select("p[class=infotype-news-description]").text();
			System.out.println("新闻标题为：" + title + "\t时间为：" + time + "\t新闻URL为：" + url + "\t新闻描述为：" + description);
		}
	}

}
