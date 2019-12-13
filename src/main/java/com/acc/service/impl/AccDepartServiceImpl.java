package com.acc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.dao.AccDepartMapper;
import com.acc.service.IAccDepartService;
import com.acc.vo.AccDepartVo;
import com.acc.model.AccDepart;

@Service("accDepartService")
@Transactional
public class AccDepartServiceImpl implements IAccDepartService {

	private static Logger _logger = LoggerFactory.getLogger(AccDepartServiceImpl.class);
	@Autowired
	private AccDepartMapper accDepartMapper;
	
	@Override
	public List<AccDepartVo> getDepartTreeAll() throws Exception {
		List<AccDepartVo> voList = new ArrayList<AccDepartVo>();
		List<AccDepart> list = accDepartMapper.getDepartParent();
		AccDepartVo vo = new AccDepartVo();
		for (int i = 0; i < list.size(); i++) {
			vo = new AccDepartVo();
			getAllTreeNodes(vo,list.get(i));
			voList.add(vo);
		}
		return voList;
	}
	public void getAllTreeNodes(AccDepartVo vo,AccDepart ad) throws Exception {
		vo.setId(ad.getDepId());
		vo.setName(ad.getItemname());
		vo.setClassId(ad.getDepId());
		Map<String, Object> seqMap = new HashMap<String, Object>(); 
		seqMap.put("depId", ad.getDepId());
		List<AccDepart> list = accDepartMapper.selectChildren(seqMap);
		List<AccDepartVo> childrenVoList = new ArrayList<AccDepartVo>();
		AccDepartVo childrenVo;
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				childrenVo = new AccDepartVo();
				childrenVo.setId(list.get(i).getDepId());
				childrenVo.setName(list.get(i).getItemname());
				childrenVo.setClassId(list.get(i).getDepId());
				childrenVoList.add(childrenVo);
				getAllTreeNodes(childrenVo,list.get(i));
			}
			vo.setChildren(childrenVoList);
		}
	}
	
	@Override
	public List<AccDepart> getDepartAll() throws Exception {
		return accDepartMapper.getDepartAll();
	}
}
