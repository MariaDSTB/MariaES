<template>
    <h1>试卷录入</h1>
    <el-form @submit.native.prevent="submitForm">
        <el-form-item label="题目" class="w-2/3">
            <el-button plain @click="open">选择题目</el-button>
        </el-form-item>
        <el-form-item label="选项">
            <el-input v-model="page.type"></el-input>
        </el-form-item>
        <el-form-item label="答案">
            <el-input v-model="page.answer"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" native-type="submit">提交</el-button>
        </el-form-item>
    </el-form>
</template>
<script lang="ts" setup>
import { ref, reactive } from 'vue'
import axios from 'axios'
import { ElMessageBox, Elmessage } from 'element-plus'
const page = ref({
    content: [],
    answer: '',
    type: ''
});

const question = reactive({
    visible: false,
    data: []
});


const submitForm = () => {
    console.log("page(ref):");
    console.log(page.value);
    console.log("question(reactive)");
    console.log(question);
};


const open = async () => {
    try {
        const response = await axios.get('http://127.0.0.1:4523/m1/4389540-0-default/questions/all'); // 替换为你的 API URL
        question.data = response.data;

        ElMessageBox.confirm({
            title: '提示',
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            center: true,
            dangerouslyUseHTMLString: true,
            message: `
                <el-table :data="question.data" style="width: 100%">
                    <el-table-column prop="content" label="题目"></el-table-column>
                    <el-table-column prop="answer" label="答案 2"></el-table-column>
                    <!-- 添加更多的列，如果需要 -->
                </el-table>
            `,
        }).then(() => {
            console.log("确定");
        }).catch(() => {
            console.log("取消");
        });
    } catch (error) {
        console.error(error);
    }
};
</script>
<style></style>
