<template>
    <el-form @submit.native.prevent="submitForm">
        <el-form-item label="考试时间">
            <div class="demonstration">Value：{{ exam.startTime }}</div>
            <el-date-picker v-model="exam.startTime" type="datetime" placeholder="Pick a Date"
                format="YYYY/MM/DD hh:mm:ss" value-format="YYYY-MM-DD h:m:s a" />
        </el-form-item>
        <el-form-item label="试卷">
            <el-select v-model="exam.page_id" value-key="id" placeholder="选择试卷" style="width: 240px">
                <el-option v-for="item in page.data" :label="item.name" :value="item.id" />
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" native-type="submit">提交</el-button>
        </el-form-item>
    </el-form>
</template>
<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue';
import axios from 'axios';
const exam = ref({
    startTime: '',
    page_id: ''
})

const page = reactive({
    data: []
});

const getPage = async () => {
    const res = await axios.get('http://127.0.0.1:4523/m1/4389540-0-default/page/all');
    page.data = res.data;
}
onMounted(getPage);

const submitForm = async () => {
    // axios.post('http://localhost:7001/exam', exam.value)
    //     .then((res: any) => {
    //         console.log(res);
    //     }).catch((err: any) => {
    //         console.log(err);
    //     });
    console.log(exam.value);
};
</script>
<style></style>