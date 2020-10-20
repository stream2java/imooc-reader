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

    /**
     * 創造新的圖書
     * @param book
     * @return
     */
    public Book createBook(Book book);

    /**
     * 更新圖書
     * @param book 新圖書數據
     * @return 更新後數據
     */
    public Book updateBook(Book book);

    /**
     * 刪除圖書
     * @param bookId 圖書編號
     */
    public void deleteBook(Long bookId);
}
