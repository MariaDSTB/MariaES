<template>
  <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#2F4F4F"
    text-color="#fff" active-text-color="#ffd04b" router>
    <el-menu-item index="/HomePage" class="router">首页</el-menu-item>
    <el-menu-item index="/RecordQuestionBank" class="router">录制题库</el-menu-item>
    <el-menu-item index="/Page" class="router">录入题库</el-menu-item>
    <el-menu-item index="/exam" class="router">考试安排</el-menu-item>
    <el-menu-item index="/grade" class="router">成绩</el-menu-item>
    <el-submenu v-if="user.loggedIn" index="4">
      <template #title>
        <img :src="user.avatar" alt="User avatar" class="w-30px h-30px rounded-full">
      </template>
      <el-menu-item index="logout" @click="logout">Logout</el-menu-item>
    </el-submenu>
    <!-- <el-menu-item v-else index="login" class="router">Login</el-menu-item> -->

  </el-menu>
  <el-main>
    <!-- 路由占位符 -->
    <router-view />
  </el-main>
</template>

<script lang="ts" setup>
import { ref } from 'vue';

const user = ref({
  loggedIn: false, // replace this with your login state
  avatar: '' // replace this with the URL of the user's avatar
});
const activeIndex = ref('/HomePage'); // 设置默认激活的菜单项

const logout = () => {
  user.value.loggedIn = false; // replace this with your logout logic
};
</script>

<style scoped>
.el-main {
  background-image: url(../assets/avatar.jpg);
  width: 100vw;
  height: 100vh;
  background-repeat: no-repeat;
}

.el-menu-demo {
  background-color: dodgerblue;
}

.router {
  font-size: larger;
  text-align: center;
}
</style>
