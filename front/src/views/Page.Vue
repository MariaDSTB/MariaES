<template>
    <h1 class="text-3xl mb-5">试卷录入</h1>
    <el-form @submit.native.prevent="submitForm">
        <el-form-item label="试卷名称" class="w-1/2">
            <el-input v-model="page.title" placeholder="请输入试卷名"></el-input>
        </el-form-item>
        <el-form-item label="题目">
            <el-select v-model="page.content" value-key="id" placeholder="录入试卷" style="width: 240px">
                <el-option v-for="item in questions.data" :label="item.content" :value="item.content" />
            </el-select>
        </el-form-item>
        <el-form-item label="答案" class="w-1/2">
            <el-input v-model="page.answer" placeholder="请输入答案"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" native-type="submit">提交</el-button>
        </el-form-item>
    </el-form>
</template>
<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'
const page = ref({
    title: '',
    content: '',
    answer: ''
});

const questions = reactive({
    data: []
});

const getQuestion = async () => {
    const res = await axios.get('http://localhost:7006/api/question/all');
    questions.data = res.data;
};

onMounted(getQuestion);

const submitForm = async () => {
    await axios.post('http://localhost:7006/api/page/add', page.value)
        .then(() => {
            ElMessage({
                message: '添加成功',
                type: 'success',
            })
        })
        .catch(() => {
            ElMessage('error', '添加失败');
        });
};


</script>
<style>
.example-showcase .el-dropdown+.el-dropdown {
    margin-left: 15px;
}

.example-showcase .el-dropdown-link {
    cursor: pointer;
    color: var(--el-color-primary);
    display: flex;
    align-items: center;
}
</style>
