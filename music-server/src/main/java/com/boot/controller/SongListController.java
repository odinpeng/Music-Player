package com.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.domain.Singer;
import com.boot.domain.Song;
import com.boot.domain.SongList;
import com.boot.service.SongListService;
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

/*歌单controller*/
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    /**
     * 添加歌单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        /*获取前端传来的参数*/
        String title = request.getParameter("title").trim();
        String pic = request.getParameter("pic").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();

        /*将数据保存到歌单对象中*/
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.insert(songList);
        if (flag) {
            jsonObject.put(Constant.CODE, 1);
            jsonObject.put(Constant.MSG, "保存成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE, 0);
        jsonObject.put(Constant.MSG, "保存失败");
        return jsonObject;
    }

    /**
     * 修改歌单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateSongList(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        /*获取前端传来的参数*/
        String id = request.getParameter("id").trim();
        String title = request.getParameter("title").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();

        /*将数据保存到歌单对象中*/
        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        boolean flag = songListService.update(songList);
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
     * 删除歌单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteSongList(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        boolean delete = songListService.delete(Integer.parseInt(id));
        return delete;
    }

    /**
     * 根据id查询歌单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        SongList songList = songListService.selectByPrimaryKey(Integer.parseInt(id));
        return songList;
    }

    /**
     * 查询所有歌单
     *
     * @return
     */
    @RequestMapping(value = "/allSongList", method = RequestMethod.GET)
    public Object allSongList() {
        List<SongList> songLists = songListService.allSongList();
        return songLists;
    }

    /**
     * 根据主题查找歌单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/songListOfTitle", method = RequestMethod.GET)
    public Object songListOfTitle(HttpServletRequest request) {
        String title = request.getParameter("title").trim();
        List<SongList> songLists = songListService.songListOfTitle(title);
        return songLists;
    }

    /**
     * 根据主题模糊查询歌单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/likeTitle", method = RequestMethod.GET)
    public Object likeTitle(HttpServletRequest request) {
        String title = request.getParameter("title").trim();
        List<SongList> songLists = songListService.likeTitle("%" + title + "%");
        return songLists;
    }

    /**
     * 根据风格模糊查询歌单
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/likeStyle", method = RequestMethod.GET)
    public Object likeStyle(HttpServletRequest request) {
        String style = request.getParameter("style").trim();
        List<SongList> songLists = songListService.likeStyle("%" + style + "%");
        return songLists;
    }

    /**
     * 更新歌单图片
     *
     * @param avatorFile
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSongListPic",method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"文件上传失败");
            return jsonObject;
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songListPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songListPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean flag = songListService.update(songList);
            if(flag){
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
        }finally {
            return jsonObject;
        }
    }
}
