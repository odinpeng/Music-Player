package com.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.domain.Song;
import com.boot.service.SongService;
import com.boot.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/*歌曲controller*/
@RestController
@RequestMapping("/song") //设置上级目录
public class SongController {

    @Autowired
    private SongService songService;

    /**
     * 添加歌曲
     * @param request
     * @param mpFile
     * @return
     */
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request, @RequestParam("file")MultipartFile mpFile){
        JSONObject jsonObject = new JSONObject();
        /*获取前端传来的参数*/
        String singerId = request.getParameter("singerId").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String pic = "/img/songPic/tubiao.jpg";
        String lyric = request.getParameter("lyric").trim();
        /*上传歌曲文件*/
        if (mpFile.isEmpty()){
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"歌曲上传失败");
            return jsonObject;
        }
        /*文件名=1970年到现在的毫秒+原文件名*/
        String fileName = System.currentTimeMillis() + mpFile.getOriginalFilename();
        /*文件路径*/
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        /*如果文件路径不存在，新增路径*/
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        /*实际的文件地址*/
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        /*存储到数据库里的相对路径地址*/
        String storeUrlPath= "/song/"+fileName;
        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            boolean flag = songService.insert(song);
            if (flag){
                jsonObject.put(Constant.CODE,1);
                jsonObject.put(Constant.MSG,"保存成功");
                jsonObject.put("avator",storeUrlPath);
                return jsonObject;
            }
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"保存失败");
            return jsonObject;
        } catch (IOException e) {
            jsonObject.put(Constant.CODE,0);
            jsonObject.put(Constant.MSG,"保存失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }

    /**
     * 根据歌手id查询歌曲
     * @param request
     * @return
     */
    @RequestMapping(value = "/singer/detail",method=RequestMethod.GET)
    public Object songOfSingerId(HttpServletRequest request){
        String singerId = request.getParameter("singerId").trim();
        List<Song> songs = songService.songOfSingerId(Integer.parseInt(singerId));
        return songs;
    }

    /**
     * 根据歌曲id查询歌曲对象
     * @param request
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public Object detail(HttpServletRequest request){
        String songId = request.getParameter("songId").trim();
        Song song = songService.selectByPrimaryKey(Integer.parseInt(songId));
        return song;
    }

    /**
     * 根据歌曲名查询歌曲对象
     * @param request
     * @return
     */
    @RequestMapping(value = "/songOfSongName", method = RequestMethod.GET)
    public Object songOfSongName(HttpServletRequest request){
        String songName = request.getParameter("songName").trim();
        List<Song> songs = songService.songOfName(songName);
        return songs;
    }

    /**
     * 根据歌曲名模糊查询歌曲对象
     * @param request
     * @return
     */
    @RequestMapping(value = "/likeSongOfName", method = RequestMethod.GET)
    public Object likeSongOfName(HttpServletRequest request){
        String songName = request.getParameter("songName").trim();
        List<Song> songs = songService.likeSongOfName("%" + songName + "%");
        return songs;
    }

    /**
     * 查询所有歌曲
     * @return
     */
    @RequestMapping(value = "/allSong",method = RequestMethod.GET)
    public Object allSong(){
        return songService.allSong();
    }


    /**
     * 修改歌曲
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateSong(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String lyric = request.getParameter("lyric").trim();
        /*将获取的数据保存在Song对象中*/
        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        boolean flag = songService.update(song);
        if (flag){
            jsonObject.put(Constant.CODE, 1);
            jsonObject.put(Constant.MSG, "修改成功");
            return jsonObject;
        }else{
            jsonObject.put(Constant.CODE, 0);
            jsonObject.put(Constant.MSG, "修改失败");
            return jsonObject;
        }
    }

    /**
     * 删除歌曲
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteSong(HttpServletRequest request){
        //-TODO 先查询数据库中对应的文件地址，再删除文件
        String id = request.getParameter("id").trim();
        Song song = songService.selectByPrimaryKey(Integer.parseInt(id));
        String url = song.getUrl();
        File file = new File(url);
        //若文件存在则删除
        if (file.exists()){
            file.delete();
        }
        boolean flag = songService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 更改歌曲图片
     * @param avatorFile
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSongPic",method= RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file") MultipartFile avatorFile,
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
                +System.getProperty("file.separator")+"songPic";
        /*如果文件路径不存在，新增路径*/
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        /*实际的文件地址*/
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        /*存储到数据库里的相对路径地址*/
        String storeAvatorPath= "/img/songPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatorPath);
            boolean flag = songService.update(song);
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

    /**
     * 更改并上传歌曲到指定文件夹
     * @param avatorFile
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSongUrl",method = RequestMethod.POST)
    public Object updateSongUrl(
            @RequestParam("file") MultipartFile avatorFile, @RequestParam("id") int id)
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
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        /*如果文件路径不存在，新增路径*/
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        /*实际的文件地址*/
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        /*存储到数据库里的相对路径地址*/
        String storeAvatorPath= "/song/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatorPath);
            boolean flag = songService.update(song);
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
