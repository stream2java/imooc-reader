package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Evaluation;

import java.util.List;

public interface EvaluationService {

    public List<Evaluation> selectById(Long BookId);
    public List<Evaluation> selectAll();
    public Evaluation changeState(Long evaluationId,String state);

    /**
     * 分頁查詢書籍
     * @param page 頁號
     * @param rows 每頁數據
     * @return
     */
    public IPage<Evaluation> paging(Integer page,Integer rows);

}
