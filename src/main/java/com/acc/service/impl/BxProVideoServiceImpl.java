package com.acc.service.impl;

import com.acc.dao.BxProVideoMapper;
import com.acc.exception.SelectException;
import com.acc.model.BxProVideo;
import com.acc.service.IBxProVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bxProVideoService")
@Transactional
public class BxProVideoServiceImpl extends BaseServiceImpl<BxProVideo> implements IBxProVideoService {

	private static Logger _logger = LoggerFactory.getLogger(BxProVideoServiceImpl.class);
    @Autowired
    private BxProVideoMapper BxProVideoMapper;

    @Override
    public List<BxProVideo> getProVideoList(String memberId) throws SelectException {
        return BxProVideoMapper.getProVideoList(memberId);
    }

    @Override
    public BxProVideo getProVideoById(String id) throws SelectException {
        return BxProVideoMapper.getProVideoById(id);
    }

    @Override
    public void deleteById(String id) throws Exception {
        BxProVideoMapper.deleteById(id);
    }

    @Override
    public void insert(BxProVideo BxProVideo) throws Exception {
        BxProVideoMapper.insert(BxProVideo);
    }

    @Override
    public void updateById(BxProVideo BxProVideo) throws Exception {
        BxProVideoMapper.updateById(BxProVideo);
    }

    @Override
    public BxProVideo getProVideoById(int id) throws SelectException{
        return BxProVideoMapper.getProVideoById(id);
    }

    @Override
    public BxProVideo getProVideoBymemberId(int memberId) throws SelectException{
        return BxProVideoMapper.getProVideoBymemberId(memberId);
    }
}
