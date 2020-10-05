package com.imooc.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.entity.Book;
import com.imooc.entity.Category;
import com.imooc.service.BookService;
import com.imooc.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class BookController {

    @Resource
    private CategoryService categoryService;
    @Resource
    private BookService bookService;

    @GetMapping("/Book")
    public ModelAndView showIndex(){
        ModelAndView mav = new ModelAndView("/index");
        List<Category> categoryList = categoryService.selectAll();
        mav.addObject("categoryList",categoryList);
        return mav;
    }

    /**
     *分頁查詢圖書列表
      * @param p 頁號
     * @return 分頁對象
     */
    @GetMapping("/books")
    @ResponseBody
     public IPage<Book> selectBook(Integer p){
        if(p==null){
            p = 1;
        }
         IPage<Book> pageObjeck = bookService.paging(p, 10);
        return  pageObjeck;
     }
}
