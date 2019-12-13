package com.acc.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;

import com.acc.exception.SelectException;
import com.acc.service.IBaseService;
import com.acc.vo.BaseQuery;
import com.acc.vo.Page;
import com.acc.vo.UserInfoQuery;

public class BaseServiceImpl<T> implements IBaseService<T> {
	
	@Resource
	private SqlSession session;
	private final String path = "com.acc.dao.";
	
	private String getMethodPath(String methodType){
		Class<?> clazz = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return path + clazz.getSimpleName() + "Mapper." + methodType;
	}
	@Override
	public Page<T> selectPage(BaseQuery query) throws SelectException {
		//获取当前条件下的总条数
		int pageCount = ((Long)session.selectOne(getMethodPath("pageCount"), query)).intValue();
		Page<T> page = new Page<T>(query.getPageSize(), pageCount, Integer.parseInt(query.getPageIndex()));
		//获取当前条件下的结果
		@SuppressWarnings("unchecked")
		List<T> selectList = (List<T>)session.selectList(getMethodPath("findPage"), query);
		page.setResult(selectList);
		return page;
	}
	
	@Override
	public Page<T> selectPageMore(UserInfoQuery query) throws SelectException {
		//获取当前条件下的总条数
		int pageCount = ((Long)session.selectOne(getMethodPath("pageCountMore"), query)).intValue();
		Page<T> page = new Page<T>(query.getPageSize(), pageCount);
		//获取当前条件下的结果
		@SuppressWarnings("unchecked")
		List<T> selectList = (List<T>)session.selectList(getMethodPath("findPageMore"), query);
		page.setResult(selectList);
		return page;
	}
	@Override
	public Page<T> selectPageMore2(UserInfoQuery query) throws SelectException {
		//获取当前条件下的总条数
		int pageCount = ((Long)session.selectOne(getMethodPath("pageCountMore2"), query)).intValue();
		Page<T> page = new Page<T>(query.getPageSize(), pageCount, Integer.parseInt(query.getPageIndex()));
		//获取当前条件下的结果
		@SuppressWarnings("unchecked")
		List<T> selectList = (List<T>)session.selectList(getMethodPath("findPageMore2"), query);
		page.setResult(selectList);
		return page;
	}
	
}
