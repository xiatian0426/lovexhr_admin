package com.acc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acc.dao.UserInfoMapper;
import com.acc.model.BxMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.exception.InsertException;
import com.acc.exception.SelectException;
import com.acc.exception.UpdateException;
import com.acc.service.IUserInfoService;

@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl extends BaseServiceImpl<BxMember> implements IUserInfoService {

	private static Logger _logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public void insert(BxMember userInfo) throws InsertException {
		userInfoMapper.insert(userInfo);
	}
	
	@Override
	public BxMember getById(String userId) throws SelectException {
		try {
			int id = Integer.parseInt(userId);
			return userInfoMapper.getById(id);
		} catch (Exception ex) {
			_logger.error("[获取用户失败,无效的用户,ID="+userId+"]");
		}
		return null;
	}

	@Override
	public BxMember getByUserName(String userName) throws SelectException {
		try {
			return userInfoMapper.getByUserName(userName);
		} catch (Exception ex) {
			_logger.error("[获取用户失败,无效的用户,userName="+userName+"]");
		}
		return null;
	}

	@Override
	public List<BxMember> getAll() throws SelectException {
		List<BxMember> list = userInfoMapper.getAll();
		return list;
	}
	
	public List<BxMember> getAll2() throws SelectException {
		List<BxMember> list = userInfoMapper.getAll2();
		return list;
	}
	
	@Override
	public List<BxMember> getAll3() throws SelectException {
		List<BxMember> list = userInfoMapper.getAll3();
		return list;
	}
	
	@Override
	public Map<Integer, BxMember> getAllMap() throws SelectException {
		List<BxMember> list = getAll();
		Map<Integer, BxMember> userInfoDictMap = new HashMap<Integer, BxMember>();
		for (BxMember userInfo : list) {
			userInfoDictMap.put(userInfo.getId(), userInfo);
		}
		return userInfoDictMap;
	}
	
	@Override
	public Map<Integer, BxMember> getAllMap2() throws SelectException {
		List<BxMember> list = getAll2();
		Map<Integer, BxMember> userInfoDictMap = new HashMap<Integer, BxMember>();
		for (BxMember userInfo : list) {
			userInfoDictMap.put(userInfo.getId(), userInfo);
		}
		return userInfoDictMap;
	}

	@Override
	public void update(BxMember userInfo) throws UpdateException {
		userInfoMapper.update(userInfo);
	}

	@Override
	public void updateUserStatus(String userId, String status) throws UpdateException {
		int id = 0;
		try {
			id = Integer.parseInt(userId);
		} catch (Exception ex) {
			_logger.error("[无效的用户,ID="+userId+"]");
			throw new UpdateException();
		}
		userInfoMapper.updateUserStatus(id, status);
	}
	@Override
	public List<BxMember> getAllByMap (Map<String, Object> map) throws SelectException{
		List<BxMember> list = userInfoMapper.getAllByMap(map);
		return list;
	}
}
