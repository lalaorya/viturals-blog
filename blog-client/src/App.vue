<template>
  <div id="app">
    <router-view/>

    <meting-js server="netease" type="playlist" id="430671609" fixed="true" mini="true" list-folded="true" ></meting-js>
    
    
  </div>
</template>
<script>


export default {
  name: 'app'
}
</script>
<style lang="stylus" type="text/stylus" rel="stylesheet/stylus">
  @import '~common/stylus/index.styl'
  #app
   font-family $body-font
</style>
