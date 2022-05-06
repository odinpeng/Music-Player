package com.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.domain.Singer;
import com.boot.service.SingerService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*歌手controller*/
@RestController
@RequestMapping("/singer") //设置上级目录
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * 添加歌手
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name").trim();//去掉前后空格
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim(); //头像
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim(); //地区
        String introduction = request.getParameter("introduction").trim(); //简介
        /*birth转化为Date格式*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateBirth = new Date();
        try {
            dateBirth = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /*将信息保存到歌手对象中*/
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(dateBirth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.insert(singer);
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
     * 修改歌手
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();//去掉前后空格
        String sex = request.getParameter("sex").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim(); //地区
        String introduction = request.getParameter("introduction").trim(); //简介
        /*birth转化为Date格式*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateBirth = new Date();
        try {
            dateBirth = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /*将信息保存到歌手对象中*/
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id)); //String->Integer
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(dateBirth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.update(singer);
        if (flag) {
            jsonObject.put(Constant.CODE, 1);
            jsonObject.put(Constant.MSG, "修改成功");
            return jsonObject;
        } else {
            jsonObject.put(Constant.CODE, 0);
            jsonObject.put(Constant.MSG, "修改失败");
            return jsonObject;
        }
    }

    /**
     * 删除歌手
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteSinger(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        boolean flag = singerService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据id查询歌手信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        return singerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有歌手
     *
     * @return
     */
    @RequestMapping(value = "/allSinger", method = RequestMethod.GET)
    public Object allSinger() {
        List<Singer> singers = singerService.allSinger();
        return singers;
    }

    /**
     * 根据歌手名称模糊查询歌手
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/singerOfName", method = RequestMethod.GET)
    public Object singerOfName(HttpServletRequest request) {
        String name = request.getParameter("name").trim();
        List<Singer> singers = singerService.singerOfName("%" + name + "%");
        return singers;
    }

    /**
     * 根据性别查询歌手
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/singerOfSex", method = RequestMethod.GET)
    public Object singerOfSex(HttpServletRequest request) {
        String sex = request.getParameter("sex").trim();
        List<Singer> singers = singerService.singerOfSex(Integer.parseInt(sex));
        return singers;
    }

    /**
     * 更新歌手图片
     * @param avatorFile
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSingerPic", method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile,
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
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"singerPic";
        /*如果文件路径不存在，新增路径*/
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        /*实际的文件地址*/
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        /*存储到数据库里的相对路径地址*/
        String storeAvatorPath= "/img/singerPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean flag = singerService.update(singer);
            if (flag){
                jsonObject.put(Constant.CODE,1);
                jsonObject.put(Constant.MSG,"上传成功");
                jsonObject.put("pic",storeAvatorPath);
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
