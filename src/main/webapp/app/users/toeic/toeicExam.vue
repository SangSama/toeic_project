<template>
  <div class="toeicExam row">

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

    <!-- bai test-->
    <div class="container">
      <h2 v-text="$t('finalProjectApp.userToeicList.toeic.topic01.title')">ETS_2021_Test_01</h2>
    </div>

    <div class="container-fluid">
      <div class="content row">
        <div class="col-md-9">
          <div class="test">
            <el-tabs type="card" v-model="activeName" @tab-click="handleClick">
              <!-- Part 01 -->
              <el-tab-pane label="Part I" name="first">
                <div class="part1">
                  <strong>LISTENING TEST</strong>
                  <p>In the Listening test. you will be asked to demonstrate how well you understand spoken English. The entire Listening test will last approximately 45 minutes. There are four parts, and directions are given for each part. You must mark your answers on the separate answer sheet. Do not write your answers in your test book.</p>
                  <strong>PART I</strong>
                  <p><b>Directions: </b>For each question in this part, you will hear four statements about a picture in your test book. When you hear the statements, you must select the one statement that best describes what you see in the picture. Then find the number of the question on your answer sheet and mark your answer. The statements will not be printed in your test book and will be spoken only one time.</p>
                  <div class="LC">
                    <audio controls>
                      <source src="../../../content/images/ets2021test01/part1/part1.mp3" type="audio/mpeg">
                    </audio>
                  </div>
                  <img src="../../../content/images/ets2021test01/part1/intro.png" class="image">
                  <p>Statement (C), ‘They’re sitting at a table,” is the best description of the picture, so you should select answer (C) and mark it on your answer sheet.</p>
                  <hr>
                  <div class="question" key="question1">
                    <p><b>Question 1:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/1.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question" key="question2">
                    <p><b>Question 2:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/2.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question" key="question3">
                    <p><b>Question 3:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/3.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 4:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/4.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 5:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/5.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 6:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/6.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                </div>
              </el-tab-pane>

              <!-- Part 02 -->
              <el-tab-pane label="Part II" name="second">
                <div class="part2">
                  <strong>LISTENING TEST</strong>
                  <p>In the Listening test. you will be asked to demonstrate how well you understand spoken English. The entire Listening test will last approximately 45 minutes. There are four parts, and directions are given for each part. You must mark your answers on the separate answer sheet. Do not write your answers in your test book.</p>
                  <strong>PART II</strong>
                  <p><b>Directions: </b>You will hear a question or statement and three responses spoken in English. They will not be printed in your test book and will be spoken only one time. Select the best response to the question or statement and mark the letter (A), (B), or (C) on your answer sheet.</p>
                  <div class="LC">
                    <audio controls>
                      <source src="../../../content/images/ets2021test01/part2/part2.mp3" type="audio/mpeg">
                    </audio>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 7:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 8:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 9:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 10:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 11:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 12:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 13:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 14:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 15:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 16:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <div class="question">
                    <p><b>Question 17:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 18:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 19:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 20:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <div class="question">
                    <p><b>Question 21:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 22:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 23:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 24:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 25:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 26:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 27:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 28:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 29:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 30:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 31:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A)</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B)</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C)</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D)</el-radio>
                    </div>
                  </div>
                </div>
              </el-tab-pane>

              <!-- Part 03 -->
              <el-tab-pane label="Part III" name="third">
                <div class="part3">
                  <strong>LISTENING TEST</strong>
                  <p>In the Listening test. you will be asked to demonstrate how well you understand spoken English. The entire Listening test will last approximately 45 minutes. There are four parts, and directions are given for each part. You must mark your answers on the separate answer sheet. Do not write your answers in your test book.</p>
                  <strong>PART III</strong>
                  <p><b>Directions: </b>You will hear some conversations between two or more people. You will be asked to answer three questions about what the speakers say in each conversation. Select the best response to each question and mark the letter (A), (B), (C), or (D) on your answer sheet. The conversations will not be printed in your test book and will be spoken only one time.</p>
                  <div class="LC">
                    <audio controls>
                      <source src="../../../content/images/ets2021test01/part3/part3.mp3" type="audio/mpeg">
                    </audio>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 32:</b>What does the man say he plans to do today?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Go sightseeing</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Attend a conference</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Purchase some gifts</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Stop at a bank</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 33:</b>What does the man ask about?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Internet access</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Use of a fitness center</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Transportation options</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Nearby restaurants</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 34:</b>What does the woman offer to do?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Make a reservation</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Print a parking pass</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Provide a password</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Check on some prices</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 35:</b>What did the man do before coming in to work?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) He met with a friend</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) He revised a menu</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) He read a newspaper</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) He bought some breakfast</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 36:</b>What does the man think will happen this week?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) There will be more customers</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) There will be a safety inspection</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A contract will be renewed</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A new chef will be hired</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 37:</b>What will the woman most likely do next?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Hire additional staff</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Schedule some repairs</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Place an order</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Print an article</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 38:</b>Who most likely is the man?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A supermarket manager</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) An appliance store employee</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) An ice-cream shop owner</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A real estate agent</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 39:</b>What problem does the woman mention?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) An invoice is incorrect</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) An item is broken</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A document is missing</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A product is too expensive</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 40:</b>What does the man suggest the woman do?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Purchase a warranty</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Return some merchandise</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Call a specialist</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Find an item online</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 41:</b>Why is the man calling?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To make a payment</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To request an extension</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To cancel a registration</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To obtain course information</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 42:</b>What does the man say about his friend?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) She works in his office</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) She benefited from a certificate program</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) She runs a successful accounting firm</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) She wrote a positive review</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 43:</b>What does the woman say the man might be eligible for?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A reduced fee</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A consultation</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A job promotion</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A free upgrade</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 44:</b>Where do the speakers work?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) At a clothing store</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) At a fitness center</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) At a software comoany</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) At a sports arena</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 45:</b>According to the speakers, what will happen next month?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Pricing options will change</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Renovation work will begin</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) New employees will be hired</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Business hours will be extended</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 46:</b>What does the man offer to do?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Make some signs</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Update a schedule</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Estimate some costs</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Send an e-mail</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 47:</b>Where most likely are the speakers?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) At a print shop</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) At a furniture factory</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) At an appliance warehouse</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) At a stationery store</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 48:</b>What problem do the men describe?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A door has been left open</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A delivery truck has been delayed</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Some employees are out sick</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Some products are defective</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 49:</b>What is scheduled to happen at one o'clock?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Some clients will visit</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Some equipment will be cleaned</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A training session will be conducted</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) An employee luncheon will be held</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 50:</b>What did the man recently finish?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A conference proposal</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) An agenda for a business trip</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A revision to a client database</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A review of some blueprints</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 51:</b>What problem does the man mention?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Additional permits are needed</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) An expense was not approved</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A supervisor is unavailable</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A Web site is not working</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 52:</b>What will the speakers do next?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Rehearse a presentation</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Print some documents</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Go have lunch</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Call technical support</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 53:</b>Who most likely is the woman?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A safety inspector</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) An overseas client</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) An athlete</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) An accountant</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 54:</b>What does the woman tell the man about her ID card?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) It is expired</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) It has the wrong photo</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Her name is misspelled</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) She forgot to bring it</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 55:</b>Why does the man say. It normally lakes a few days'?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To reject a request</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To address a concern</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To complain about a delay</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To acknowledge an accomplishment</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 56:</b>What type of event are the speakers attending?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A trade show</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A now-hire orientation</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A celebrity interview</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A grand opening</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 57:</b>Where do the men work?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) At a mobile phone manufacturer</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) At a software development company</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) At an appliance repair shop</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) At an advertising agency</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 58:</b>What will the woman most likely do next?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Exchange contact information</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Consult with a colleague</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Check a schedule</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Look at a brochure</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 59:</b>Why is the man excited?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) He has been promoted</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) His vacation request was approved</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A research article will be published</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A project will receive extra funding</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 60:</b>What does the woman mean when she says, our budget's going to be fairly limited"?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) The company's decision is not surprising</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A fund-raiser should be organized</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Some numbers need to be checked</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) The man's suggestion may not work</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 61:</b>What does the woman say about local students?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) She has worked with them before</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) She is tutoring them in science</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) They are participating in a conference</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) They have made several donations</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Picture 62-64:</b></p>
                    <img src="../../../content/images/ets2021test01/part3/62-64.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 62:</b>Look at the graphic. Who placed the order the woman is picking up?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Richard</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Alison</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Tomas</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Janet</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 63:</b>What event does the woman mention?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A client meeting</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A birthday party</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A retirement luncheon</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) An anniversary celebration</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 64:</b>What does the woman ask the man about?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A form of payment</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A delivery service</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A greeting card</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) An ingredient</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Picture 65-67:</b></p>
                    <img src="../../../content/images/ets2021test01/part3/65-67.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 65:</b>Why does the woman ask to reschedule her appointment?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) She has to have her car repaired</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) She is visiting a friend</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) She has a business meeting</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) She needs to go to the bank</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 66:</b>Look at the graphic. Where will the woman most likely go tomorrow?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To location A</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To location B</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To location C</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To location D</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 67:</b>What does the man ask the woman to do?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Pay a fee in advance</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Register online</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Complete a survey</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Come to an office early</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Picture 68-70:</b></p>
                    <img src="../../../content/images/ets2021test01/part3/68-70.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 68:</b>Where do the speakers work?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) At a flower shop</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) At a real estate company</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) At a paint store</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) At a newspaper publisher</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 69:</b>What does the woman say about color advertisements?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) They are difficult to design</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) They attract more attention</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) They can be sent electronically</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) They should use specific colors</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 70:</b>Look at the graphic. How much will the speakers pay for their advertisement?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) $300</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) $500</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) $250</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) $400</el-radio>
                    </div>
                  </div>
                  <hr>

                </div>
              </el-tab-pane>

              <!-- Part 04 -->
              <el-tab-pane label="Part IV" name="fourth">
                <div class="part4">
                  <strong>LISTENING TEST</strong>
                  <p>In the Listening test. you will be asked to demonstrate how well you understand spoken English. The entire Listening test will last approximately 45 minutes. There are four parts, and directions are given for each part. You must mark your answers on the separate answer sheet. Do not write your answers in your test book.</p>
                  <strong>PART IV</strong>
                  <p><b>Directions: </b>You will hear some talks given by a single speaker. You will be asked to answer three questions about what the speaker says in each talk. Select the best response to each question and mark the letter (A), (B), (C), or (D) on your answer sheet. The talks will not be printed in your test book and will be spoken only one time.</p>
                  <div class="LC">
                    <audio controls>
                      <source src="../../../content/images/ets2021test01/part4/part4.mp3" type="audio/mpeg">
                    </audio>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 71:</b>What type of product is being discussed?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A coffee maker</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A microwave oven</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A portable heater</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A water filter</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 72:</b>What is the purpose of the advertisement?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To reveal a product release date</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To announce a talent show</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To find volunteers for a focus group</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To explain details of a warranty</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 73:</b>What bonus is mentioned?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A raffle ticket</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A coupon</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Travel reimbursement</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Free shipping</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 74:</b>Where is the speaker?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) At a hotel</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) At a travel agency</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) On a train</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) In an airport</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 75:</b>What will happen al the company tomorrow?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) New furniture will arrive</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Some clients will visit</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A new employee will be introduced</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Some computer software will be updated</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 76:</b>Why does the speaker say, "Paul lived in Spain for ten years"?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To recommend a colleague for a task</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To suggest a travel destination</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To express surprise about an anniversary date</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To correct a data-entry error</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 77:</b>What is the announcement about?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Anew sales team</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) An outdoor work space</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A branch office</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A marketing campaign</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 78:</b>What can the listeners find on the company Web site?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A newspaper article</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A video demonstration</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) An architectural plan</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A revised policy</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 79:</b>According to the speaker, what have some people expressed concern about?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A work schedule</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A completion date</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Internet connectivity</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Parking availability</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 80:</b>Where are the listeners?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) At a trade show</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) At a fund-raising banquet</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) On a company retreat</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) On a city tour</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 81:</b>What does the speaker imply when she says, "tickets are sold our?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) An event has been rescheduled</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) An activity is popular</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A ticket office is closed</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A refund will be issued</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 82:</b>What does the speaker remind the listeners to bring?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) An umbrella</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A pair of comfortable shoes</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) An identification card</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A receipt for payment</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 83:</b>What kind of service is being advertised?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Office cleaning</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Tailoring</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Grocery delivery</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Landscaping</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 84:</b>How can the listeners start using a service?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) By visiting a local shop</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) By scheduling a consultation</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) By calling a telephone number</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) By creating an account online</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 85:</b>What special promotion is available?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A free gift</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A money-back guarantee</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A membership upgrade</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A personalized price quote</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 86:</b>What is the purpose of the talk?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To thank project volunteers</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To explain an event schedule</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To introduce a new employee</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To welcome a guest speaker</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 87:</b>According to the speaker, why might the listeners be familiar with Ms. Wang?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) She starred in a television show</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) She won a journalism award</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) She used to be an intern at the company</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) She has relatives who work for the company</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 88:</b>What event WA the listeners encouraged to attend?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A board meeting</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A theater performance</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A training session</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A staff luncheon</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 89:</b>Who will be featured at Saturday's event?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Sculptors</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Musicians</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Authors</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Comedians</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 90:</b>What is money being raised for?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) An after-school program</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A sports tournament</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A building improvement project</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A health clinic</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 91:</b>Why are volunteers needed?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To set up equipment</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To sell tickets</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To donate food</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To direct traffic</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 92:</b>Which field does the speaker most likely work in?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Film production</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Interior design</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Computer programming</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Broadcast journalism</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 93:</b>What improvement did the speaker make?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) She hired more staff</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) She used different software</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) She changed some colors</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) She talked to a specialist</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 94:</b>Why does the speaker say, "I'm having some trouble uploading the file'?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To request some new equipment</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To complain about a policy</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To ask for help</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To explain a delay</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Picture 95-97:</b></p>
                    <img src="../../../content/images/ets2021test01/part4/95-97.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 95:</b>Where does the speaker most likely work?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) At a bookshop</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) At an electronics store</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) At a home-improvement store</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) At a clothing store</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 96:</b>What will happen next Tuesday?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) New inventory will arrive</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) The store will close for a holiday</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) New employees will begin working</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A district manager will visit</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 97:</b>Look at the graphic. What job will the speaker do tomorrow?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Greeter</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Sales representative</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Cashier</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Stock clerk</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Picture 98-100:</b></p>
                    <img src="../../../content/images/ets2021test01/part4/98-100.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 98:</b>Why is the speaker traveling?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) He is taking a vacation</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) He is making a delivery</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) He is inspecting properties</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) He is conducting training</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 99:</b>Look at the graphic. Which item number should the listener order?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) NT-105</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) FF-200</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A-888</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) C-250</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 100:</b>What does the speaker say will happen next week?</p>
                    <img src="" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Some prices will be discounted</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Some job applicants will be interviewed</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A new product will be released</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) An office will move to a new location</el-radio>
                    </div>
                  </div>
                  <hr>
                </div>
              </el-tab-pane>

              <!-- Part 05 -->
              <el-tab-pane label="Part V" name="fifth">
                <div class="part5">
                  <strong>READING TEST</strong>
                  <p>In the Reading test, you will read a variety of texts and answer several different types of reading comprehension questions. The entire Reading test will last 75 minutes. There are three parts, and directions are given for each part. You are encouraged to answer as many questions as possible within the time allowed.</p>
                  <p>You must mark your answers on the separate answer sheet. Do not write your answers in your test book.</p>
                  <strong>PART V</strong>
                  <p><b>Directions: </b>A word or phrase is missing in each of the sentences below. Four answer choices are given below each sentence. Select the best answer to complete the sentence. Then mark the letter (A), (B), (C), or (D) on your answer sheet.</p>
                  <hr>
                  <div class="question">
                    <p><b>Question 101:</b>Mr. Sokolov _____ a positive review of his stay at the Olana Hotel.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) write</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) wrote</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) writing</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) was written</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 102:</b>The manager often leads new employees through the safety procedures _____</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) her</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) herself</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) hers</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) she</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 103:</b>The corporate fitness center is equipped _____ fourteen stationary hinynles</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) at</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) on</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) with</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) about</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 104:</b>Professor Phuong will go over the use of the laboratory _____ with the interns next week.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) instruments</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) instrumental</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) instrumentally</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) instrumented</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 105:</b>Arnby Cable _____ £25.00 to all Internet subscribers after the weeklong service interruption.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) refunded</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) accepted</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) divided</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) deposited</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 106:</b>The board of directors will meet next Monday to examine the current hiring _____</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) purpose</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) intent</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) assembly</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) policy</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 107:</b>Passengers shniild not leave their seats _____ a flight attendant gives them permission to do so.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) unless</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) rather</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) instead</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) otherwise</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 108:</b>The goal of the committee was to evaluate the company's bylaws and offer _____ for improvement.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) renewals</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) registrations</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) recommendations</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) reimbursements</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 109:</b>The building's new ventilation system circulates heat much _____ than before.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) even</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) most even</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) evenly</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) more evenly</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 110:</b>Glowood Appliances promises customers their money back _____ they are not satisfied with their purchase.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) while</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) and</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) if</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) then</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 111:</b>The theater doors will close and the show will start at precisely 8:00 p.m., so guests are reminded to be _____.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) rapid</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) sudden</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) punctual</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) instant</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 112:</b>All vacation requests must be made to your supervisor _____ the requested date.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) prior to</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) except for</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) previously</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) because</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 113:</b>Most banks now offer clients the option of receiving their statements electronically or _____ mail.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) from</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) of</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) in</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) by</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 114:</b>Construction at Langhall Plaza is going so well that shops might open before the expected date.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) completion</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) selectinn</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) decision</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) option</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 115:</b>Kohmek, Inc., is seeking a suitable site _____ the construction of its electronics factory.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) for</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) so</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) to</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) more</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 116:</b>Studies show that the average audience forms its _____ of the speaker within the first few seconds of the presentation.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Impress</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) impressive</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) impressively</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) impression</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 117:</b>Mr. Cutler has been asked to _____ domestic sales of low-calorie beverages.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) oversee</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) possess</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) succeed</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) persist</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 118:</b>Cranford Culinary Academy offers 35 different classes for _____ chefs.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) aspires</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) aspirations</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) aspiring</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) to aspire</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 119:</b>_____ , items sold at the Scottville Craft Fair are unique and of very high quality.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Fairly</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Typically</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Simply</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Entirely</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 120:</b>Mamton Home Furnishings _____ customized furniture in Pennsylvania for more than a century.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) manufactures</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) is manufacturing</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) has manufactured</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) manufacture</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 121:</b>Please hold any phone calls for Ms. Tanaka ______ she will be in meetings all day.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) as</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) but</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) despite</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) similarly</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 122:</b>Dolores Gutierrez excels as an estate planning attorney who helps clients manage their assets _____ .</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) effect</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) effectively</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) effects</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) effective</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 123:</b>Mr. Singh was _____ about sales of the fragrance after the first round of customer focus groups.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) extensive</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) distinct</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) optimistic</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) superior</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 124:</b>Data from the finance department was used to _____ predict the company's future expenses.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) either</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) ever</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) yet</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) better</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 125:</b>Immediately after the decrease in production was announced, everyone began discussing how _____ would impact work schedules.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) us</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) it</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) theirs</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) yours</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 126:</b>Every year Arrow Mill. Inc., processes a _____ amount of grain.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) durable</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) direct</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) resolute</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) substantial</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 127:</b>Tomorrow morning, both escalators in the store will be turned off periodically in order to perform _____ maintenance.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) required</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) require</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) requiring</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) requires</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 128:</b>Those who wish to volunteer at the annual Sebastian Park flower-planting event this Saturday _____ to arrive early.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) asks</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) are asked</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) has been asking</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) to ask</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 129:</b>Although Mr. Yanamura's theory is _____ controversial, it does help explain the latest changes in the market.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) neatly</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) urgently</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) gracefully</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) certainly</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 130:</b>A recent _____ found that property values in the Agate Valley region had increased by 3 percent between January and June.</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) assessment</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) assessed</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) assessable</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) to assess</el-radio>
                    </div>
                  </div>
                  <hr>
                </div>
              </el-tab-pane>

              <!-- Part 06 -->
              <el-tab-pane label="Part VI" name="sixth">
                <div class="part6">
                  <strong>READING TEST</strong>
                  <p>In the Reading test, you will read a variety of texts and answer several different types of reading comprehension questions. The entire Reading test will last 75 minutes. There are three parts, and directions are given for each part. You are encouraged to answer as many questions as possible within the time allowed.</p>
                  <p>You must mark your answers on the separate answer sheet. Do not write your answers in your test book.</p>
                  <strong>PART VI</strong>
                  <p><b>Directions: </b>Read the texts that follow. A word, phrase, or sentence is missing in parts of each text. Four answer choices for each question are given below the text. Select the best answer to complete the text. Then mark the letter (A), (B), (C), or (D) on your answer sheet.</p>
                  <hr>
                  <div class="question">
                    <p><b>Question 131-134:</b>refer to the following information.</p>
                    <img src="../../../content/images/ets2021test01/part6/131-134.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 131:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) attracted</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) entered</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) awarded</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) promoted</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 132:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) they</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) them</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) themselves</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) theirs</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 133:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) location</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) situation</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) machine</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) schedule</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 134:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Registration begins on 3 February and continues through 18 March.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) This speaker is well-known throughout the industry.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) The conference was held in New Zealand in previous years.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) We'd like to get your feedback on last year's conference.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 135-138:</b>refer to the following advertisement.</p>
                    <img src="../../../content/images/ets2021test01/part6/135-138.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 135:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) who</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) accordingly</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) recently</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) where</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 136:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) provided</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) will provide</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) will have provided</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) is providing</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 137:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Your travel expenses are covered by Wallace & Prence.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) We place clients in apartments throughout the world.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Duties also include supporting clients after their arrival.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Incomplete applications will not be considered.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 138:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) questions</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) documents</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) references</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) qualifications</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 139-142:</b>refer to the following newspaper article</p>
                    <img src="../../../content/images/ets2021test01/part6/139-142.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 139:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) doubted</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) estimated</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) crowded</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) organized</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 140:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) She usually serves them warm.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) She created them when she started the restaurant.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) His family members prefer eating healthy meals.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) His social media campaign has also boosted sales.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 141:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) leave</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) left</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) is leaving</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) have left</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 142:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) expansion</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) supervision</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) submission</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) division</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 143-146:</b>refer to the following memo.</p>
                    <img src="../../../content/images/ets2021test01/part6/143-146.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 143:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) However</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Therefore</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) In addition</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) For example</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 144:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) sent</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) will be sent</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) will have sent</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) had been sent</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 145:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Support</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Absence</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Failure</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Approval</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 146:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) I will see you at the meeting.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Write down your new log-in ID.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Let me know when this is done.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Thank you for your cooperation.</el-radio>
                    </div>
                  </div>
                  <hr>
                </div>
              </el-tab-pane>

              <!-- Part 07 -->
              <el-tab-pane label="Part VII" name="seventh">
                <div class="part7">
                  <strong>READING TEST</strong>
                  <p>In the Reading test, you will read a variety of texts and answer several different types of reading comprehension questions. The entire Reading test will last 75 minutes. There are three parts, and directions are given for each part. You are encouraged to answer as many questions as possible within the time allowed.</p>
                  <p>You must mark your answers on the separate answer sheet. Do not write your answers in your test book.</p>
                  <strong>PART VII</strong>
                  <p><b>Directions: </b>In this pail you will read a selection of texts, such as magazine and newspaper articles, e-mails, and instant messages. Each text or set of texts is followed by several questions. Select the best answer for each question and mark the letter (A), (B), (C), or (D) on your answer sheet.</p>
                  <hr>
                  <div class="question">
                    <p><b>Question 147-148:</b>refer to the following notice.</p>
                    <img src="../../../content/images/ets2021test01/part7/147-148.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 147:</b>What is indicated about Asherton Manor?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) It is available for private portico.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) It is open daily from 11:00 A.M. to 5:00 P.M.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) It always offers guided tours.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) It is near a train station.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 148:</b>What will NOT be free at the fair?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Games</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Music</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Shuttle rides</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) On-site parking</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 149-150:</b>refer to the following advertisement.</p>
                    <img src="../../../content/images/ets2021test01/part7/149-150.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 149:</b>What is the purpose of the advertisement?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To offer a discount to new clients</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To announce new menu items</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To describe the services of a business</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To explain delivery requirements</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 150:</b>According to the advertisement, what is available by special request?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Food sampling</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Vegetarian meals</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Itemized bills</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Decorations</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 151-152:</b>refer to the following e-mail.</p>
                    <img src="../../../content/images/ets2021test01/part7/151-152.jpg" class="image">
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 151:</b>According to Ms. Bryant, why might Mr. Wiest not have seen the original document?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) She forgot to attach it to her e-mail.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) She sent it to a different person.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) It may have gone to the wrong e-mail folder.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) The tile in it may have been too large.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 152:</b>Who most likely is Ms. Bryant?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) An architect</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) An apartment-building manager</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A banker</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A property owner</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 153-154:</b>refer to the following online chat discussion.</p>
                    <img src="../../../content/images/ets2021test01/part7/153-154.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 153:</b>What has Mr. Ubari NOT done?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Purchased a new camera</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Connected the camera to the computer</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Restarted the camera</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Restarted the computer</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 154:</b>At 3:42 P.M., what does Mr. Ubari most likely mean when he writes, "Yes, it's there"?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) He found an icon.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) He moved the camera.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) He uploaded the pictures.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) He sees the instructions.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 155-157:</b>refer to the following memo.</p>
                    <img src="../../../content/images/ets2021test01/part7/155-157.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 155:</b>Who most likely is Mr. Perez?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A factory floor worker</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A food service worker</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) The safety inspector</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) The general manager</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 156:</b>When will the inspection begin?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) At 8:30 A.M.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) At 10:00 A.M.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) At 12:30 P.M.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) At 3:30 P.M.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 157:</b>What does the memo suggest about factory floor workers?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) They must prepare written reports.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) They will be able to leave work early.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Their afternoon work might be interrupted.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Their workstations might be unavailable.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 158-160:</b>refer to the following e-mail.</p>
                    <img src="../../../content/images/ets2021test01/part7/158-160.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 158:</b>Who most likely is Mr. Mayer?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A health professional</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A Healthhome Web-site user</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A Web-site designer</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A Healthhome article contributor</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 159:</b>The word look" in paragraph 1, line 2. is closest in meaning to</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) search</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) appearance</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) photograph</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) glance</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 160:</b>What does the e-mail suggest about the previous With site?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) It did not have an online store.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Some people visited it on a daily basis.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Viewing it on some devices was difficult.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) It featured interactive nutrition quizzes.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 161-163:</b>refer to the following article.</p>
                    <img src="../../../content/images/ets2021test01/part7/161-163.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 161:</b>What is the purpose of the article?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To advertise Webmarch's new online streaming service</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To detail a merger between Webmarch and Movieriver</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To profile the history and future of Webmarch</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To report on Webmarch's changes in pricing</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 162:</b>What is true about Webmarch?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Its competitors offer fewer shows.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Its customer base has tripled in two years.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) It is currently available in 46 countries.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) It offers videos in many languages.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 163:</b>In which of the positions marked [1], [2], [3], and [4] does the following sentence best belong? "it was an immediate success."</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) [1]</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) [2]</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) [3]</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) [4]</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 164-167:</b>refer to the following instructions.</p>
                    <img src="../../../content/images/ets2021test01/part7/164-167.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 164:</b>For whom are the instructions most likely intended?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Users of Zovtech products</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Salespeople in Zovtech stores</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Designers of Zovtech products</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Writers of Zovtech product manuals</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 165:</b>What does Zovtech manufacture?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Appliances</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Automobiles</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Cleaning supplies</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Photography equipment</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 166:</b>According to the instructions, why are pictures useful?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) They can be understood by people in different countries.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) They clearly show when something is not working properly.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) They reduce the amniint of written text needed.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) They attract more people to a Web site.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 167:</b>The word "grasp" in paragraph 1, line 10 is closest in meaning to</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) hold</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) tighten</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) accomplish</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) understand</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 168-171:</b>refer to the following review.</p>
                    <img src="../../../content/images/ets2021test01/part7/168-171.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 168:</b>Who most likely is Ms. Mohlmann?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A product tester</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) An event planner</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A corporate trainer</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A sales representative</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 169:</b>What is indicated about Ms. Mohlmann?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) She travels often for her job.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) She prefers paper documents.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) She receives software for free.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) She gives frequent presentations.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 170:</b>Why was Ms. Mohlmann unhappy with S-Tor's customer service?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Her issue took too long to resolve.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) The instructions given were not clear.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Help-desk employees were unfriendly.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) She felt pressured to make a purchase</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 171:</b>In which of the positions marked [1], [2], [3], and [4] does the following sentence best belong? 'Instructing me to repeat the same steps over and over seemed pointless."</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) [1]</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) [2]</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) [3]</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) [4]</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 172-175:</b>refer to the following text-message.</p>
                    <img src="../../../content/images/ets2021test01/part7/172-175.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 172:</b>Where do the writers most likely work?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) At a tree service company</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) At a real estate agency</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) At a construction firm</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) At an auto repair shop</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 173:</b>What does Ms. Mirzoyan expect to do tomorrow?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Purchase a property</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Have visitors at her home</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Hire a new moving company</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Use Ms. McNair's business again</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 174:</b>When will the writers most likely meet at 234 Lakeside Avenue?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) In one hour</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) In two hours</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) In three hours</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) In four hours</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 175:</b>At 12:21 P.M., what does Mr. Davison most likely mean when he writes, its Saturday"?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) He does not have extra time.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) He thinks they should not accept more work.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Grady is not usually available then.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Miles did not make a good decision.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 176-180:</b>refer to the following article and invitation.</p>
                    <img src="../../../content/images/ets2021test01/part7/176-180.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 176:</b>What does the mayor suggest people do to thank bus drivers?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Donate to a fund</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Give the drivers a card</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Post an online greeting to drivers</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Write a letter to the local newspaper</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 177:</b>According to the article, what group of people has recently become larger?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Football fans</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Train passengers</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Frecosta residents</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Shopping center customers</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 178:</b>What is true about some of the people who will attend the football game on May 7 ?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) They will travel to the eastern end of Frecosta.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) They must live in the city of Frecosta.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) They must reply to an e-mall from the mayor.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) They may bring only three guests to the game.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 179:</b>What will happen at 1:00 P.M. on May 7 ?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) The stadium will open.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A football game will begin.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Refreshments will be served.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) An awards ceremony will be held</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 180:</b>According to the invitation, how can transit workers obtain tickets?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) By visiting the bus station</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) By calling the mayor's office</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) By arriving to the game early</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) By ordering through a Web site</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 181-185:</b>refer to the following memo and e-mail.</p>
                    <img src="../../../content/images/ets2021test01/part7/181-185.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 181:</b>What is the purpose of the memo?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To announce a company reorganization</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To explain new procedures</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To introduce an efficiency expert</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To bring attention to a problem</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 182:</b>What step in the shipping process will NOT change?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Looking up addresses using the software</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Using bar code scanners</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Creating bar code stickers for all orders</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Putting priority items on a specific shelf</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 183:</b>What is implied about Turing Photography?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Its address labels should be highlighted in yellow.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) It purchased software at a reduced price.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) It provides the warehouse with supplies.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) It has received complaints about the scanners.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 184:</b>What is most likely true about Mr. Mansouri?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) His shift begins at 8 A.M.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) His team is testing out the JLX sottware.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) He prefers using the RERQ system.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) He handles all priority shipping orders.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 185:</b>What will Ms. Peralta most likely do after reading the e-mail?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Find a missing package</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Research long-life batteries</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Reschedule a deadline</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Correct an address</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 186-190:</b>refer to the following Web page and e-mails.</p>
                    <img src="../../../content/images/ets2021test01/part7/149-150.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 186:</b>What is indicated about Ohayon Marketing?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) It specializes in family-owned companies.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) It has a diverse client portfolio.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) It mainly supports export firms.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) It earns most of its revenue from the apparel Industry.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 187:</b>For what industry did Ohayon Marketing first consult?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Online education</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Specialty Foods</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Apparel</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Technology</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 188:</b></p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) It has offices in New Delhi.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) It recently hired several new employees.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) It is under new management.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) It recently merged with a larger firm.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 189:</b>Who is Mr. Mehta?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) A travel agent</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) An executive recruiter</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A former colleague of Mr. Ohayon's</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) An Ohayon Marketing partner</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 190:</b>Why will Mr. Ohayon most likely contact Ms. Kader?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To remind her to send an itinerary</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To rent a new office location</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To explore business opportunities</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To get sightseeing recommendations</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 191-195:</b>refer to the following flyer, invoice, and online review</p>
                    <img src="../../../content/images/ets2021test01/part7/149-150.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 191:</b>According to the flyer, why should the reader e-mail Steel Wheels Vehicle Insurance?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To inquire about plan coverage</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To obtain a repair estimate</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To schedule an appointment</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To find a participating service center</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 192:</b>What insurance plan does Mr. Xiong most likely have?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) The Maintenance Plan</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) The Repair Plan</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) The Cleaning Plan</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) The Combination Plan</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 193:</b>According to the invoice. what could Mr. Xiong have requested on December 13?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) The mechanic's work certificate</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) His car's old parts</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) A full tank of gas</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) A loaner vehicle</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 194:</b>What product did Mr. Xiong most likely purchase?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Goldings synthetic oil</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) A Harmon air filter</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Lift-brite fabric treatment</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Eversweet spray</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 195:</b>According to the online review, what impressed Mr. Xiong about the product?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Its reasonable price</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Its pleasant scent</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Its innovative packaging</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Its sudden popularity</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 196-200:</b>refer to the following mission statement, notice, and announcement.</p>
                    <img src="../../../content/images/ets2021test01/part7/149-150.jpg" class="image">
                    <span></span>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 196:</b>What does the HHNA indicate in the mission statement?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) It is an independently run group.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) It was formed recently.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) It is advised by city officials.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) It charges membership fees.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 197:</b>Why are residents encouraged to attend a meeting on Monday, April 17 ?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) To receive training in public speaking</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) To show support for some city politicians</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) To get information about a zoning proposal</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) To meet some new neighbors</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 198:</b>What HHNA goal is most likely being challenged by the Chenaux Company?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Goal 1</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Goal 2</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Goal 3</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Goal 4</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 199:</b>What is suggested about Ms. Redman?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) She spoke at a meeting in April.</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) She owned a business in Hulani Harbor.</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) She is responsible for distributing the meeting agendas.</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) She writes articles for a newsletter.</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 200:</b>What does Ms. Redman suggest qualifies her for the representative position?</p>
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">(A) Her university degree</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">(B) Her fresh leadership ideas</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">(C) Her awareness of national issues</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">(D) Her experience in supporting local groups</el-radio>
                    </div>
                  </div>
                  <hr>
                </div>
              </el-tab-pane>

            </el-tabs>
          </div>
        </div>

        <div class="col-md-3">
          <div class="point">
            <p id="timer">0{{ hour }} : {{ minutes }} : {{ seconds }}</p>
            <div class="numberAnswer row">
              <div class="col-md-2 answer">1</div>
              <div class="col-md-2 answer">2</div>
              <div class="col-md-2 answer">3</div>
              <div class="col-md-2 answer">4</div>
              <div class="col-md-2 answer">5</div>
              <div class="col-md-2 answer">6</div>
              <div class="col-md-2 answer">7</div>
              <div class="col-md-2 answer">8</div>
              <div class="col-md-2 answer">9</div>
              <div class="col-md-2 answer">10</div>

              <div class="col-md-2 answer">11</div>
              <div class="col-md-2 answer">12</div>
              <div class="col-md-2 answer">13</div>
              <div class="col-md-2 answer">14</div>
              <div class="col-md-2 answer">15</div>
              <div class="col-md-2 answer">16</div>
              <div class="col-md-2 answer">17</div>
              <div class="col-md-2 answer">18</div>
              <div class="col-md-2 answer">19</div>
              <div class="col-md-2 answer">20</div>

              <div class="col-md-2 answer">21</div>
              <div class="col-md-2 answer">22</div>
              <div class="col-md-2 answer">23</div>
              <div class="col-md-2 answer">24</div>
              <div class="col-md-2 answer">25</div>
              <div class="col-md-2 answer">26</div>
              <div class="col-md-2 answer">27</div>
              <div class="col-md-2 answer">28</div>
              <div class="col-md-2 answer">29</div>
              <div class="col-md-2 answer">30</div>

              <div class="col-md-2 answer">31</div>
              <div class="col-md-2 answer">32</div>
              <div class="col-md-2 answer">33</div>
              <div class="col-md-2 answer">34</div>
              <div class="col-md-2 answer">35</div>
              <div class="col-md-2 answer">36</div>
              <div class="col-md-2 answer">37</div>
              <div class="col-md-2 answer">38</div>
              <div class="col-md-2 answer">39</div>
              <div class="col-md-2 answer">40</div>

              <div class="col-md-2 answer">41</div>
              <div class="col-md-2 answer">42</div>
              <div class="col-md-2 answer">43</div>
              <div class="col-md-2 answer">44</div>
              <div class="col-md-2 answer">45</div>
              <div class="col-md-2 answer">46</div>
              <div class="col-md-2 answer">47</div>
              <div class="col-md-2 answer">48</div>
              <div class="col-md-2 answer">49</div>
              <div class="col-md-2 answer">50</div>

              <div class="col-md-2 answer">51</div>
              <div class="col-md-2 answer">52</div>
              <div class="col-md-2 answer">53</div>
              <div class="col-md-2 answer">54</div>
              <div class="col-md-2 answer">55</div>
              <div class="col-md-2 answer">56</div>
              <div class="col-md-2 answer">57</div>
              <div class="col-md-2 answer">58</div>
              <div class="col-md-2 answer">59</div>
              <div class="col-md-2 answer">60</div>

              <div class="col-md-2 answer">61</div>
              <div class="col-md-2 answer">62</div>
              <div class="col-md-2 answer">63</div>
              <div class="col-md-2 answer">64</div>
              <div class="col-md-2 answer">65</div>
              <div class="col-md-2 answer">66</div>
              <div class="col-md-2 answer">67</div>
              <div class="col-md-2 answer">68</div>
              <div class="col-md-2 answer">69</div>
              <div class="col-md-2 answer">70</div>

              <div class="col-md-2 answer">71</div>
              <div class="col-md-2 answer">72</div>
              <div class="col-md-2 answer">73</div>
              <div class="col-md-2 answer">74</div>
              <div class="col-md-2 answer">75</div>
              <div class="col-md-2 answer">76</div>
              <div class="col-md-2 answer">77</div>
              <div class="col-md-2 answer">78</div>
              <div class="col-md-2 answer">79</div>
              <div class="col-md-2 answer">80</div>

              <div class="col-md-2 answer">81</div>
              <div class="col-md-2 answer">82</div>
              <div class="col-md-2 answer">83</div>
              <div class="col-md-2 answer">84</div>
              <div class="col-md-2 answer">85</div>
              <div class="col-md-2 answer">86</div>
              <div class="col-md-2 answer">87</div>
              <div class="col-md-2 answer">88</div>
              <div class="col-md-2 answer">89</div>
              <div class="col-md-2 answer">90</div>

              <div class="col-md-2 answer">91</div>
              <div class="col-md-2 answer">92</div>
              <div class="col-md-2 answer">93</div>
              <div class="col-md-2 answer">94</div>
              <div class="col-md-2 answer">95</div>
              <div class="col-md-2 answer">96</div>
              <div class="col-md-2 answer">97</div>
              <div class="col-md-2 answer">98</div>
              <div class="col-md-2 answer">99</div>
              <div class="col-md-2 answer">100</div>

              <div class="col-md-2 answer">101</div>
              <div class="col-md-2 answer">102</div>
              <div class="col-md-2 answer">103</div>
              <div class="col-md-2 answer">104</div>
              <div class="col-md-2 answer">105</div>
              <div class="col-md-2 answer">106</div>
              <div class="col-md-2 answer">107</div>
              <div class="col-md-2 answer">108</div>
              <div class="col-md-2 answer">109</div>
              <div class="col-md-2 answer">110</div>

              <div class="col-md-2 answer">111</div>
              <div class="col-md-2 answer">112</div>
              <div class="col-md-2 answer">113</div>
              <div class="col-md-2 answer">114</div>
              <div class="col-md-2 answer">115</div>
              <div class="col-md-2 answer">116</div>
              <div class="col-md-2 answer">117</div>
              <div class="col-md-2 answer">118</div>
              <div class="col-md-2 answer">119</div>
              <div class="col-md-2 answer">120</div>

              <div class="col-md-2 answer">121</div>
              <div class="col-md-2 answer">122</div>
              <div class="col-md-2 answer">123</div>
              <div class="col-md-2 answer">124</div>
              <div class="col-md-2 answer">125</div>
              <div class="col-md-2 answer">126</div>
              <div class="col-md-2 answer">127</div>
              <div class="col-md-2 answer">128</div>
              <div class="col-md-2 answer">129</div>
              <div class="col-md-2 answer">130</div>

              <div class="col-md-2 answer">131</div>
              <div class="col-md-2 answer">132</div>
              <div class="col-md-2 answer">133</div>
              <div class="col-md-2 answer">134</div>
              <div class="col-md-2 answer">135</div>
              <div class="col-md-2 answer">136</div>
              <div class="col-md-2 answer">137</div>
              <div class="col-md-2 answer">138</div>
              <div class="col-md-2 answer">139</div>
              <div class="col-md-2 answer">140</div>

              <div class="col-md-2 answer">141</div>
              <div class="col-md-2 answer">142</div>
              <div class="col-md-2 answer">143</div>
              <div class="col-md-2 answer">144</div>
              <div class="col-md-2 answer">145</div>
              <div class="col-md-2 answer">146</div>
              <div class="col-md-2 answer">147</div>
              <div class="col-md-2 answer">148</div>
              <div class="col-md-2 answer">149</div>
              <div class="col-md-2 answer">150</div>

              <div class="col-md-2 answer">151</div>
              <div class="col-md-2 answer">152</div>
              <div class="col-md-2 answer">153</div>
              <div class="col-md-2 answer">154</div>
              <div class="col-md-2 answer">155</div>
              <div class="col-md-2 answer">156</div>
              <div class="col-md-2 answer">157</div>
              <div class="col-md-2 answer">158</div>
              <div class="col-md-2 answer">159</div>
              <div class="col-md-2 answer">160</div>

              <div class="col-md-2 answer">161</div>
              <div class="col-md-2 answer">162</div>
              <div class="col-md-2 answer">163</div>
              <div class="col-md-2 answer">164</div>
              <div class="col-md-2 answer">165</div>
              <div class="col-md-2 answer">166</div>
              <div class="col-md-2 answer">167</div>
              <div class="col-md-2 answer">168</div>
              <div class="col-md-2 answer">169</div>
              <div class="col-md-2 answer">170</div>

              <div class="col-md-2 answer">171</div>
              <div class="col-md-2 answer">172</div>
              <div class="col-md-2 answer">173</div>
              <div class="col-md-2 answer">174</div>
              <div class="col-md-2 answer">175</div>
              <div class="col-md-2 answer">176</div>
              <div class="col-md-2 answer">177</div>
              <div class="col-md-2 answer">178</div>
              <div class="col-md-2 answer">179</div>
              <div class="col-md-2 answer">180</div>

              <div class="col-md-2 answer">181</div>
              <div class="col-md-2 answer">182</div>
              <div class="col-md-2 answer">183</div>
              <div class="col-md-2 answer">184</div>
              <div class="col-md-2 answer">185</div>
              <div class="col-md-2 answer">186</div>
              <div class="col-md-2 answer">187</div>
              <div class="col-md-2 answer">188</div>
              <div class="col-md-2 answer">189</div>
              <div class="col-md-2 answer">190</div>

              <div class="col-md-2 answer">191</div>
              <div class="col-md-2 answer">192</div>
              <div class="col-md-2 answer">193</div>
              <div class="col-md-2 answer">194</div>
              <div class="col-md-2 answer">195</div>
              <div class="col-md-2 answer">196</div>
              <div class="col-md-2 answer">197</div>
              <div class="col-md-2 answer">198</div>
              <div class="col-md-2 answer">199</div>
              <div class="col-md-2 answer">200</div>

            </div>
            <div class="btn-submit">
              <router-link class="alert-link join" to="/users/toeic/toeicStart/toeicExam/toeicResult" type="primary">
                <p>SUBMIT</p>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>


<script lang="ts" src="./toeicExam.component.ts"></script>
<script>
export default {
  data() {
    return {
      activeName: 'first',
      radio: '',
      hour: 2,
      minutes: 0,
      seconds: 0
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
  },
  watch: {

    seconds: {
      handler(value) {
        if (value >= 0) {
          setTimeout(() => {
            this.seconds--;
          }, 1000);
        }
        if (value == -1) {
          this.seconds = 59;
          this.minutes--;
          if (this.minutes == -1) {
            this.minutes = 59;
            this.hour--;
          }
        }
      },
      immediate: true // This ensures the watcher is triggered upon creation
    },
  }
};
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
h2 {
  text-align: center;
  margin-top: 30px;
  margin-bottom: 30px;
}

/**/
.toeicExam {

}
.content {
  /*line-height: 25px;*/
}
.content p {
  text-align: left;
  line-height: 20px;
}
.part1 {

}
.part1 .image {

}
.part1 .question {

}
.part1 .question img.image {

}
.part1 .select {

}
.part1 .select .select-sub {

}
.el-radio {
  width: 22%;
}
.point {

}
.point .numberAnswer {

}
.numberAnswer .answer {
  height: 70px;
  border: 5px solid #003c72;
  border-radius: 50%;
  margin: 5px;
  text-align: center;
  font-size: 20px;
  font-weight: bolder;
  padding-top: 3%;
}
#timer {
  height: 50px;
  font-size: 60px;
  font-weight: bolder;
  text-align: center;
  margin-bottom: 20px;
}
.btn-submit {

}
.btn-submit p {
  text-align: center;
  border: 1px solid #003c72;
  border-radius: 10px;
  width: 200px;
  height: 50px;
  line-height: 50px;
  margin: auto;
  margin-top: 50px;
  margin-bottom: 30px;
  background: linear-gradient(to bottom right, #003c72, #c000de);
  color: #ffffff;
}


</style>
