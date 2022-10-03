<template>
    <div>
        <el-dialog title="修改" :visible.sync="dialogVisibleEdit" width="40%">
            <el-form :model="form" :rules="rules">
                <el-form-item label="物资名称" :label-width="formLabelWidth" prop="gools">
                    <el-input v-model="form.goods" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="物资数量" :label-width="formLabelWidth" prop="totle">
                    <el-input v-model="form.total" autocomplete="off"></el-input>
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
                <el-button @click="dialogVisibleEdit = false">取 消</el-button>
                <el-button type="primary" @click="submitEdit()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: "Update",
    props: ["form"],
    data() {
        return {
            formLabelWidth: "80px",
            dialogVisibleEdit: false,
            rules: {
                goods: [
                    { required: true, message: '请输入物资名称', trigger: 'blur' },
                ],
                total: [
                    { required: true, message: '请输入物资数量', trigger: 'blur' }
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
            this.dialogVisibleEdit = true;
        },
        //把修改后的数据传给父组件
        submitEdit() {
            this.$emit("UpdateFormData", this.form);
            this.dialogVisibleEdit = false;
        },
    },
};
</script>

<style>
</style>