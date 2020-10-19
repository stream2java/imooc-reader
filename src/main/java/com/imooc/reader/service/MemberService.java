package com.imooc.reader.service;

import com.imooc.reader.entity.Evaluation;
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

    /**
     *
     * @param memberId 會員編號
     * @param bookId  圖書編號
     * @return 閱讀狀態
     */
    public MemberReadState selectMemberReadState(Long memberId,Long bookId);

    /**
     *
     * @param memberId 會員編號
     * @param bookId   圖書編號
     * @param readState 閱讀狀態
     * @return 閱讀狀態
     */
    public MemberReadState updateMemberReadState(Long memberId, Long bookId ,Integer readState);

    /**
     * 發布新的短評
     * @param memberId 會員編號
     * @param bookId 圖書編號
     * @param score 分數
     * @param content 內容
     * @return 短評對象
     */
    public Evaluation evaluate(Long memberId,Long bookId, Integer score,String content);

    /**
     * 評論點讚
     * @param evaluationId 評論編號
     * @return 短評對象
     */
    public Evaluation enjoy(Long evaluationId);
}
