package com.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.service.AdminService;
import com.boot.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*管理员Controller*/
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/admin/login/status",method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean flag = adminService.verifyPassword(name,password);
        if(flag){
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"登录成功");
            session.setAttribute(Constant.NAME,name);
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"用户名或密码错误");
        return jsonObject;
    }
}
