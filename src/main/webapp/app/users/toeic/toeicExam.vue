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
                  <strong>PART 1</strong>
                  <p><b>Directions: </b>For each question in this part, you will hear four statements about a picture in your test book. When you hear the statements, you must select the one statement that best describes what you see in the picture. Then find the number of the question on your answer sheet and mark your answer. The statements will not be printed in your test book and will be spoken only one time.</p>
                  <img src="../../../content/images/ets2021test01/part1/intro.png" class="image">
                  <p>Statement (C), ‘They’re sitting at a table,” is the best description of the picture, so you should select answer (C) and mark it on your answer sheet.</p>
                  <hr>
                  <div class="question">
                    <p><b>Question 1:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/1.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">A</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">B</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">C</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">D</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 2:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/2.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">A</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">B</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">C</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">D</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 3:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/3.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">A</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">B</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">C</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">D</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 4:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/4.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">A</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">B</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">C</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">D</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 5:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/5.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">A</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">B</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">C</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">D</el-radio>
                    </div>
                  </div>
                  <hr>
                  <div class="question">
                    <p><b>Question 6:</b></p>
                    <img src="../../../content/images/ets2021test01/part1/6.jpg" class="image">
                    <div class="select">
                      <el-radio v-model="radio" label="1" size="medium">A</el-radio>
                      <el-radio v-model="radio" label="2" size="medium">B</el-radio>
                      <el-radio v-model="radio" label="3" size="medium">C</el-radio>
                      <el-radio v-model="radio" label="4" size="medium">D</el-radio>
                    </div>
                  </div>
                  <hr>
                </div>
              </el-tab-pane>

              <!-- Part 02 -->
              <el-tab-pane label="Part II" name="second">
                <div class="part2">

                </div>
              </el-tab-pane>

              <!-- Part 03 -->
              <el-tab-pane label="Part III" name="third">
                <div class="part3">

                </div>
              </el-tab-pane>

              <!-- Part 04 -->
              <el-tab-pane label="Part IV" name="fourth">
                <div class="part4">

                </div>
              </el-tab-pane>

              <!-- Part 05 -->
              <el-tab-pane label="Part V" name="fifth">
                <div class="part5">

                </div>
              </el-tab-pane>

              <!-- Part 06 -->
              <el-tab-pane label="Part VI" name="sixth">
                <div class="part6">

                </div>
              </el-tab-pane>

              <!-- Part 07 -->
              <el-tab-pane label="Part VII" name="seventh">
                <div class="part7">

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
// const startingMinutes = 120;
// let time = startingMinutes * 60;
// const countdownEl = document.getElementById('timer');
// setInterval(updateCountdown, 1000);
// function updateCountdown() {
//   const minutes = Math.floor(time/60);
//   let seconds = time % 60;
//   seconds = seconds < 10 ? '0' + seconds : seconds;
//   countDownEl.innerHTML = `${minutes}:${seconds}`;
//   time--;
// }
export default {

  data() {
    return {
      activeName: 'first',
      radio: '',
      hour: 2,
      // minute: this.minutes,
      minutes: 0,
      // second: this.seconds,
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

    // minute: {
    //   handler(value) {
    //     if (value < 10) {
    //       this.minute = '0' + this.minutes;
    //     }
    //   }
    // },
    //
    // second: {
    //   handler(value) {
    //     if (value < 10) {
    //       this.second = '0' + this.seconds;
    //     }
    //   }
    // },

    // minutes: {
    //   handler(value) {
    //
    //     if (value > 0) {
    //       setTimeout(() => {
    //         this.minutes--;
    //       }, 60000);
    //     }
    //
    //   },
    //   immediate: true // This ensures the watcher is triggered upon creation
    // }

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
.part1 .select el-radio {

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
