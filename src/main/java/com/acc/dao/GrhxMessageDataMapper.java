package com.acc.dao;

import java.util.List;
import java.util.Map;

import com.acc.exception.SelectException;
import com.acc.model.GrhxMessageData;

public interface GrhxMessageDataMapper {

	List<GrhxMessageData> getAll() throws SelectException;
	void insert(GrhxMessageData grhxMessageData) throws Exception;
	void insertContent(GrhxMessageData grhxMessageData) throws Exception;
	void update(GrhxMessageData grhxMessageData) throws Exception;
	void updateContent(GrhxMessageData grhxMessageData) throws Exception;
	void delete(Map<String, Object> map) throws Exception;
	void deleteContent(Map<String, Object> map) throws Exception;
	GrhxMessageData getById(Map<String, Object> map) throws Exception;
	GrhxMessageData getByIdContent(Map<String, Object> map) throws Exception;
	List<GrhxMessageData> getByMessagetype (Map<String, Object> map) throws SelectException;
	List<GrhxMessageData> getByMessagetypeNum (Map<String, Object> map) throws SelectException;
	List<GrhxMessageData> getByMap(Map<String, Object> map) throws SelectException;
}
