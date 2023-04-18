package book.c4.com.crawler.rediff;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
public class RediffCrawler extends WebCrawler {
	//设置正则规则---以.htm为后缀
	private final static Pattern URLPattern = Pattern.compile(".*(\\.htm)$");
	/**
	 * 用于过滤URL，如这里必须时以https://www.rediff.com/开头
	 * 以及".shtml"结尾的URL才可以被访问
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		return URLPattern.matcher(href).matches()&&
				href.startsWith("https://www.rediff.com/");
	}

	/**
	 * 处理URL
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL(); // 获取url
		if (URLPattern.matcher(url).matches() 
				&& page.getParseData() instanceof HtmlParseData) {
			FileWriter writer = null;
			try {
				//data目录下面存储每篇文档的内容
				writer = new FileWriter("data/" 
						+page.getWebURL().getDocid() + ".txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			HtmlParseData htmlParseData = (HtmlParseData) page
					.getParseData();
			String html = htmlParseData.getHtml();
			try {
				writer.append("新闻的id为:" 
						+ page.getWebURL().getDocid()
						+ "\n链接为：" + page.getWebURL().getURL()
						+ "\n新闻的标题为:" + htmlParseData.getTitle() 
						+"\n");
				writer.append(html + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}