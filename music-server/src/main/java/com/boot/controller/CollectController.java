package com.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.domain.Collect;
import com.boot.domain.Comment;
import com.boot.service.CollectService;
import com.boot.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*收藏Controller*/
@RestController
@RequestMapping("collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 添加收藏
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        if (songId == null || songId.equals("")) {
            jsonObject.put(Constant.CODE, 0);
            jsonObject.put(Constant.MSG, "收藏歌曲为空");
            return jsonObject;
        }
        if (collectService.existSongId(Integer.parseInt(userId), Integer.parseInt(songId))) {
            jsonObject.put(Constant.CODE, 2);
            jsonObject.put(Constant.MSG, "歌曲已经收藏过了");
            return jsonObject;
        }
        Collect collect = new Collect();
        collect.setUserId(Integer.valueOf(userId));
        collect.setType(Byte.valueOf(type));
        collect.setSongId(Integer.valueOf(songId));
        boolean flag = collectService.insert(collect);
        if (flag) {
            jsonObject.put(Constant.CODE, 1);
            jsonObject.put(Constant.MSG, "收藏成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE, 0);
        jsonObject.put(Constant.MSG, "收藏失败");
        return jsonObject;
    }



    /**
     * 根据用户id和歌曲id删除用户收藏
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Object deleteCollect(HttpServletRequest request){
        String userId = request.getParameter("userId").trim();
        String songId = request.getParameter("songId").trim();
        boolean flag = collectService.deleteByUserIdSongId(Integer.parseInt(userId), Integer.parseInt(songId));
        return flag;
    }

    /**
     * 查询所有收藏
     *
     * @return
     */
    @RequestMapping(value = "/allCollect", method = RequestMethod.GET)
    public Object allCollect() {
        return collectService.allCollect();
    }

    /**
     * 根据用户id查询其收藏歌曲
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/collectOfUserId", method = RequestMethod.GET)
    public Object collectOfUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId").trim();
        List<Collect> collects = collectService.collectOfUserId(Integer.valueOf(userId));
        return collects;
    }

}
