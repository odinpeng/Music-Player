<template> <!-- 返回顶部按钮 -->
    <div class="scroll-top" @click="returnTop">
        <div class="box-in"></div>
    </div>
    
</template>

<script>
export default {
    name: 'scroll-top',
    methods:{
        returnTop(){
            document.documentElement.scrollTop = document.body.scrollTop= 0; //使所有游览器支持
        }
    }
}
</script>

<style lang="scss" scope>
@import '../assets/css/scroll-top.scss';
</style>