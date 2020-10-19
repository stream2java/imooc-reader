package com.imooc.service;

import com.imooc.reader.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestServiceTest {
    @Resource
    private TestService testService;

    @Test
    public void batchImport() {
        testService.batchImport();
        System.out.println("批量導入成功");
    }


}