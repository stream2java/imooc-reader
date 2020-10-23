package com.imooc.reader.controller.management;
import com.imooc.reader.service.EvaluationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/management/comment")
public class MEvaluationController {

    @Resource
    EvaluationService evaluationService;


    @GetMapping("/index.html")
    public ModelAndView showBook() {
        return new ModelAndView("/management/comment");
    }

    @GetMapping("/list")
    @ResponseBody
    public Map list(Integer page, Integer limit) {
//        if (page == null) {
//            page = 1;
//        }
//        if ((limit == null)) {
//            limit = 10;
//        }
//        IPage<Book> pageObject = bookService.paging(null, null, page, limit);
        Map result = new HashMap();
//        result.put("code", "0");
//        result.put("msg", "success");
//        result.put("data", pageObject.getRecords());//當前頁面數據
//        result.put("count", pageObject.getTotal());
        return result;
    }

}
