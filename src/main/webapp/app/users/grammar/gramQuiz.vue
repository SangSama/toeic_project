<template>
  <div class="gramQuiz row">

    <!--tieu de-->
    <div class="title">
      <h1 class="display-4" v-text="$t('finalProjectApp.userGrammarList.grammar.title')">Grammar</h1>
      <div>
        <div class="el-breadcrumb">
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/" v-text="$t('home.about.home')">Home</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/users/grammar" v-text="$t('finalProjectApp.userGrammarList.grammar.topicList')">Grammar Topic List</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/users/grammar/gramTopic" v-text="$t('finalProjectApp.userGrammarList.grammar.topic02.title')">Simple Present & Present Continuous</router-link>
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
            <router-link class="alert-link join" to="/users/grammar/gramTopic">
              <button @click="restartQuiz()">Restart</button>
            </router-link>
          </div>
          <div class="btn-result">
            <router-link class="alert-link join" to="/users/grammar/gramTopic/gramQuiz/gramResult">
              <button>Result</button>
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


<script lang="ts" src="./gramQuiz.component.ts"></script>
<script>
export default {
  data() {
    return {
      questions: [
        {
          question:'My parents normally __________ breakfast at 7:00 a.m',
          propositions: [
            {props:'eat',correct:true},
            {props:'eats',},
            {props:'are eating',},
            {props:'is eating',},
          ]
        },
        {
          question:'This week Barbara is away on business so Tom ________________ dinner for himself',
          propositions: [
            {props:'cook',},
            {props:'cooks',},
            {props:'are cooking',},
            {props:'is cooking',correct:true},
          ]
        },
        {
          question:'Barbara usually _____________ dinner for her husband after work',
          propositions: [
            {props:'cook',},
            {props:'cooks',correct:true},
            {props:'are cooking',},
            {props:'is cooking',},
          ]
        },
        {
          question:'John always __________ on time for meetings.',
          propositions: [
            {props:'arrive',},
            {props:'arrives',correct:true},
            {props:'are arriving',},
            {props:'is arriving',},
          ]
        },
        {
          question:'John __________ at this moment',
          propositions: [
            {props:'arrive',},
            {props:'arrives',},
            {props:'are arriving',},
            {props:'is arriving',correct:true},
          ]
        },
        {
          question:'We often _________ tests at our school',
          propositions: [
            {props:'do',correct:true},
            {props:'dose',},
            {props:'is doing',},
            {props:'are doing',},
          ]
        },
        {
          question:'I ________ to my teacher now.',
          propositions: [
            {props:'talk',},
            {props:'talks',},
            {props:'am talking',correct:true},
            {props:'is talking',},
          ]
        },
        {
          question:'Look! Mandy and Susan _______a film on TV.',
          propositions: [
            {props:'watches',},
            {props:'is watching',},
            {props:'am watching',},
            {props:'are watching',correct:true},
          ]
        },
        {
          question:'Listen! The band _______the new guitar',
          propositions: [
            {props:'test',},
            {props:'am testing',},
            {props:'is testing',correct:true},
            {props:'are testing',},
          ]
        },
        {
          question:'First I ______, then I dress.',
          propositions: [
            {props:'wash',correct:true},
            {props:'washes',},
            {props:'am washing',},
            {props:'are washing',},
          ]
        },
        {
          question:'Quiet please! I ________ a test.',
          propositions: [
            {props:'do',},
            {props:'am doing',correct:true},
            {props:'is doing',},
            {props:'are doing',},
          ]
        },
        {
          question:'At the moment, the two kids _________on the floor.',
          propositions: [
            {props:'sit',},
            {props:'sits',},
            {props:'are sitting',correct:true},
            {props:'is sitting',},
          ]
        },
        {
          question:'Jeff ________ a book right now, he _______ a story to Linda.',
          propositions: [
            {props:'hold – read',},
            {props:'holds – reads',},
            {props:'is holding - is reading',correct:true},
            {props:'are holding - are reading',},
          ]
        },
        {
          question:'Linda ________ Jeff’s stories.',
          propositions: [
            {props:'love',},
            {props:'loves',correct:true},
            {props:'am loving',},
            {props:'is loving',},
          ]
        },
        {
          question:'He ______a story to her every day',
          propositions: [
            {props:'read',},
            {props:'reads',correct:true},
            {props:'am reading',},
            {props:'is reading',},
          ]
        },
        {
          question:'Jenny usually __________ to school, but today she _________ the bus because it _______.',
          propositions: [
            {props:'cycle - take – rain',},
            {props:'cycles - takes – rains',},
            {props:'cycles - takes - is raining',},
            {props:'cycles - is taking - is raining',correct:true},
          ]
        },
        {
          question:'The train always __________ on time.',
          propositions: [
            {props:'leave',},
            {props:'leaves',correct:true},
            {props:'is leaving',},
            {props:'are leaving',},
          ]
        },
        {
          question:'“What’s the matter? Why ____________?”',
          propositions: [
            {props:'do you cry',},
            {props:'you are crying',},
            {props:'is you crying',},
            {props:'are you crying',correct:true},
          ]
        },
        {
          question:'I never ________ to the swimming pool',
          propositions: [
            {props:'go',correct:true},
            {props:'goes',},
            {props:'is going',},
            {props:'am going',},
          ]
        },
        {
          question:'What will happen if we __________ water?',
          propositions: [
            {props:'not conserve',},
            {props:'are not conserve',},
            {props:'do not conserve',correct:true},
            {props:'does not conserve',},
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
  font-size: 16px;
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

.btn-result {
  display: flex;
  width: 100%;
  height: auto;
  justify-content: center;
  margin-top: 10px;
}

.btn-result button {
  width: 150px;
  height: 35px;
  outline: none;
  border: 0;
  background-color: rgb(176, 144, 231);
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
