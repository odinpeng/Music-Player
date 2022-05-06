package com.boot.dao;

import com.boot.domain.Comment;
import com.boot.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    /**
     * 添加评论
     * @param comment
     * @return
     */
    public int insert(Comment comment);

    /**
     * 修改评论
     * @param comment
     * @return
     */
    public int update(Comment comment);

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(Integer id);

    /**
     * 根据主键查询评论对象
     * @param id
     * @return
     */
    public Comment selectByPrimaryKey(Integer id);

    /**
     * 查询所有评论
     * @return
     */
    public List<Comment> allComment();


    /**
     * 查询某个歌曲的所有评论
     * @param songId
     * @return
     */
    public List<Comment> commentOfSongId(Integer songId);


    /**
     * 查询某个歌单下的所有评论
     * @param songListId
     * @return
     */
    public List<Comment> commentOfSongListId(Integer songListId);

}
