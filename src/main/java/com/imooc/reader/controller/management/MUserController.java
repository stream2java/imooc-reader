package com.imooc.reader.controller.management;

import com.imooc.reader.entity.User;
import com.imooc.reader.service.UserService;
import com.imooc.reader.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/management")
public class MUserController {
    @Resource
    private UserService userService;

    @GetMapping("/login.html")
    public ModelAndView showLogin(){
        return new ModelAndView("/management/login");
    }
    @PostMapping("/check_login")
    @ResponseBody
    public Map checkLogin(String username, String password, String vc, HttpSession session){
        String verifyCode = (String) session.getAttribute("kaptchaVerifyCode");
        Map result = new HashMap();
        if (vc == null || verifyCode == null || !vc.equalsIgnoreCase(verifyCode)) {
            result.put("code", "VC01");
            result.put("msg", "驗證碼錯誤");
        }  else {
            try {
                User user = userService.checkLogin(username, password);
                session.setAttribute("loginUser",user);
                result.put("code","0");
                result.put("msg","success");
            } catch (BussinessException ex) {
                result.put("code",ex.getCode());
                result.put("msg",ex.getMsg());
            }

        }
        return  result;
    }



}
