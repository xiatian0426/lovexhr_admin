package com.acc.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.GrhxMessageDataMapper;
import com.acc.exception.SelectException;
import com.acc.model.GrhxMessageData;
import com.acc.service.IGrhxMessageDataService;

@Service("grhxMessageDataService")
@Transactional
public class GrhxMessageDataServiceImpl extends BaseServiceImpl<GrhxMessageData> implements IGrhxMessageDataService {

	private static Logger _logger = LoggerFactory.getLogger(GrhxMessageDataServiceImpl.class);
	@Autowired
	private GrhxMessageDataMapper grhxMessageDataMapper;
	
	@Override
	public List<GrhxMessageData> getAll() throws SelectException {
		List<GrhxMessageData> list = grhxMessageDataMapper.getAll();
		return list;
	}
	@Override
	public void insert(GrhxMessageData grhxMessageData) throws Exception {
		// TODO Auto-generated method stub
		grhxMessageDataMapper.insert(grhxMessageData);
		grhxMessageDataMapper.insertContent(grhxMessageData);
	}
	
	@Override
	public void update(GrhxMessageData grhxMessageData) throws Exception {
		// TODO Auto-generated method stub
		grhxMessageDataMapper.update(grhxMessageData);
		grhxMessageDataMapper.updateContent(grhxMessageData);
	}
	@Override
	public void deleteById(Map<String, Object> map) throws Exception{
		// TODO Auto-generated method stub
		grhxMessageDataMapper.delete(map);
	}
	@Override
	public GrhxMessageData getById(Map<String, Object> map) throws Exception{
		// TODO Auto-generated method stub
		return grhxMessageDataMapper.getById(map);
	}
	@Override
	public GrhxMessageData getByIdContent(Map<String, Object> map) throws Exception{
		// TODO Auto-generated method stub
		return grhxMessageDataMapper.getByIdContent(map);
	}
	
	@Override
	public List<GrhxMessageData> getByMessagetype (Map<String, Object> map) throws SelectException{
		// TODO Auto-generated method stub
		return grhxMessageDataMapper.getByMessagetype(map);
	}

	@Override
	public List<GrhxMessageData> getByMessagetypeNum (Map<String, Object> map) throws SelectException{
		// TODO Auto-generated method stub
		return grhxMessageDataMapper.getByMessagetypeNum(map);
	}

	@Override
	public List<GrhxMessageData> getByMap(Map<String, Object> map) throws SelectException {
		return grhxMessageDataMapper.getByMap(map);
	}
}
