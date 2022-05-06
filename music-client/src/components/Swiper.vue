<template>
    <div class="swiper">
        <!-- interval: 设置轮播图自动替换时间(ms) -->
        <el-carousel :interval="2000" type="card" height="280px"> 
            <el-carousel-item v-for="(item,index) in swiperList" :key="index">
                <img :src="item.picImg"/>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>


<script>
import {swiperList} from "../assets/data/swiper"; //引入swiper.vue

export default {
    name: "swiper",
    data(){
        return{
            swiperList: []
        }
    },
    created(){
        this.swiperList=swiperList;
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/swiper.scss';

</style>