<template>
    <div>
        <h1>试卷录入</h1>
        <el-form>
            <el-form-item label="题目">
                <el-input></el-input>
            </el-form-item>
            <el-form-item label="选项">
                <el-input></el-input>
            </el-form-item>
            <el-form-item label="答案">
                <el-input></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary">提交</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script lang="ts" setup>
import { ref } from 'vue'

const question = ref({
    title: '',
    answer: '',
    type: ''
});

const submit = () => {
    console.log(question.value);
}
</script>
<style>

</style>