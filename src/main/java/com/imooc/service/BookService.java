package com.imooc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.entity.Book;

public interface BookService {
    public IPage<Book> paging(Integer page, Integer rows);
}
