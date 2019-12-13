/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.acc.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

public class ProprietorQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;
	private String company;
	private String linkman;
	private String tel;
	private String address;
	private String type;
	private Integer createrid;
	private Date createtime;
	private Integer operaterid;
	private Date operatetime;
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCreaterid() {
		return createrid;
	}
	public void setCreaterid(Integer createrid) {
		this.createrid = createrid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
	
}
