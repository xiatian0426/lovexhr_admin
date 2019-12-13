/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.acc.vo;

import java.io.Serializable;

/**
 * @version 1.0
 * @since 1.0
 */

public class MessageDataQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = -4531206206911444935L;
	private String title;
	private String createTime;
	private String date;
	private String province;
	private String messagetype;
	private String content;
	private String webtype;
	private String sortColumns;
	private String messagetypes;
	private String frontmodule;
	private String isdelete;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWebtype() {
		return webtype;
	}

	public void setWebtype(String webtype) {
		this.webtype = webtype;
	}

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	public String getMessagetypes() {
		return messagetypes;
	}

	public void setMessagetypes(String messagetypes) {
		this.messagetypes = messagetypes;
	}

	public String getFrontmodule() {
		return frontmodule;
	}

	public void setFrontmodule(String frontmodule) {
		this.frontmodule = frontmodule;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	
}
