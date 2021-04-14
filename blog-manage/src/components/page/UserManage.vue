<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-addressbook"></i> 用户管理
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="query.nick_name" placeholder="用户名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜索</el-button>
                <el-button type="primary" icon="el-icon-refresh" @click="refreshData">重置</el-button>
            </div>
            <el-table
                :data="users"
                border
                :row-class-name="tableRowClassName"
                class="table"
                header-cell-class-name="table-header"
            >
                <el-table-column prop="id" label="ID" width="55" align="center"></el-table-column>
                <el-table-column prop="username" label="用户名"></el-table-column>
                <!-- <el-table-column prop="nick" label="昵称"></el-table-column> -->
                
                
                <!-- <el-table-column label="评论内容" width="200">
                    <template slot-scope="scope">{{scope.row.content}}</template>
                </el-table-column> -->
                <el-table-column label="用户头像" align="center">
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.avatar"
                            :preview-src-list="[scope.row.avatar]"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="introduction" label="用户简介"></el-table-column>

                <el-table-column prop="role" label="用户角色"></el-table-column>

                <el-table-column label="操作" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-delete"
                            class="red"
                            @click="handleDelete(scope.row.id)"
                        >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 分页条 -->
            <div class="pagination">
                <el-pagination
                    @current-change="getData"
                    :current-page="currentPage"
                    :page-count="total"
                    layout="prev, pager, next"
                    background
                    hide-on-single-page
                ></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'users',
    inject: ['reload'],
    data() {
        return {
            query: {
                username: ''
            },
            users:[],
            // comments: [],
            currentPage: 1,
            pageSize: 5,
            total: 0
        };
    },
    mounted() {
        this.getData(1,5);
    },
    methods: {
        getData(currentPage,pageSize) {
            const _this = this;
            this.$axios
                .post('/admin/user/list', 
                     {
                        currentPage: currentPage,
                        pageSize: pageSize
                    }
                )
                .then(res => {
                    _this.users = res.data.data.records;
                    _this.currentPage = res.data.data.current;
                    _this.total = res.data.data.pages;
                });
        },
        // 触发搜索按钮
        // handleSearch() {
        //     const _this = this;
        //     this.$axios
        //         .get('/admin/comment/' + _this.query.nick_name, {
        //             headers: {
        //                 Authorization: localStorage.getItem('token')
        //             }
        //         })
        //         .then(res => {
        //             if (res.data.code == 200) {
        //                 this.$message.success(res.data.msg);
        //                 _this.comments = res.data.data;
        //             } else {
        //                 this.$message.error(res.data.msg);
        //             }
        //         });
        // },
        // 删除操作
        handleDelete(index) {
            // 二次确认删除
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            })
                .then(() => {
                    this.$axios
                        .get('/admin/user/delete'+'?id='+index
                        )
                        .then(res => {
                            if (res.data.code == 200) {
                                this.$message.success(res.data.msg);
                                this.reload();
                            } else {
                                this.$message.error(res.data.msg);
                            }
                        })
                        .catch(err => {
                            this.$message.error('不要再试了哦，没有权限');
                        });
                })
                .catch(() => {});
        },
        
        refreshData() {
            this.getData(1);
        },
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
.table-td-thumb {
    display: block;
    margin: auto;
    width: 40px;
    height: 40px;
}
/*上传图片*/
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.avatar-uploader .el-upload:hover {
    border-color: #409eff;
}
.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}
.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
.el-table >>> .warning-row {
    background: oldlace;
}

.el-table .success-row {
    background: #f0f9eb;
}
</style>
