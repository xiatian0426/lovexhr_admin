<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/pages.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>后台用户添加</title>
	<%@ include file="../common/jsp_contants.jsp"%>
	<%@ include file="../common/js_contants.jsp"%>
	<link rel="stylesheet" type="text/css" href="${cssRoot }/right_style.css" />
	<link rel="stylesheet" type="text/css" href="${cssRoot }/zTreeStyle.css" />
	<script type="text/javascript" src="${jsRoot }/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${jsRoot }/move_sel_tree.js"></script>
	<!-- 引入 Bootstrap -->
	<link href="${toolRoot}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${toolRoot}/bootstrap/css/bootstrap-self.css" rel="stylesheet">
	<script src="${toolRoot}/bootstrap/js/bootstrap.min.js"></script>
	<!-- 日历控件 -->
	<script type="text/javascript" src="${toolRoot }/My97DatePicker/WdatePicker.js"></script>
	<!-- ZTree控件 -->
	<script type="text/javascript" src="${jsRoot }/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${jsRoot }/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript" src="${jsRoot }/dtree.js"></script>
	<!-- 验证 -->
	<link rel="stylesheet" href="${toolRoot }/validata/validationEngine.css" />
	<script type="text/javascript" src="${toolRoot }/validata/jquery.validationEngine.js"></script>
	<script type="text/javascript" src="${toolRoot }/validata/jquery.validationEngine-zh_CN.js" charset="utf-8"></script>
	<!-- 引入 Bootstrap -->
	<link href="${toolRoot}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${toolRoot}/bootstrap/css/bootstrap-self.css" rel="stylesheet">
	<script src="${toolRoot}/bootstrap/js/bootstrap.min.js"></script>
	<script teye="text/javascript">
		var departItem;
		//栏目树初始化设置
		var setting1 = {
			check: {
				enable: true,
				chkboxType:{ "Y" : "ps", "N" : "ps" }
			},
			data:{
				simpleData: {
					enable: true,
					rootPId: '0'
				}
			},
			view: {
				showIcon: false,
				dblClickExpand: dblClickExpand
			},
			callback: {
				onCheck:onCheck
			}
		};
		$(function(){
			//开启表单验证
			$("#userForm").validationEngine();
			departItem = ${departItem};
			$.fn.zTree.init($("#departItem"), setting1, departItem);
		})
		//双击节点不展开
		function dblClickExpand(treeId, treeNode) {
			return treeNode.level = 0;
		};
		function onCheck(){
			//加载已选产品树
			var treeObj = $.fn.zTree.getZTreeObj("departItem");
			var nodes = treeObj.getCheckedNodes(true);
			var msg = "";
		    for (var i = 0; i < nodes.length; i++) {
		        msg += nodes[i].id+",";
		    }
		    $("#manageDepart").val(msg);
		}
		function save(){
			var roleId = $("#roleId").val();
			if(roleId=='0'){
				$("#roleId").validationEngine("showPrompt","请选择所属角色","error");
				$(this).focus();
				return false;
			}
		}
	</script>
</head>
<body style=" font-size: 13px;">
	<form action="/user/add" name="userForm" method="post" target="main" id="userForm" onsubmit="return save();">
		<input type="hidden" name="manageDepart" id="manageDepart" value="">
		<div class="clearB"></div>
		<div class="r_box" style="padding: 0; width: 777px;">
			<div class="adress" >
				当前位置：后台用户添加
			</div>
			<table  style=" font-size: 13px; " align=center>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">登录名称：</td>
					<td>
						<span>
							<input id="userName" name="userName" type="text" style="width: 172px;" class="validate[required,noSpecialCaracters] text-input self-form-control"/>
							<font color="red">*</font>
						</span>
					</td>
					<td style="background:#A0E0F7;padding: 10px 15px;">登录密码：</td>
					<td>
						<span>
							<input id="userPassword" name="userPassword" type="password" style="width: 172px;" class="validate[required,maxSize[12]] text-input self-form-control"/>
							<font color="red">*</font>
						</span>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">真实姓名：</td>
					<td>
						<span>
							<input id="userRealname" name="userRealname" type="text" style="width: 172px;" class="validate[required,noSpecialCaracters,,maxSize[12]] text-input self-form-control"/>
							<font color="red">*</font>
						</span>
					</td>
					<td style="background:#A0E0F7;padding: 10px 15px;">所属角色：</td>
					<td>
						<select class="select-nosearch" name='roleId' id="roleId" style="width: 172px;">
							<option value='0'>请选择</option>
							<c:forEach items="${roleList}" var="role" varStatus="status">
								<option value='${role.id}'>
									${role.roleName} 
								</option>
							</c:forEach>
						</select>
						<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td style="background:#A0E0F7;padding: 10px 15px;">所属部门：</td>
					<td>
						<select name="departClass" class="select-nosearch validate[required,noSpecialCaracters,,maxSize[12]]" style="width: 172px;">
							<c:forEach items="${departList}" var="depart" varStatus="status">
								<option value='${depart.depId}'>
									${depart.itemname} 
								</option>
							</c:forEach>
						</select>
					</td>
					<td style="background:#A0E0F7;padding: 10px 15px;">负责部门：</td>
					<td>
						<div class="move_sel_tree" style="height:150px; width: 172px; overflow:scroll;">
				            <ul id="departItem" class="ztree"></ul>
				        </div>
					</td>
				</tr>
			</table>
			<div class="sub_div">
				<input type="submit" class="sub_btn" value=" "/>
				<input type="reset" class="reset_btn" value=" " />
			</div>
		</div>
	</form>
</body>
</html>
