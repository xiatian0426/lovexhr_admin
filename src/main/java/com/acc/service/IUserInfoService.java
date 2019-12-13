package com.acc.service;

import java.util.List;
import java.util.Map;

import com.acc.exception.InsertException;
import com.acc.exception.SelectException;
import com.acc.exception.UpdateException;
import com.acc.model.BxMember;

public interface IUserInfoService extends IBaseService<BxMember> {

	/**
	 * 保存 用户
	 * @param userInfo
	 * @throws InsertException
	 * @author TANGCY
	 */
	void  insert (BxMember userInfo) throws InsertException;

	/**
	 * 获取全部用户信息
	 * @param userId
	 * @return
	 * @throws SelectException
	 */
	List<BxMember> getAll () throws SelectException;
	/**
	 * 获取启用的全部用户信息
	 * @param userId
	 * @return
	 * @throws SelectException
	 */
	List<BxMember> getAll3 () throws SelectException;
	/**
	 * 根据用户ID查询用户
	 * @param userId
	 * @return
	 * @throws SelectException
	 */
    BxMember getById (String userId) throws SelectException;

	/**
	 * 修改用户-根据ID
	 * @param userInfo
	 * @throws UpdateException
	 * @author TANGCY
	 * @since 2016年10月25日
	 */
	void update (BxMember userInfo) throws UpdateException;

	/**
	 * 根据用户名称查询用户
	 * @param userName
	 * @return
	 * @throws SelectException
	 */
    BxMember getByUserName (String userName) throws SelectException;

	public Map<Integer, BxMember> getAllMap() throws SelectException;
	public Map<Integer, BxMember> getAllMap2() throws SelectException;

	/**
	 * 修改用户状态
	 * @throws UpdateException
	 */
	void  updateUserStatus (String userId, String status)  throws UpdateException;
	/**
	 * 根据条件获取用户信息
	 * @param userId
	 * @return
	 * @throws SelectException
	 */
	List<BxMember> getAllByMap (Map<String, Object> map) throws SelectException;
}
