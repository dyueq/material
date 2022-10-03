<template>
    <div>
        <el-card shadow="hover">
            <div class="user">
                <img :src="userImg" />
                <div class="userInfo">
                    <p class="name">
                        欢迎使用疫情物资慈善捐赠系统
                        <br>
                        <span>用户名：{{ userName === '' ? "您还没有登录哦" : userName }}</span>
                    </p>
                    <p class="access">身份：&nbsp;&nbsp;&nbsp;{{ userName === 'admin' ? "管理员" : "普通用户" }}</p>
                </div>
            </div>
            <hr />
            <div class="login-info">
                <p>
                    登录时间：
                    <span>{{ getNowDate() }}</span>
                </p>
            </div>
        </el-card>
        <div class="fa">
            <div style="width: 37.5rem; height: 18.75rem" ref="bar"></div>
            <div style="width: 37.5rem; height: 18.75rem" ref="pie"></div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            userImg: require("../assets/img/1.jpg"),
            barChart: null,
            pieChart: null,
            materialName: [],   //柱状图的x轴数据
            materialTotal: [],  //y轴数据
            materialArr: [],    //饼状图数据
            userName: '',

        }
    },
    created() {
        this.getSessionUser()
    },
    mounted() {
        this.drawBar()
        this.drawPie()
    },
    methods: {
        getSessionUser() {
            this.$axios.get(`/sessionUser`)
                .then((res) => {
                    this.userName = res.data.data.userName
                })
        },
        getNowDate() {
            let myDate = new Date();
            return myDate.toLocaleDateString();
        },
        drawBar() {
            //2.初始化
            this.barChart = this.$echarts.init(this.$refs.bar);
            this.$axios.get(`/nameAndTotal`)
                .then((res) => {
                    this.materialName = res.data.data.name
                    this.materialTotal = res.data.data.total
                    //3.配置数据
                    let barOption = {
                        title: {
                            text: '仓库物资详情',
                            subtext: '柱状图',
                            left: 'center'
                        },
                        tooltip: {},
                        xAxis: {
                            type: 'category',
                            axisLabel: {
                                interval: 0
                            },
                            data: this.materialName//x轴
                        },
                        yAxis: {}, //y轴
                        series: [{
                            name: '数量',
                            type: 'bar',
                            data: this.materialTotal //配置项
                        }]
                    };
                    // 4.传入数据
                    this.barChart.setOption(barOption);
                })
        },
        drawPie() {
            //2.初始化
            this.pieChart = this.$echarts.init(this.$refs.pie);
            this.$axios.get(`/getPie`)
                .then((res) => {
                    console.log(res);
                    let arr = res.data.data
                    for (let i = 0; i < arr.length; i++) {
                        let obj = {}
                        obj.value = arr[i].total
                        obj.name = arr[i].goods
                        this.materialArr.push(obj)
                    }
                    //3.配置数据
                    let pieOption = {
                        title: {
                            text: '仓库物资详情',
                            subtext: '饼状图',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left'
                        },
                        series: [
                            {
                                name: '数量',
                                type: 'pie',
                                radius: '50%',
                                data: this.materialArr,
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };
                    // 4.传入数据
                    this.pieChart.setOption(pieOption);
                })
        },
    }
};
</script>

<style lang="less" scoped>
.fa {
    display: flex;
    justify-content: space-around;
    margin-top: 3.125rem;

    div {
        box-shadow: 0 .125rem .75rem 0 rgba(0, 0, 0, 0.1);
    }
}

.el-card {
    margin-top: 1.25rem;
    margin-left: 3.4375rem;
    width: 37.5rem;
    height: 20rem;
}

.user img {
    width: 150px;
    border-radius: 50%;
}

.user .userInfo .access {
    color: rgb(83, 83, 83);
    font-size: 16px;
    margin: 5px;
}

.user .userInfo .name {
    font-size: 30px;
    margin: 5px;
}

.login-info {
    margin-top: 25px;
}

.login-info p {
    color: rgb(102, 99, 99);
    font-size: 14px;
}

.login-info p span {
    margin-left: 50px;
}
</style>