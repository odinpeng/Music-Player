<template>
    <!-- 歌单查询页面 -->
    <div class="song-list">
        <!-- 歌单头 -->
        <ul class="song-list-header">
            <li v-for="(item,index) in songStyle" :key="index" @click="handleChangeView(item.name)"
                :class="{active:item.name==activeName}">
                {{item.name}}
            </li>
        </ul>
        <!-- 歌单内容 -->
        <div>
            <content-list :contentList="data"></content-list>
            <div class="pagination">
                <el-pagination @current-change="handleCurrentChange" background layout="total,prev,pager,next"
                        :current-page="currentPage" :page-size="pageSize" :total="albumDatas.length">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
import ContentList from '../components/ContentList';
import {getAllSongList, getSongListOfLikeTitle,getSongListOfLikeStyle} from '../api/index';
import {mixin} from '../mixins';
import {songStyle} from '../assets/data/songList';

export default {
    name: 'song-list',
    components:{
        ContentList
    },
    data(){
        return{
            albumDatas: [],    //歌单数据
            pageSize: 15,      //页面显示的歌单数
            currentPage:1,     //当前页数
            songStyle:[],      //歌单风格
            activeName:'全部歌单',        //当前风格的焦点
        }
    },
    computed:{
        //计算当前表格中的数据
        data(){
            return this.albumDatas.slice((this.currentPage - 1)*this.pageSize,this.currentPage*this.pageSize);
        }
    },
    mounted(){
        this.songStyle=songStyle;
        this.getSongList();
    },
    methods:{
        //查询歌单
        getSongList(){
            getAllSongList()
            .then(res=>{
                this.currentPage=1;
                this.albumDatas = res
            })
        },
        //获取当前页
        handleCurrentChange(val){
            this.currentPage=val;
        },
        //根据style显示对应的歌单
        handleChangeView(name){
            this.activeName = name;
            this.albumDatas = [];
            if(name == '全部歌单'){
                this.getSongList();
            }else{
                this.goSongListOfStyle(name)
            }
        },
        //根据style查询对应的歌单
        goSongListOfStyle(style){
            getSongListOfLikeStyle(style)
                .then(res =>{
                        this.currentPage = 1;
                        this.albumDatas = res;
                    }) 
        },
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/song-list.scss';
</style>