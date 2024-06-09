<script setup lang="ts">
import {ref} from "vue";

const gradeId = ref('')
</script>

<template>
  <p>输入您想要查那个成绩(成绩代码)</p>
  <el-input
      v-model="gradeId"
      style="width: 140px;display: inline-block;vertical-align: top"
      :autosize="{ minRows: 2, maxRows: 4 }"
      type="textarea"
      placeholder=""
  />
  <el-button type="primary">确认</el-button>
</template>

<style scoped>

</style>