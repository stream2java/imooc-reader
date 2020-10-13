package com.imooc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.reader.entity.Member;
import com.imooc.reader.entity.MemberReadState;
import com.imooc.reader.mapper.MemberMapper;
import com.imooc.reader.mapper.MemberReadStateMapper;
import com.imooc.reader.util.MD5Utils;
import com.imooc.service.MemberService;
import com.imooc.service.exception.BussinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("memberService")
@Transactional
public class MemberServiceImpl implements MemberService {
    @Resource
    private MemberMapper memberMapper;

    @Resource
    private MemberReadStateMapper memberReadStateMapper;

    @Override
    public Member createMember(String username, String password, String nickname) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("username",username);
        List<Member> memberList = memberMapper.selectList(queryWrapper);
        if(memberList.size() >0){
            throw new BussinessException("M01","用戶名已存在");
        }
        Member member = new Member();
        member.setUsername(username);
        member.setNickname(nickname);
        int salt = new Random().nextInt(1000) + 1000;
        String md5 = MD5Utils.md5Digest(password,salt);
        member.setPassword(md5);
        member.setSalt(salt);
        member.setCreateTime(new Date());
        memberMapper.insert(member);
        return member;
    }

    @Override
    public Member checkLogin(String username, String password) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<Member>();
        queryWrapper.eq("username",username);
        Member member = memberMapper.selectOne(queryWrapper);
        if(member ==null){
            throw new BussinessException("M02","用戶不存在");
        }

        String md5 = MD5Utils.md5Digest(password, member.getSalt());
        if(!md5.equals(member.getPassword())){
            throw new BussinessException("M03","輸入密碼有誤");
        }


        return member;
    }

    @Override
    public MemberReadState selectMemberReadState(Long memberId, Long bookId) {
        QueryWrapper<MemberReadState> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id",memberId);
        queryWrapper.eq("book_id",bookId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(queryWrapper);
        return memberReadState;
    }
}
