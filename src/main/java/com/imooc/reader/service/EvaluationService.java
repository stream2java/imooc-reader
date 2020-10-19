package com.imooc.reader.service;

import com.imooc.reader.entity.Evaluation;

import java.util.List;

public interface EvaluationService {

    public List<Evaluation> selectById(Long BookId);

}
