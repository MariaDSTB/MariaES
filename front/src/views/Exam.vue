<template>
    <el-form @submit.native.prevent="submitForm">
        <el-form-item label="考试时间">
            <!-- <div class="demonstration">Value：{{ exam.startTime }}</div> -->
            <el-date-picker v-model="exam.startTime" type="datetime" placeholder="考试日期" format="YYYY-MM-DD HH:mm:ss"
                date-format="MMM DD, YYYY" time-format="HH:mm" />
        </el-form-item>
        <el-form-item label="试卷">
            <el-select v-model="exam.pageId" value-key="id" placeholder="选择试卷" style="width: 240px">
                <el-option v-for="item in page.data" :label="item.name" :value="item.id" />
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-input v-model="exam.title" class="w-1/2" placeholder="填入考试名称"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" native-type="submit">提交</el-button>
        </el-form-item>
    </el-form>
</template>
<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';
const exam = ref({
    startTime: '',
    pageId: '',
    title: ''
})

const page = reactive({
    data: []
});

const getPage = async () => {
    const res = await axios.get('http://localhost:7003/api/page/all');
    page.data = res.data.data;
}
onMounted(getPage);

const submitForm = async () => {
    axios.post('http://localhost:7005/api/exam/add', exam.value)
        .then(() => {
            ElMessage({
                message: '添加成功',
                type: 'success',
            });
        })
        .catch(() => {
            ElMessage({
                message: '添加失败',
                type: 'error'
            });
        });
};
</script>
<style></style>