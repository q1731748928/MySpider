package book.c4.com.jsoupparse;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SelectTestMakro {

	public static void main(String[] args) throws IOException {
		//获取URL对应的HTML内容
		Document doc = Jsoup.connect("https://www.makro.co.za/search?q=%3Arelevance%3Acategory%3ABG%3Abrand%3ACapdase").timeout(5000).get();
		Elements elements = doc.select("input[class=js-gtmProductListItem]");
		for (Element ele : elements) {
			System.out.println(ele.attr("value"));
		}
		System.out.println(elements.size());
		Elements elements1 = doc.select("a[class=product-tile-inner__img]");
		System.out.println(elements1.size());
		for (Element ele : elements1) {
			System.out.println(ele.attr("href"));
		}
//		System.out.println(elements);
		Document doc1 = Jsoup.connect("https://www.makro.co.za/electronics-computers/cellphones/cell-phone-accessories/pouches/capdase-soft-jacket-vika-samsung-galaxy-s4-mini-cover-black-blue-/p/0b1f4e04-67e7-4302-8128-1c33e133c1c1").timeout(5000).get();
		String product_id = doc1.select("span[itemprop=productId]").text();
		String description = doc1.select("span[itemprop=description]").text();
		String model = doc1.select("span[itemprop=model]").text();
		System.out.println(product_id + "\t" + description + "\t" + model);
	}

}
