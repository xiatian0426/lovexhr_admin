package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.vo.BaseQuery;
import com.acc.vo.Page;
import com.acc.vo.UserInfoQuery;

public interface IBaseService<T> {

	/**
	 * 
	 * @param query
	 * @return
	 * @throws SelectException
	 */
	Page<T> selectPage (BaseQuery query) throws SelectException;
	
	/**
	 * 
	 * @param query
	 * @return
	 * @throws SelectException
	 */
	Page<T> selectPageMore (UserInfoQuery query) throws SelectException;
	Page<T> selectPageMore2 (UserInfoQuery query) throws SelectException;
}
