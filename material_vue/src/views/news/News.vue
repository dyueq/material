<template>
    <div class="top">
        <el-card class="box-card">
            <p>{{ annoTitle }}</p>
            <div class="text">
                {{ annoText }}
            </div>
        </el-card>
        <div class="body">
            <div class="left">
                <h3>公告列表</h3>
                <p @click="showAnno(item)" v-for="(item, index) in annoTitleArr" :key="index">{{ item }}</p>
            </div>
            <div class="right">
                <h3>新闻列表</h3>
                <p @click="showNews(item)" v-for="(item, index) in newsTitleArr" :key="index">{{ item }}</p>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: 'News',
    data() {
        return {
            annoTitle: '',
            annoText: '',
            annoArr: [],
            annoTitleArr: [],
            newsTitleArr: [],
        }
    },
    created() {
        this.getAnno()
    },
    methods: {
        getAnno() {
            this.$axios.get(`/getAnno`)
                .then((res) => {
                    this.annoArr = res.data.data.annoList
                    this.annoTitle = this.annoArr[this.annoArr.length - 1].annoTitle
                    this.annoText = this.annoArr[this.annoArr.length - 1].annoText

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
        showAnno(item) {
            this.$axios.get(`/AnnoByTitle/${item}`,)
                .then((res) => {
                    this.annoTitle = res.data.data.annoTitle
                    this.annoText = res.data.data.annoText
                })
        },
        showNews(item) {
            this.$axios.get(`/newsByTitle/${item}`)
                .then((res) => {
                    this.annoTitle = res.data.data.newsTitle
                    this.annoText = res.data.data.newsText
                })
        },
    },
    computed: {
        commonText() {
            let arr = this.annoText.split("")
            return arr.map((item) => {
                return item === "\n" ? "<br>" : item
            }).join("")
        }
    }
}
</script>
<style lang="less" scoped>
.top {
    margin-top: 20px;
    width: 100%;
}

.text {
    font-size: 14px;
    padding: 18px 0;
    white-space: pre-line;
}

.box-card {
    width: 100%;
    min-height: 400px;

    p {
        font-size: 30px;
        margin-top: 0;
        text-align: center;
    }
}

.body {
    margin-top: 30px;
    display: flex;
    justify-content: space-around;

    .left,
    .right {
        width: 500px;
        min-height: 300px;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

        p {
            margin-left: 10px
        }
    }

    h3 {
        color: #909399;
        text-align: center;
    }
}
</style>