package com.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.domain.Rank;
import com.boot.domain.Song;
import com.boot.service.RankService;
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

/*评分controller层*/
@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    /**
     * 添加评价
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/rank/add", method = RequestMethod.POST)
    public Object addSong(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        /*获取前端传来的参数*/
        String songListId = request.getParameter("songListId").trim();
        String consumerId = request.getParameter("consumerId").trim();
        String score = request.getParameter("score").trim();

        Rank rank = new Rank();
        rank.setSongListId(Integer.parseInt(songListId));
        rank.setConsumerId(Integer.parseInt(consumerId));
        rank.setScore(Integer.parseInt(score));
        boolean flag = rankService.insert(rank);
        if (flag) {
            jsonObject.put(Constant.CODE, 1);
            jsonObject.put(Constant.MSG, "评价成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE, 0);
        jsonObject.put(Constant.MSG, "评价失败");
        return jsonObject;
    }

    /**
     * 计算平均分
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/rank",method = RequestMethod.GET)
    public Object rankOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId").trim();
        return rankService.rankOfSongListId(Integer.parseInt(songListId));
    }


}
