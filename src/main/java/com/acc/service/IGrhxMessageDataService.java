package com.acc.service;

import java.util.List;
import java.util.Map;

import com.acc.exception.SelectException;
import com.acc.model.GrhxMessageData;

public interface IGrhxMessageDataService extends IBaseService<GrhxMessageData>{
	List<GrhxMessageData> getAll () throws SelectException;
	/**
	 * 保存信息类型
	 * @param grhxMessageType
	 * @throws Exception
	 */
	void insert (GrhxMessageData grhxMessageData) throws Exception;
	
	/**
	 * 修改信息类型
	 * @param grhxMessageType
	 * @throws Exception
	 */
	void update (GrhxMessageData grhxMessageData) throws Exception;
	/**
	 * 删除信息类型
	 * @param map
	 * @throws Exception
	 */
	void deleteById(Map<String, Object> map) throws Exception;
	/**
	 * 查询信息类型
	 * @param map
	 * @throws Exception
	 */
	GrhxMessageData getById(Map<String, Object> map) throws Exception;
	GrhxMessageData getByIdContent(Map<String, Object> map) throws Exception;
	
	List<GrhxMessageData> getByMessagetype (Map<String, Object> map) throws SelectException;
	List<GrhxMessageData> getByMessagetypeNum (Map<String, Object> map) throws SelectException;
	List<GrhxMessageData> getByMap(Map<String, Object> map) throws SelectException;
}
