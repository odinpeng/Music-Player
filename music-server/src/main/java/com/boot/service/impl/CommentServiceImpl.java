package com.boot.service.impl;

import com.boot.dao.CommentMapper;
import com.boot.domain.Comment;
import com.boot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*评论Service实现类*/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 添加评论
     *
     * @param comment
     * @return
     */
    @Override
    public boolean insert(Comment comment) {
        return commentMapper.insert(comment)>0;
    }

    /**
     * 修改评论
     *
     * @param comment
     * @return
     */
    @Override
    public boolean update(Comment comment) {
        return commentMapper.update(comment)>0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return commentMapper.delete(id)>0;
    }

    /**
     * 根据主键查询评论对象
     *
     * @param id
     * @return
     */
    @Override
    public Comment selectByPrimaryKey(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有评论
     *
     * @return
     */
    @Override
    public List<Comment> allComment() {
        return commentMapper.allComment();
    }

    /**
     * 查询某个歌曲的所有评论
     *
     * @param songId
     * @return
     */
    @Override
    public List<Comment> commentOfSongId(Integer songId) {
        return commentMapper.commentOfSongId(songId);
    }

    /**
     * 查询某个歌单下的所有评论
     *
     * @param songListId
     * @return
     */
    @Override
    public List<Comment> commentOfSongListId(Integer songListId) {
        return commentMapper.commentOfSongListId(songListId);
    }
}
