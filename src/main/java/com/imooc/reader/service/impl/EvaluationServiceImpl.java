package com.imooc.reader.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.entity.Book;
import com.imooc.reader.entity.Evaluation;
import com.imooc.reader.entity.Member;
import com.imooc.reader.mapper.BookMapper;
import com.imooc.reader.mapper.EvaluationMapper;
import com.imooc.reader.mapper.MemberMapper;
import com.imooc.reader.service.EvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service("evaluationService")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {

    @Resource
    EvaluationMapper evaluationMapper;
    @Resource
    MemberMapper memberMapper;
    @Resource
    BookMapper bookMapper;
    @Override
    public List<Evaluation> selectById(Long bookId) {
        Book book = bookMapper.selectById(bookId);
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_Id",bookId);
        queryWrapper.eq("state","enable");
        queryWrapper.orderByDesc("create_time");
        List<Evaluation> evaluationList = evaluationMapper.selectList(queryWrapper);
        for (Evaluation eva:evaluationList) {
            Member member = memberMapper.selectById(eva.getMemberId());
            eva.setMember(member) ;
            eva.setBook(book);

        }
        return evaluationList;
    }

    public List<Evaluation> selectAll() {
        List<Evaluation> list = evaluationMapper.selectList(new QueryWrapper<>());
        return list;
    }

    @Override
    public Evaluation changeState(Long evaluationId, String state) {
        return null;
    }
}
