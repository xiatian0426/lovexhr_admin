package com.acc.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.acc.model.BxMember;
import com.acc.util.Constants;

/**
 * 操作权限判断 自定义标签
 * @author Bruce
 * @date 2012-7-24
 */
public class OptionAuthTag extends TagSupport {

	/*
	 * 操作id
	 */
	private String opId;
	/*
	 * 不包含权限时，显示内容
	 * 包含权限时，不显示内容
	 */
	private String notContain;
	
	
	public String getOpId() {
		return opId;
	}



	public void setOpId(String opId) {
		this.opId = opId;
	}



	public String getNotContain() {
		return notContain;
	}



	public void setNotContain(String notContain) {
		this.notContain = notContain;
	}



	@Override
	public int doStartTag() throws JspException {
        BxMember user = (BxMember)pageContext.getSession().getAttribute(Constants.LOGINUSER);
		int falt= SKIP_BODY;
		String roleId = user.getRoleId();
		String[] roleIds = opId.split(",");
		for (int i = 0; i < roleIds.length; i++) {
			if(roleIds[i].equals(roleId)){
				falt= SKIP_PAGE;	//显示标签体中的内容
				break;
			}else{
				falt= SKIP_BODY;	//忽略标签体中的内容
			}
		}
		return falt;
		
	}

}

