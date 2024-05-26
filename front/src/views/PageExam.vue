<script setup lang="ts">
import {ref, onMounted, reactive} from 'vue';
import { useRoute } from 'vue-router';
import { PageVo, ExamVo } from '../types.ts';
const route = useRoute();
const pageVo = ref<PageVo>({QuestionPageEntity: [], createTime: "", title: ""});
const answers = reactive<{ [key: string]: string }>({});
onMounted(() => {
  if (route.query.data) {
    pageVo.value = JSON.parse(route.query.data as string);
  }
});
</script>

<template>
  <div v-if="pageVo">
    <h1>{{ pageVo.title }}</h1>
    <ul>
      <li v-for="question in pageVo.QuestionPageEntity" :key="question.id">
        {{ question.content }}
        <el-input
            v-model="answers[question.id]"
            style="width: 140px;display: inline-block;vertical-align: top"
            :autosize="{ minRows: 2, maxRows: 4 }"
            type="textarea"
            placeholder="请输入答案"
        />
      </li>
    </ul>
    <el-button type="primary">交卷</el-button>
  </div>
</template>
<style scoped>

</style>