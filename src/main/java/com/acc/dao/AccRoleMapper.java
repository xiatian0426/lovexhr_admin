package com.acc.dao;

import java.util.List;

import com.acc.exception.SelectException;
import com.acc.model.AccRole;

public interface AccRoleMapper {

	List<AccRole> getLinkManRoleAll() throws SelectException;
	List<AccRole> getBusinessRoleAll() throws SelectException;
	List<AccRole> getUserRoleAll() throws SelectException;
	List<AccRole> getById (String id) throws SelectException;
	List<AccRole> getAll() throws SelectException;
}
