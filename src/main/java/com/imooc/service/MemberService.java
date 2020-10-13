package com.imooc.service;

import com.imooc.reader.entity.Member;
import com.imooc.reader.entity.MemberReadState;

public interface MemberService {
    /**
     *
     * @param username 帳號
     * @param password 密碼
     * @param nickname 暱稱
     * @return
     */
    public Member createMember(String username,String password,String nickname);
    public Member checkLogin(String username,String password);
    public MemberReadState selectMemberReadState(Long memberId,Long bookId);
}
