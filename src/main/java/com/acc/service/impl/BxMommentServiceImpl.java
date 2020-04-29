package com.acc.service.impl;

import com.acc.dao.BxMommentMapper;
import com.acc.model.BxMomment;
import com.acc.service.IBxMommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bxMommentService")
@Transactional
public class BxMommentServiceImpl extends BaseServiceImpl<BxMomment> implements IBxMommentService {

	private static Logger _logger = LoggerFactory.getLogger(BxMommentServiceImpl.class);
    @Autowired
    private BxMommentMapper bxMommentMapper;

    @Override
    public void updateById(BxMomment bxMomment) throws Exception{
        bxMommentMapper.updateById(bxMomment);
    }

}
