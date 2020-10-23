package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceImplTest {
    @Resource
    private BookService bookService;

    @Test
    public void paging() {
        IPage<Book> pageObject = bookService.paging((long) 2,"quantity",1, 10);
        List<Book> records = pageObject.getRecords();
        for (Book b : records      ) {
            System.out.println(b.getBookId()+":"+b.getBookName());
        }


    }
}