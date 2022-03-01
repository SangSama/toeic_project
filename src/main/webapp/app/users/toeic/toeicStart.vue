<template>
  <div class="toeicStart row">

    <!--tieu de-->
    <div class="title">
      <h1 class="display-4" v-text="$t('finalProjectApp.userToeicList.toeic.title')">TOEIC</h1>
      <div>
        <div class="el-breadcrumb">
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/" v-text="$t('home.about.home')">Home</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/users/toeic" v-text="$t('finalProjectApp.userToeicList.toeic.topicList')">TOEIC Exam List</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/users/toeic/toeicStart" v-text="$t('finalProjectApp.userToeicList.toeic.topic01.title')">ETS_2021_Test_01</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
        </div>
      </div>
    </div>

    <!-- start -->
    <div class="container">
      <div class="start">
        <h2 v-text="$t('finalProjectApp.userToeicList.toeic.topic01.title')">ETS_2021_Test_01</h2>
        <div class="btn-start">
          <router-link class="alert-link join" to="/users/toeic/toeicStart/toeicExam" type="primary">
            <i class="el-icon-video-play"></i>
            <p v-text="$t('finalProjectApp.userToeicList.toeic.start')">START</p>
          </router-link>
<!--          <el-link href="/users/toeic/toeicStart/toeicExam" type="primary">-->
<!--            <i class="el-icon-video-play"></i>-->
<!--            <p>START</p>-->
<!--          </el-link>-->
        </div>
        <p>Bấm <span v-text="$t('finalProjectApp.userToeicList.toeic.start')">START</span> để bắt đầu làm bài nhé ^^</p>
      </div>
    </div>



  </div>
</template>


<script lang="ts" src="./toeicStart.component.ts"></script>


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
.start {
  text-align: center;
  height: 590px;
}
.start h2 {
  margin-top: 30px;
  margin-bottom: 100px;
}
.start .btn-start {;
}
.btn-start .join{
  text-align: center;
  margin-bottom: 50px;
}
.btn-start i {
  font-size: 200px;
  font-weight: bolder;
}
.btn-start p {
  font-size: 50px;
  font-weight: bolder;
}



</style>
