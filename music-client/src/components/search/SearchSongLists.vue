<template>
    <!-- 歌单查询页面 -->
    <div class="search-song-lists">
        <content-list :contentList="albumDatas"></content-list>
    </div>
</template>

<script>
import ContentList from '../ContentList';
import {getSongListOfLikeTitle} from '../../api/index';
import {mixin} from '../../mixins';

export default {
    name: 'search-song-lists',
    components:{
        ContentList
    },
    data(){
        return{
            albumDatas: []
        }
    },
    mounted(){
        this.getSearchList();
    },
    methods:{
        //查询歌单
        getSearchList(){
            if(!this.$route.query.keywords){
                this.notify('您输入的内容为空','warning')
            }else{
                getSongListOfLikeTitle(this.$route.query.keywords)
                    .then(res =>{
                        if(res){
                            this.albumDatas = res
                        }else{
                            this.notify('暂无该歌曲内容','warning')
                        }
                    })
            }
        },
    }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/search-song-lists.scss';
</style>