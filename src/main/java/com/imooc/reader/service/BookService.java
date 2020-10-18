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

    /**
     * 根據圖書查詢對象
     * @param bookId
     * @return 圖書對象
     */
    public Book selectById(Long bookId);

    /**
     * 更新圖書評分/評價數量
     */

    public void updateEvaluation();
    /**
     * 創建新的圖書
     */
    public Book createBook(Book book);
}
