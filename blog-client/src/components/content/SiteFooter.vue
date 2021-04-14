<template>
  <div class="site-footer">
    <!--侧边栏页脚-->
    <div class="side-bar-footer">
      <!-- <div class="links">
        <a href="https://github.com/progzc/zcblog" target="_blank"><span class="iconfont github">&#xe64a;</span></a>
        <a href="https://gitee.com/progzc" target="_blank"><span class="iconfont gitee">&#xe6d0;</span></a>
        <a href="#" target="_blank"><span class="iconfont admin">&#xe6ed;</span></a>
        <a href="#" target="_blank"><span class="iconfont docs">&#xe6f7;</span></a>
        <a href="http://qhnmn5y5g.hn-bkt.clouddn.com/personal-wx.jpg" target="_blank"><span class="iconfont wx">&#xe601;</span></a>
      </div> -->
      <div class="copyRight">
        {{ $t("copyright.desc") }}<span>&copy;</span>2021-2022 by
        {{ $t("homeNav.pseudonym") }}
      </div>
      <div class="site-static-info">
        <span class="pv"
          >{{ $t("homeNav.pageView") }}&nbsp;&nbsp;&nbsp;{{ pv }}</span
        >
        <span class="uv"
          >{{ $t("homeNav.uniqueVisitor") }}&nbsp;&nbsp;&nbsp;{{ uv }}</span
        >
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: "SiteFooter",
  data() {
    return {
      uv: 0,
      pv: 0,
    };
  },
  created() {
    // this.getPVUV();
    // 因为每个路由页面都有footer这个组件。因此在这个组件搞了一个方法来统计当前访问量和访客量
    // 访问量：一天之内，只要这个ip访问了多少个页面
    // 访客量：一天之内，一个ip只能是一个访客
    // 因为访问量本来就不是多可信的数据。所以通过ip+路由后缀来确认是不是同一个用户
    this.commitPVUV(this.$route.path);
  },
  methods: {

    commitPVUV(myroute) {
      const _this = this;
      this.$axios.get("statisUP?route=" + myroute).then(res => {
        console.log(res.data.data);
        _this.uv = res.data.data.uv;
        _this.pv = res.data.data.pv;
        console.log(111111);
        console.log(_this.pv);
      })
      .catch((err) => {
        
      });
    },
  },
};
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus" scoped>
.site-footer {
  .side-bar-footer {
    font-size: 0.85rem;
    font-style: normal;
    font-weight: 400;

    .links {
      text-align: center;

      a {
        color: inherit;

        span {
          font-size: 1.2rem;
          margin: 0 10px;
        }

        .github {
          &:hover {
            color: #098DE6;
            cursor: pointer;
          }
        }

        .gitee {
          &:hover {
            color: #C71D23;
            cursor: pointer;
          }
        }

        .admin {
          margin-left: 5px;
          margin-right: 5px;

          &:hover {
            color: #409EFF;
            cursor: pointer;
          }
        }

        .docs {
          &:hover {
            color: #41B883;
            cursor: pointer;
          }
        }

        .wx {
          margin-left: 5px;

          &:hover {
            color: #1AAD19;
            cursor: pointer;
          }
        }
      }
    }

    .copyRight {
      margin: 5px 0;
      text-align: center;
      opacity: 0.7;
    }

    .site-static-info {
      margin-top: 5px;
      padding-bottom: 5px;
      text-align: center;
      opacity: 0.7;

      .pv {
        margin-right: 15px;
      }

      .uv {
        margin-left: 15px;
      }
    }
  }
}
</style>
