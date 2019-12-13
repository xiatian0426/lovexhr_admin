package com.acc.resolve;

import java.util.HashMap;
import java.util.Map;

public class ResolveConstants {

	@SuppressWarnings("serial")
	public static final Map<String, String> CUSTOMER_FIELD = new HashMap<String, String>(){{
		put("1", "fullName");// 客户全称
		put("2", "telephone");//客户电话
		put("3", "email");//电子邮件
		put("4", "province");//所在省份
		put("5", "url");//客户网站
		
		put("6", "address");//客户地址
		put("7", "corsource");//客户来源
		put("8", "channel");//所属行业
		put("9", "level");//客户等级
		put("10", "cclevel");//CC客户等级
		
		put("11", "nextaccessTimeString");//下次回访
		put("12", "remark");//备注信息
		put("13", "status");// 是否公海
	}};
	
	public static String getCustomerFieldName (String key) {
		return CUSTOMER_FIELD.get(key);
	}
	
	@SuppressWarnings("serial")
	public static final Map<String, String> CONTACT_FIELD = new HashMap<String, String>(){{
		put("1", "customerId");// 客户全称
		put("2", "linkmanName");// 姓名
		put("3", "linkmanEnName");// 英文名
		put("4", "sex");// 性别
		put("5", "department");// 所在部门
		put("6", "position");// 职位
		put("7", "responsiblearea");// 负责领域
		put("8", "telephone");// 电话号码
		put("9", "phone");// 手机号码
		put("10", "role");// 联系人角色
		put("11", "email");// 电子邮件
		put("12", "state");// 状态 写死 0：在线1:离线
		put("13", "remark");// 备注及其他
	}};
	
	public static String getContactFieldName (String key) {
		return CONTACT_FIELD.get(key);
	}
	
}
