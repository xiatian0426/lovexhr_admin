package com.acc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.AccRoleMapper;
import com.acc.exception.SelectException;
import com.acc.model.AccRole;
import com.acc.service.IAccRoleService;

@Service("accRoleService")
@Transactional
public class AccRoleServiceImpl extends BaseServiceImpl<AccRole> implements IAccRoleService {

	private static Logger _logger = LoggerFactory.getLogger(AccRoleServiceImpl.class);
	@Autowired
	private AccRoleMapper accRoleMapper;
	
	@Override
	public List<AccRole> getLinkManRoleAll() throws SelectException {
		List<AccRole> list = accRoleMapper.getLinkManRoleAll();
		return list;
	}
	
	@Override
	public List<AccRole> getUserRoleAll() throws SelectException {
		List<AccRole> list = accRoleMapper.getUserRoleAll();
		return list;
	}
	
	@Override
	public List<AccRole> getBusinessRoleAll() throws SelectException {
		List<AccRole> list = accRoleMapper.getBusinessRoleAll();
		return list;
	}
	
	@Override
	public List<AccRole> getById (String id) throws SelectException{
		List<AccRole> list = accRoleMapper.getById(id);
		return list;
	}
	
	@Override
	public Map<String, AccRole> getLinkManRoleAllMap() throws SelectException {
		List<AccRole> list = getLinkManRoleAll();
		Map<String, AccRole> accRoleDictMap = new HashMap<String, AccRole>();
		for (AccRole corsource : list) {
			accRoleDictMap.put(corsource.getId()+"", corsource);
		}
		return accRoleDictMap;
	}
	
	@Override
	public Map<String, AccRole> getBusinessRoleAllMap() throws SelectException {
		List<AccRole> list = getAll();
		Map<String, AccRole> accRoleDictMap = new HashMap<String, AccRole>();
		for (AccRole corsource : list) {
			accRoleDictMap.put(corsource.getId()+"", corsource);
		}
		return accRoleDictMap;
	}
	
	@Override
	public List<AccRole> getAll() throws SelectException {
		List<AccRole> list = accRoleMapper.getAll();
		return list;
	}
}
