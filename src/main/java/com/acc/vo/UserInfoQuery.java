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

public class UserInfoQuery extends BaseQuery implements Serializable {
	private static final long serialVersionUID = 3148176768559230877L;
	/** id */
	private int id;
	/** userName */
	private String userName;
	/** userPassword */
	private String userPassword;
	/** createDate */
	private Date createDateBegin;
	private Date createDateEnd;
	private String createDateBeginString;
	private String createDateEndString;
	/** status */
	private String status;
	/** createrId */
	private int createrId;
	/** modifyDate */
	private Date modifyDateBegin;
	private Date modifyDateEnd;
	/** modifierId */
	private int modifierId;
	/** roleId */
	private String roleId;
	/** managedepart */
	private String manageDepart;
	/** departclass */
	private String departclass;
	/** userRealname */
	private String userRealname;
	
	private String selState;//1:今天应联系  2：我的客户 3:7天未联系 4:15天未联系 5:30天未联系
	private String nextTimeString;
	private int ownerId;
	private int customerId;
	private String type;
	private String fullName;
	private String sortColumns;
	private String province;
	private String channel;
	private String corsource;
	private String telephone;
	private String linkmanName;
	private String level;
	private String startCustomer;
	private String endCustomer;
	private String businessName;
	private String userInfo;
	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int value) {
		this.id = value;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public void setUserName(String value) {
		this.userName = value;
	}
	
	public String getUserPassword() {
		return this.userPassword;
	}
	
	public void setUserPassword(String value) {
		this.userPassword = value;
	}
	
	public Date getCreateDateBegin() {
		return this.createDateBegin;
	}
	
	public void setCreateDateBegin(Date value) {
		this.createDateBegin = value;
	}	
	
	public Date getCreateDateEnd() {
		return this.createDateEnd;
	}
	
	public void setCreateDateEnd(Date value) {
		this.createDateEnd = value;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public int getCreaterId() {
		return this.createrId;
	}
	
	public void setCreaterId(int value) {
		this.createrId = value;
	}
	
	public Date getModifyDateBegin() {
		return this.modifyDateBegin;
	}
	
	public void setModifyDateBegin(Date value) {
		this.modifyDateBegin = value;
	}	
	
	public Date getModifyDateEnd() {
		return this.modifyDateEnd;
	}
	
	public void setModifyDateEnd(Date value) {
		this.modifyDateEnd = value;
	}
	
	public int getModifierId() {
		return this.modifierId;
	}
	
	public void setModifierId(int value) {
		this.modifierId = value;
	}
	
	public String getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(String value) {
		this.roleId = value;
	}
	
	public String getManageDepart() {
		return manageDepart;
	}

	public void setManageDepart(String manageDepart) {
		this.manageDepart = manageDepart;
	}

	public String getDepartClass() {
		return this.departclass;
	}
	
	public void setDepartclass(String value) {
		this.departclass = value;
	}
	
	public String getUserRealname() {
		return this.userRealname;
	}
	
	public void setUserRealname(String value) {
		this.userRealname = value;
	}

	public String getSelState() {
		return selState;
	}

	public void setSelState(String selState) {
		this.selState = selState;
	}

	public String getCreateDateBeginString() {
		return createDateBeginString;
	}

	public void setCreateDateBeginString(String createDateBeginString) {
		this.createDateBeginString = createDateBeginString;
	}

	public String getCreateDateEndString() {
		return createDateEndString;
	}

	public void setCreateDateEndString(String createDateEndString) {
		this.createDateEndString = createDateEndString;
	}

	public String getNextTimeString() {
		return nextTimeString;
	}

	public void setNextTimeString(String nextTimeString) {
		this.nextTimeString = nextTimeString;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCorsource() {
		return corsource;
	}

	public void setCorsource(String corsource) {
		this.corsource = corsource;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStartCustomer() {
		return startCustomer;
	}

	public void setStartCustomer(String startCustomer) {
		this.startCustomer = startCustomer;
	}

	public String getEndCustomer() {
		return endCustomer;
	}

	public void setEndCustomer(String endCustomer) {
		this.endCustomer = endCustomer;
	}

	public String getDepartclass() {
		return departclass;
	}
	
}
