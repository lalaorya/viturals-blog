<template>
<!-- article.content是html格式的 -->
<!-- // github的markdown css样式 -->
  <div class="article-page-content markdown-body"  v-html="article.content">
    {{article.content}}
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'ArticlePageContent',
  props: ['article']
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus" scoped>

// 这里本来是引入article styl的样式
  // @import '~common/stylus/article/article.styl'

</style>
