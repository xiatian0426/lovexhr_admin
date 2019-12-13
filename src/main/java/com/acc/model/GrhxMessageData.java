/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.acc.model;

import java.util.Date;

import com.acc.util.CalendarUtil;
import com.acc.vo.MessageDataQuery;


/**
 * @version 1.0
 * @since 1.0
 */


public class GrhxMessageData implements java.io.Serializable{
	
	private static final long serialVersionUID = -4943890200434457901L;
	//alias
	public static final String TABLE_ALIAS = "GrhxMessageData";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_TITLE = "title";
	public static final String ALIAS_CREATE_TIME = "createTime";
	public static final String ALIAS_DATE = "date";
	public static final String ALIAS_PROVINCE = "province";
	public static final String ALIAS_MESSAGETYPE = "messagetype";
	public static final String ALIAS_CONTENT = "content";
	public static final String ALIAS_WEBTYPE = "webtype";
	public static final String ALIAS_CREATE_ID = "createId";
	public static final String ALIAS_OPERATERID = "operaterId";
	public static final String ALIAS_OPERATETIME = "operateTime";

	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID
     */

	private Integer id;
    /**
     * title       db_column: TITLE
     */
	private String title;
    /**
     * createTime       db_column: CREATE_TIME
     */

	private Date createTime;
	private String createTimeString;
    /**
     * date       db_column: DATE
     */
	private Date date;
	private String dateString;
	/**
     * province       db_column: PROVINCE
     */
	private String province;
	/**
     * provinceName       db_column: provinceName
     */
	private String provincename;
	/**
     * messagetype       db_column: MESSAGETYPE
     */
	private String messagetype;
	/**
     * messagename       db_column: messagename
     */
	private String messagename;
	/**
     * content       db_column: CONTENT
     */
	private String content;
	/**
     * webtype       db_column: WEBTYPE
     */
	private String webtype;
	/**
     * createId       db_column: createId
     */
	private Integer createid;
	/**
     * userRealName       db_column: userRealName
     */
	private String userRealName;
	/**
     * operaterId       db_column: operaterId
     */
	private Integer operaterid;
	/**
     * operateTime       db_column: operateTime
     */
	private Date operatetime;
	/**
     * busType       db_column: busType
     */
	private String busType;

	private String frontmodule;

	private Integer isdelete;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateTimeString() {
		if(getCreateTime()!=null){
			return CalendarUtil.dateToString(getCreateTime(), "yyyy-MM-dd HH:mm:ss");
		}else{
			return "";
		}
	}
	public void setCreateTimeString(String createTimeString) {
		this.createTimeString = createTimeString;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDateString() {
		if(getDate()!=null){
			return CalendarUtil.dateToString(getDate(), "yyyy-MM-dd HH:mm:ss");
		}else{
			return "";
		}
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
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
	public String getWebtype() {
		return webtype;
	}
	public void setWebtype(String webtype) {
		this.webtype = webtype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCreateid() {
		return createid;
	}
	public void setCreateid(Integer createid) {
		this.createid = createid;
	}
	public Integer getOperaterid() {
		return operaterid;
	}
	public void setOperaterid(Integer operaterid) {
		this.operaterid = operaterid;
	}
	public Date getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}
	public String getProvincename() {
		return provincename;
	}
	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}
	public String getMessagename() {
		return messagename;
	}
	public void setMessagename(String messagename) {
		this.messagename = messagename;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getFrontmodule() {
		return frontmodule;
	}
	public void setFrontmodule(String frontmodule) {
		this.frontmodule = frontmodule;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
}

