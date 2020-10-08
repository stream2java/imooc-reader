package com.imooc.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Category;
import com.imooc.reader.entity.Evaluation;
import com.imooc.service.BookService;
import com.imooc.service.CategoryService;
import com.imooc.service.EvaluationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Resource
    private EvaluationService evaluationService;

    @GetMapping("/")
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("/index");
        List<Category> categoryList = categoryService.selectAll();
        mav.addObject("categoryList", categoryList);
        return mav;
    }

    /**
     * 分頁查詢圖書列表
     *
     * @param p 頁號
     * @return 分頁對象
     */
    @GetMapping("/books")
    @ResponseBody
    public IPage<Book> selectBook(Long categoryId, String order, Integer p) {
        if (p == null) {
            p = 1;
        }
        IPage<Book> pageObjeck = bookService.paging(categoryId, order, p, 10);
        return pageObjeck;
    }

    @GetMapping("/book/{id}")
    public ModelAndView showDetail(@PathVariable Long id) {
        Book book = bookService.selectById(id);
        List<Evaluation> evaluationList = evaluationService.selectById(id);
        ModelAndView mav = new ModelAndView("/detail");
        mav.addObject("book", book);
        mav.addObject("evaluationList",evaluationList);
        return mav;

    }
}
