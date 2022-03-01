<template>
  <div class="vocaList row">

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
        </div>
      </div>
    </div>

    <!--danh sach-->
    <div class="container">
      <div class="list">
        <el-row :gutter="20">
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Contracts</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary/vocaList/vocaTopic" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Marketing</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Warranties</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Business Planning</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Conferences</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Computers</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <!-- ******************************************************************************************************** -->
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Office Technology</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Office Procedures</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Electronics</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Correspondence</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Job Advertising and Recruiting</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Applying and Interviewing</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <!-- ******************************************************************************************************** -->
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Hiring and Training</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Salaries and Benefits</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Promotions, Pensions, and Awards</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Shopping</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Ordering Supplies</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Shipping</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <!-- ******************************************************************************************************** -->
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Hiring and Training</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Salaries and Benefits</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Promotions, Pensions, and Awards</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Shopping</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Ordering Supplies</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                    </div>
                  </div>
                </el-card>
              </el-row>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="test-item grid-content bg-purple">
              <el-row>
                <el-card :body-style="{ padding: '0px' }">
                  <img src="../../../content/images/icon02.jpg" class="image">
                  <div style="padding: 14px;">
                    <span>Shipping</span>
                    <div class="bottom clearfix">
                      <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
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


<script lang="ts" src="./vocaList.component.ts"></script>


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
  margin-top: 16px;
  height: 300px;
  background-color: #ffffff;
}
.test-item span {
  padding-top: 5px;
  padding-bottom: 5px;
  font-weight: bold;
  font-size: 16px;
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
  padding-bottom: 0;
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
