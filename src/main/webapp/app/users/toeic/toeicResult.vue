<template>
  <div class="toeicResult row">

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

    <!--ket qua-->
    <div class="container">
      <div class="result">
        <h3><3 Chúc mừng bạn đã hoàn thành bài thi ^^</h3>
        <h5>Hiện tại trang web vẫn đang trong quá trình phát triển, các bạn có thể dành 3-5 phút cho nhóm phát triển thêm ý kiến đánh giá được không ạ?</h5>
        <router-link class="alert-link join" to="" type="primary">
          <p style="font-size: 20px; font-weight: bold; color: #dc3545;">-- <i class="el-icon-thumb" style="transform: rotate(45deg);"></i> Link đánh giá --</p>
        </router-link>
        <table class="total">
          <tr>
            <th><b></b></th>
            <th><b>Number</b></th>
            <th><b>Point</b></th>
            <th><b>Total</b></th>
          </tr>
          <tr>
            <td>
              <ul>
                <li>Listening (LC)</li>
                <li>Reading (RC)</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>100</li>
                <li>100</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>495</li>
                <li>495</li>
              </ul>
            </td>
            <td>
              <ul>
                <li rowspan="2">990</li>
              </ul>
            </td>
          </tr>
        </table>
        <br>
        <h3>SO SÁNH KẾT QUẢ LÀM BÀI CỦA BẠN</h3>
        <table class="detail">
          <tr style="font-weight: bolder; font-size: 20px">
            <th colspan="6"><b>LISTENING</b></th>
            <th colspan="6"><b>READING</b></th>
          </tr>
          <tr>
            <th><b></b></th>
            <th><b>Bạn chọn</b></th>
            <th><b>Đáp án</b></th>
            <th><b></b></th>
            <th><b>Bạn chọn</b></th>
            <th><b>Đáp án</b></th>
            <th><b></b></th>
            <th><b>Bạn chọn</b></th>
            <th><b>Đáp án</b></th>
            <th><b></b></th>
            <th><b>Bạn chọn</b></th>
            <th><b>Đáp án</b></th>
          </tr>
          <tr>
            <td>
              <ul>
                <li>Câu 1</li>
                <li>Câu 2</li>
                <li>Câu 3</li>
                <li>Câu 4</li>
                <li>Câu 5</li>
                <li>Câu 6</li>
                <li>Câu 7</li>
                <li>Câu 8</li>
                <li>Câu 9</li>
                <li>Câu 10</li>
                <li>Câu 11</li>
                <li>Câu 12</li>
                <li>Câu 13</li>
                <li>Câu 14</li>
                <li>Câu 15</li>
                <li>Câu 16</li>
                <li>Câu 17</li>
                <li>Câu 18</li>
                <li>Câu 19</li>
                <li>Câu 20</li>
                <li>Câu 21</li>
                <li>Câu 22</li>
                <li>Câu 23</li>
                <li>Câu 24</li>
                <li>Câu 25</li>
                <li>Câu 26</li>
                <li>Câu 27</li>
                <li>Câu 28</li>
                <li>Câu 29</li>
                <li>Câu 30</li>
                <li>Câu 31</li>
                <li>Câu 32</li>
                <li>Câu 33</li>
                <li>Câu 34</li>
                <li>Câu 35</li>
                <li>Câu 36</li>
                <li>Câu 37</li>
                <li>Câu 38</li>
                <li>Câu 39</li>
                <li>Câu 40</li>
                <li>Câu 41</li>
                <li>Câu 42</li>
                <li>Câu 43</li>
                <li>Câu 44</li>
                <li>Câu 45</li>
                <li>Câu 46</li>
                <li>Câu 47</li>
                <li>Câu 48</li>
                <li>Câu 49</li>
                <li>Câu 50</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>C</li>
                <li>A</li>
                <li>D</li>
                <li>B</li>
                <li>C</li>
                <li>C</li>
                <li>A</li>
                <li>A</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>C</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>D</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>D</li>
                <li>D</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>A</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>C</li>
                <li>A</li>
                <li>D</li>
                <li>B</li>
                <li>C</li>
                <li>C</li>
                <li>A</li>
                <li>A</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>C</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>D</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>D</li>
                <li>D</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>A</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>Câu 51</li>
                <li>Câu 52</li>
                <li>Câu 53</li>
                <li>Câu 54</li>
                <li>Câu 55</li>
                <li>Câu 56</li>
                <li>Câu 57</li>
                <li>Câu 58</li>
                <li>Câu 59</li>
                <li>Câu 60</li>
                <li>Câu 61</li>
                <li>Câu 62</li>
                <li>Câu 63</li>
                <li>Câu 64</li>
                <li>Câu 65</li>
                <li>Câu 66</li>
                <li>Câu 67</li>
                <li>Câu 68</li>
                <li>Câu 69</li>
                <li>Câu 70</li>
                <li>Câu 71</li>
                <li>Câu 72</li>
                <li>Câu 73</li>
                <li>Câu 74</li>
                <li>Câu 75</li>
                <li>Câu 76</li>
                <li>Câu 77</li>
                <li>Câu 78</li>
                <li>Câu 79</li>
                <li>Câu 80</li>
                <li>Câu 81</li>
                <li>Câu 82</li>
                <li>Câu 83</li>
                <li>Câu 84</li>
                <li>Câu 85</li>
                <li>Câu 86</li>
                <li>Câu 87</li>
                <li>Câu 88</li>
                <li>Câu 89</li>
                <li>Câu 90</li>
                <li>Câu 91</li>
                <li>Câu 92</li>
                <li>Câu 93</li>
                <li>Câu 94</li>
                <li>Câu 95</li>
                <li>Câu 96</li>
                <li>Câu 97</li>
                <li>Câu 98</li>
                <li>Câu 99</li>
                <li>Câu 100</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>C</li>
                <li>A</li>
                <li>D</li>
                <li>B</li>
                <li>C</li>
                <li>C</li>
                <li>A</li>
                <li>A</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>C</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>D</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>D</li>
                <li>D</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>A</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>C</li>
                <li>A</li>
                <li>D</li>
                <li>B</li>
                <li>C</li>
                <li>C</li>
                <li>A</li>
                <li>A</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>C</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>D</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>D</li>
                <li>D</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>A</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>Câu 101</li>
                <li>Câu 102</li>
                <li>Câu 103</li>
                <li>Câu 104</li>
                <li>Câu 105</li>
                <li>Câu 106</li>
                <li>Câu 107</li>
                <li>Câu 108</li>
                <li>Câu 109</li>
                <li>Câu 110</li>
                <li>Câu 111</li>
                <li>Câu 112</li>
                <li>Câu 113</li>
                <li>Câu 114</li>
                <li>Câu 115</li>
                <li>Câu 116</li>
                <li>Câu 117</li>
                <li>Câu 118</li>
                <li>Câu 119</li>
                <li>Câu 120</li>
                <li>Câu 121</li>
                <li>Câu 122</li>
                <li>Câu 123</li>
                <li>Câu 124</li>
                <li>Câu 125</li>
                <li>Câu 126</li>
                <li>Câu 127</li>
                <li>Câu 128</li>
                <li>Câu 129</li>
                <li>Câu 130</li>
                <li>Câu 131</li>
                <li>Câu 132</li>
                <li>Câu 133</li>
                <li>Câu 134</li>
                <li>Câu 135</li>
                <li>Câu 136</li>
                <li>Câu 137</li>
                <li>Câu 138</li>
                <li>Câu 139</li>
                <li>Câu 140</li>
                <li>Câu 141</li>
                <li>Câu 142</li>
                <li>Câu 143</li>
                <li>Câu 144</li>
                <li>Câu 145</li>
                <li>Câu 146</li>
                <li>Câu 147</li>
                <li>Câu 148</li>
                <li>Câu 149</li>
                <li>Câu 150</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>C</li>
                <li>A</li>
                <li>D</li>
                <li>B</li>
                <li>C</li>
                <li>C</li>
                <li>A</li>
                <li>A</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>C</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>D</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>D</li>
                <li>D</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>A</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>C</li>
                <li>A</li>
                <li>D</li>
                <li>B</li>
                <li>C</li>
                <li>C</li>
                <li>A</li>
                <li>A</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>C</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>D</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>D</li>
                <li>D</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>A</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>Câu 151</li>
                <li>Câu 152</li>
                <li>Câu 153</li>
                <li>Câu 154</li>
                <li>Câu 155</li>
                <li>Câu 156</li>
                <li>Câu 157</li>
                <li>Câu 158</li>
                <li>Câu 159</li>
                <li>Câu 160</li>
                <li>Câu 161</li>
                <li>Câu 162</li>
                <li>Câu 163</li>
                <li>Câu 164</li>
                <li>Câu 165</li>
                <li>Câu 166</li>
                <li>Câu 167</li>
                <li>Câu 168</li>
                <li>Câu 169</li>
                <li>Câu 170</li>
                <li>Câu 171</li>
                <li>Câu 172</li>
                <li>Câu 173</li>
                <li>Câu 174</li>
                <li>Câu 175</li>
                <li>Câu 176</li>
                <li>Câu 177</li>
                <li>Câu 178</li>
                <li>Câu 179</li>
                <li>Câu 180</li>
                <li>Câu 181</li>
                <li>Câu 182</li>
                <li>Câu 183</li>
                <li>Câu 184</li>
                <li>Câu 185</li>
                <li>Câu 186</li>
                <li>Câu 187</li>
                <li>Câu 188</li>
                <li>Câu 189</li>
                <li>Câu 190</li>
                <li>Câu 191</li>
                <li>Câu 192</li>
                <li>Câu 193</li>
                <li>Câu 194</li>
                <li>Câu 195</li>
                <li>Câu 196</li>
                <li>Câu 197</li>
                <li>Câu 198</li>
                <li>Câu 199</li>
                <li>Câu 200</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>C</li>
                <li>A</li>
                <li>D</li>
                <li>B</li>
                <li>C</li>
                <li>C</li>
                <li>A</li>
                <li>A</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>C</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>D</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>D</li>
                <li>D</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>A</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>C</li>
                <li>A</li>
                <li>D</li>
                <li>B</li>
                <li>C</li>
                <li>C</li>
                <li>A</li>
                <li>A</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>C</li>
                <li>A</li>
                <li>B</li>
                <li>A</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>A</li>
                <li>A</li>
                <li>C</li>
                <li>D</li>
                <li>C</li>
                <li>A</li>
                <li>C</li>
                <li>B</li>
                <li>B</li>
                <li>D</li>
                <li>D</li>
                <li>B</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>B</li>
                <li>D</li>
                <li>A</li>
                <li>A</li>
              </ul>
            </td>
          </tr>
        </table>

        <div class="btn-footer">
          <div class="btn-quiz">
            <el-row>
              <router-link class="alert-link" to="" style="width: 300px; font-weight: bold; font-size: 18px;">Link video giải đề ETS 2021 test 01</router-link>
              <router-link class="alert-link" to="/users/grammar/gramTopic/gramQuiz" style="width: 300px; font-weight: bold; font-size: 18px;">Link chữa đề ETS 2021 test 01</router-link>
            </el-row>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>


<script lang="ts" src="./toeicResult.component.ts"></script>


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
.result {
  margin-top: 50px;
  text-align: center;
}
.total tr, td, th{
  background-color: #b3d4fc;
  border-color: #000000;
}
table {
  font-size: 16px;
  font-weight: bold;
  margin-top: 30px;
  border-collapse: collapse;
  width: 100%;
}
td, th {
  border: 1px solid #dddddd;
  text-align: center;
  padding-top: 5px;
  padding-bottom: 5px;
}
.detail li:nth-child(even) {
  background-color: #c2c0ff;
}
ul {
  padding-left: 0;
}
ul li {
  list-style: none;
}

/**/
.btn-footer {
}
.btn-quiz {
  margin-top: 40px;
  margin-bottom: 90px;
  text-align: center;
}
.btn-quiz .alert-link {
  border: 1px solid #3e8acc;
  border-radius: 5px;
  margin: 0 50px;
  padding: 10px 20px
}

</style>
