<template>
    <div>
        <div class="top">
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
                <el-form-item label="审核状态">
                    <el-select v-model="recordForm.check" placeholder="请选择状态">
                        <el-option label value></el-option>
                        <el-option label="待审核" value="待审核"></el-option>
                        <el-option label="已通过" value="已通过"></el-option>
                        <el-option label="未通过" value="未通过"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <hr />
        <template>
            <el-table :data="tableData" style="width: 100%" :default-sort="{ prop: 'date', order: 'descending' }">
                <el-table-column v-for="(val, key) in tableHead" :key="key" :prop="key" :label="val" sortable
                    width="180" class="cell" align="center"></el-table-column>
            </el-table>
        </template>
        <div>
            <el-pagination @current-change="handleCurrentChange" :current-page="page.currentPage" :page-size="page.size"
                layout="total, prev, pager, next" :total="page.total"></el-pagination>
        </div>
    </div>
</template>
<script>
export default {
    name: 'RecordIn',
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
                recordCheck: "审核状态"
            },
            tableData: [],
            recordForm: {
                userName: '',
                goods: '',
                type: '',
                check: ''
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
            //调后端分页查询该用户所有捐赠记录的方法
            this.$axios.get(`/getAllRecord/${this.page.currentPage}/${this.page.size}`)
                .then((res) => {
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
        onSubmit() {
            // console.log(this.recordForm);
            this.$axios.post(`/getAllRecordBy/${this.page.currentPage}/${this.page.size}`, this.recordForm)
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
<style scoped>
.el-pagination {
    margin-top: 20px;
}

.el-form {
    margin-top: 20px;
    margin-left: 20px;
}
</style>