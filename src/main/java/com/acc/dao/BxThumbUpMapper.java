package com.acc.dao;

import com.acc.model.BxThumbUp;
import org.apache.ibatis.annotations.Param;

public interface BxThumbUpMapper extends BaseMapper<BxThumbUp>{

    void deleteById(@Param("id") int id) throws Exception;

}
