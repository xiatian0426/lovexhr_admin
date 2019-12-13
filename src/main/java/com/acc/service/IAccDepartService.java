package com.acc.service;

import java.util.List;

import com.acc.exception.SelectException;
import com.acc.model.AccDepart;
import com.acc.vo.AccDepartVo;

public interface IAccDepartService {
	/**
	 * 获取全部部门树结构
	 * @return
	 * @throws SelectException
	 */
	List<AccDepartVo> getDepartTreeAll () throws Exception;
	/**
	 * 获取全部部门
	 * @return
	 * @throws SelectException
	 */
	List<AccDepart> getDepartAll () throws Exception;
}
