package com.boot.controller;

import com.alibaba.fastjson.JSONObject;
import com.boot.domain.Comment;
import com.boot.service.CommentService;
import com.boot.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*评论Controller*/
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object add(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        String songListId = request.getParameter("songListId");
        String content = request.getParameter("content");
        Comment comment = new Comment();
        comment.setUserId(Integer.valueOf(userId));
        comment.setType(Byte.valueOf(type));
        if (Byte.valueOf(type)==0){ //0为歌曲评论
            comment.setSongId(Integer.valueOf(songId));
        }else{ //1为歌单评论
            comment.setSongListId(Integer.valueOf(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.insert(comment);
        if (flag) {
            jsonObject.put(Constant.CODE, 1);
            jsonObject.put(Constant.MSG, "评论成功");
            return jsonObject;
        }
        jsonObject.put(Constant.CODE, 0);
        jsonObject.put(Constant.MSG, "评论失败");
        return jsonObject;
    }

    /**
     * 修改评论
     * @param request
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Object updateComment(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String userId = request.getParameter("userId").trim();
        String type = request.getParameter("type").trim();
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId");
        String content = request.getParameter("content").trim();
        Comment comment = new Comment();
        comment.setId(Integer.valueOf(id));
        comment.setUserId(Integer.valueOf(userId));
        comment.setType(Byte.valueOf(type));
        /*判断前台传来的songId和songListId是否为空，不为空再set*/
        if (songId!=null && songId.equals("")){
            songId = null;
        }else{
            comment.setSongId(Integer.valueOf(songId));
        }
        if (songListId!=null && songListId.equals("")){
            songListId = null;
        }else{
            comment.setSongListId(Integer.valueOf(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.update(comment);
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
     * 删除评论
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object deleteComment(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        boolean flag = commentService.delete(Integer.parseInt(id));
        return flag;
    }

    /**
     * 根据评论id查询评论对象
     * @param request
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public Object selectByPrimaryKey(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        Comment comment = commentService.selectByPrimaryKey(Integer.valueOf(id));
        return comment;
    }

    /**
     * 查询所有评论
     * @return
     */
    @RequestMapping(value = "/allComment",method = RequestMethod.GET)
    public Object allComment(){
        return commentService.allComment();
    }

    /**
     * 根据歌曲id查询评论列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/commentOfSongId", method = RequestMethod.GET)
    public Object commentOfSongId(HttpServletRequest request){
        String songId = request.getParameter("songId").trim();
        List<Comment> comments = commentService.commentOfSongId(Integer.valueOf(songId));
        return comments;
    }

    /**
     * 跟据歌单id查询评论列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/commentOfSongListId",method = RequestMethod.GET)
    public Object commentOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId");          //歌曲id
        return commentService.commentOfSongListId(Integer.parseInt(songListId));
    }

    /**
     * 点赞
     * @param request
     * @return
     */
    @RequestMapping(value = "/like",method = RequestMethod.POST)
    public Object like(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String up = request.getParameter("up").trim();
        Comment comment = new Comment();
        comment.setId(Integer.valueOf(id));
        comment.setUp(Integer.valueOf(up));
        boolean flag = commentService.update(comment);
        if (flag){
            jsonObject.put(Constant.CODE, 1);
            jsonObject.put(Constant.MSG, "点赞成功");
            return jsonObject;
        }else{
            jsonObject.put(Constant.CODE, 0);
            jsonObject.put(Constant.MSG, "点赞失败");
            return jsonObject;
        }
    }
}
