<template>
  <div class="desk-side-bar">
    <!--侧边栏主要内容-->
    <div class="side-bar-sticky">
      <div class="author-header">
        <div class="author-avatar">
          <img src="~assets/img/blogImage.jpg" />
        </div>
        <div class="author-pseudonym">{{ $t("homeNav.pseudonym") }}</div>
        <div class="author-motto">{{ $t("homeNav.motto") }}</div>

        <Divider />
        <!-- github等链接的位置 -->
        <Link />

        <Collapse
          v-model="value2"
          accordion
          simple
          style="text-align: left; padding-left: 0px; font-weight: 500"
        >
          <Panel name="1" hide-arrow:true style="padding: 5px 0">
            公告栏😁
            <div slot="content">博客正在更新中!!</div>
          </Panel>
          <Panel name="2" hide-arrow:true style="padding: 5px 0">
            博客更新内容😎
            <div slot="content">debug中...</div>
          </Panel>
          <Panel name="3" hide-arrow:true style="padding: 5px 0">
            关于我🙋‍♂️
            <div slot="content">大三在校生，计算机专业</div>
          </Panel>
        </Collapse>
      </div>
      <div class="blog-search">
        <iv-input
          search
          :placeholder="$t('homeNav.searchPlaceholder')"
          v-model="keyWord"
          clearable
          @on-enter="search"
        ></iv-input>
      </div>
      <div class="side-bar-nav-menu">
        <div class="menu-item-home">
          <a class="menu-item-choose" href="/"
            ><span class="iconfont">&#xe501;</span>{{ $t("homeNav.home") }}</a
          >
        </div>
        <div class="menu-item-gallery">
          <a class="menu-item-choose" href="#"
            ><span class="iconfont">&#xe891;</span
            >{{ $t("homeNav.moment") }}</a
          >
        </div>
        <div class="menu-item-mix">
          <a class="menu-item-timeline" href="/timeline"
            ><span class="iconfont">&#xe7e3;</span
            >{{ $t("homeNav.timeline") }}</a
          >
          <span class="separator"></span>
          <a class="menu-item-tags" href="/tags"
            ><span class="iconfont">&#xe613;</span>{{ $t("homeNav.tags") }}</a
          >
          <span class="separator"></span>
          <a class="menu-item-tags" href="/tags"
            ><span class="iconfont">&#xe613;</span>{{ $t("homeNav.categorys") }}</a
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import Link from "../Link.vue";
import SiteFooter from "../SiteFooter.vue";
export default {
  components: { SiteFooter, Link },
  name: "DeskSideBar",
  data() {
    return {
      keyWord: "",
      value2: "0",
    };
  },
  mounted() {},
  methods: {
    search() {
      if (this.keyWord === "" || this.keyWord.length < 1) {
        this.$Message.warning({
          content: this.$t("warningMessage.nullForSearch"),
        });
      } else {
        this.$Message.success({
          content: this.$t("successMessage.successForSearch"),
        });
      }
    },
  },
};
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus" scoped>
.desk-side-bar {
  position: relative;
  width: 100%;

  .side-bar-sticky {
    padding: 14px 10px;
    text-align: center;

    .author-header {
      padding: 0rem;
      margin: 0rem;
      border-radius: 6px;
      background-color: $color-content-background;

      .author-avatar {
        display: block;
        text-align: center;


        img {
          display: inline-block;
          vertical-align: top;
          padding: 2px;
          width: 100%;
          max-width: 100px;
          border-radius: 50%;
          border: 1px solid $color-border;
        }
      }

      .author-pseudonym {
        margin: 0.8rem 0 0 0;
        line-height: 1.8rem;
        height: 1.8rem;
        white-space: nowrap;
        font-size: 1.3rem !important;
        font-weight: 400;
        color: $color-font-content;
      }

      .author-motto {
        margin: 0.5rem 0;
        font-weight: 300;
        color: $color-font-blog-desc;
      }

      // 穿透进去修改样式
      >>> .ivu-collapse-content>.ivu-collapse-content-box {
        padding-bottom: 5px;
      }

      >>> .ivu-collapse>.ivu-collapse-item>.ivu-collapse-header {
        padding-left: 10px;
      }

      >>> .ivu-collapse>.ivu-collapse-item>.ivu-collapse-header>i {
        margin-right: 2px;
      }

      >>> .ivu-divider-horizontal {
        margin-bottom: 20px;
      }
    }

    .blog-search {
      padding: 0.8rem 0;
      margin: 1rem 0.4rem;

      >>>.ivu-input { // 穿透进iViewUI组件更改样式
        border-color: $color-on-hover;
      }
    }

    .side-bar-nav-menu {
      padding: 0.5rem 0;
      margin: 3rem 0.8em 0.8rem 0.8rem;
      font-weight: normal;

      .menu-item-choose {
        display: inline-block;
        color: $color-font-content;
        text-decoration: none;

        &:hover {
          color: $color-on-hover;
          cursor: pointer;
        }
      }

      span {
        font-size: 1.05rem;
        margin: 0 5px;
      }

      .ivu-collapse-content>.ivu-collapse-content-box {
        padding-bottom: 3px;
      }

      .menu-item-mix {
        margin: 3rem 0 1rem 0;

        .menu-item-timeline {
          display: inline-block;
          color: $color-font-content;
          text-decoration: none;

          &:hover {
            color: $color-on-hover;
            cursor: pointer;
          }
        }

        .separator {
          margin: 0 6px 0 10px;
          border-right: 1px solid $color-gradually-gray-71;
        }

        .menu-item-tags {
          display: inline-block;
          color: $color-font-content;
          text-decoration: none;

          &:hover {
            color: $color-on-hover;
            cursor: pointer;
          }
        }
      }
    }
  }
}
</style>
