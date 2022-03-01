<template>
  <div class="about row">

<!--tieu de-->
    <div class="title">
      <h1 class="display-4" v-text="$t('home.about.title')">About us</h1>
      <div>
        <div class="el-breadcrumb">
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/" v-text="$t('home.about.home')">Home</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/about" v-text="$t('home.about.aboutus')">About</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
        </div>
      </div>
    </div>

<!--muc-->
    <div class="container">
      <div class="alert alert-success bg-test diss">
        <div class="test-title">
          <h3 v-text="$t('home.test.title')">Our three key points</h3>
        </div>
        <div class="test">
          <el-row :gutter="20">
            <el-col :span="8">
              <div class="test-item grid-content bg-purple">
                <el-row>
                  <el-card :body-style="{ padding: '0px' }">
                    <img src="../../../content/images/vocabulary.jpg" class="image">
                    <div style="padding: 14px;">
                      <span v-text="$t('home.test.vocabulary')">VOCABULARY</span>
                      <div class="bottom clearfix">
                        <ul>
                          <li v-text="$t('home.test.voca01')">It is important for typical language learning.</li>
                          <li v-text="$t('home.test.voca02')">Vocabulary is the focus of language.</li>
                          <li v-text="$t('home.test.voca03')">Vocabulary is the core of English when communicating.</li>
                          <li v-text="$t('home.test.voca04')">Vocabulary is the foundation for development skills.</li>
                        </ul>
                        <router-link class="alert-link join" to="/users/vocabulary" v-text="$t('home.join')">JOIN</router-link>
                      </div>
                    </div>
                  </el-card>
                </el-row>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="test-item grid-content bg-purple">
                <el-row>
                  <el-card :body-style="{ padding: '0px' }">
                    <img src="../../../content/images/grammar02.jpg" class="image">
                    <div style="padding: 14px;">
                      <span v-text="$t('home.test.grammar')">GRAMMAR</span>
                      <div class="bottom clearfix">
                        <ul>
                          <li v-text="$t('home.test.gram01')">It can be said that grammar is a very important part with competitions of writing competitions or exams on the school.</li>
                          <li v-text="$t('home.test.gram02')">However, for communication, grammar is not an important issue.</li>
                          <li v-text="$t('home.test.gram03')">The important English grammar does not depend on the purpose of the learners.</li>
                        </ul>
                        <router-link class="alert-link join" to="/users/grammar" v-text="$t('home.join')">JOIN</router-link>
                      </div>
                    </div>
                  </el-card>
                </el-row>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="test-item grid-content bg-purple">
                <el-row>
                  <el-card :body-style="{ padding: '0px' }">
                    <img src="../../../content/images/toeic01.jpg" class="image">
                    <div style="padding: 14px;">
                      <span v-text="$t('home.test.toeic')">TOEIC</span>
                      <div class="bottom clearfix">
                        <ul>
                          <li v-text="$t('home.test.toeic01')">It is an English test designed to assess the ability to use English in daily situations.</li>
                          <li v-text="$t('home.test.toeic02')">Learning TOEIC is a student's "passport ticket".</li>
                          <li v-text="$t('home.test.toeic03')">TOEIC is a measure of large companies and corporations.</li>
                          <li v-text="$t('home.test.toeic04')">TOEIC helps you more confident, Western cultural exchange.</li>
                        </ul>
                        <router-link class="alert-link join" to="/users/toeic" v-text="$t('home.join')">JOIN</router-link>
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

<!--loi ich-->
    <div class="container">
      <div class="alert alert-success diss">
        <div class="contact-us">
          <div class="image">
            <img src="../../../content/images/about01.jpg">
          </div>
          <div class="text">
            <h3 v-text="$t('home.about.text')">The results of learners in the focus of English practice you will receive.</h3>
            <ul>
              <li v-text="$t('home.about.text01')">Vocabulary from basic to advanced communication and in TOEIC exam preparation.</li>
              <li v-text="$t('home.about.text02')">Full grammar topics for demand from zero to international certification exam.</li>
              <li v-text="$t('home.about.text03')">Test with the TOEIC exam quickly, compact, easy and sword in time as well as cost.</li>
              <li v-text="$t('home.about.text04')">Know how to learn and practice English</li>
            </ul>
          </div>
        </div>
      </div>
    </div>

<!--thanh vien-->
    <div class="container">
      <div class="team">
        <div class="team-title">
          <h3 v-text="$t('home.about.team')">Our Team</h3>
        </div>
        <div class="team-member">
          <el-row :gutter="12">
            <el-col :span="8">
              <el-card shadow="never">
                <div class="item"></div>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card shadow="hover">
                <el-row>
                  <el-card>
                    <img src="../../../content/images/avatar01.jpg" class="image">
                    <div class="info">
                      <p><strong>Trương Thị Sáng</strong></p>
                      <div class="bottom clearfix">
                        <ul>
                          <li>Admin / Developer / Designer</li>
                          <li>Phone number: 0988161359</li>
                          <li>Student Code: 20172785</li>
                        </ul>
                      </div>
                    </div>
                  </el-card>
                </el-row>
              </el-card>
            </el-col>
            <el-col :span="8">
              <el-card shadow="never">
                <div class="item"></div>
              </el-card>
            </el-col>
          </el-row>
        </div>
      </div>
    </div>

  </div>
</template>


<script  lang="ts" src="./about.component.ts"></script>


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
.diss {
  border: none;
  background: none;
  color: #002950;
}

/**/
.bg-test {
  padding-top: 20px;
  padding-bottom: 20px;
}
.bg-img .image {
  width: 100%;
  display: block;
}

/**/
.test-title h3 {
  padding-top: 30px;
  padding-bottom: 20px;
  text-align: center;
  font-weight: bold;
  color: #002950;
}
.test .el-row {
  margin-bottom: 20px;
}
.test .el-row:last-child {
  margin-bottom: 0;
}
.test .el-col {
  border-radius: 4px;
}
.test .bg-purple-dark {
  background: #99a9bf;
}
.test .bg-purple {
  background: #d3dce6;
}
.test .bg-purple-light {
  background: #e5e9f2;
}
.test .grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.test .row-bg {
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
}
.test-item .bottom ul li {
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

/**/
.contact-us {
  width: 100%;
  display: inline-flex;
  height: 400px;
}
.contact-us .image {
  width: auto;
  height: 400px;
  display: block;
}
.contact-us .image img {
  width: auto;
  height: 100%;
}
.contact-us .text {
  padding-top: 30px;
  width: auto;
  background-color: #ffffff;
}
.contact-us .text h3 {
  font-weight: bold;
}
.contact-us .text ul {
  padding-top: 10px;
}
.contact-us .text ul li {
  padding-top: 15px;
}

/**/
.team {

}
.team .team-title {
  text-align: center;
}
.team .team-title h3 {
  font-weight: bold;
  color: #002950;
  padding-bottom: 20px;
}
.team .team-member {
  width: 100%;
  height: 100%;
  display: block;
}
.team .team-member img.image {
  display: block;
  margin-left: auto;
  margin-right: auto;
  width: auto;
  height: 100%;
  border-radius: 50%;
}
.team .team-member .info p {
  padding-top: 10px;
  font-size: 20px;
}
.team .team-member .item {
  background-color: #3e8acc;
}

</style>
