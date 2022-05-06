<template>
  <div id="app">    
    <the-header/>
    <router-view class="music-content"/>   
    <song-audio/>
    <the-aside/>
    <play-bar/>
    <scroll-top/>
    <the-footer/>
  </div>
</template>

<script>
import PlayBar from './components/PlayBar.vue';
import ScrollTop from './components/ScrollTop.vue';
import SongAudio from './components/SongAudio.vue';
import TheFooter from './components/TheFooter.vue';
import TheHeader from './components/TheHeader'; 
import TheAside from './components/TheAside';

export default {
  name: 'App',
  components: {
    TheHeader,
    ScrollTop,
    TheFooter,
    SongAudio,
    PlayBar,
    TheAside
  }
}
</script>


<style  lang="scss" scoped>
@import './assets/css/app.scss';
</style>
