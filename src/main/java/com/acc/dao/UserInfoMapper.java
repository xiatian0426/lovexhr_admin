package com.acc.dao;

import java.util.List;
import java.util.Map;

import com.acc.model.BxMember;
import org.apache.ibatis.annotations.Param;

import com.acc.exception.SelectException;
import com.acc.exception.UpdateException;

public interface UserInfoMapper  extends BaseMapper<BxMember>{

    BxMember getById(@Param("id") int id) throws SelectException;

    BxMember getByUserName(@Param("userName") String userName) throws SelectException;
	
	List<BxMember> getAll() throws SelectException;
	List<BxMember> getAll2() throws SelectException;
	List<BxMember> getAll3() throws SelectException;

	void update(BxMember userInfo) throws UpdateException;
	List<BxMember> selectCustomerTotalWork(String currentDate) throws Exception;
	void updateUserStatus(@Param("id") int id, @Param("status") String status) throws UpdateException;
	List<BxMember> getAllByMap(Map<String, Object> map) throws SelectException;
}
