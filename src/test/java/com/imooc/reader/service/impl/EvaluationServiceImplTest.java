package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.service.EvaluationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EvaluationServiceImplTest {
    @Resource
    private EvaluationService evaluationService;

    @Test
    public void paging() {
        IPage<Evaluation> pageObject = evaluationService.paging(1, 10);
        List<Evaluation> records = pageObject.getRecords();
        for (Evaluation e:records
             ) {
            System.out.println(e.getEvaluationId()+e.getContent() );

        }

    }
}