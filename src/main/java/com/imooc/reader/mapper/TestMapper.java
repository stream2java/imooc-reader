package com.imooc.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.reader.entity.Test;

public interface TestMapper extends BaseMapper<Test> {
    public void insertSample();


}
