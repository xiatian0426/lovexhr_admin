/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.acc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class AccDepart implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 1756258302889590552L;

	private String id;
	
	private String itemname;

	private String depId;
	
	private String isdelete;
	
	private String parentdepId;
	
	private List<AccDepart> children = new ArrayList<AccDepart>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getParentdepId() {
		return parentdepId;
	}

	public void setParentdepId(String parentdepId) {
		this.parentdepId = parentdepId;
	}

	public List<AccDepart> getChildren() {
		return children;
	}

	public void setChildren(List<AccDepart> children) {
		this.children = children;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

}

