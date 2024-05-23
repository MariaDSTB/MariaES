<template>
    <h1>试卷录入</h1>
    <el-form @submit.native.prevent="submitForm">
        <el-form-item label="题目">
            <el-input v-model="question.content">题库</el-input>
        </el-form-item>
        <el-form-item label="选项">
            <el-input v-model="question.type"></el-input>
        </el-form-item>
        <el-form-item label="答案">
            <el-input v-model="question.answer"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" native-type="submit">提交</el-button>
        </el-form-item>
    </el-form>
    <h1 class="text-blue-500">
        这是一个tailwindcss样式
    </h1>
</template>
<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const question = ref({
    content: '',
    answer: '',
    type: ''
});

const submitForm = () => {
    console.log(question.value);
};

// const submitForm = () => {
//     axios.post('http://localhost:3000/api/page', question.value)
//         .then(res => {
//             console.log(res.data);
//         })
// };
onMounted(() => {
    axios.get('http://127.0.0.1:4523/m1/4389540-0-default/questions/1')
        .then((res: any) => {
            console.log(res.data);
        })
});
</script>
<style></style>
