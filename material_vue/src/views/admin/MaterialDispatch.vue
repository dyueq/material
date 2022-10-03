<template>
    <div class="fa">
        <div class="top">
            <el-form :model="form" :rules="rules" ref="form">
                <el-form-item label="物资名称" :label-width="formLabelWidth" prop="recordGoods">
                    <el-input v-model="form.recordGoods" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="物资数量" :label-width="formLabelWidth" prop="recordTotal">
                    <el-input v-model.number="form.recordTotal" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="物资单位" :label-width="formLabelWidth" prop="recordUnit">
                    <el-radio-group v-model="form.recordUnit">
                        <el-radio label="个"></el-radio>
                        <el-radio label="包"></el-radio>
                        <el-radio label="件"></el-radio>
                        <el-radio label="盒"></el-radio>
                        <el-radio label="箱"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="物资类别" :label-width="formLabelWidth" prop="recordType">
                    <el-select v-model="form.recordType" placeholder="请选择类别">
                        <el-option label="食品" value="食品"></el-option>
                        <el-option label="衣物" value="衣物"></el-option>
                        <el-option label="药品" value="药品"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="发放地址" :label-width="formLabelWidth" prop="recordAddress">
                    <el-cascader
                        :options="options"
                        ref="cascader"
                        v-model="form.recordAddress"
                        clearable
                    ></el-cascader>
                </el-form-item>
                <el-form-item label="物资备注" :label-width="formLabelWidth">
                    <el-input type="textarea" v-model="form.recordRemark"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="foot">
                <el-button type="primary" @click="submitAdd('form')">提 交</el-button>
                <el-button type="primary" @click="reset('form')">重 置</el-button>
            </span>
        </div>
        <hr />
        <p>捐赠记录</p>
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
        </el-table>
        <el-pagination
            @current-change="handleCurrentChange"
            :current-page="page.currentPage"
            :page-size="page.size"
            layout="total, prev, pager, next"
            :total="page.total"
        ></el-pagination>
    </div>
</template>
<script>
export default {
    name: 'MaterialDispatch',
    data() {
        return {
            formLabelWidth: "80px",
            tableHead: {
                recordName: "捐赠人",
                recordGoods: "物资名称",
                recordTotal: "物资数量",
                recordUnit: "物资单位",
                recordType: "物资种类",
                recordDate: "捐赠时间",
                recordAddress: "捐赠地址",
                recordRemark: "物资备注",
            },
            tableData: [{
                recordName: "zs",
                recordGoods: "口罩",
                recordTotal: "1",
                recordUnit: "个",
                recordType: "衣物",
                recordDate: "2022-02-22",
                recordAddress: "捐赠地址",
                recordRemark: "上海市普陀区金沙江路",
            }],
            form: {
                recordGoods: '',
                recordTotal: '',
                recordUnit: '',
                recordType: '',
                recordAddress: '',
                recordRemark: '',
            },
            rules: {
                recordGoods: [
                    { required: true, message: '请输入物资名称', trigger: 'blur' },
                ],
                recordTotal: [
                    { required: true, message: '请输入物资数量', trigger: 'blur' },
                    { type: 'number', message: '请输入数字', trigger: 'blur' },
                ],
                recordUnit: [
                    { required: true, message: '请选择类别', trigger: 'change' }
                ],
                recordType: [
                    { required: true, message: '请选择单位', trigger: 'change' }
                ],
                recordAddress: [
                    { required: true, message: '请选择地址', trigger: 'change' }
                ],
            },
            options: [
                {
                    value: '直辖市',
                    label: '直辖市',
                    children: [
                        {
                            value: '北京市',
                            label: '北京市'
                        },
                        {
                            value: '天津市',
                            label: '天津市'
                        },
                        {
                            value: '上海市',
                            label: '上海市'
                        }]
                },
                {
                    value: '河北省',
                    label: '河北省',
                    children: [
                        {
                            value: '保定市',
                            label: '保定市'
                        },
                        {
                            value: '唐山市',
                            label: '唐山市'
                        },
                        {
                            value: '衡水市',
                            label: '衡水市'
                        }]
                },
                {
                    value: '山东省',
                    label: '山东省',
                    children: [
                        {
                            value: '济南市',
                            label: '济南市'
                        },
                        {
                            value: '青岛市',
                            label: '青岛市'
                        },
                        {
                            value: '烟台市',
                            label: '烟台市'
                        }]
                },
            ],
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
        //获取全部捐出记录
        getAll() {
            this.$axios.get(`/getOutRecord/${this.page.currentPage}/${this.page.size}`)
                .then((res) => {
                    // console.log(res);
                    this.tableData = res.data.data.records;
                    this.page.size = res.data.data.size;
                    this.page.currentPage = res.data.data.current;
                    this.page.total = res.data.data.total;
                })
        },
        submitAdd(formName) {
            this.$refs[formName].validate((valid) => {
                // 成功
                if (valid) {
                    this.form.recordAddress = this.form.recordAddress.join("/")
                    // console.log(this.form);
                    this.$axios.post(`/addOutRecord/`, this.form)
                        .then((res) => {
                            if (res.data.flag) {
                                alert(res.data.msg)
                                this.$refs[formName].resetFields();
                                this.getAll()
                            } else {
                                alert(res.data.msg)
                            }
                        })
                } else {
                    // 失败 
                    alert("请按提示输入信息")
                    return false;
                }

            });
        },
        reset(formName) {
            this.$refs[formName].resetFields();
        },
        //分页
        handleCurrentChange(val) {
            // 更改页码为当前页 再查询
            this.page.currentPage = val;
            this.getAll();
        },
    },
}
</script>
<style lang="less" scoped>
.fa {
    top: 6.25rem;
    position: absolute;
    .top {
        width: 25rem;
        height: 25rem;
        position: relative;
        left: 50%;
        transform: translate(-50%);
        margin-bottom: 3.125rem;
        .foot {
            margin-left: 9.375rem;
        }
    }
}
</style>