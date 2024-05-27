<style>
.el-alert__title {
  font-size: 25px;
}

.el-alert--warning .el-alert__content p {
  font-size: 15px;
}

.el-alert {
  width: 300px;
  height: 100px;
}

.title {
  width: 500px;
}

.single-options {
  font-size: 0;
  padding-top: 30px;
}

.single-options>* {
  display: inline-block;
  font-size: 16px;
  /* 恢复字体大小 */
  vertical-align: top;
  /* 顶对齐，防止纵向错位 */
}

.multiple-options {
  font-size: 0;
  padding-top: 30px;
}

.multiple-options>* {
  display: inline-block;
  font-size: 16px;
  /* 恢复字体大小 */
  vertical-align: top;
  /* 顶对齐，防止纵向错位 */
}

.options p {
  margin-right: 10px;
  /* 在标签和输入框之间添加一些间距 */
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

.addBtn {
  margin-top: 30px;
}

.operateQuestionBtn {
  position: absolute;
  top: 150px;
  left: 350px;
}

.selectQuestion {
  display: inline-block;
}

.selectQuestion p {
  display: inline-block;
  vertical-align: top;
  margin-right: 10px;
  /* 在标签和输入框之间添加一些间距 */
}

.selectQuestion .el-input {
  width: 140px;
  display: inline-block;
  vertical-align: top;
}

.searchForQuestions * {
  display: inline-block;
  /* 将所有子元素设置为内联块级元素 */
  vertical-align: middle;
  /* 垂直居中对齐 */
}

.searchForQuestionBtn {
  position: absolute;
  top: 150px;
  left: 450px;
}
</style>

<template>
  <el-alert title="警告提示" type="warning" effect="dark" :closable="false">请老师仔细填写以下信息</el-alert>
  <el-button type="primary" class="operateQuestionBtn" @click="dialogVisible = true, fetchQuestions">
    操作题库
  </el-button>
  <el-button type="primary" class="searchForQuestionBtn" @click="dialogVisible1 = true">
    题目查找
  </el-button>
  <div class="question">
    <p>题目类型</p>
    <el-cascader v-model="QuestionType" :options="options1" @change="handleTypeChange" />
  </div>
  <div class="type">
    <p>题目科目</p>
    <el-cascader v-model="QuestionSubject" :options="options2" />
  </div>
  <div class="difficuity">
    <p>题目难易</p>
    <el-cascader v-model="QuestionDifficulty" :options="options3"></el-cascader>
  </div>
  <div>
    <p>题头</p>
    <el-input v-model="QuestionTitle" class="title" placeholder="请输入" :autosize="{ minRows: 2, maxRows: 4 }"
      type="textarea" clearable />
  </div>
  <transition-group name="fade">
    <div class="single-choice" v-if="showSingleChoice && QuestionType === 'SCQ'">
      <div class="single-options">
        <p>选项A</p>
        <el-input v-model="optionA" style="width: 240px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
          placeholder="请输入" />
        <p>选项B</p>
        <el-input v-model="optionB" style="width: 240px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
          placeholder="请输入" />
        <p>选项C</p>
        <el-input v-model="optionC" style="width: 240px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
          placeholder="请输入" />
        <p>选项D</p>
        <el-input v-model="optionD" style="width: 240px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
          placeholder="请输入" />
      </div>
      <p>单选题答案</p>
      <el-input v-model="QuestionAnswer" style="width: 240px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
        placeholder="请输入单选题答案" />
    </div>
    <div class="multiple-choice" v-if="showMultipleChoice && QuestionType === 'MCQ'">
      <div class="multiple-options">
        <p>选项A</p>
        <el-input v-model="optionA" style="width: 240px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
          placeholder="请输入" />
        <p>选项B</p>
        <el-input v-model="optionB" style="width: 240px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
          placeholder="请输入" />
        <p>选项C</p>
        <el-input v-model="optionC" style="width: 240px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
          placeholder="请输入" />
        <p>选项D</p>
        <el-input v-model="optionD" style="width: 240px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
          placeholder="请输入" />
      </div>
      <p>多选题答案</p>
      <el-input v-model="QuestionAnswer" style="width: 240px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
        placeholder="请输入多选题答案" />
    </div>

    <div class="cloze-answer" v-if="showClozeAnswer && QuestionType === 'CQ'">
      <p>填空题答案</p>
      <el-input v-model="QuestionAnswer" style="width: 240px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
        placeholder="请输入填空题答案" />
    </div>

    <div class="essay-question" v-if="showEssayQuestion && QuestionType === 'EQ'">
      <p>解答题答案</p>
      <el-input v-model="QuestionAnswer" style="width: 500px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea"
        placeholder="请输入解答题答案" />
    </div>
  </transition-group>
  <el-row>
    <el-button class="addBtn" type="success" @click="addQuestions">添加</el-button>
  </el-row>
  <el-dialog title="题目" v-model="dialogVisible" width="70%">
    <div class="selectQuestion" v-for="(question, index) in questions" :key="index">
      <p>题目编号</p>
      <el-input v-model="question.id" style="width: 140px;display: inline-block;vertical-align: top"
        :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="" disabled />
      <p>题头</p>
      <el-input v-model="question.content" style="width: 140px;display: inline-block;vertical-align: top"
        :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="" disabled />
      <p>难易程度</p>
      <el-input v-model="question.difficulty" style="width: 140px;display: inline-block;vertical-align: top"
        :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="" disabled />
      <p>题目科目</p>
      <el-input v-model="question.type" style="width: 140px;display: inline-block;vertical-align: top"
        :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="" disabled />
      <el-dialog title="题目更改" v-model="dialogVisible2" width="70%">
        <p>题目编号</p>
        <el-input v-model="selectQuestionId" style="width: 140px;display: inline-block;vertical-align: top"
          :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="" disabled />
        <p>题头</p>
        <el-input v-model="selectQuestionTitle" style="width: 140px;display: inline-block;vertical-align: top"
          :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="" />
        <p>难易程度</p>
        <el-cascader v-model="selectQuestionDifficulty" :options="options3" />
        <p>题目科目</p>
        <el-cascader v-model="selectQuestionSubject" :options="options2" />
        <el-cascader v-model="selectQuestionType" :options="options1" />
        <p>题目答案</p>
        <el-input v-model="selectQuestionAnswer" style="width: 140px;display: inline-block;vertical-align: top"
          :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="" />
        <el-button type="primary" @click="updateQuestion">保存</el-button>
        <el-button type="danger" @click="dialogVisible2 = false">取消</el-button>
      </el-dialog>
      <el-button type="warning" @click="editQuestion(index)">更改</el-button>
      <el-button type="danger" @click="deleteQuestion(index)">删除</el-button>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog title="题目查找" v-model="dialogVisible1" width="70%">
    <div class="searchForQuestions">
      <p>题目编号：</p>
      <el-input v-model="searchForQuestionId" style="width: 140px;display: inline-block;vertical-align: top"
        :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="" />
      <el-button type="primary" style="margin: 30px" @click="searchForQuestions"> 查找 </el-button>
    </div>
    <span class="dialog-footer1">
      <el-button type="primary" @click="dialogVisible1 = false">确 定</el-button>
    </span>
  </el-dialog>
</template>
<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'
const QuestionType = ref('');
const QuestionSubject = ref('');
const QuestionDifficulty = ref('');
const QuestionTitle = ref('')
const optionA = ref('')
const optionB = ref('')
const optionC = ref('')
const optionD = ref('')
const singleChoiceAnswer = ref('')
const multipleChoiceAnswer = ref('')
const clozeAnswer = ref('')
const essayAnswer = ref('')
const showSingleChoice = ref(false)
const showMultipleChoice = ref(false)
const showClozeAnswer = ref(false)
const showEssayQuestion = ref(false)
const dialogVisible = ref(false)
const dialogVisible1 = ref(false)
const dialogVisible2 = ref(false)
const selectQuestionType = ref('')
const selectQuestionId = ref(0)
const selectQuestionTitle = ref('')
const selectQuestionDifficulty = ref('')
const selectQuestionSubject = ref('')
const selectQuestionAnswer = ref('')
const questions = ref<Array<{
  subject: string;
  id: number;
  content: string;
  difficulty: string;
  type: string;
  answer: string
}>>([]);
const searchForQuestionId = ref(0);
const QuestionAnswer = computed({
  get: () => {
    switch (QuestionType.value) {
      case 'SCQ':
        return singleChoiceAnswer.value;
      case 'MCQ':
        return multipleChoiceAnswer.value;
      case 'CQ':
        return clozeAnswer.value;
      case 'EQ':
        return essayAnswer.value;
      default:
        return '';
    }
  },
  set: (value) => {
    switch (QuestionType.value) {
      case 'SCQ':
        singleChoiceAnswer.value = value;
        break;
      case 'MCQ':
        multipleChoiceAnswer.value = value;
        break;
      case 'CQ':
        clozeAnswer.value = value;
        break;
      case 'EQ':
        essayAnswer.value = value;
        break;
    }
  }
});
interface question {
  subject: string;
  type: string;
  difficulty: string;
  content: string;
  answer: string;
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
    QuestionType.value = 'SCQ'
  } else if (value[0] === 'CQ') {
    showSingleChoice.value = false
    showMultipleChoice.value = false
    showClozeAnswer.value = true
    showEssayQuestion.value = false
    QuestionType.value = 'CQ'
  } else if (value[0] === 'MCQ') {
    showSingleChoice.value = false
    showMultipleChoice.value = true
    showClozeAnswer.value = false
    showEssayQuestion.value = false
    QuestionType.value = 'MCQ'
  } else if (value[0] === 'EQ') {
    showSingleChoice.value = false
    showMultipleChoice.value = false
    showClozeAnswer.value = false
    showEssayQuestion.value = true
    QuestionType.value = 'EQ'
  } else {
    showSingleChoice.value = false
    showMultipleChoice.value = false
    showClozeAnswer.value = false
    showEssayQuestion.value = false
    QuestionType.value = ''
  }
}

const fetchQuestions = async () => {
  try {
    const response = await axios.get('http://localhost:7001/api/question/all')
    questions.value = response.data // 假设这是包含多个对象的数组
    // 假设 questions 是一个数组，且至少包含一个对象
    if (questions.value.length > 0) {
      selectQuestionId.value = questions.value[0].id
      selectQuestionTitle.value = questions.value[0].content
      selectQuestionDifficulty.value = questions.value[0].difficulty
      selectQuestionSubject.value = questions.value[0].subject
    }
    console.log(questions.value[0].id)
  } catch (error) {
    console.error('Error fetching questions:', error)
  }
}
const addQuestions = async () => {
  try {
    const addQuestion: question = {
      type: QuestionType.value,
      subject: QuestionSubject.value[0],
      difficulty: QuestionDifficulty.value[0],
      content: QuestionTitle.value,
      answer: QuestionAnswer.value
    };
    // 检查所有必要的属性是否已被设置
    if (
      QuestionType.value &&
      QuestionSubject.value[0] &&
      QuestionDifficulty.value[0] &&
      QuestionTitle.value &&
      QuestionAnswer.value
    ) {
      // 发送请求到服务器
      try {
        await axios.post('http://localhost:7001/api/question/add', addQuestion)
          .then(() => {
            ElMessage({
              message: '添加成功',
              type: 'success',
            })
          })
          .catch(() => {
            ElMessage('error', '添加失败');
          });
      } catch (error) {
        console.error('Error sending request:', error);
        alert('添加失败，请检查网络连接或服务器状态');
      }
    } else {
      // 如果任何属性为空，弹出警告框
      alert('请确保所有属性都已填写！');
    }
  } catch (error) {
    console.error('Error in addQuestions function:', error);
    alert('添加失败，请检查输入并重试');
  }
};
const searchForQuestions = async () => {
  try {
    const response = await axios.get(`http://127.0.0.1:4523/m1/4462281-4108499-default/RecordQuestionBank/searchQuestion`, {
      params: {
        id: searchForQuestionId.value
      }
    });
    const questionVo = response.data;
    // 更新UI，例如显示找到的QuestionVo对象的属性
    selectQuestionId.value = questionVo.selectQuestionId;
    selectQuestionTitle.value = questionVo.selectQuestionTitle;
    selectQuestionDifficulty.value = questionVo.selectQuestionDifficulty;
    selectQuestionSubject.value = questionVo.selectQuestionSubject;
  } catch (error) {
    console.error('Error searching for question:', error);
    // 处理错误，例如弹出错误消息
    alert('查找失败，请检查网络连接或服务器状态');
  }
};
const deleteQuestion = async (index: number) => {
  try {
    const id = questions.value[index].id;
    const response = await axios.delete(`http://127.0.0.1:7001/api/question/${id}`);
    if (response.status === 200) {
      // 从前端数组中移除题目
      questions.value.splice(index, 1);
      alert('删除成功');
    } else {
      alert('删除失败');
    }
  } catch (error) {
    console.error('Error deleting question:', error);
    alert('删除失败，请检查网络连接或服务器状态');
  }
};
const editQuestion = (index: number) => {
  // 获取当前索引的question对象
  const question = questions.value[index];

  // 打开对话框，显示question的详细信息
  dialogVisible2.value = true;
  selectQuestionId.value = question.id;
  selectQuestionTitle.value = question.content;
  selectQuestionDifficulty.value = question.difficulty;
  selectQuestionSubject.value = question.type;
  QuestionAnswer.value = question.answer;
};
const updateQuestion = async () => {
  // 创建一个新的question对象，包含所有属性
  const updatedQuestion = {
    type: selectQuestionType.value,
    id: selectQuestionId.value,
    content: selectQuestionTitle.value,
    difficulty: selectQuestionDifficulty.value,
    subject: selectQuestionSubject.value,
    answer: QuestionAnswer.value
  };

  // 检查是否有属性发生变化
  const originalQuestion = questions.value.find(
    q => q.id === selectQuestionId.value
  );

  // 如果originalQuestion存在，并且有任何属性不同，则更新question
  if (originalQuestion &&
    (originalQuestion.content !== updatedQuestion.content ||
      originalQuestion.difficulty !== updatedQuestion.difficulty ||
      originalQuestion.type !== updatedQuestion.subject ||
      originalQuestion.answer !== updatedQuestion.answer)) {

    try {
      // 发送请求到服务器，更新题目
      const response = await axios.put(
        `http://127.0.0.1:4523/m1/4462281-4108499-default/RecordQuestionBank/updateQuestion`,
        updatedQuestion
      );
      if (response.status === 200) {
        // 更新成功，更新前端数组中的对象
        const index = questions.value.findIndex(
          q => q.id === selectQuestionId.value
        );
        if (index !== -1) {
          questions.value[index] = updatedQuestion;
        }
        alert('更新成功');
      } else {
        alert('更新失败');
      }
    } catch (error) {
      console.error('Error updating question:', error);
      alert('更新失败，请检查网络连接或服务器状态');
    }
  } else {
    // 如果没有变化，则不发送请求
    alert('没有变化，不需要更新');
  }
};

onMounted(async () => {
  await fetchQuestions(); // 等待数据加载
});
</script>