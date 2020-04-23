package com.acc.dao;

import com.acc.exception.SelectException;
import com.acc.model.BxProVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BxProVideoMapper extends BaseMapper<BxProVideo>{

    List<BxProVideo> getProVideoList(@Param("memberId") String memberId) throws SelectException;

    void deleteById(@Param("id") String id) throws Exception;

    BxProVideo getProVideoById(String id) throws SelectException;

    void updateById(BxProVideo BxProVideo) throws Exception;

    BxProVideo getProVideoById(int id) throws SelectException;

    BxProVideo getProVideoBymemberId(@Param("memberId") int memberId) throws SelectException;
}
