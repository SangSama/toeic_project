<template>
  <div class="vocaTopic row">

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

    <!--danh sach tu vung-->
    <div class="container">
      <div class="list">
        <el-row :gutter="20">
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Abide by</strong>(v) tuân thủ, tuân theo</p>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Agreement</strong>(n) Sự thỏa thuận</p>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Assurance</strong>(n) Bảo đảm, chắc chắn</p>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Cancellation</strong>(n) sự hủy bỏ</p>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Determine</strong>(v) Giải quyết vấn đề</p>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Engage</strong>(v) Thuê, mướn</p>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Establish</strong>(v) Thành lập</p>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Obligate</strong>(v) bắt buộc, ép buộc</p>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Party</strong>(n) Nhóm làm việc chung</p>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Provision</strong>(n) Sự cung cấp</p>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Resolve</strong>(v) Kiên quyết, quyết định</p>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="grid-content bg-purple">
              <p><strong>Specify</strong>(v) định rõ, ghi rõ</p>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>

    <div class="container">
      <div class="btn-quiz">
        <el-row>
          <router-link class="alert-link" to="/users/vocabulary/vocaList/vocaTopic/vocaQuiz" style="width: 300px; font-weight: bold; font-size: 18px;">Quiz</router-link>
<!--          <el-button style="width: 300px; font-weight: bold; font-size: 18px;" type="primary" round>Quizz</el-button>-->
        </el-row>
      </div>
    </div>

  </div>
</template>


<script lang="ts" src="./vocaTopic.component.ts"></script>


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
.list {
  margin-top: 50px;
  font-size: 20px;
}
.list .el-row {
  margin-bottom: 20px;
}
.list .el-row:last-child {
   margin-bottom: 0;
}
.list .el-col {
  border-radius: 4px;
  margin-top: 10px;
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
.list p:hover {
  background-color: #90bce7;
}
.list strong {
  margin-right: 10px;
}

.btn-quiz {
  margin-top: 40px;
  margin-bottom: 90px;
  text-align: center;
}
.alert-link {
  border: 1px solid #3e8acc;
  border-radius: 5px;
  margin: 0 200px;
  padding: 10px 20px
}
</style>
