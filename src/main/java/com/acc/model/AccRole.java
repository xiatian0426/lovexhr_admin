/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.acc.model;

/**
 * @version 1.0
 * @since 1.0
 */


public class AccRole implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "AccRole";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ROLE_NAME = "roleName";
	public static final String ALIAS_STATE = "state";
	public static final String ALIAS_IS_ROLEMESSAGERIGHT = "roleMessageRight";
	public static final String ALIAS_IS_ROLEMESSAGERIGHTNAME = "roleMessageRightName";
	
	//date formats
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
    /**
     * id       db_column: ID 
     */	
	
	private Integer id;
    /**
     * roleName       db_column: ROLE_NAME 
     */	
	private String roleName;
    /**
     * state       db_column: STATE 
     */	
	private String state;
	/**
     * roleMessageRight       db_column: roleMessageRight 
     */	
	private String roleMessageRight;
	/**
     * roleMessageRightName       db_column: roleMessageRightName 
     */	
	private String roleMessageRightName;
	//columns END

	public AccRole(){
	}

	public AccRole(
		Integer id
	){
		this.id = id;
	}

	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setRoleName(String value) {
		this.roleName = value;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	public void setState(String value) {
		this.state = value;
	}
	
	public String getState() {
		return this.state;
	}

	public String getRoleMessageRight() {
		return roleMessageRight;
	}

	public void setRoleMessageRight(String roleMessageRight) {
		this.roleMessageRight = roleMessageRight;
	}

	public String getRoleMessageRightName() {
		return roleMessageRightName;
	}

	public void setRoleMessageRightName(String roleMessageRightName) {
		this.roleMessageRightName = roleMessageRightName;
	}

}

