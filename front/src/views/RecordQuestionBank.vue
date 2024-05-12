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
.single-options {
  font-size: 0;
  padding-top: 30px;
}

.single-options > * {
  display: inline-block;
  font-size: 16px; /* 恢复字体大小 */
  vertical-align: top; /* 顶对齐，防止纵向错位 */
}
.multiple-options {
  font-size: 0;
  padding-top: 30px;
}

.multiple-options > * {
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
.addBtn{
  margin-top: 30px;
}
.operateQuestionBtn{
  position: absolute;
  top: 150px;
  left: 350px;
}
.selectQuestion{
  display: inline-block;
}
.selectQuestion p {
  display: inline-block;
  vertical-align: top;
  margin-right: 10px; /* 在标签和输入框之间添加一些间距 */
}
.selectQuestion .el-input {
  width: 140px;
  display: inline-block;
  vertical-align: top;
}
</style>

<template>
  <el-alert title="警告提示" type="warning" effect="dark" :closable="false">请老师仔细填写以下信息</el-alert>
  <el-button type="primary" class="operateQuestionBtn" @click="dialogVisible = true,fetchQuestions">
  操作题库
  </el-button>
  <div class="question">
    <p>题目类型</p>
    <el-cascader v-model="value1" :options="options1" @change="handleTypeChange"/>
  </div>
  <div class="type">
    <p>题目科目</p>
    <el-cascader v-model="value2" :options="options2"/>
  </div>
  <div class="difficuity">
    <p>题目难易</p>
    <el-cascader v-model="value3" :options="options3"></el-cascader>
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
      <div class="single-options">
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

    <div class="multiple-choice" v-if="showMultipleChoice">
      <!-- ...选项A、B、C、D... -->
      <div class="multiple-options">
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

    <div class="essay-question" v-if="showEssayQuestion">
      <p>解答题答案</p>
      <el-input
          v-model="essayQuestion"
          style="width: 500px"
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          placeholder="请输入填空题答案"
      />
    </div>
  </transition-group>
  <el-row>
    <el-button class="addBtn" type="success">添加</el-button>
  </el-row>
  <el-dialog
      title="题目"
      v-model="dialogVisible"
      width="70%"
  >
    <div class="selectQuestion" v-for="(question, index) in questions" :key="index">
      <p>题目编号</p>
      <el-input
          v-model="question.selectQuestionId"
          style="width: 140px;display: inline-block;vertical-align: top"
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          placeholder=""
          disabled
      />
      <p>题头</p>
      <el-input
          v-model="question.selectQuestionId"
          style="width: 140px;display: inline-block;vertical-align: top"
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          placeholder=""
          disabled
      />
      <p>难易程度</p>
      <el-input
          v-model="question.selectQuestionId"
          style="width: 140px;display: inline-block;vertical-align: top"
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          placeholder=""
          disabled
      />
      <p>题目科目</p>
      <el-input
          v-model="question.selectQuestionId"
          style="width: 140px;display: inline-block;vertical-align: top"
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          placeholder=""
          disabled
      />
    </div>
    <div class="searchForQuestions">

    </div>
    <template #footer>
    <span class="dialog-footer">
      <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
    </span>
    </template>
  </el-dialog>
</template>
<script setup lang="ts">
import {ref, onMounted} from 'vue'
import axios from 'axios'
const value1 = ref('')
const value2 = ref('')
const value3 = ref('')
const title = ref('')
const optionA = ref('')
const optionB = ref('')
const optionC = ref('')
const optionD = ref('')
const clozeAnswer = ref('')
const essayQuestion = ref('')
const showSingleChoice = ref(false)
const showMultipleChoice = ref(false)
const showClozeAnswer = ref(false)
const showEssayQuestion = ref(false)
const dialogVisible = ref(false)
const selectQuestionId = ref(0)
const selectQuestionTitle = ref('')
const selectQuestionDifficulty = ref('')
const selectQuestionSubject = ref('')
const questions = ref<Array<{ selectQuestionId: number; selectQuestionTitle: string; selectQuestionDifficulty: string; selectQuestionSubject: string; }>>([]); // 定义一个响应式数组来存储问题
// 使用axios获取数据
const fetchQuestions = async () => {
  try {
    const response = await axios.get('http://127.0.0.1:4523/m1/4462281-4108499-default/RecordQuestionBank/selectQuestion')
    questions.value = response.data // 假设这是包含多个对象的数组
    console.log(response.data)
    // 假设 questions 是一个数组，且至少包含一个对象
    if (questions.value.length > 0) {
      selectQuestionId.value = questions.value[0].selectQuestionId
      selectQuestionTitle.value = questions.value[0].selectQuestionTitle
      selectQuestionDifficulty.value = questions.value[0].selectQuestionDifficulty
      selectQuestionSubject.value = questions.value[0].selectQuestionSubject
    }
    console.log(questions.value[0].selectQuestionId)
  } catch (error) {
    console.error('Error fetching questions:', error)
  }
}
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
const options3 = [
  {
    value: 'VeryEasy',
    label: '非常简单',
  },
  {
    value: 'Easy',
    label: '简单',
  },
  {
    value: 'normal',
    label: '一般',
  },
  {
    value: 'difficult',
    label: '难',
  },
  {
    value: 'VeryDifficult',
    label: '非常难',
  },
]
const handleTypeChange = (value: any) => {
  if (value[0] === 'SCQ') {
    showSingleChoice.value = true
    showMultipleChoice.value = false
    showClozeAnswer.value = false
    showEssayQuestion.value = false
  } else if (value[0] === 'CQ') {
    showSingleChoice.value = false
    showMultipleChoice.value = false
    showClozeAnswer.value = true
    showEssayQuestion.value = false
  }else if(value[0] === 'MCQ'){
    showSingleChoice.value = false
    showMultipleChoice.value = true
    showClozeAnswer.value = false
    showEssayQuestion.value = false
  }
  else if(value[0] === 'EQ'){
    showSingleChoice.value = false
    showMultipleChoice.value = false
    showClozeAnswer.value = false
    showEssayQuestion.value = true
  }
  else {
    showSingleChoice.value = false
    showMultipleChoice.value = false
    showClozeAnswer.value = false
    showEssayQuestion.value = false
  }
}
onMounted(async () => {
  await fetchQuestions(); // 等待数据加载
});
</script>