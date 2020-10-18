package com.imooc.reader.task;

import com.imooc.reader.service.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/*
    完成自動計算任務
 */
@Component
public class ComputeTask {

    @Resource
    private BookService bookService;
    //任務調度
    @Scheduled(cron = "0 0 1 * * ? ")
//            * * 0 * * ?
    public  void updateEvaluation(){
        bookService.updateEvaluation();
        System.out.println("已更新評分");
    }
}
