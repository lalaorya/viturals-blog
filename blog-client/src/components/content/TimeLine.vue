<template>
  <div class="timeline">
    <slot name='title'></slot>
    <div class="item" v-for="item in itemList" :key="item.id">
      <div class="item-year" v-if="checkYear(item.publishTime)" >
        <span class="iconfont">&#xe503;</span>{{getYear(item.publishTime)}}
      </div>
      <div class="item-abstract" @click="handleToArticle(item.id)">
        <span class="item-date">{{getMonthDate(item.publishTime)}}</span>
        <span class="item-title">{{item.title}}</span>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import { str2Date } from 'common/js/utils'

export default {
  name: 'TimeLine',
  props: ['itemList', 'temp'],
  methods: {
    checkYear (publishTime) {
      var year = this.getYear(publishTime)
      if (this.temp.isEmpty()) {
        this.temp.put(year, 1)
        return true
      }
      if (this.temp.containsKey(year)) {
        return false
      } else {
        this.temp.put(year, 1)
        return true
      }
    },
    getYear (publishTime) {
      // return str2Date(publishTime).getFullYear()
      console.log(publishTime);
      return new Date(publishTime).getFullYear()
    },
    getMonthDate (publishTime) {
      // const monthDate = str2Date(createdTime)
      const monthDate = new Date(publishTime);
      let month = monthDate.getMonth() + 1
      month = month < 10 ? ('0' + '' + month) : month
      let date = monthDate.getDate()
      date = date < 10 ? ('0' + '' + date) : date
      return month + '-' + date
    },
    handleToArticle (id) {
      console.log('跳到id')
      this.$router.push({ path: `/article/${id}` });
      // this.$emit('linkToArticle', id)
    }
  }
}
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus" scoped>
  @import '~common/stylus/index.styl'
  .timeline
    .item
      .item-year
        margin 0.9rem 0 0.9rem 1rem
        font-weight 400
        font-size 1.2rem
        color $color-on-hover
        .iconfont
          margin-right 10px
      .item-abstract
        position relative
        margin 0 0 0 1.6rem
        height 1.8rem
        line-height 1.8rem
        font-weight 400
        border-left 1px solid $color-border
        &:before
          content '\e605'
          position absolute
          z-index 10
          left -9.5px
          top -1px
          font-size 18px
          font-family 'iconfont' !important
          opacity 0.65
        &:hover
          color $color-timeline-checked
          cursor pointer
          opacity 1
          font-weight 500
          font-size 15px
          transition-duration 0.2s
          transition-timing-function ease-in-out
          transition-delay 0s
          transition-property background
        .item-date
          margin 0 22px
        .item-title
          position absolute
          left 5rem
  @media screen and (max-width: $size-sm)
    .item-title
      overflow hidden
      width 9rem
      white-space nowrap
      text-overflow ellipsis
</style>
