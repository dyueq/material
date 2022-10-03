<template>
    <div class="top">
        <div class="box1">
            <img src="../assets/img/1.jpg" />
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                class="demo-ruleForm">
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model.number="ruleForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="ruleForm.password" show-password></el-input>
                </el-form-item>
                <a>
                    <router-link to="/regist">点我注册账号</router-link>
                </a>
                <el-form-item class="last">
                    <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
            <div v-show="isShow" class="slide">
                <slide-verify ref="slideblock" @again="onAgain" @success="onSuccess" @fail="onFail" :slider-text="text"
                    :accuracy="accuracy" :imgs="imgArr"></slide-verify>
                <div>{{ msg }}</div>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    name: 'Login',
    data() {
        // 手机号码验证规则
        let phoneReg = /^[1][3-9][0-9]{9}$/
        // 密码验证规则
        let passReg = /^[_a-zA-Z0-9]+$/
        // 验证手机号码 
        var checkPhone = (rule, value, callback) => {
            if (!value) {
                return callback(new Error('手机号码不能为空'));
            }
            setTimeout(() => {
                if (!Number.isInteger(value)) {
                    callback(new Error('请输入数字值'));
                } else {
                    if (!phoneReg.test(value)) {
                        callback(new Error('请如实填写手机号码'))
                    } else {
                        callback();
                    }
                    callback();
                }
            }, 500);
        };
        // 验证密码
        var checkPass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (!passReg.test(value)) {
                    callback(new Error('只能由数字、字母、下划线组成'))
                }
                callback();
            }
        };
        return {
            // 表单数据
            ruleForm: {
                password: '',
                phone: ''
            },
            // 验证规则
            rules: {
                password: [
                    { validator: checkPass, trigger: 'blur' }
                ],
                phone: [
                    { validator: checkPhone, trigger: 'blur' }
                ]
            },
            //验证码是否显示
            isShow: false,
            imgArr: [
                require('@/assets/img/1.jpg'),
                require('@/assets/img/2.png'),
                require('@/assets/img/3.png'),
                require('@/assets/img/4.png'),
                require('@/assets/img/5.png'),
                require('@/assets/img/6.jpg'),
            ],
            msg: '',
            text: '向右滑动->',
            // 精确度小，可允许的误差范围小；为1时，则表示滑块要与凹槽完全重叠，才能验证成功。默认值为5
            accuracy: 10,
        };
    },
    methods: {
        // 提交
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    // alert('submit!');
                    // console.log(this.ruleForm);
                    this.$axios.post("/user/login", this.ruleForm)
                        .then((res) => {
                            // console.log(res);
                            if (res.data.flag) {
                                // alert(res.data.msg)
                                this.isShow = true
                            } else {
                                alert(res.data.msg)
                            }
                        })

                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        // 重置
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        onSuccess(times) {
            this.msg = `验证成功, 耗时${(times / 1000).toFixed(1)}s`
            setTimeout(() => {
                this.$router.push({ name: 'main' })
            }, 1000);
        },
        onFail() {
            this.msg = '验证失败，请重试'
        },
        onAgain() {
            this.msg = '验证异常，再试一次';
            // 刷新
            this.$refs.slideblock.reset();
        },
    }
}
</script>
<style lang="less" scoped>
.top {
    width: 1680px;
    height: 900px;
    background-color: rgb(54, 53, 53);
}

.box1 {
    height: 25rem;
    width: 25rem;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    // background-color: aqua;
    border: 1px solid black;
    box-shadow: 5px 5px black;



    .el-form {
        margin-top: 100px;
        margin-right: 50px;

        .last {
            margin-top: 50px;
            margin-left: 50px;
        }
    }

    a {
        float: right;
        font-size: 14px;
        text-decoration: none;
        color: white;
    }

    img {
        position: absolute;
        height: 100px;
        transform: translate(150%, -50%);
        border-radius: 50%;
    }

    .slide {
        position: absolute;
        left: 55%;
        top: 105%;
        transform: translate(-50%, -50%);
    }
}
</style>