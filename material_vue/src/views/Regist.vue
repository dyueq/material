<template>
    <div class="top">
        <div class="box1">
            <router-link to="/">已有账号，前往登录</router-link>
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                class="demo-ruleForm">
                <el-form-item label="用户名" prop="userName">
                    <el-input v-model="ruleForm.userName"></el-input>
                </el-form-item>
                <el-form-item label="身份证号" prop="userIdcard">
                    <el-input v-model="ruleForm.userIdcard"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="userEmail">
                    <el-input v-model="ruleForm.userEmail"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="userPhone">
                    <el-input v-model.number="ruleForm.userPhone"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="userPassword">
                    <el-input type="password" v-model="ruleForm.userPassword" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item class="last">
                    <el-button type="primary" @click="submitForm('ruleForm')">注册</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
<script>
export default {
    name: "Regist",
    data() {
        // 手机号码正则表达式
        let phoneReg = /^[1][3-9][0-9]{9}$/
        // 密码正则表达式
        let passReg = /^[_a-zA-Z0-9]+$/
        // 身份证号正则表达式
        let idcardReg = /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
        // 邮箱正则表达式
        let emailReg = /^([a-zA-Z0-9]+[-_\.]?)+@[a-zA-Z0-9]+\.[a-z]+$/
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
        // 验证确认密码
        var checkPass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.userPassword) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        var checkUser = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入用户名'));
            } else if (value.length > 5) {
                callback(new Error('用户名不能多于5个汉字'));
            } else {
                callback();
            }
        }
        var checkIdcard = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入身份证号'));
            } else if (!idcardReg.test(value)) {
                callback(new Error('请输入有效的身份证号'));
            } else {
                callback();
            }
        }
        // 验证邮箱
        var checkEmail = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入邮箱'));
            } else if (!emailReg.test(value)) {
                callback(new Error('请输入有效的邮箱'));
            } else {
                callback();
            }
        }
        return {
            // 表单数据
            ruleForm: {
                userName: '',
                userIdcard: '',
                userEmail: '',
                userPhone: '',
                userPassword: '',
                checkPass: ''
            },
            // 验证规则
            rules: {
                userPassword: [
                    { validator: checkPass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: checkPass2, trigger: 'blur' }
                ],
                userPhone: [
                    { validator: checkPhone, trigger: 'blur' }
                ],
                userName: [
                    { validator: checkUser, trigger: 'blur' }
                ],
                userIdcard: [
                    { validator: checkIdcard, trigger: 'blur' }
                ],
                userEmail: [
                    { validator: checkEmail, trigger: 'blur' }
                ]
            }
        };
    },
    methods: {
        // 提交
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                // 成功
                if (valid) {
                    this.$axios.post(`/user`, this.ruleForm)
                        .then((res) => {
                            if (res.data.flag) {
                                alert(res.data.msg)
                                this.$router.push({ name: "login" })
                            } else {
                                alert(res.data.msg)
                            }
                        })
                    // 失败
                } else {
                    return false;
                }
            });
        },
        // 重置
        resetForm(formName) {
            this.$refs[formName].resetFields();
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
    height: 30rem;
    width: 30rem;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    border: 1px solid black;
    box-shadow: 5px 5px black;
    padding: 1rem;

    .el-form {
        margin-top: 30px;
        margin-right: 30px;

        .last {
            // margin-top: 50px;
            margin-left: 80px;
        }
    }

    a {
        float: right;
        font-size: 14px;
        text-decoration: none;
        color: white;
    }
}
</style>