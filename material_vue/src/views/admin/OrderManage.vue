<template>
    <div>
        <div>
            <el-form :inline="true" :model="recordForm">
                <el-form-item label="捐赠人">
                    <el-input v-model="recordForm.userName" placeholder="捐赠人"></el-input>
                </el-form-item>
                <el-form-item label="物资名称">
                    <el-input v-model="recordForm.goods" placeholder="物资名称"></el-input>
                </el-form-item>
                <el-form-item label="物资类别">
                    <el-select v-model="recordForm.type" placeholder="请选择类别">
                        <el-option label value></el-option>
                        <el-option label="食品" value="食品"></el-option>
                        <el-option label="衣物" value="衣物"></el-option>
                        <el-option label="药品" value="药品"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <template>
            <el-table :data="tableData" style="width: 100%" :default-sort="{ prop: 'date', order: 'descending' }"
                size="middle">
                <el-table-column v-for="(val, key) in tableHead" :key="key" :prop="key" :label="val" sortable
                    width="180" class="cell" align="center"></el-table-column>
                <el-table-column label="操作" width="180" header-align="center" fixed="right" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" @click="allow(scope)">通过</el-button>
                        <el-button type="text" @click="notAllow(scope)">不通过</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </template>
        <div class="btn">
            <el-button type="primary" @click="passAll">全部通过</el-button>
            <el-button type="danger" @click="unPassAll">全部拒绝</el-button>
        </div>
        <div>
            <el-pagination @current-change="handleCurrentChange" :current-page="page.currentPage" :page-size="page.size"
                layout="total, prev, pager, next" :total="page.total"></el-pagination>
        </div>
    </div>
</template>
<script>
export default {
    name: 'OrderManage',
    data() {
        return {
            tableHead: {
                recordName: "捐赠人",
                recordGoods: "物资名称",
                recordTotal: "物资数量",
                recordUnit: "物资单位",
                recordType: "物资种类",
                recordRemark: "物资备注",
                recordDate: "捐赠时间",
                recordCheck: "审核状态",
            },
            tableData: [],
            recordForm: {
                userName: '',
                goods: '',
                type: '',
            },
            //分页数据
            page: {
                currentPage: 1,
                size: 10,
                total: 3,
            },
        }
    },
    created() {
        this.getAll()
    },
    methods: {
        getAll() {
            this.$axios.get(`/admin/order/${this.page.currentPage}/${this.page.size}`)
                .then((res) => {
                    this.tableData = res.data.data.records;
                    this.page.size = res.data.data.size;
                    this.page.currentPage = res.data.data.current;
                    this.page.total = res.data.data.total;
                })
        },
        handleCurrentChange(val) {
            // 更改页码为当前页 再查询
            this.page.currentPage = val;
            this.getAll();
        },
        allow(scope) {
            if (confirm("确定要通过吗")) {
                scope.row.recordCheck = "已通过"
                this.$axios.put(`/admin/allowOrder`, scope.row)
                    .then((res) => {
                        if (res.data.flag) {
                            this.$message({
                                type: "success",
                                message: "该记录已通过"
                            })
                            this.getAll()
                        }
                    })
            }
        },
        notAllow(scope) {
            if (confirm("确定要不通过吗")) {
                scope.row.recordCheck = "未通过"
                this.$axios.put(`/admin/allowOrder`, scope.row)
                    .then((res) => {
                        if (res.data.flag) {
                            this.$message({
                                type: "success",
                                message: "该记录已拒绝"
                            })
                            this.getAll()
                        }
                    })
            }
        },
        passAll() {
            if (confirm("确定把本页数据全部通过吗")) {
                for (let i = 0; i < this.tableData.length; i++) {
                    this.tableData[i].recordCheck = "已通过"
                }
                this.$axios.put(`/admin/allowAll`, this.tableData)
                    .then((res) => {
                        if (res.data.flag) {
                            this.$message({
                                type: "success",
                                message: "本页记录已通过"
                            })
                            this.getAll()
                        }
                    })
            }
        },
        unPassAll() {
            if (confirm("确定把本页数据全部拒绝吗")) {
                for (let i = 0; i < this.tableData.length; i++) {
                    this.tableData[i].recordCheck = "未通过"
                }
                this.$axios.put(`/admin/allowAll`, this.tableData)
                    .then((res) => {
                        if (res.data.flag) {
                            this.$message({
                                type: "success",
                                message: "本页记录已拒绝"
                            })
                            this.getAll()
                        }
                    })
            }
        },
        onSubmit() {
            this.$axios.post(`/getAllowRecordBy/${this.page.currentPage}/${this.page.size}`, this.recordForm)
                .then((res) => {
                    this.tableData = res.data.data.records;
                    this.page.size = res.data.data.size;
                    this.page.currentPage = res.data.data.current;
                    this.page.total = res.data.data.total;
                })
        }
    },
}
</script>
<style>
.el-pagination {
    margin-top: 1.25rem;
}

.el-form {
    margin-top: 1.25rem;
    margin-left: 1.25rem;
}

.btn {
    margin-top: 3.125rem;
    float: right;
}
</style>