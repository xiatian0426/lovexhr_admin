package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.exception.UpdateException;
import com.acc.model.BxMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxMemberMapper {

    BxMember getMemberByWechat(@Param("wechat") String wechat) throws SelectException;

    BxMember getMemberById(@Param("id") int id) throws SelectException;

    void updateMemberById(BxMember bxMember) throws Exception;

    void insert(BxMember bxMember) throws Exception;

    void deleteMemberById(int id) throws Exception;

}
