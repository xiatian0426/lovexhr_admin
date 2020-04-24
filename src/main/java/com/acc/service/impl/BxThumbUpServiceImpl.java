package com.acc.service.impl;

import com.acc.dao.BxThumbUpMapper;
import com.acc.model.BxThumbUp;
import com.acc.service.IBxThumbUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bxThumbUpService")
@Transactional
public class BxThumbUpServiceImpl extends BaseServiceImpl<BxThumbUp> implements IBxThumbUpService {

	private static Logger _logger = LoggerFactory.getLogger(BxThumbUpServiceImpl.class);
    @Autowired
    private BxThumbUpMapper bxThumbUpMapper;

    @Override
    public void deleteById(int id) throws Exception {
        bxThumbUpMapper.deleteById(id);
    }
}
