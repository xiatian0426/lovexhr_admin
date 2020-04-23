package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxProVideo;

import java.util.List;

public interface IBxProVideoService  extends IBaseService<BxProVideo>{
    List<BxProVideo> getProVideoList(String memberId) throws SelectException;

    BxProVideo getProVideoById(String id) throws SelectException;

    void deleteById(String id) throws Exception;

    void insert(BxProVideo BxProVideo) throws Exception;

    void updateById(BxProVideo BxProVideo) throws Exception;

    BxProVideo getProVideoById(int id) throws SelectException;

    BxProVideo getProVideoBymemberId(int memberId) throws SelectException;
}
