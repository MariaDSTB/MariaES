<style>
.el-alert__title {
  font-size: 25px;
}
.el-alert--warning .el-alert__content p {
  font-size: 15px;
}
.el-alert{
  width: 300px;
  height: 100px;
}
.title{
  width: 500px;
}
.options {
  font-size: 0;
  padding-top: 30px;
}

.options > * {
  display: inline-block;
  font-size: 16px; /* 恢复字体大小 */
  vertical-align: top; /* 顶对齐，防止纵向错位 */
}

.options p {
  margin-right: 10px; /* 在标签和输入框之间添加一些间距 */
}
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>

<template>
  <el-alert title="警告提示" type="warning" effect="dark" :closable="false">请老师仔细填写以下信息</el-alert>
  <div class="question">
    <p>题目类型</p>
    <el-cascader v-model="value1" :options="options1" @change="handleTypeChange"/>
  </div>
  <div class="type">
    <p>题目科目</p>
    <el-cascader v-model="value2" :options="options2"/>
  </div>
  <div>
    <p>题头</p>
    <el-input
        v-model="title"
        class="title"
        placeholder="请输入"
        :autosize="{ minRows: 2, maxRows: 4 }"
        type="textarea"
        clearable
    />
  </div>
  <transition-group name="fade">
    <div class="single-choice" v-if="showSingleChoice">
      <!-- ...选项A、B、C、D... -->
      <div class="options">
        <p>选项A</p>
        <el-input
            v-model="optionA"
            style="width: 240px"
            :autosize="{ minRows: 2, maxRows: 4 }"
            type="textarea"
            placeholder="请输入"
        />
        <p>选项B</p>
        <el-input
            v-model="optionB"
            style="width: 240px"
            :autosize="{ minRows: 2, maxRows: 4 }"
            type="textarea"
            placeholder="请输入"
        />
        <p>选项C</p>
        <el-input
            v-model="optionC"
            style="width: 240px"
            :autosize="{ minRows: 2, maxRows: 4 }"
            type="textarea"
            placeholder="请输入"
        />
        <p>选项D</p>
        <el-input
            v-model="optionD"
            style="width: 240px"
            :autosize="{ minRows: 2, maxRows: 4 }"
            type="textarea"
            placeholder="请输入"
        />
      </div>
    </div>

    <div class="cloze-answer" v-if="showClozeAnswer">
      <p>填空题答案</p>
      <el-input
          v-model="clozeAnswer"
          style="width: 240px"
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          placeholder="请输入填空题答案"
      />
    </div>
  </transition-group>
</template>
<script setup lang="ts">
import { ref } from 'vue'
const value1 = ref('')
const value2 = ref('')
const title = ref('')
const optionA = ref('')
const optionB = ref('')
const optionC = ref('')
const optionD = ref('')
const clozeAnswer = ref('')
const showSingleChoice = ref(false) // 控制单选题选项显示的响应式变量
const showClozeAnswer = ref(false)
const options1 = [
  {
    value: 'SCQ',
    label: '单选题',
  },
  {
    value: 'MCQ',
    label: '多选题',
  },
  {
    value: 'CQ',
    label: '填空题',
  },
  {
    value: 'EQ',
    label: '解答题',
  },
]
const options2 = [
  {
    value: 'SE',
    label: '软件工程',
  },
  {
    value: 'DM',
    label: '数据挖掘',
  },
]
const handleTypeChange = (value: any) => {
  if (value[0] === 'SCQ') {
    showSingleChoice.value = true
    showClozeAnswer.value = false // 确保单选题和填空题答案不会同时显示
  } else if (value[0] === 'CQ') {
    showSingleChoice.value = false
    showClozeAnswer.value = true // 当选择填空题时显示填空题答案输入框
  } else {
    showSingleChoice.value = false
    showClozeAnswer.value = false // 其他题目类型不显示填空题答案输入框
  }
}
</script>