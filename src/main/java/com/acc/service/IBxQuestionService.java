package com.acc.service;

import com.acc.exception.SelectException;
import com.acc.model.BxQuestion;

public interface IBxQuestionService extends IBaseService<BxQuestion> {

    void deleteById(String id) throws Exception;

    BxQuestion getQuestionById(String id) throws SelectException;

}
