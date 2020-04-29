package com.acc.service;

import com.acc.model.BxMomment;

public interface IBxMommentService extends IBaseService<BxMomment> {

    void updateById(BxMomment bxMomment) throws Exception;
}
