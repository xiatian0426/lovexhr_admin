package com.acc.service;

import com.acc.model.BxThumbUp;

public interface IBxThumbUpService extends IBaseService<BxThumbUp>{

    void deleteById(int id) throws Exception;

}
