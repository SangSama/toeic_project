<template>
  <div class="home row">
<!--    <el-backtop target=".home" :bottom="100" :visibility-height="100">-->
<!--      <div class="el-backtop" style="right:40px; bottom:40px">-->
<!--        <i class="el-icon-caret-top"></i>-->
<!--      </div>-->
<!--    </el-backtop>-->

    <div class="col-md-3">
      <span class="hipster img-fluid rounded"></span>
    </div>
    <div class="col-md-9">
      <h1 class="display-4" v-text="$t('home.title')">Welcome, TOEICsv!</h1>

      <div>
        <!--Dang nhap thanh cong-->
        <div class="alert alert-success" v-if="authenticated">
          <span v-if="username" v-text="$t('home.logged.message', { username: username })">You are logged in as user "{{ username }}"</span>
        </div>

        <div class="alert alert-success diss" v-if="authenticated">
          <div class="bg-img">
            <img src="../../../content/images/home02.jpg" class="image">
          </div>
        </div>

        <div class="alert alert-success bg-test" v-if="authenticated">
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

        <div class="alert alert-success diss" v-if="authenticated">
          <div class="contact-us">
            <div class="image">
              <img src="../../../content/images/contact02.jpg">
            </div>
            <div class="text">
              <h4 v-text="$t('home.contactus.text')">Contact us if you have any questions or want to contribute to the development of the website.</h4>
              <h6><strong>Trương Thị Sáng</strong> - Admin / Developer / Designer</h6>
              <ul>
                <li>sang.tt172785@sis.hust.edu.vn</li>
                <li>sang.sama.1999@gmail.com</li>
              </ul>
            </div>
          </div>
        </div>

        <!--Trang dang nhap/dang ky-->

        <div class="alert alert-warning diss" v-if="!authenticated">
          <div class="bg-login">
            <img src="../../../content/images/home01.jpg" class="image">
          </div>
        </div>

        <div class="alert alert-warning diss" v-if="!authenticated">
          <el-row class="signin">
            <el-button class="signin-btn" type="primary" v-on:click="openLogin()" v-text="$t('global.messages.info.authenticated.link')" round plain>Sign in</el-button>
          </el-row>
        </div>

<!--        <div class="alert alert-warning" v-if="!authenticated">-->
<!--          <span v-text="$t('global.messages.info.authenticated.prefix')">If you want to </span>-->
<!--          <a class="alert-link" v-on:click="openLogin()" v-text="$t('global.messages.info.authenticated.link')">sign in</a-->
<!--          ><span v-html="$t('global.messages.info.authenticated.suffix')"-->
<!--            >, you can try the default accounts:<br />- Administrator (login="admin" and password="admin") <br />- User (login="user" and-->
<!--            password="user").</span-->
<!--          >-->
<!--        </div>-->

        <div class="alert alert-warning signup diss" v-if="!authenticated">
          <span v-text="$t('global.messages.info.register.noaccount')">You don't have an account yet?</span>&nbsp;
          <router-link class="alert-link" to="/register" v-text="$t('global.messages.info.register.link')"
            >Register a new account</router-link
          >
        </div>


      </div>
    </div>
  </div>
</template>


<script lang="ts" src="./home.component.ts"></script>


<style>
  /*.home {*/
  /*  overflow-y: scroll;*/
  /*}*/

  /*.el-backtop {*/
  /*  position: fixed;*/
  /*  background-color: #fff;*/
  /*  width: 40px;*/
  /*  height: 40px;*/
  /*  border-radius: 50%;*/
  /*  color: #409eff;*/
  /*  display: flex;*/
  /*  align-items: center;*/
  /*  justify-content: center;*/
  /*  font-size: 20px;*/
  /*  box-shadow: 0 0 6px rgba(0,0,0,.12);*/
  /*  cursor: pointer;*/
  /*  z-index: 5;*/
  /*}*/

  /*.el-icon-caret-top:before {*/
  /*  content: " ";*/
  /*}*/

  /*.scrolltotop {*/
  /*  height: 100%;*/
  /*  width: 100%;*/
  /*  background-color: #f2f5f6;*/
  /*  box-shadow: 0 0 6px rgba(0,0,0, .12);*/
  /*  text-align: center;*/
  /*  line-height: 40px;*/
  /*  color: #1989fa;*/
  /*}*/

  .hipster.img-fluid {
    width: 415px;
    height: 600px;
    margin-top: 50px;
  }

  .alert-success {
    border-color: #79b5e8;
    color: #ffffff;
    background-image: linear-gradient(to bottom right, #003c72, #c000de);
  }

  .diss {
    border: none;
    background: none;
    color: #002950;
  }

  .bg-test {
    padding-top: 20px;
    padding-bottom: 20px;
    background: none;
    border: none;
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

  .bg-img .image {
    width: 100%;
    display: block;
  }

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

  .contact-us {
    display: inline-flex;
    height: 150px;
  }
  .contact-us .image {
    width: 450px;
    height: 150px;
    display: block;
  }
  .contact-us .image img {
    width: auto;
    height: 100%;
  }
  .contact-us .text {
    width: auto;
    background-color: #ffffff;
  }
  .contact-us .text h4,h5 {
    font-weight: bold;
  }
  .contact-us .text ul {
  }
  .contact-us .text ul li {
  }

  .bg-login {
    width: 100%;
    height: 480px;
  }
  .bg-login img {
    width: 100%;
    height: auto;
  }

  .signin {
    text-align: center;
  }
  .signin .signin-btn {
    text-transform: uppercase;
    font-weight: bold;
    font-size: 20px;
    width: 200px;
    height: 50px;
  }

  .signup {
    text-align: center;
    margin-bottom: 40px;
  }

</style>
