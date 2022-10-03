<template>
    <div>
        <div class="top">
            <el-form :inline="true" :model="userInfo">
                <el-form-item label="用户名">
                    <el-input v-model="userInfo.userName" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="手机号码">
                    <el-input v-model="userInfo.userPhone" placeholder="请输入手机号码"></el-input>
                </el-form-item>
                <el-form-item label="身份证号">
                    <el-input v-model="userInfo.userIdcard" placeholder="请输入用户身份证号"></el-input>
                </el-form-item>
                <el-form-item label="是否冻结">
                    <el-select v-model="userInfo.userDisable" placeholder="请选择用户状态">
                        <el-option label value></el-option>
                        <el-option label="已冻结" value="1"></el-option>
                        <el-option label="未冻结" value="0"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <hr />
        <template>
            <el-table
                :data="tableData"
                style="width: 100%"
                :default-sort="{ prop: 'date', order: 'descending' }"
            >
                <el-table-column
                    v-for="(val, key) in tableHead"
                    :key="key"
                    :prop="key"
                    :label="val"
                    sortable
                    width="180"
                    class="cell"
                    align="center"
                ></el-table-column>
                <el-table-column label="是否冻结" width="180" header-align="center" align="center">
                    <template slot-scope="scope">
                        <el-button
                            v-show="scope.row.userDisable === 0"
                            type="danger"
                            icon="el-icon-delete"
                            size="mini"
                            @click="toDisabled(scope)"
                        >冻结</el-button>
                        <p v-show="scope.row.userDisable === 1">已冻结</p>
                    </template>
                </el-table-column>
                <el-table-column label="是否解冻" width="180" header-align="center" align="center">
                    <template slot-scope="scope">
                        <el-button
                            v-show="scope.row.userDisable === 1"
                            type="primary"
                            icon="el-icon-edit"
                            size="mini"
                            @click="unDisabled(scope)"
                        >解冻</el-button>
                        <p v-show="scope.row.userDisable === 0">已解冻</p>
                    </template>
                </el-table-column>
            </el-table>
        </template>
        <div>
            <el-pagination
                @current-change="handleCurrentChange"
                :current-page="page.currentPage"
                :page-size="page.size"
                layout="total, prev, pager, next"
                :total="page.total"
            ></el-pagination>
        </div>
    </div>
</template>
<script>
export default {
    name: 'UserManage',
    data() {
        return {
            tableHead: {
                userName: "用户名",
                userIdcard: "身份证号",
                userPhone: "手机号码",
                userEmail: "用户邮箱",
                // userDisable: "是否禁用"
            },
            tableData: [],
            userInfo: {
                userName: "",
                userIdcard: "",
                userPhone: "",
                userDisable: ""
            },
            //分页数据
            page: {
                currentPage: 1,
                size: 5,
                total: 3,
            },
        }
    },
    created() {
        this.getAll()
    },
    methods: {
        getAll() {
            this.$axios.get(`/user/${this.page.currentPage}/${this.page.size}`)
                .then((res) => {
                    // console.log(res);
                    this.tableData = res.data.data.records;
                    this.page.size = res.data.data.size;
                    this.page.currentPage = res.data.data.current;
                    this.page.total = res.data.data.total;
                })
        },
        //分页
        handleCurrentChange(val) {
            // 更改页码为当前页 再查询
            this.page.currentPage = val;
            this.getAll();
        },
        //冻结
        toDisabled(scope) {
            if (confirm("是否冻结该用户")) {
                scope.row.userDisable = 1
                // console.log(scope.row);
                //调方法---修改userDisable = 1
                this.$axios.put(`/user/updateInfo/`, scope.row)
                    .then((res) => {
                        if (res.data.flag) {
                            this.$message({
                                type: 'success',
                                message: "修改成功"
                            })
                        }
                    })
            }
        },
        //解冻
        unDisabled(scope) {
            if (confirm("是否解冻该用户")) {
                scope.row.userDisable = 0
                //调方法---修改userDisable = 1
                this.$axios.put(`/user/updateInfo/`, scope.row)
                    .then((res) => {
                        if (res.data.flag) {
                            // alert("修改成功")
                            this.$message({
                                type: 'success',
                                message: "修改成功"
                            })
                        }
                    })
            }
        },
        //提交表单
        onSubmit() {
            // console.log(this.userInfo);
            this.$axios.post(`/userBy/${this.page.currentPage}/${this.page.size}`, this.userInfo)
                .then((res) => {
                    // console.log(res);
                    this.tableData = res.data.data.records;
                    this.page.size = res.data.data.size;
                    this.page.currentPage = res.data.data.current;
                    this.page.total = res.data.data.total;
                })
        },
    },
}
</script>

<style scoped>
.el-pagination {
    margin-top: 20px;
}
.el-form {
    margin-top: 20px;
    margin-left: 20px;
}
</style>
