package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxRecruit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxRecruitMapper extends BaseMapper<BxRecruit>{

    List<BxRecruit> getRecruitList(@Param("memberId") String memberId) throws SelectException;

    Integer getRecruitCount(@Param("memberId") String memberId) throws SelectException;

    void deleteById(@Param("id") String id) throws Exception;

    BxRecruit getRecruitById(String id) throws SelectException;

    void updateById(BxRecruit bxRecruit) throws Exception;
}
