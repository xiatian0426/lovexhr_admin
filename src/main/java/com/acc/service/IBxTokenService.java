package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxToken;

public interface IBxTokenService {

    BxToken getToken(Integer type) throws SelectException;

    void updateToken(BxToken bxToken) throws Exception;

    void updateMemberWxaCodeById(String id,String wxaCode) throws Exception;

}
