<template>
  <div class="vocaQuiz row">

    <!--tieu de-->
    <div class="title">
      <h1 class="display-4" v-text="$t('finalProjectApp.userVocabularyList.vocabulary.title')">Vocabulary</h1>
      <div>
        <div class="el-breadcrumb">
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/" v-text="$t('home.about.home')">Home</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/users/vocabulary" v-text="$t('finalProjectApp.userVocabularyList.vocabulary.topicList')">Vocabulary Topic List</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/users/vocabulary/vocaList" v-text="$t('finalProjectApp.userVocabularyList.vocabulary.topic01.title')">600 WORDS TOEIC</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/users/vocabulary/vocaList/vocaTopic">Contracts</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
        </div>
      </div>
    </div>

    <!-- -->
    <div class="container app">
      <div class="quiz">
        <div class="quiz-header">
          <h1>QUIZ</h1>
        </div>

        <!-- -->
        <div class="quiz-main" v-for="(question,index) in questions.slice(a,b)" :key="index" v-show="quiz">
          <div class="box-question">
            <h2>Question {{b}}/{{questions.length}}</h2>
            <p>{{question.question}}</p>
          </div>
          <div class="box-suggestions">
            <ul>
              <li
                v-for="(proposition,index) in question.propositions"
                :key="index"
                @click="selectResponse(proposition,index)"
                :class=" correct ? check(proposition) : ''"
              >
                {{proposition.props}}
                <div class="fas fa-check" v-if="correct ?  proposition.correct: ''"></div>
                <div class="fas fa-times" v-if="correct ?  !proposition.correct: ''"></div>
              </li>
            </ul>
          </div>
        </div>

        <div class="box-score" v-if="score_show">
          <h2>Your score is</h2>
          <h2>{{score}}/{{questions.length}}</h2>
          <div class="btn-restart">
            <router-link class="alert-link join" to="/users/vocabulary/vocaList/vocaTopic">
              <button @click="restartQuiz()">Restart</button>
            </router-link>
          </div>
        </div>

        <!-- -->
        <div class="quiz-footer">
          <div class="box-button" v-if="progress < 100">
            <button  @click="nextQuestion()" :style="!next ? 'background-color: rgb(106, 128, 202)' : ''">Next</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>


<script lang="ts" src="./vocaQuiz.component.ts"></script>
<script>
export default {
  data() {
    return {
      questions: [
        {
          question:'Abide by',
          propositions: [
            {props:'(v) Chứng minh, giải thích',},
            {props:'(v) Tuân thủ, tuân theo',correct:true},
            {props:'(n) Sự tổ chức sắp xếp',},
            {props:'(v) Tập hợp, thu thập',},
          ]
        },
        {
          question:'Agreement',
          propositions: [
            {props:'(n) Hậu quả, kết quả',},
            {props:'(n) Thông cảm',},
            {props:'(n) Sự thỏa thuận',correct:true},
            {props:'(n) Liên kết, kết hợp',},
          ]
        },
        {
          question:'Assurance',
          propositions: [
            {props:'(n) Bảo đảm, chắc chắn',correct:true},
            {props:'(v) Chứng minh, giải thích',},
            {props:'(n) Luật, quy tắc',},
            {props:'(v) Tổng kết, thu thập',},
          ]
        },
        {
          question:'Cancellation',
          propositions: [
            {props:'(n) sự hủy bỏ',correct:true},
            {props:'(n) Sự phổ biến',},
            {props:'(adj) Đang thịnh hành, hiện tại',},
            {props:'(v) Thuê, mướn',},
          ]
        },
        {
          question:'Determine',
          propositions: [
            {props:'(v) Thuyết phục',},
            {props:'(v) Kết thúc, hết hiệu lực',},
            {props:'(v) Giải quyết vấn đề',correct:true},
            {props:'(v) Xóa, loại bỏ',},
          ]
        },
        {
          question:'Engage',
          propositions: [
            {props:'(v) Bảo vệ',},
            {props:'(v) Tái diễn',},
            {props:'(v) bắt buộc, ép buộc',},
            {props:'(v) Thuê, mướn',correct:true},
          ]
        },
        {
          question:'Establish',
          propositions: [
            {props:'(v) Tổng kết, thu thập',},
            {props:'(v) Thành lập',correct:true},
            {props:'(v) Lảng tránh, lờ đi',},
            {props:'(v) Hàm ý, nói bóng',},
          ]
        },
        {
          question:'Obligate',
          propositions: [
            {props:'(v) truyền cảm hứng',},
            {props:'(v) Hứa, cam kết',},
            {props:'(v) bắt buộc, ép buộc',correct:true},
            {props:'(v) suy ra, nhận ra',},
          ]
        },
        {
          question:'Party',
          propositions: [
            {props:'(n) Sự nổi tiếng, danh tiếng',},
            {props:'(n, v) Đơn xin, kiến nghị',},
            {props:'(n) Nhóm làm việc chung',correct:true},
            {props:'(n) sự thỏa mãn',},
          ]
        },
        {
          question:'Provision',
          propositions: [
            {props:'(n) Sự thuyết phục',},
            {props:'(n) Sự cung cấp',correct:true},
            {props:'(n) Sự nổi tiếng, danh tiếng',},
            {props:'(n) Dự trù, vạch kế hoạch',},
          ]
        },
        {
          question:'Resolve',
          propositions: [
            {props:'(v) Kiên quyết, quyết định',correct:true},
            {props:'(v) Tăng cường, củng cố',},
            {props:'(v) Thay đổi, bất đồng',},
            {props:'(n, v) Sự thực hành',},
          ]
        },
        {
          question:'Specify',
          propositions: [
            {props:'(v) Thay đổi, bất đồng',},
            {props:'(v) định rõ, ghi rõ',correct:true},
            {props:'(adj) bằng lời',},
            {props:'(v) Sửa lại',},
          ]
        },
      ],

      a:0,
      b:1,
      next:true,
      score_show:false,
      quiz:true,
      score:0,
      correct:false,
      progress:0,
    }
  },

  name: 'vocaQuiz',

  methods: {

    selectResponse(e) {
      this.correct = true;
      this.next = false;
      if(e.correct) {
        this.score++;
      }
    },

    check(status) {
      if(status.correct) {
        return 'correct'
      }else {
        return 'incorrect'
      }
    },

    nextQuestion() {
      if(this.next) {
        return;
      }
      this.progress = this.progress + (100/this.questions.length);
      if(this.questions.length - 1 == this.a) {
        this.score_show = true;
        this.quiz = false;
      }else {
        this.a++;
        this.b++;
        this.correct = false;
        this.next = true;
      }
    },

    skipQuestion() {
      if(!this.next) {
        return;
      }
      this.progress = this.progress + (100/this.questions.length);
      if(this.questions.length - 1 == this.a) {
        this.score_show = true;
        this.quiz = false;
      }else {
        this.a++;
        this.b++;
      }
    },

    restartQuiz() {
      Object.assign(this.$data, this.$options.data()); // reset data in vue
    },

  }
}
</script>


<style scoped>
/**/
.title {
  color: #ffffff;
  width: 100%;
  height: 150px;
  background-image: linear-gradient(to bottom right, #003c72, #c000de);
  padding-top: 30px;
  padding-left: 100px;
}
.el-breadcrumb {
  padding-top: 10px;
  font-size: 14px;
  line-height: 1px;
}
.el-breadcrumb:before {
  display: table;
  content: "";
}
.item {
  color: #ffffff;
  font-weight: 700;
  text-decoration: none;
}
.item:hover {
  text-decoration: none;
  color: #ffffff;
  font-weight: 700;
}
.el-breadcrumb__separator {
  margin: 0 9px;
  font-weight: 700;
  color: #c0c4cc;
}

/**/
.app {
  display: flex;
  width: 100%;
  height: 620px;
  justify-content: center;
  position: relative;
}
.quiz {
  display: flex;
  width: 100%;
  height: 90%;
  position: absolute;
  top: 20px;
  bottom: 0;
  margin: auto;
  flex-flow: column;
  text-align: center;
  border: 1px solid #e7eae0;
  border-radius: 10px;
  background-color: #ffffff;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
}
.quiz-header {
  display: flex;
  width: 100%;
  height: 20%;
  border-bottom: 1px solid #e7eae0;
  justify-content: center;
  align-items: center;
  background-color: #90bce7;
  border-radius: 10px 10px 0 0;
}
.quiz-header h1 {
}
.quiz-main {
  display: flex;
  width: 100%;
  height: 70%;
  flex-flow: column;
  margin: auto;
}

.box-question {
  margin-top: 20px;
}
.box-question p {
  font-size: 20px;
  text-transform: uppercase;
}

.box-suggestions {
  display: flex;
  width: 100%;
  justify-content: center;
  margin: auto;
}

ul {
  display: flex;
  width: 80%;
  margin: 0;
  padding: 0;
  flex-flow: column;
}
ul li {
  list-style: none;
  line-height: 2;
  border: 1px solid #3e8acc;
  margin-bottom: 20px;
  border-radius: 15px;
  cursor: pointer;
}
ul li:hover {
  background-color: #78b1e0;
  /*color: #ffffff;*/
}

li.correct {
  border: 1px solid rgb(22, 50, 115);
  background-color: rgb(22, 50, 115);
  color: white;
  font-weight: 600;
}

li.incorrect {
  border: 1px solid rgb(177, 145, 234);
  background-color: rgb(177, 145, 234);
  color: white;
  font-weight: 600;
}
.box-score {
  display: flex;
  width: 100%;
  height: 70%;
  flex-flow: column;
}

.box-score h2 {
  margin-top: 40px;
}

/*i {*/
/*  display: none;*/
/*  color: white;*/
/*}*/

.step-progress {
  display: flex;
  width: 100%;
  height: 5px;
  background-color: rgb(106, 128, 202);
  transition: 0.5s;
}

.btn-restart {
  display: flex;
  width: 100%;
  height: auto;
  justify-content: center;
  margin-top: 50px;
}

.btn-restart button {
  width: 150px;
  height: 35px;
  outline: none;
  border: 0;
  background-color: rgb(106, 128, 202);
  color: white;
  font-size: 18px;
  cursor: pointer;
  border-radius: 15px;
  margin: auto;
  margin-bottom: 10px;
  letter-spacing: 2px;
}

/*.next {*/
/*  background-color: rgb(106, 128, 202);*/
/*}*/

.quiz-footer {
  display: flex;
  width: 100%;
  height: 10%;
  justify-content: center;
  border-top: 1px solid #e7eae0;
  background-color: #90bce7;
  border-radius: 0 0 10px 10px;
}

.box-button {;
  width: 200px;
}

.box-button button {
  width: 100%;
  height: 35px;
  border-radius: 20px;
  background-image: linear-gradient(to bottom right, #003c72, #c000de);
  margin-top: 10px;
  color: #ffffff;
}

</style>
