<template>
    <div>
        <div class="top">
            <el-button type="primary" @click="announcement">新增公告</el-button>
            <el-button type="primary" @click="news">新增新闻</el-button>
        </div>
        <el-divider></el-divider>
        <div class="body">
            <div class="left">
                <p @click="showAnno(item)" v-for="(item, index) in annoTitleArr" :key="index">{{ item }}</p>
            </div>
            <div class="right">
                <p @click="showNews(item)" v-for="(item, index) in newsTitleArr" :key="index">{{ item }}</p>
            </div>
        </div>
        <!-- 新建公告 -->
        <el-dialog title="新建公告" :visible.sync="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="标题" :label-width="formLabelWidth">
                    <el-input v-model="form.annoTitle" placeholder="请输入标题" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="内容" :label-width="formLabelWidth">
                    <el-input type="textarea" autosize placeholder="请输入内容" v-model="form.annoText">
                    </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="fabu">发 布</el-button>
            </div>
        </el-dialog>
        <!-- 公告详情 -->
        <el-dialog :title=annoTitle :visible.sync="anno">
            <div>{{ annoText }}</div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="anno = false">取 消</el-button>
                <el-button type="primary" @click="deleteAnno">删 除</el-button>
            </div>
        </el-dialog>
        <!-- 新建新闻 -->
        <el-dialog title="新建新闻" :visible.sync="newsDialogFormVisible">
            <el-form :model="form">
                <el-form-item label="标题" :label-width="formLabelWidth">
                    <el-input v-model="form.newsTitle" placeholder="请输入标题" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="内容" :label-width="formLabelWidth">
                    <el-input type="textarea" autosize placeholder="请输入内容" v-model="form.newsText">
                    </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="newsDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="fabu">发 布</el-button>
            </div>
        </el-dialog>
        <!-- 新闻详情 -->
        <el-dialog :title=newsTitle :visible.sync="newsVisible">
            <div>{{ newsText }}</div>
            <div slot="footer" class="dialog-footer">
                <el-button @click="newsVisible = false">取 消</el-button>
                <el-button type="primary" @click="deleteNews">删 除</el-button>
            </div>
        </el-dialog>
    </div>


</template>
<script>
export default {
    name: 'NewsManage',
    data() {
        return {
            //新增公告
            dialogFormVisible: false,
            //新增新闻
            newsDialogFormVisible: false,
            //公告详情
            anno: false,
            //新闻详情
            newsVisible: false,
            formLabelWidth: '120px',
            form: {
                annoTitle: '',
                annoText: '',
                newsTitle: '',
                newsText: ''
            },

            annoTitle: '',
            annoText: '',
            annoId: 0,
            newsTitle: '',
            newsText: '',

            annoTitleArr: [],
            newsTitleArr: [],
        }
    },
    created() {
        this.getAll()
    },
    methods: {
        getAll() {
            this.$axios.get(`/getAnno`)
                .then((res) => {
                    let annoArr = []
                    let newsArr = []
                    for (let i = 0; i < res.data.data.annoList.length; i++) {
                        annoArr.push(res.data.data.annoList[i].annoTitle)
                        this.annoTitleArr = annoArr
                    }
                    for (let i = 0; i < res.data.data.newsList.length; i++) {
                        newsArr.push(res.data.data.newsList[i].newsTitle)
                        this.newsTitleArr = newsArr
                    }
                })
        },
        //打开新建公告页面
        announcement() {
            this.form = {}
            this.dialogFormVisible = true
        },
        fabu() {
            this.$axios.post(`/addAnno`, this.form)
                .then((res) => {
                    if (res.data.flag) {
                        this.$message({
                            type: "success",
                            message: "发布成功"
                        })
                    } else {
                        alert(res.data.msg)
                    }
                    this.getAll()
                })
            this.dialogFormVisible = false
            this.newsDialogFormVisible = false
        },
        news() {
            this.form = {}
            this.newsDialogFormVisible = true
        },
        showAnno(item) {
            this.anno = true
            this.$axios.get(`/AnnoByTitle/${item}`,)
                .then((res) => {
                    this.annoTitle = res.data.data.annoTitle
                    this.annoText = res.data.data.annoText
                    this.annoId = res.data.data.annoId
                })
        },
        showNews(item) {
            this.newsVisible = true
            this.$axios.get(`/newsByTitle/${item}`)
                .then((res) => {
                    this.newsTitle = res.data.data.newsTitle
                    this.newsText = res.data.data.newsText
                    this.annoId = res.data.data.annoId
                })
        },
        deleteAnno() {
            if (confirm("确定要删除吗")) {
                this.$axios.delete(`deleteAnno/${this.annoId}`)
                    .then((res) => {
                        if (res.data.flag) {
                            this.$message({
                                type: "success",
                                message: "删除成功"
                            })
                        }
                        this.getAll()
                    })
                this.anno = false
            }
        },
        deleteNews() {
            if (confirm("确定要删除吗")) {
                this.$axios.delete(`deleteAnno/${this.annoId}`)
                    .then((res) => {
                        if (res.data.flag) {
                            this.$message({
                                type: "success",
                                message: "删除成功"
                            })
                        }
                        this.getAll()
                    })
                this.newsVisible = false
            }
        }
    },
}
</script>
<style lang="less" scoped>
.top {
    height: 50px;
    display: flex;
    justify-content: space-around;
    margin-top: 1.875rem;
}

.body {
    display: flex;
    justify-content: space-around;

    .left,
    .right {
        width: 400px;
        min-height: 300px;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

        p {
            margin-left: 10px
        }
    }
}
</style>