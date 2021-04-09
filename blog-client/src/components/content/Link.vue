<template>
  <div class="site-footer">
    <!--侧边栏页脚-->
    <div class="side-bar-footer">
      <div class="links">
        <a href="https://github.com/sang-Mu/viturals-blog/tree/master" target="_blank"><span class="iconfont github">&#xe64a;</span></a>
        <a href="https://gitee.com/" target="_blank"><span class="iconfont gitee">&#xe6d0;</span></a>
        <a href="#" target="_blank"><span class="iconfont admin">&#xe6ed;</span></a>
        <a href="#" target="_blank"><span class="iconfont docs">&#xe6f7;</span></a>
        <a href="#" target="_blank"><span class="iconfont wx">&#xe601;</span></a>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'Link',
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus" scoped>
  .site-footer
    .side-bar-footer
      font-size 0.85rem
      font-style normal
      font-weight 400
      .links
        text-align center
        padding-bottom :15px
        a
          color inherit
          span
            font-size 1.2rem
            margin 0 10px
          .github
            &:hover
              color #098DE6
              cursor pointer
          .gitee
            &:hover
              color #C71D23
              cursor pointer
          .admin
            margin-left 5px
            margin-right 5px
            &:hover
              color #409EFF
              cursor pointer
          .docs
            &:hover
              color #41B883
              cursor pointer
          .wx
            margin-left 5px
            &:hover
              color #1AAD19
              cursor pointer
</style>
