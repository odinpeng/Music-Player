<template>
    <div class="singer-album">
        <!-- 歌手左侧信息 -->
        <div class="album-slide">
            <div class="singer-img">
                <img :src="attachImageUrl(singer.pic)">
            </div>
            <ul class="info">
                <li v-if="singer.sex==0||singer.sex==1">{{attachSex(singer.sex)}}</li>
                <li>生日：{{attachBirth(singer.birth)}}</li>
                <li>故乡：{{singer.location}}</li>
            </ul>
        </div>
        <!-- 歌手右侧信息 -->
        <div class="album-content">
            <div class="intro">
                <h2>{{singer.name}}</h2>
                <span>{{singer.introduction}}</span>
            </div>
            <div class="content">
                <album-content :songList="listOfSongs">
                    <template slot="title">歌单</template>
                </album-content>
            </div>
        </div>
    </div>
</template>

<script>
import {mixin} from '../mixins';
import {mapGetters} from 'vuex';
import {songOfSingerId} from '../api/index';
import AlbumContent from "../components/AlbumContent";

export default {
    name: 'singer-album',
    mixins: [mixin],
    components:{
        AlbumContent
    },
    data(){
        return {
            singerId:'',        //歌手id
            singer:{},          //歌手信息
            
        }
    },
    computed:{
        ...mapGetters([
            'listOfSongs',      //当前播放列表
            'tempList',         //当前歌手对象
            'loginIn',          //用户是否已经登录
            'userId',           //当前用户id
        ])
    },
    created(){
        this.singerId = this.$route.params.id;
        this.singer=this.tempList;
        this.getSongOfSingerId();
    },
    methods:{
        //根据歌手id查询歌曲
        getSongOfSingerId(){
            songOfSingerId(this.singerId)
                .then(res => {
                    this.$store.commit('setListOfSongs',res);
                })
                .catch(err =>{
                    console.log(err)
                })
        },
        //获取性别
        attachSex(value){
            if(value==0){
                return '女'
            }else if(value==1){
                return '男'
            }
            return ''
        },
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/singer-album.scss';
</style>