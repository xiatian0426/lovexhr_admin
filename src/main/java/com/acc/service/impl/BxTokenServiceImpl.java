package com.acc.service.impl;

import com.acc.dao.BxTokenMapper;
import com.acc.dao.UserInfoMapper;
import com.acc.exception.SelectException;
import com.acc.model.BxToken;
import com.acc.service.IBxTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bxTokenService")
@Transactional
public class BxTokenServiceImpl implements IBxTokenService {

	private static Logger _logger = LoggerFactory.getLogger(BxTokenServiceImpl.class);
	@Autowired
	private BxTokenMapper bxTokenMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public BxToken getToken(Integer type) throws SelectException {
        return bxTokenMapper.getToken(type);
    }

    @Override
    public void updateToken(BxToken bxToken) throws Exception {
        bxTokenMapper.delete(bxToken.getType());
        bxTokenMapper.insert(bxToken);

    }

    @Override
    public void updateMemberWxaCodeById(String id,String wxaCode) throws Exception{
        userInfoMapper.updateMemberWxaCodeById(id,wxaCode);
    }

}
