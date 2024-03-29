import { get, post } from "./http";

// 判断用户是否登录成功
export const getLoginStatus = (params) => post(`admin/login/status`, params);


//----------歌手相关接口----------
//添加歌手
export const setSinger = (params) => post(`singer/add`, params);
// 查询所有歌手(无参数)
export const getAllSinger = () => get(`singer/allSinger`);
// 修改歌手
export const updateSinger = (params) => post(`singer/update`, params);
//删除歌手
export const delSinger = (id) => get(`singer/delete?id=${id}`);


//----------歌曲相关接口----------
//根据歌手id查询歌曲
export const songOfSingerId = (id) => get(`song/singer/detail?singerId=${id}`);
//修改歌曲
export const updateSong = (params) => post(`song/update`, params);
//删除歌曲
export const delSong = (id) => get(`song/delete?id=${id}`);
//根据歌曲id查询歌曲对象
export const songOfSongId = (id) => get(`song/detail?songId=${id}`);
//根据歌曲名获取歌曲对象
export const songOfSongName = (songName) => get(`song/songOfSongName?songName=${songName}`);
// 查询所有歌曲(无参)
export const allSong = () => get(`song/allSong`);


//----------歌单相关接口----------
//添加歌单
export const setSongList = (params) => post(`songList/add`, params);
// 查询所有歌单(无参数)
export const getAllSongList = () => get(`songList/allSongList`);
// 修改歌单
export const updateSongList = (params) => post(`songList/update`, params);
//删除歌手
export const delSongList = (id) => get(`songList/delete?id=${id}`);


//----------歌单的歌曲接口----------
//根据歌单id查询歌曲列表
export const listSongDetail = (songListId) => get(`listSong/detail?songListId=${songListId}`);
//给歌单增加歌曲
export const listSongAdd = (params) => post(`listSong/add`, params);
//删除歌单的歌曲
export const delListSong = (songId, songListId) => get(`listSong/delete?songId=${songId}&songListId=${songListId}`);


//----------用户相关接口----------
//添加用户
export const setConsumer = (params) => post(`consumer/add`, params);
//查询所有用户(无参数)
export const getAllConsumer = () => get(`consumer/allConsumer`);
// 修改用户
export const updateConsumer = (params) => post(`consumer/update`, params);
//删除用户
export const delConsumer = (id) => get(`consumer/delete?id=${id}`);
//根据用户id查询用户对象
export const getUserOfId =(id) => get(`/consumer/selectByPrimaryKey?id=${id}`);

//----------收藏相关接口----------
//删除收藏
export const deleteCollection = (userId,songId) => get(`collect/delete?userId=${userId}&songId=${songId}`);
//根据用户id查询其收藏列表
export const getCollectOfUserId = (userId) => get(`/collect/collectOfUserId?userId=${userId}`);


//----------评论相关接口----------
//根据歌单id查询其评论列表
export const getCommentOfSongListId = (songListId) => get(`/comment/commentOfSongListId?songListId=${songListId}`);
//删除评论
export const deleteComment = (id) => get(`/comment/delete?id=${id}`);


