<template>
  <div class="grammar row">

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
            <router-link class="item" to="/users/vocabulary" v-text="$t('finalProjectApp.userGrammarList.grammar.topicList')">Grammar Topic List</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
        </div>
      </div>
    </div>

    <!--loi dau-->
    <div class="container">
      <div class="content">
        <p class="lead" v-text="$t('finalProjectApp.userGrammarList.home.subtitle.sub01')">Is English grammar really necessary?</p>
        <p class="lead" v-text="$t('finalProjectApp.userGrammarList.home.subtitle.sub02')">Learn grammar helps "right" English. Grammar helps you convey the exact idea. Grammar helps improve communication skills.</p>
        <p class="lead" v-text="$t('finalProjectApp.userGrammarList.home.subtitle.sub03')">Writing the right grammar in job application. Writing and accompanying grammar will increase the value of personal brands. English grammar increases the skills of using mother tongue.</p>
        <p class="lead" v-text="$t('finalProjectApp.userGrammarList.home.subtitle.sub04')">If you want to understand the meaning of the sentence is conveyed from others, or want to develop the idea of expressing the idea, we need to learn as many English grammar as possible. English grammar is really necessary for anyone who wants to effectively communicate English.</p>
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
                  <img src="../../../content/images/grammartopic1.jpg" class="image">
                  <div style="padding: 14px; background-color: #e0e0ff; ">
                    <span v-text="$t('finalProjectApp.userGrammarList.grammar.topic01.title')">THÌ HIỆN TẠI ĐƠN VÀ HIỆN TẠI TIẾP DIỄN</span>
                    <div class="bottom clearfix">
                      <ul>
                        <li v-text="$t('finalProjectApp.userGrammarList.grammar.topic01.text')">Grammar owners need to know in the TOEIC test</li>
                        <li><i class="el-icon-view"></i>200</li>
                        <li><i class="el-icon-user-solid"></i>150</li>
                      </ul>
                      <router-link class="alert-link join" to="/users/grammar/gramTopic" v-text="$t('home.join')">JOIN</router-link>
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
                  <img src="../../../content/images/grammartopic2.jpg" class="image">
                  <div style="padding: 14px; background-color: #e0e0ff; ">
                    <span v-text="$t('finalProjectApp.userGrammarList.grammar.topic02.title')">THÌ HIỆN TẠI HOÀN THÀNH VÀ THÌ HIỆN TẠI HOÀN THÀNH TIẾP DIỄN</span>
                    <div class="bottom clearfix">
                      <ul>
                        <li v-text="$t('finalProjectApp.userGrammarList.grammar.topic01.text')">Grammar owners need to know in the TOEIC test</li>
                        <li><i class="el-icon-view"></i>200</li>
                        <li><i class="el-icon-user-solid"></i>180</li>
                      </ul>
                      <router-link class="alert-link join" to="/users/grammar/gramTopic" v-text="$t('home.join')">JOIN</router-link>
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
                  <img src="../../../content/images/grammartopic5.jpg" class="image">
                  <div style="padding: 14px; background-color: #e0e0ff; ">
                    <span v-text="$t('finalProjectApp.userGrammarList.grammar.topic03.title')">DANH TỪ</span>
                    <div class="bottom clearfix">
                      <ul>
                        <li v-text="$t('finalProjectApp.userGrammarList.grammar.topic01.text')">Grammar owners need to know in the TOEIC test</li>
                        <li><i class="el-icon-view"></i>200</li>
                        <li><i class="el-icon-user-solid"></i>100</li>
                      </ul>
                      <router-link class="alert-link join" to="/users/grammar/gramTopic" v-text="$t('home.join')">JOIN</router-link>
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
                  <img src="../../../content/images/grammartopic6.jpg" class="image">
                  <div style="padding: 14px; background-color: #e0e0ff; ">
                    <span v-text="$t('finalProjectApp.userGrammarList.grammar.topic04.title')">TÍNH TỪ VÀ TRẠNG TỪ</span>
                    <div class="bottom clearfix">
                      <ul>
                        <li v-text="$t('finalProjectApp.userGrammarList.grammar.topic01.text')">Grammar owners need to know in the TOEIC test</li>
                        <li><i class="el-icon-view"></i>200</li>
                        <li><i class="el-icon-user-solid"></i>212</li>
                      </ul>
                      <router-link class="alert-link join" to="/users/grammar/gramTopic" v-text="$t('home.join')">JOIN</router-link>
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


<script lang="ts" src="./grammar.component.ts"></script>


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
.grammar {
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
  background-color: #90bce7;
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
