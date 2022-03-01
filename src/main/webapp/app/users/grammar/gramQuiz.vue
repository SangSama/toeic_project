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
        <div class="quiz-main">
          <div class="box-question">
            <h2>Question 5/20</h2>
            <p>My parents normally __________ breakfast at 7:00 a.m</p>
          </div>
          <div class="box-suggestions">
            <ul>
              <li>eat</li>
              <li>eats</li>
              <li>are eating</li>
              <li>is eating</li>
            </ul>
          </div>
        </div>
        <div class="quiz-footer">
          <div class="box-button">
            <router-link class="item" to="/users/grammar/gramTopic/gramQuiz/gramResult"><button>Next</button></router-link>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>


<script lang="ts" src="./gramQuiz.component.ts"></script>


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
  background-color: #003c72;
  color: #ffffff;
}
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
