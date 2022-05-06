package com.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.domain.Consumer;
import com.boot.domain.Consumer;
import com.boot.domain.Consumer;
import com.boot.service.ConsumerService;
import com.boot.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*用户controller*/
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    /**
     * 添加用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addConsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phoneNum").trim();
        String email = request.getParameter("email");
        String birth = request.getParameter("birth").trim();
        String introduction = request.getParameter("introduction").trim(); //简介
        String location = request.getParameter("location").trim(); //地区
        String avator = request.getParameter("avator").trim(); //头像

        /*验证用户名和密码是否为空，为空直接寄*/
        if (username==null || "".equals(username)){
            jsonObject.put(Constant.CODE, 0);
            jsonObject.put(Constant.MSG, "用户名不能为空");
            return jsonObject;
        }

        /*判断用户名是否已经存在*/
        Consumer theConsumer = consumerService.getByUserName(username);
        if (theConsumer!=null){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"用户名已存在");
            return jsonObject;
        }

        if (password==null || "".equals(password)){
            jsonObject.put(Constant.CODE, 0);
            jsonObject.put(Constant.MSG, "密码不能为空");
            return jsonObject;
        }

        /*birth转化为Date格式*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateBirth = new Date();
        try {
            dateBirth = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*将信息保存到歌手对象中*/
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex)); //String -> Byte
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(dateBirth); //转换后的birth
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);
        boolean flag = consumerService.insert(consumer);
        if (flag) { //保存成功
            jsonObject.put(Constant.CODE, 1);
            jsonObject.put(Constant.MSG, "添加成功");
            return jsonObject;
        } else { //保存失败
            jsonObject.put(Constant.CODE, 0);
            jsonObject.put(Constant.MSG, "添加失败");
            return jsonObject;
        }
    }

    /**
     * 用户登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        if(username==null||username.equals("")){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"密码不能为空");
            return jsonObject;
        }

        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        boolean flag = consumerService.verifyPassword(username,password);
        if(flag){   //验证成功
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"登录成功");
            jsonObject.put("userMsg",consumerService.getByUserName(username));
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"用户名或密码错误");
        return jsonObject;
    }

    /**
     * 修改用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateConsumer(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();          //主键
        String username = request.getParameter("username").trim();     //账号
        String password = request.getParameter("password").trim();     //密码
        String sex = request.getParameter("sex").trim();               //性别
        String phoneNum = request.getParameter("phoneNum").trim();     //手机号
        String email = request.getParameter("email").trim();           //电子邮箱
        String birth = request.getParameter("birth").trim();           //生日
        String introduction = request.getParameter("introduction").trim();//签名
        String location = request.getParameter("location").trim();      //地区

        if(username==null||username.equals("")){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"用户名不能为空");
            return jsonObject;
        }
        if(password==null||password.equals("")){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"密码不能为空");
            return jsonObject;
        }
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        boolean flag = consumerService.update(consumer);
        if(flag){   //保存成功
            jsonObject.put(Constant.CODE,1);
            jsonObject.put(Constant.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE,0);
        jsonObject.put(Constant.MSG,"修改失败");
        return jsonObject;
    }

    /**
     * 删除用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteConsumer(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        boolean flag = consumerService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据id查询用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        return consumerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getByUserName", method = RequestMethod.GET)
    public Object getByUserName(HttpServletRequest request) {
        String username = request.getParameter("username").trim();
        return consumerService.getByUserName(username);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping(value = "/allConsumer", method = RequestMethod.GET)
    public Object allConsumer() {
        List<Consumer> consumers = consumerService.allConsumer();
        return consumers;
    }

    /**
     * 验证用户名和密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/verifyPassword", method = RequestMethod.POST)
    public Object verifyPassword(HttpServletRequest request){
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        return consumerService.verifyPassword(username, password);
    }

    /**
     * 更新用户图片
     * @param avatorFile
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateConsumerPic", method = RequestMethod.POST)
    public Object updateConsumerPic(@RequestParam("file") MultipartFile avatorFile,
                                  @RequestParam("id") int id)
    {
        JSONObject jsonObject = new JSONObject();
        if (avatorFile.isEmpty()){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"文件上传失败");
            return jsonObject;
        }
        /*文件名=1970年到现在的毫秒+原文件名*/
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        /*文件路径*/
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"avatorImages";
        /*如果文件路径不存在，新增路径*/
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        /*实际的文件地址*/
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        /*存储到数据库里的相对路径地址*/
        String storeAvatorPath= "/avatorImages/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            boolean flag = consumerService.update(consumer);
            if (flag){
                jsonObject.put(Constant.CODE,1);
                jsonObject.put(Constant.MSG,"上传成功");
                jsonObject.put("avator",storeAvatorPath);
                return jsonObject;
            }
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"上传失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"上传失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }




}
