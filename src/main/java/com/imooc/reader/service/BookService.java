package com.imooc.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;


public interface BookService {
    /**
     *
     * @param categoryId 分類編號
     * @param order 排序方式
     * @param page 頁號
     * @param rows 每頁紀錄數
     * @return
     */
    public IPage<Book> paging(Long categoryId, String order, Integer page, Integer rows);
    public Book selectById(Long bookId);
    public Book createBook(Book book);
}
