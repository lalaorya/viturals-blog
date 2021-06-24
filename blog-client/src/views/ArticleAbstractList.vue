<template>
  <!-- 首页 -->
  <content-box>
    <div class="article-abstract-container" slot="content">
      <!--文章摘要内容-->
      <div
        class="article-abstract-item"
        v-for="article in articleAbstractList"
        :key="article.id"
      >
        <h1>
          <a href="javascript:void(0);" @click="handleToArticle(article.id)">
            {{ article.title }}</a
          ><span class="toTop iconfont" v-if="article.top === 1"
            >[{{ $t("homeNav.toTop") }}]</span
          >
        </h1>
        <article-info :article="article"></article-info>
        <p>
          {{ article.summary }}
          <!-- 根据id跳转到文章详细路由 -->
          <span class="article-link" @click="handleToArticle(article.id)">
            <!-- seeMore 国际化内容 -->
            {{ $t("homeNav.seeMore") }}
          </span>
        </p>
        <!-- 标签 -->
        <tag-wall :tags="article.tags"></tag-wall>
      </div>
      <!--页码-->
      <div class="article-abstract-page-container">
        <iv-page
          class-name="article-abstract-pagination"
          v-if="pagination.total"
          :total="pagination.total"
          :current="pagination.currentPage"
          :pageSize="pagination.pageSize"
          @on-change="handleCurrentChange"
        >
          
        </iv-page>
      </div>
      <site-footer id="site-footer"></site-footer>
    </div>
    
  </content-box>
</template>

<script type="text/ecmascript-6">
import ContentBox from "components/content/ContentBox";
import TagWall from "components/content/TagWall";
import ArticleInfo from "components/content/ArticleInfo";
import SiteFooter from 'components/content/SiteFooter'

export default {
  name: "ArticleAbstractList",
  components: {
    "content-box": ContentBox,
    "tag-wall": TagWall,
    "article-info": ArticleInfo,
    'site-footer': SiteFooter
  },
  data() {
    return {
      pagination: {
        // 总文章数目
        total: 0,
        currentPage: 1,
        // 每页5条记录s
        pageSize: 10,
      },

      articleAbstractList:[],
     
    };
  },
  created() {
    this.findPage();
  },
  methods: {
    checkPage() {
      return this.articleAbstractList.length >= this.pagination.pageSize;
    },
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.findPage();
      console.log(currentPage)
    },
    findPage() {
      const _this=this;
      var param = {
        // 封装查询条件
        currentPage: this.pagination.currentPage,
        pageSize: this.pagination.pageSize,
      };
      // 发送Ajax请求，提交分页相关参数
      console.log(param);
       this.$axios.post("blog/page",param).then(res => {
        console.log(res.data.data)
        // _this.blogcount = res.data.data;
        _this.articleAbstractList=res.data.data.records;
        _this.pagination.total=res.data.data.total;
        // _this.pagination.currentPage=res.data.data.current;
        
      });
    },
    handleToArticle(id) {
      console.log("跳转到文章" + id);
      // 这里应该是/id 动态路由
      this.$router.push({ path: `/article/${id}` });
    },
  },
};
</script>

<style lang="stylus" type="text/stylus" rel="stylesheet/stylus" scoped>
@import '~common/stylus/index.styl';

.article-abstract-container {
  .article-abstract-item {
    display: block;
    padding: 1rem 1.2rem 1rem 1.2rem;
    margin: 40px 10px 0 10px;
    border-radius: 6px;
    background-color: $color-content-background;

    &:hover {
      -webkit-box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
      -moz-box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    }

    &:first-of-type {
      margin-top: 10px;
    }

    &:last-of-type {
      margin-bottom: $footer-height-pageContent;
    }

    h1>a {
      height: 1.6rem;
      line-height: 1.6rem;
      text-align: left;
      font-size: 20px;
      font-weight: 400;

      &:hover {
        color: $color-on-hover;
        cursor: pointer;
        border-bottom: 1px solid $color-on-hover;
      }
    }

    h1>span {
      margin-left: 5px;
      color: $color-nav;
      line-height: 1.8rem;
      height: 1.8rem;
      font-size: 1.2rem;
      font-weight: 500;
    }

    p {
      font-weight: 400;
      font-size: 14px;
      text-indent: 1.6rem;
      line-height: 1.6rem;
      text-align: justify;

      .article-link {
        color: $color-on-hover;

        &:hover {
          border-bottom: 1px solid $color-on-hover;
          cursor: pointer;
        }

        &:after {
          content: '\e517';
          margin-left: 0;
          font-family: 'iconfont' !important;
        }
      }
    }
  }

  .article-abstract-page-container {
    padding: 50px 0 80px 0;
    height: 1.5rem;
    line-height: 1.5rem;
    text-align: center;

    .article-abstract-pagination {
      >>>.ivu-page-item {
        width: 20px !important;
        min-width: 20px !important;
        border: none !important;
        border-radius: 0 !important;
        background: none !important;
      }

      >>>.ivu-page-item-active {
        border: none !important;
        background-color: $color-on-hover !important;
      }

      >>>a {
        margin: 0 !important;
        color: #515a6e;
        font-family: $body-font !important;
      }

      >>>.ivu-page-prev {
        border: none !important;
        border-radius: 0 !important;
        background: none !important;
      }

      >>>.ivu-page-next {
        border: none !important;
        border-radius: 0 !important;
        background: none !important;
      }
    }
  }
}

>>>.content-header { // 解决添加阴影导致的盒子下沉的效果(若不下沉,盒子上边阴影又无法显现)
  height: $header-height-pageContent - 10px !important;
}

@media screen and (max-width: $size-sm) {
  >>>.content-container {
    width: 100% !important;
  }

  >>>.content-header {
    height: 0 !important;
  }

  .article-abstract-item:first-of-type {
    margin-top: 0 !important; // 解决移动端上边距不一致问题（根源均在于为添加上边阴影导致的问题）
  }
}
</style>
