<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { PageVo } from '../types';
import axios from "axios"; // 确保这个路径是正确的

const route = useRoute();
const pageVo = ref<PageVo>({ QuestionPageEntity: [], createTime: "", title: "" });
const answers = ref<string[]>([]);
const globalArray = ref<any[]>([]);
const score = ref(0);
const submitExam = async () => {
    try {
      let answersValue = 1;
      const response = await axios.get(`http://127.0.0.1/api/page/${answersValue}`);
        globalArray.value = response.data;
        // 比较answers和globalArray数组
        score.value = globalArray.value.length ===
            answers.value.length && globalArray.value.every((value, index) =>
                value === answers.value[index]) ? 100 : 0;
    } catch (error) {
        console.error('提交考试失败:', error);
    }
    const response1 = await axios.post('http://127.0.0.1/add', { score: score.value });
};
onMounted(() => {
    if (route.query.data) {
        pageVo.value = JSON.parse(route.query.data as string);
        // 初始化answers数组，为每个问题提供一个空答案
        answers.value = pageVo.value.QuestionPageEntity.map(() => ' ');
    }
});
</script>

<template>
    <div v-if="pageVo">
        <h1>{{ pageVo.title }}</h1>
        <ul>
            <li v-for="(question, index) in pageVo.QuestionPageEntity" :key="question.id">
                {{ question.content }}
                <el-input v-model="answers[index]" style="width: 140px;display: inline-block;vertical-align: top"
                    :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="请输入答案" />
            </li>
        </ul>
        <el-button type="primary" @click="submitExam">交卷</el-button>
    </div>
</template>
<style scoped>
/* 在这里添加样式 */
</style>