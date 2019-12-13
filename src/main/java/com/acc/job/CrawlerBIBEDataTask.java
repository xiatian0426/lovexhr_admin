package com.acc.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.acc.model.GrhxMessageData;
import com.acc.service.IGrhxMessageDataService;
import com.acc.util.CalendarUtil;

@Component("crawlerBIBEDataTask")
public class CrawlerBIBEDataTask {
	@Autowired
	private IGrhxMessageDataService  grhxMessageDataService;
	
	/*
	@Scheduled(cron = "0 30 11,18 * * ?")
	public void execute () {
		Map<String, String> provinceMap = null;
		try {
			System.out.println("获取数据开始");
			provinceMap = accProvinceService.getProvince();
			String currdate = CalendarUtil.getCurrentDate();
			//爬取数据 	njxm:拟建项目
			int nzjxm = 1;
			for (int i = 0; i < 20; i++) {
				nzjxm = getCrawlerDataBIBE(i+1,provinceMap,"njxm",currdate);
				System.out.println("njxm==="+nzjxm);
				if(nzjxm==0){
					break;
				}
			}
			
			System.out.println("获取数据结束");
		} catch (SelectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	private int getCrawlerDataBIBE(int pageNum,Map<String, String> provinceMap,String busType,String currdate){
		try {
			Entities.EscapeMode.base.getMap().clear();
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			String uu = "https://www.bibenet.com/project/1?p="+pageNum;
			Document doc = Jsoup.connect(uu)
					.header("Accept", "*/*")
					.header("Accept-Encoding", "gzip, deflate")
				.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
				.header("Referer", "https://www.baidu.com/")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
				.timeout(5000)
				.get();
			Elements eles = doc.getElementsByClass("striped-table").get(0).children().get(1).children();
			String url;
			String title;
			String province;
			String messageType = "";
			String date;
			Document docDetail;
			GrhxMessageData grhxMessageData;
			String pro;
			Map<String, Object> map;
			List<GrhxMessageData> list;
			for(Element ele :eles){
				try {
					grhxMessageData = new GrhxMessageData();
					url = ele.getElementsByTag("a").get(0).attr("href");
					title = ele.getElementsByTag("a").get(0).text();
					date = ele.getElementsByClass("title-color").get(4).text();
					if(date==null || !date.equals(currdate)){
						if(CalendarUtil.addDay(currdate, -1).equals(date)){
							return 0;
						}
						if(CalendarUtil.isAfter(currdate,date)){
							return 0;
						}
					}
					province = ele.getElementsByClass("title-color").get(1).text();
					if(title!=null && title.startsWith("重点项目")){
						messageType = "5";
					}else{
						messageType = "4";
					}
					grhxMessageData.setMessagetype(messageType);
					grhxMessageData.setBusType(busType);
					grhxMessageData.setTitle(title);
					grhxMessageData.setDate(sim.parse(date));
					pro = provinceMap.get(province);
					if(pro==null || "".equals(pro)){
						continue;
					}
					grhxMessageData.setProvince(pro);
					//验证该数据是否存在
					map = new HashMap<String, Object>();
					map.put("title", grhxMessageData.getTitle());
					map.put("province", grhxMessageData.getProvince());
					map.put("messagetype", grhxMessageData.getMessagetype());
					map.put("date", grhxMessageData.getDate());
					list = grhxMessageDataService.getByMap(map);
					if(list == null || list.size() == 0){
						grhxMessageData.setWebtype("4");
						grhxMessageData.setCreateTime(new Date());
						
						docDetail = Jsoup.connect("https://www.bibenet.com"+url)
								.header("Accept", "*/*")
								.header("Accept-Encoding", "gzip, deflate")
							.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
							.header("Referer", "https://www.baidu.com/")
							.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
							.timeout(5000)
							.get();
						//第一种格式
						Element con_info = docDetail.getElementsByClass("table-bordered").get(0);
						String detail = con_info.html();
						grhxMessageData.setContent(detail);
						if(grhxMessageData.getContent()!=null && !"".equals(grhxMessageData.getContent())){
							grhxMessageDataService.insert(grhxMessageData);
						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
	public static void main(String[] args) throws Exception {
		String url;
		int count = 0;
		String uu;
		String title;
		String province;
		String messageType = "";
		String date;
		Document docDetail;
		GrhxMessageData grhxMessageData;
		String pro;
		Map<String, Object> map;
		List<GrhxMessageData> list;
		for (int i = 1; i < 2; i++) {
			try {
				uu = "https://www.bibenet.com/project/1?p="+i;
				System.out.println(uu);
				Document doc = Jsoup.connect(uu).header("Accept", "*/*")
						.header("Accept-Encoding", "gzip, deflate")
					.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
					.header("Referer", "https://www.baidu.com/")
					.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
					.timeout(5000)
					.get();
				Elements eles = doc.getElementsByClass("striped-table").get(0).children().get(1).children();
				for(Element ele :eles){
					try {
						grhxMessageData = new GrhxMessageData();
						url = ele.getElementsByTag("a").get(0).attr("href");
						title = ele.getElementsByTag("a").get(0).text();
						date = ele.getElementsByClass("title-color").get(4).text();
						province = ele.getElementsByClass("title-color").get(1).text();
						messageType = "4";
						grhxMessageData.setMessagetype(messageType);
						grhxMessageData.setTitle(title);
						grhxMessageData.setWebtype("4");
						grhxMessageData.setCreateTime(new Date());
						docDetail = Jsoup.connect("https://www.bibenet.com"+url)
								.header("Accept-Encoding", "gzip, deflate")
								.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
								.header("Referer", "https://www.baidu.com/")
								.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
								.timeout(5000)
								.get();
						Element con_info = docDetail.getElementsByClass("table-bordered").get(0);
						String detail = con_info.html();
						grhxMessageData.setContent(detail);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				System.out.println("=================="+count);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
