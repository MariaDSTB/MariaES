<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
const pages = ref<Array<{
  id: number;
  title: string;
  content: string;
  answer: string;
}>>([]);
const pageId = ref('');
const router = useRouter();

const confirm = async () => {
  try {
    const response = await axios.get(`http://127.0.0.1:7003/api/page/all`);
    console.log(response);
    pages.value = response.data.data;
  } catch (error) {
    alert('加载页面失败');
  }
};
const startPage = async () => {
  const response = await axios.get(`http://127.0.0.1:7003/api/page/all`);
  await router.push({
    name: 'startExam',
    params: { id: pageId.value },
    query: {
      pageData: JSON.stringify(response.data),
    },
  });
}
onMounted(confirm);
</script>

<template>
  <div class="choosePage" v-for="(page, index) in pages" :key="index">
    <el-input v-model="page.id" style="width: 140px;display: inline-block;vertical-align: top" autosize type="textarea"
      placeholder="ID" disabled />
    <el-input v-model="page.title" style="width: 140px;display: inline-block;vertical-align: top" autosize
      type="textarea" placeholder="Title" disabled />
    <el-input v-model="page.content" style="width: 140px;display: inline-block;vertical-align: top" autosize
      type="textarea" placeholder="Content" disabled />
    <el-button type="primary" @click="startPage">确认</el-button>
  </div>
</template>

<style scoped>
/* 这里可以添加您的样式 */
.choosePage {
  margin: 50px
}

.choosePage el-input {
  padding: 50px
}
</style>
