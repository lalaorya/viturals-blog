<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item> <i class="el-icon-lx-calendar"></i> 博客 </el-breadcrumb-item>
                <el-breadcrumb-item>编写文章</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <!-- 和article进行双向绑定 -->
            <el-form ref="form" :model="article" label-width="80px">
                <el-row :gutter="20">
                    <el-col :span="12">
                        <el-form-item label="文章标题">
                            <el-input v-model="article.title" style="width: 400px"></el-input>
                        </el-form-item>
                        <el-form-item label="文章配图">
                            <el-upload
                                class="upload-demo"
                                ref="upload"
                                action="string"
                                accept="imgae/jpeg, image/png, image/jpg"
                                list-type="picture-card"
                                :before-upload="onBeforeUploadImage"
                                :on-change="fileChange"
                                :file-list="fileList"
                                :limit="1"
                            >
                                <!-- 文章上传先不处理，这行本放在上面空行 -->
                                <!-- :http-request="UploadImage" -->
                                <el-button size="small" type="primary">点击上传</el-button>
                                <div slot="tip" class="el-upload__tip">只能上传jpg/jpeg/png文件</div>
                            </el-upload>
                        </el-form-item>
                        <el-form-item label="内容摘要">
                            <el-input v-model="article.summary" type="textarea" :rows="2" style="width: 400px"></el-input>
                        </el-form-item>
                        <el-form-item label="发布时间" style="width: 400px">
                            <el-col :span="11">
                                <el-date-picker
                                    type="datetime"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    placeholder="选择日期"
                                    v-model="article.publishTime"
                                    style="width: 100%"
                                ></el-date-picker>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="是否置顶">
                            <el-switch v-model="article.top" :active-value="1" :inactive-value="0" active-color="#13ce66"></el-switch>
                        </el-form-item>
                        <el-form-item label="文章标签">
                            <el-select v-model="article.tags" multiple placeholder="+New Tag" size="mini">
                                <el-option v-for="item in tagsList" :key="item.id" :label="item.name" :value="item.name"> </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="文章分类">
                            <el-select v-model="article.categorys" multiple placeholder="+New Tag" size="mini">
                                <el-option v-for="item in categorysList" :key="item.id" :label="item.name" :value="item.name"> </el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                </el-row>
                <mavon-editor
                    v-model="article.content"
                    ref="md"
                    @imgAdd="$imgAdd"
                    @change="change"
                    style="min-height: 600px"
                    :ishljs="true"
                    codeStyle="atom-one-dark"
                />
                <el-form-item size="large">
                    <el-button class="editor-btn" type="primary" @click="release">发布文章</el-button>
                    <el-button class="editor-btn" type="primary" @click="saveblog">保存草稿</el-button>
                    <el-button class="editor-btn" type="info" @click="$router.back(-1)">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import { mavonEditor } from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';
var token = localStorage.getItem('token'); // 要保证取到
export default {
    name: 'markdown',
    data: function () {
        return {
            configs: {},
            // content: '',

            article: {
                id: null,
                title: '',
                summary: '',
                content: '',
                publishTime: '',
                updateTime: '',
                readNum: 0,
                likeNum: 0,
                pictureUrl: '',
                articleStatus: 0,
                top: 0,
                categorys: [],
                tags: []
            },

            html: '',
            // inputVisibleTag: false,
            // inputValueTag: '',
            // inputVisibleCategory: false,
            // inputValueCategory: '',
            // 所有分类
            categorysList: [],
            // 所有标签
            tagsList: [],
            fileList: [],
            logo: ''
        };
    },
    components: {
        mavonEditor
    },
    watch: {
        $route: function () {
            if (this.$route.query.blog_id >= 0) {
                this.getBlog();
            }else{
                this.article={};
            }
            // 获取所有标签和分类信息
            this.getTagsList();
            this.getCategorysList();
        }
    },
    created: function() {
        this.html = this.article.content;
        // 跳转到这个路由页面的时候，判断是否有路径变量blog_id,如果有,查询该id的文章并展示在页面上
        // 这个方法的设计是为了从文章列表的编辑跳转时直接导入文章信息
        // console.log(this.$route.query.blog_id);
        // if (this.$route.query.blog_id >= 0) {
        //     console.log('ppppppppppppppp');
        //     this.getBlog();
        //     // location.reload();
        // }
        // 获取所有标签和分类信息
        this.getTagsList();
        this.getCategorysList();
    },
    methods: {
        getBlog() {
            const _this = this;
            const id = this.$route.query.blog_id;
            // 这里改成发两次请求比较好，第一次请求查询detail，第二次请求查询list
            this.$axios.get(`/admin/article/${id}`).then((res) => {
                _this.article = res.data.data;
                // console.log(_this.article);
                // _this.content = res.data.data.blog_content;
                // _this.tagsList = res.data.data.tags;
                // _this.categorys = res.data.data.cateGory;
            });
        },
        getTagsList() {
            const _this = this;
            this.$axios.get('/admin/tag/list').then((res) => {
                _this.tagsList = res.data.data;
                // _this.categorys = res.data.data.cateGory;
            });
        },
        getCategorysList() {
            const _this = this;
            this.$axios.get('/admin/category/list').then((res) => {
                _this.categorysList = res.data.data;
                // _this.categorys = res.data.data.cateGory;
            });
        },

        // markdown中的图片上传
        // 将图片上传到服务器，返回地址替换到md中
        $imgAdd(pos, $file) {},
        change(value, render) {
            const _this = this;
            // // render 为 markdown 解析后的结果
            // //  this.blogDetail.blog_content=render;
            _this.html = render;

            console.log(render);
        },
        // 发布文章
        release() {
            const _this = this;
            // _this.blogDetail.tags = _this.tagsList;
            // _this.blogDetail.blog_content = _this.content;
            // _this.blogDetail.cateGory = _this.categorys;
            _this.article.articleStatus = 1;
            // _this.article.content = _this.html;
            console.log(_this.article);
            this.$axios
                .post('/admin/article/saveBlog', _this.article)
                .then((res) => {
                    if (res.data.code == 200) {
                        this.$message.success(res.data.msg);
                        // _this.$router.push({ path: '/admin/articles' })
                    } else {
                        this.$message.error(res.data.msg);
                    }
                })
                .catch((err) => {
                    this.$message.error('不要再试了哦，没有权限');
                });
        },
        // 存为草稿
        saveblog() {
            const _this = this;
            // _this.blogDetail.tags = _this.tagsList;
            // _this.blogDetail.blog_content = _this.content;
            // _this.blogDetail.cateGory = _this.categorys;
            _this.article.articleStatus = 0;
            this.$axios
                .post('/admin/saveBlog', _this.blogDetail, {
                    headers: {
                        Authorization: localStorage.getItem('token')
                    }
                })
                .then((res) => {
                    if (res.data.code == 200) {
                        this.$message.success(res.data.msg);
                    } else {
                        this.$message.error(res.data.msg);
                    }
                })
                .catch((err) => {
                    this.$message.error('不要再试了哦，没有权限');
                });
        },

        // 上传前校验格式
        onBeforeUploadImage(file) {
            const isIMAGE = file.type === 'image/jpeg' || 'image/jpg' || 'image/png';
            const isLt1M = file.size / 1024 / 1024 < 10;
            if (!isIMAGE) {
                this.$message.error('上传文件只能是图片格式!');
            }
            if (!isLt1M) {
                this.$message.error('上传文件大小不能超过 10MB!');
            }
            return isIMAGE && isLt1M;
        },
        UploadImage(param) {
            // 新建一个表单对象，和我们写得表单用法一样
            console.log(param.file);
            const formData = new FormData();
            formData.append('blog_img', param.file);
            this.$axios
                .post(
                    '/admin/uploadImg',
                    formData
                    // headers: {
                    //     Authorization: localStorage.getItem('token')
                    // }
                )
                .then((res) => {
                    this.$message.success(res.data.msg);
                    this.blogDetail.blog_cover_image = res.data.data;
                    param.onSuccess(); // 上传成功的图片会显示绿色的对勾
                    // 但是我们上传成功了图片， fileList 里面的值却没有改变，还好有on-change指令可以使用
                })
                .catch((err) => {
                    this.$message.error('没有权限啦');
                    param.onError();
                });
        },
        fileChange(file) {
            this.$refs.upload.clearFiles(); //清除文件对象
            this.logo = file.raw; // 取出上传文件的对象，在其它地方也可以使用
            this.fileList = [{ name: file.name, url: file.url }]; // 重新手动赋值filstList， 免得自定义上传成功了, 而fileList并没有动态改变， 这样每次都是上传一个对象
        }
    }
};
</script>
<style scoped>
.editor-btn {
    margin-top: 20px;
}
.el-tag + .el-tag {
    margin-left: 10px;
}
.button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
}
.input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
}
</style>