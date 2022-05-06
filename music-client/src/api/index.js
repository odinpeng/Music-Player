import { get, post } from "./http";
import Axios from "axios";

//----------歌手相关接口----------
// 查询所有歌手(无参数)
export const getAllSinger = () => get(`/singer/allSinger`);
//根据性别查询歌手
export const getSingerOfSex = (sex) =>get(`/singer/singerOfSex?sex=${sex}`)


//----------歌曲相关接口----------
//根据歌手id查询歌曲
export const songOfSingerId = (id) => get(`/song/singer/detail?singerId=${id}`);
//根据歌曲id查询歌曲对象
export const songOfSongId = (id) => get(`/song/detail?songId=${id}`);
//根据歌曲名模糊查询歌曲对象
export const likeSongOfName =(songName) => get(`/song/likeSongOfName?songName=${songName}`);


//----------歌单相关接口----------
// 查询所有歌单(无参数)
export const getAllSongList = () => get(`/songList/allSongList`);
//跟据主题模糊查询歌单
export const getSongListOfLikeTitle = (title) => get(`/songList/likeTitle?title=${title}`);
//根据风格模糊查询歌单
export const getSongListOfLikeStyle = (style) => get(`/songList/likeStyle?style=${style}`);


//----------歌单的歌曲接口----------
//根据歌单id查询歌曲列表
export const listSongDetail = (songListId) => get(`/listSong/detail?songListId=${songListId}`);


//----------用户相关接口----------
//查询所有用户(无参数)
export const getAllConsumer = () => get(`/consumer/allConsumer`);
// 注册用户
export const SignUp = (params) => post(`/consumer/add`,params);
//登录
export const loginIn = (params) => post(`/consumer/login`,params);
//下载音乐
export const download = (url) => Axios({
    method: 'get',
    url: url,
    responseType:'blob'
});
//根据用户id查询用户对象
export const getUserOfId =(id) => get(`/consumer/selectByPrimaryKey?id=${id}`);
//更改用户信息
export const updateUserMsg = (params) => post(`/consumer/update`,params);


//----------评价相关接口----------
//新增评价
export const setRank = (params) =>post(`/rank/add`,params);
//平均评价分数
export const getRankOfSongListId = (songListId) => get(`/rank?songListId=${songListId}`)


//----------评论相关接口----------
//提交评论
export const setComment =(params) => post(`/comment/add`,params);
//点赞
export const setLike =(params) => post(`/comment/like`,params);
//返回当前歌单或歌曲的评论列表
export const getAllComment = (type,id) => {
    if(type == 0){              //歌曲
        return get(`/comment/commentOfSongId?songId=${id}`);
    }else{                      //歌单
        return get(`/comment/commentOfSongListId?songListId=${id}`);
    }
}


//----------收藏相关接口----------
//添加收藏
export const setCollect = (params) =>post(`/collect/add`,params);
//根据用户id查询其收藏列表
export const getCollectOfUserId = (userId) => get(`/collect/collectOfUserId?userId=${userId}`);