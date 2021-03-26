package com.hhj.blogbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhj.blogbackend.dto.ArticleDetail;
import com.hhj.blogbackend.mapper.ArticleMapper;
import com.hhj.blogbackend.mapper.ArticleTagRelationMapper;
import com.hhj.blogbackend.mapper.TagMapper;
import com.hhj.blogbackend.pojo.Article;
import com.hhj.blogbackend.pojo.ArticleCategoryRelation;
import com.hhj.blogbackend.pojo.ArticleTagRelation;
import com.hhj.blogbackend.pojo.Category;
import com.hhj.blogbackend.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ArticleServiceImpl  implements ArticleService {
    @Autowired
    ArticleMapper mapper;

    @Autowired
    TagService tagService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleTagRelationService articleTagRelationService;

    @Autowired
    ArticleCategoryRelationService articleCategoryRelationService;
    @Override
    public List<Article> selectList() {
        List<Article> articles = mapper.selectList(null);
        return articles;
    }

    @Override
    public Integer selectCount() {
        Integer integer = mapper.selectCount(null);
        return integer;
    }

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param queryWrapper 查询条件
     * @return
     */
    @Override
    public Page selectPage(int currentPage,Wrapper<Article> queryWrapper) {
        Page<Article> page = new Page<>(currentPage, 6);
        mapper.selectPage(page, null);
        System.out.println(page);
        return page;
    }

    @Override
    public int deleteById(Serializable id) {
        int i = mapper.deleteById(id);
        return i;
    }

    @Override
    public int updateById(Article entity) {
        int i = mapper.updateById(entity);
        return i;
    }

    @Override
    public Article selectById(Serializable id) {
        Article article = mapper.selectById(id);
        return article;
    }

    @Override
    public ArticleDetail selectDetailById(Serializable id) {
        /**
         * 根据文章id获取标签数组
         */
        // 根据文章id查找TagidList
        List<Integer> tagIdList = articleTagRelationService.selectList(id);
        log.info("查询了id为{}的文章的标签序号为{}",id,tagIdList.toString());
        // 根据TagIdList查找 TagLsit
        List<String> tagList = tagService.selectTagById(tagIdList);
        log.info("查询了标签序号为{}的标签名为{}",tagIdList.toString(),tagList.toString());

        /**
         * 根据文章id获取分类数组
         */
        // 根据文章id查找categoryId List
        List<Integer> categoryIdList = articleCategoryRelationService.selectList(id);
        log.info("查询了id为{}的文章的分类序号为{}",id,categoryIdList.toString());
        // 根据categoryIdList查找 categoryList
        List<String> categoryList = categoryService.selectCategoryById(categoryIdList);
        log.info("查询了分类序号为{}的标签名为{}",categoryIdList.toString(),categoryList.toString());

        /**
         *  根据文章id查找文章
         */
        Article article = mapper.selectById(id);
        log.info("查询了文章序号为{}的文章{}",id,article.getTitle());

        ArticleDetail articleDetail = new ArticleDetail();
        BeanUtil.copyProperties(article,articleDetail,false);
        articleDetail.setTags((ArrayList)tagList);
        articleDetail.setCategorys((ArrayList)categoryList);

        return articleDetail;
    }



    @Override
    public boolean saveBlog(ArticleDetail articleDetail) {
        Article article = new Article();
        BeanUtil.copyProperties(articleDetail,article);
        System.out.println(article.getId()+"ppppppppppppppppppppppppppppppppp");
        if(article.getId()==0){
            article.setId(mapper.selectCount(null)+1);
        }
        try{
            mapper.insert(article);
        }catch (Exception e){
            mapper.updateById(article);
        }



//        log.info(article.toString());
        ArrayList<String> tags = articleDetail.getTags();
        // 根据标签名查询标签id
        List<Integer> ids = tagService.selectIdByName(tags);
        // 根据文章id和标签id更新关系表
        List<ArticleTagRelation> list=new ArrayList<>();
        for(Integer i:ids){
            list.add(new ArticleTagRelation(article.getId(),i));
        }
        articleTagRelationService.saveBatch(list);

        // 根据分类名查询分类id
        ArrayList<String> categorys = articleDetail.getCategorys();
        List<Category> idCg = categoryService.list((Wrapper) new QueryWrapper<>().select("id").in("name", categorys));
        List<Integer> ids2 = new ArrayList<>();
        for(Category c:idCg){
            ids2.add(c.getId());
        }
        log.info(ids2.toString());

        // 根据文章id和分类id更新关系表
        List<ArticleCategoryRelation> list2=new ArrayList<>();
        for(Integer i:ids2){
            list2.add(new ArticleCategoryRelation(article.getId(),i));
        }
        articleCategoryRelationService.saveBatch(list2);

        return true;
    }


}
