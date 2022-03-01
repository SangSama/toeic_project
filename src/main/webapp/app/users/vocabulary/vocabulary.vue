<template>
  <div class="vocabulary row">

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
        </div>
      </div>
    </div>

    <!--loi dau-->
    <div class="container">
      <div class="content">
        <p class="lead" v-text="$t('finalProjectApp.userVocabularyList.home.subtitle.sub01')">Vocabulary is the foundation for development skills.</p>
        <p class="lead" v-text="$t('finalProjectApp.userVocabularyList.home.subtitle.sub02')">Rich, abundant capital will support you a lot in developing and
          perfecting other skills more effectively.</p>
        <p class="lead" v-text="$t('finalProjectApp.userVocabularyList.home.subtitle.sub03')">Get capital from variety, you can easily understand the communication
          content even if your grammar is not too firm. And when I understand fast, understand it right, you will be able to reflect
          the reply quickly. The process of listening, understanding and saying there will be more and more fluent, fluent.</p>
        <p class="lead" v-text="$t('finalProjectApp.userVocabularyList.home.subtitle.sub04')">In addition to issues related to listening, speaking or reading skills,
          English vocabulary also helps develop the brain in the ability to write quickly, in the right context and not missing.
          From there, you can completely confidently write a letter or present a problem in English without worrying about anything.</p>
      </div>
    </div>

    <!--danh sach-->
    <div class="container">
      <div class="list">
        <el-row :gutter="20">
          <el-col :span="6">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/vocabulary600.jpg" class="image">
                  <div style="padding: 14px;">
                    <span v-text="$t('finalProjectApp.userVocabularyList.vocabulary.topic01.title')">600 WORDS TOEIC</span>
                    <div class="bottom clearfix">
                      <ul>
                        <li v-text="$t('finalProjectApp.userVocabularyList.vocabulary.topic01.text')">Some topics in 600 basic TOEIC vocabulary. The common vocabulary will appear in the TOEIC exam.</li>
                        <li><i class="el-icon-view"></i>200</li>
                        <li><i class="el-icon-user-solid"></i>150</li>
                      </ul>
                      <router-link class="alert-link join" to="/users/vocabulary/vocaList" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/vocabulary500.jpg" class="image">
                  <div style="padding: 14px;">
                    <span v-text="$t('finalProjectApp.userVocabularyList.vocabulary.topic02.title')">500 WORDS POPULAR</span>
                    <div class="bottom clearfix">
                      <ul>
                        <li v-text="$t('finalProjectApp.userVocabularyList.vocabulary.topic02.text')">Some topics in 500 basic TOEIC vocabulary. The common vocabulary will appear in the TOEIC exam.</li>
                        <li><i class="el-icon-view"></i>200</li>
                        <li><i class="el-icon-user-solid"></i>180</li>
                      </ul>
                      <router-link class="alert-link join" to="/users/vocabulary/vocaList" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/vocabulary1500.jpg" class="image">
                  <div style="padding: 14px;">
                    <span v-text="$t('finalProjectApp.userVocabularyList.vocabulary.topic03.title')">1500 WORDS TOEIC</span>
                    <div class="bottom clearfix">
                      <ul>
                        <li v-text="$t('finalProjectApp.userVocabularyList.vocabulary.topic03.text')">Some topics in 1500 basic TOEIC vocabulary. The common vocabulary will appear in the TOEIC exam.</li>
                        <li><i class="el-icon-view"></i>200</li>
                        <li><i class="el-icon-user-solid"></i>100</li>
                      </ul>
                      <router-link class="alert-link join" to="/users/vocabulary/vocaList" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/vocabulary3500.jpg" class="image">
                  <div style="padding: 14px;">
                    <span v-text="$t('finalProjectApp.userVocabularyList.vocabulary.topic04.title')">3500 WORDS TOEIC</span>
                    <div class="bottom clearfix">
                      <ul>
                        <li v-text="$t('finalProjectApp.userVocabularyList.vocabulary.topic04.text')">Some topics in 3500 basic TOEIC vocabulary. The common vocabulary will appear in the TOEIC exam.</li>
                        <li><i class="el-icon-view"></i>200</li>
                        <li><i class="el-icon-user-solid"></i>212</li>
                      </ul>
                      <router-link class="alert-link join" to="/users/vocabulary/vocaList" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>

  </div>
</template>


<script lang="ts" src="./vocabulary.component.ts"></script>


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
.vocabulary {
  font-size: 15px;
}

.content {
  margin-top: 25px;
}
.content p {
  font-size: 16px;
  text-align: left;
  text-indent: 30px;
  margin-bottom: 15px;
  font-style: italic;
  font-weight: bold;
  line-height: 25px;
}

/**/
.list {
  margin-top: 20px;
}
.list .el-row {
  margin-bottom: 20px;
}
.list .el-row:last-child {
  margin-bottom: 0;
}
.list .el-col {
  border-radius: 4px;
}
.list .bg-purple-dark {
  background: #99a9bf;
}
.list .bg-purple {
  background: #d3dce6;
}
.list .bg-purple-light {
  background: #e5e9f2;
}
.list .grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.list .row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

/**/
.test-item {
}
.test-item span {
  font-weight: bold;
  font-size: 18px;
}
.test-item .bottom {
  /*margin-top: 13px;*/
  /*line-height: 12px;*/
}
.test-item .bottom ul {
  padding-left: 0;
}
.test-item .bottom ul li {
  list-style-type: none;
  text-indent: 10px;
  font-size: 16px;
}
.test-item .bottom ul li:first-child {
  text-indent: 20px;
}
.test-item .bottom ul li i {
  padding-right: 5px;
}
.test-item .bottom .join {
  color: #3e8acc;
  padding: 0;
  float: right;
}
.test-item .image {
  width: 100%;
  display: block;
}
.test-item .clearfix:before,
.test-item .clearfix:after {
  display: table;
  content: "";
}
.test-item .clearfix:after {
  clear: both
}

</style>
