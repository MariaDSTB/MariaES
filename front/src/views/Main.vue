<template>
  <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      background-color="#2F4F4F"
      text-color="#fff"
      active-text-color="#ffd04b"
      router
  >
    <el-menu-item index="/HomePage" class="HomePage">首页</el-menu-item>
    <el-menu-item index="/RecordQuestionBank" class="RecordQuestionBank">录制题库</el-menu-item>
  </el-menu>
  <el-main>
    <!-- 路由占位符 -->
    <router-view/>
  </el-main>
</template>

<script lang="ts" setup>
import { ref } from 'vue';

const activeIndex = ref('/HomePage'); // 设置默认激活的菜单项
</script>

<style scoped>
.el-menu-demo {
  background-color: dodgerblue;
}
.HomePage {
  font-size: larger;
  text-align: center;
}
.RecordQuestionBank {
  font-size: larger;
  text-align: center;
}
</style>
