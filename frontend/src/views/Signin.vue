<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../assets/vue.svg" alt="" />
      </div>
      <!-- 登录表单区域 -->
      <el-form label-width="0px" class="login_form">
        <!-- 用户名 -->
        <el-form-item>
          <el-input v-model="user.name" placeholder="Username"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item>
          <el-input v-model="user.password" placeholder="Password"></el-input>
        </el-form-item>
        <!-- 按钮区域 -->
        <el-row justify="end">
          <el-form-item class="login_btn">
            <el-button type="primary" @click="signin">登录</el-button>
            <el-button type="info" @click="signup">注册</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </div>
  </div>

</template>

<script lang="ts" setup>
import axios from 'axios';
import { ref } from 'vue';
import { userInfoStore } from '../store/user.ts';
import router from '../router/index.ts';
const user = ref({
  name: '',
  password: ''
});
const store = userInfoStore();

const signin = async () => {
  await axios.post('http://localhost:80/api/authorization/signIn', user.value)
    .then((res: any) => {
      console.log(res.data)
      if (res.data.code === 200) {
        store.setIsLogin(true);
        router.push('/HomePage').catch(err => console.log(err));
      } else {
        alert('登录失败');
      }
    }).catch((err: any) => {
      console.log(err);
    });
};
const signup = async () => {
  axios.post('http://localhost:80/api/authorization/signOut', user.value)
    .then((res: any) => {
      console.log(res);
    }).catch((err: any) => {
      console.log(err);
    });
};
</script>

<style scoped>
.login_container {
  background-color: #2b4b6b;
  height: 100vh;
}

.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  .avatar_box {
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.login_btn {
  justify-content: flex-end;
}
</style>