package com.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.domain.ListSong;
import com.boot.domain.Song;
import com.boot.service.ListSongService;
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
import java.util.List;

/*歌单的歌曲Controller*/
@RestController
@RequestMapping("/listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    /**
     * 添加歌单的歌曲
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        /*获取前端传来的参数*/
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();
        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        boolean flag = listSongService.insert(listSong);
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
     * 根据歌单id查询歌曲
     * @param request
     * @return
     */
    @RequestMapping(value = "/detail",method=RequestMethod.GET)
    public Object detail(HttpServletRequest request){
        String songListId = request.getParameter("songListId").trim();
        List<ListSong> listSongs = listSongService.listSongOfSongListId(Integer.parseInt(songListId));
        return listSongs;
    }


    /**
     * 删除歌单的歌曲
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(HttpServletRequest request){
        String songId = request.getParameter("songId").trim(); //歌曲id
        String songListId = request.getParameter("songListId").trim(); //歌单id
        boolean flag = listSongService.deleteBySongIdAndSongListId(Integer.parseInt(songId), Integer.parseInt(songListId));
        return flag;
    }



}
