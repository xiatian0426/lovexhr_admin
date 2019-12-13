package com.acc.dao;

import java.util.List;
import java.util.Map;

import com.acc.exception.SelectException;
import com.acc.model.AccDepart;

public interface AccDepartMapper {

	List<AccDepart> getDepartAll() throws SelectException;
	List<AccDepart> getDepartParent() throws SelectException;
	List<AccDepart> selectChildren(Map<String, Object> seqMap ) throws SelectException;
}
