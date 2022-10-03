<template>
    <div>
        <el-dialog title="新建捐赠" :visible.sync="dialogVisible" width="40%">
            <el-form :model="form" :rules="rules" ref="form">
                <el-form-item label="物资名称" :label-width="formLabelWidth" prop="goods">
                    <el-input v-model="form.goods" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="物资数量" :label-width="formLabelWidth" prop="total">
                    <el-input v-model.number="form.total" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="物资单位" :label-width="formLabelWidth" prop="unit">
                    <el-radio-group v-model="form.unit">
                        <el-radio label="个"></el-radio>
                        <el-radio label="包"></el-radio>
                        <el-radio label="件"></el-radio>
                        <el-radio label="盒"></el-radio>
                        <el-radio label="箱"></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="物资类别" :label-width="formLabelWidth" prop="type">
                    <el-select v-model="form.type" placeholder="请选择类别">
                        <el-option label="食品" value="食品"></el-option>
                        <el-option label="衣物" value="衣物"></el-option>
                        <el-option label="药品" value="药品"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="物资备注" :label-width="formLabelWidth">
                    <el-input type="textarea" v-model="form.remark"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitAdd('form')">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: "AddDonotion",
    data() {
        return {
            formLabelWidth: "80px",
            dialogVisible: false,
            form: {
                goods: "",
                total: "",
                type: "",
                unit: "",
                remark: ""
            },
            rules: {
                goods: [
                    { required: true, message: '请输入物资名称', trigger: 'blur' },
                ],
                total: [
                    { required: true, message: '请输入物资数量', trigger: 'blur' },
                    { type: 'number', message: '请输入数字', trigger: 'blur' },
                ],
                type: [
                    { required: true, message: '请选择类别', trigger: 'change' }
                ],
                unit: [
                    { required: true, message: '请选择单位', trigger: 'change' }
                ],
            }
        };
    },
    methods: {
        init() {
            this.dialogVisible = true;
            //重置表单
            this.form = {};
            // this.$refs.form.resetFields();
        },
        //把form的数据传给父组件
        submitAdd(formName) {
            this.$refs[formName].validate((valid) => {
                // 成功
                if (valid) {
                    console.log(this.form);
                    this.$emit("newFormData", this.form);
                    this.dialogVisible = false;
                } else {
                    // 失败 
                    alert("请按提示输入信息")
                    return false;
                }
                // this.$refs[formName].resetFields();
            });

        },
    },
};
</script>

<style>
</style>