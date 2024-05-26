<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
const pageId = ref('');
const router = useRouter();

const confirm = async () => {
  if (pageId.value.trim() === '') {
    alert('请输入试卷代码');
    return;
  }
  try {
    const response = await axios.get(`/api/page/${pageId.value}`);
    const response1 = await axios.get(`/api/Exam/${pageId.value}`);
    // 检查响应状态码是否为200
    if (response.status === 200 && response1.status === 200) {
      // 跳转到 PageExam 页面，并将数据作为参数传递
      await router.push({
        name: 'PageExam',
        params: {id: pageId.value},
        query: {
          pageData: JSON.stringify(response.data),
          examData: JSON.stringify(response1.data)
        } // 将数据转化为字符串通过 query 参数传递
      });
    } else {
      alert('加载页面失败');
    }
  } catch (error) {
    // 处理请求错误
    alert('加载页面失败');
  }
};
</script>

<template>
  <p>输入您想要考那套试卷(试卷代码)</p>
  <el-input
      v-model="pageId"
      style="width: 140px;display: inline-block;vertical-align: top"
      :autosize="{ minRows: 2, maxRows: 4 }"
      type="textarea"
      placeholder=""
  />
  <el-button type="primary" @click="confirm">确认</el-button>
</template>

<style scoped>

</style>