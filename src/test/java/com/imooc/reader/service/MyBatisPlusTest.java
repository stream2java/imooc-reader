package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.entity.Test;
import com.imooc.reader.mapper.TestMapper;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MyBatisPlusTest {

    @Resource
    private TestMapper testMapper;

    @org.junit.Test
    public void testInsert(){
        Test test = new Test();
        test.setContent("MyBatisTest");
        testMapper.insert(test);
    }

    @org.junit.Test
    public void testDelete(){
        testMapper.deleteById(9);
    }

    public void testSelect(){
        QueryWrapper<Test> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",7);
        List<Test> list = testMapper.selectList(queryWrapper);


    }
}
