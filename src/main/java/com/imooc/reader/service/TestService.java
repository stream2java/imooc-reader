package com.imooc.reader.service;

import com.imooc.reader.mapper.TestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TestService {
    @Resource
    private TestMapper testMapper;

    @Transactional
    public void batchImport() {
        for (int i = 0; i < 5; i++) {
//            if (i == 3) {
//                throw new RuntimeException("運行錯誤測試");
//            }
//            testMapper.insert();
        }
    }
}
