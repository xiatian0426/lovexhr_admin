/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.acc.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class AccDepartVo implements java.io.Serializable, Cloneable{
	
	private static final long serialVersionUID = 3645336330874342555L;
	private String id;
	private String name;
	private String classId;
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	private List<AccDepartVo> children = new ArrayList<AccDepartVo>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AccDepartVo> getChildren() {
		return children;
	}

	public void setChildren(List<AccDepartVo> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public AccDepartVo clone() throws CloneNotSupportedException {
		AccDepartVo vo = new AccDepartVo();
		vo.setId(this.id);
		vo.setName(this.name);
		vo.setClassId(this.classId);
		List<AccDepartVo> child = new ArrayList<AccDepartVo>();
		for(AccDepartVo v: this.children){
			AccDepartVo clone = v.clone();
			child.add(clone);
		}
		vo.setChildren(child);
		return vo;
	}

}

