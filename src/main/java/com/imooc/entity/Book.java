package com.imooc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("book")
public class Book {
    @TableId(type = IdType.AUTO)
    private Long BookId;

    public Long getBookId() {
        return BookId;
    }

    public void setBookId(Long bookId) {
        BookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Float getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(Float evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public Integer getEvaluationQuantity() {
        return evaluationQuantity;
    }

    public void setEvaluationQuantity(Integer evaluationQuantity) {
        this.evaluationQuantity = evaluationQuantity;
    }

    private String bookName;
    private  String subTitle;
    private  String author;
    private  String cover;
    private  Long categoryId;
    private  Float evaluationScore;
    private Integer evaluationQuantity;
}
