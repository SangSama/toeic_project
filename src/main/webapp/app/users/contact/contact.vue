<template>
  <div class="contact row">

    <!--tieu de-->
    <div class="title">
      <h1 class="display-4" v-text="$t('home.contact.title')">Contact us</h1>
      <div>
        <div class="el-breadcrumb">
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/" v-text="$t('home.about.home')">Home</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/contact" v-text="$t('home.contact.contactus')">Contact</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
        </div>
      </div>
    </div>

    <!---->
    <div class="container detail-contact">
      <div class="col-md-8">
        <h4 v-text="$t('home.contact.content')">Get in Touch</h4>

        <div class="detail-form">
          <el-form label-position="top" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="auto" class="demo-ruleForm">
            <el-form-item label="Subject" placeholder="Enter Subject" prop="subject">
              <el-input v-model="ruleForm.subject"></el-input>
            </el-form-item>

            <el-form-item label="Message" placeholder="Enter Message" prop="desc">
              <el-input type="textarea" v-model="ruleForm.desc"></el-input>
            </el-form-item>

            <el-form-item label="Activity name" placeholder="Enter your name" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>

            <el-form-item
              prop="email"
              label="Email"
              placeholder="Enter your email"
              :rules="[
                  { required: true, message: 'Please input email address', trigger: 'blur' },
                  { type: 'email', message: 'Please input correct email address', trigger: ['blur', 'change'] }
                ]"
            >
              <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">SEND</el-button>
            </el-form-item>
          </el-form>
        </div>


      </div>

      <div class="col-md-4">
        <div class="detail-info">
          <div class="team-member">
            <el-col :span="24">
              <el-card shadow="hover">
                <div class="info">
                  <div class="bottom clearfix">
                    <p>
                      <i class="el-icon-school" style="font-size: medium"></i>
                      <span>
                          <strong>Đại học Bách Khoa Hà Nội</strong>
                          <br>
                          <i>C9 - 307, Viện Điện tử - Viễn thông</i>
                        </span>
                    </p>
                    <p>
                      <i class="el-icon-mobile-phone" style="font-size: larger"></i>
                      <span>
                          <strong>+84 988 161 359</strong>
                          <br>
                          <i>Mon to Fri 9am to 6pm</i>
                        </span>
                    </p>
                    <p>
                      <i class="el-icon-message" style="font-size: larger"></i>
                      <span>
                          <strong>sang.sama.1999@gmail.com</strong>
                          <br>
                          <i>Send us your query anytime!</i>
                        </span>
                    </p>
                  </div>
                </div>
              </el-card>
            </el-col>
          </div>

          <img src="../../../content/images/contact01.jpg" class="image">
        </div>
      </div>
    </div>
  </div>
</template>

<script  lang="ts" src="./contact.component.ts"></script>

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
.detail-contact {
  display: inline-flex;
  margin-top: 30px;
}
.detail-contact h4 {
  margin-bottom: 10px;
  font-weight: bold;
}

/**/
.detail-info {
  width: 100%;
}
.detail-info img.image {
  width: 340px;
  height: auto;
}
.team-member {
  width: 100%;
  height: 100%;
}




</style>
