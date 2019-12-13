package com.acc.service;

import java.util.List;
import java.util.Map;

import com.acc.exception.SelectException;
import com.acc.model.AccRole;

public interface IAccRoleService  extends IBaseService<AccRole>{
	/**
	 * 获取全部客户角色
	 * @return
	 * @throws SelectException
	 */
	List<AccRole> getLinkManRoleAll () throws SelectException;
	List<AccRole> getUserRoleAll() throws SelectException;
	/**
	 * 获取全部用户角色
	 * @return
	 * @throws SelectException
	 */
	List<AccRole> getBusinessRoleAll () throws SelectException;
	List<AccRole> getById (String id) throws SelectException;
	
	public Map<String, AccRole> getLinkManRoleAllMap() throws SelectException;
	
	public Map<String, AccRole> getBusinessRoleAllMap() throws SelectException;
	
	/**
	 * 获取全部角色
	 * @return
	 * @throws SelectException
	 */
	List<AccRole> getAll () throws SelectException;
}
