package com.imooc.reader.controller;

import com.imooc.reader.entity.Member;
import com.imooc.service.MemberService;
import com.imooc.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {
    @Resource
    private MemberService memberService;

    @GetMapping("/register.html")
    public ModelAndView showRegister(){
        return new ModelAndView("/register");
    }

    @GetMapping("/login.html")
    public ModelAndView showLogin(){
        return new ModelAndView("/login");
    }

    @PostMapping("/registe")
    @ResponseBody
    public Map registe(String vc, String username, String password, String nickname, HttpServletRequest request){
        //get verifyCode
        String verifyCode = (String) request.getSession().getAttribute("kaptchaVerifyCode");
        Map result = new HashMap();
        if(vc ==null || verifyCode==null || !vc.equalsIgnoreCase(verifyCode)){
            result.put("code","VC01");
            result.put("msg","驗證碼錯誤");
        }else{
            try {
                memberService.createMember(username, password, nickname);
                result.put("code","0");
                result.put("msg","success");
            } catch (BussinessException ex) {
                ex.printStackTrace();
                result.put("code", ex.getCode());
                result.put("msg",ex.getMsg()) ;
            }
        }

        return  result;
    }
    @PostMapping("/check_login")
    @ResponseBody
    public  Map checkLogin(String username, String password, String vc, HttpSession session){
        String verifyCode = (String)session.getAttribute("kaptchaVerifyCode");
        Map result = new HashMap();
        if(vc ==null || verifyCode==null || !vc.equalsIgnoreCase(verifyCode)){
            result.put("code","VC01");
            result.put("msg","驗證碼錯誤");
        }else{
            try {
                Member member = memberService.checkLogin(username, password);
                session.setAttribute("loginMember",member);
                result.put("code","0");
                result.put("msg","success");
            } catch (BussinessException ex) {
                ex.printStackTrace();
                result.put("code", ex.getCode());
                result.put("msg",ex.getMsg()) ;
            }
        }
        return  result;
    }
}
