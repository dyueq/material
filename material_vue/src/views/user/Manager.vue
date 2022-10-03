<template>
    <div>
        <el-button type="primary" @click="addRow" class="btn1">新建捐赠</el-button>
        <AddDonotion ref="hiddenAdd" @newFormData="newFormData" />
        <template>
            <el-table
                v-show="tableData.length !== 0"
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
                    align="center"
                ></el-table-column>
                <el-table-column label="操作" width="180" header-align="center" align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="primary"
                            icon="el-icon-edit"
                            size="mini"
                            @click="editForm(scope)"
                        >修改</el-button>
                        <el-button
                            type="danger"
                            icon="el-icon-delete"
                            size="mini"
                            @click="deleteRow(scope)"
                        >移除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </template>
        <UpdateDonotion @UpdateFormData="UpdateFormData" :form="form" ref="hiddenEdit" />
        <div v-show="tableData.length !== 0">
            <el-button type="primary" @click="addAll" class="btn2">提交</el-button>
        </div>
    </div>
</template>

<script>

import AddDonotion from '@/components/add/AddDonotion.vue';
// import axios from 'axios';
import UpdateDonotion from '../../components/update/UpdateDonotion.vue';
export default {
    //用户捐赠管理页面
    name: "Manager",
    components: { AddDonotion, UpdateDonotion },
    data() {
        return {
            tableHead: {
                goods: "物资名称",
                total: "物资数量",
                unit: "物资单位",
                type: "物资种类",
                remark: "物资备注"
            },
            tableData: [],
            //根据id查找的数据
            form: {},
        };
    },
    methods: {
        // 打开添加页面
        addRow() {
            this.$refs.hiddenAdd.init();
        },
        //把添加的记录展示到页面上
        newFormData(form) {
            // alert("新建成功")
            //把捐赠信息添加到页面  一刷新就没了
            this.tableData.push(form)
        },
        // 打开修改页面
        editForm(scope) {
            // console.log(scope);
            this.$refs.hiddenEdit.init();
            this.form = scope.row
        },
        // 修改数据
        UpdateFormData(UpdateForm) {
            console.log(UpdateForm);
        },
        // 删除
        deleteRow(scope) {
            // console.log(scope);
            if (confirm("确定要删除吗")) {
                this.tableData.splice(scope.$index, 1);
            }
        },
        //添加所有
        addAll() {
            //tableData
            // 添加成未审核记录
            console.log(this.tableData);
            this.$axios.post("/toCheck", this.tableData)
                .then((res) => {
                    console.log(res);
                    if (res.data.flag) {
                        alert(res.data.msg);
                    } else {
                        alert(res.data.msg);
                    }
                    this.tableData = []
                })
        }
    },
}
</script>
</script>
<style scoped>
.btn1 {
    margin-top: 1.25rem;
}
.btn2 {
    margin-top: 3.125rem;
    margin-right: 25rem;
    float: right;
}
</style>