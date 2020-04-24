package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxQuestion;
import org.apache.ibatis.annotations.Param;

public interface BxQuestionMapper {

    void deleteById(@Param("id") String id) throws Exception;

    BxQuestion getQuestionById(@Param("id") String id) throws SelectException;

    void insert(BxQuestion bxQuestion) throws Exception;

    void updateById(BxQuestion bxQuestion) throws Exception;
}
