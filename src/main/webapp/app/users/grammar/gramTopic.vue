<template>
  <div class="gramTopic row">

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
            <router-link class="item" to="/users/grammar" v-text="$t('finalProjectApp.userGrammarList.grammar.topicList')">Grammar Topic List</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/users/grammar/gramTopic" v-text="$t('finalProjectApp.userGrammarList.grammar.topic02.title')">Simple Present & Present Continuous</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
        </div>
      </div>
    </div>

    <!--ly thuyet-->
    <div class="container">
      <h2>THÌ HIỆN TẠI ĐƠN<br>&<br>HIỆN TẠI TIẾP DIỄN</h2>
      <div class="list">
        <p>Thì hiện tại đơn và thì hiện tại tiếp diễn thường khiến nhiều bạn lẫn lộn trong bài
          thi, không biết chúng khác nhau cái gì trong cấu trúc và ý nghĩa. Vì vậy, chúng ta cùng
          xem lại kiến thức về thì hiện tại đơn và thì hiện tại tiếp diễn nhé. Các bạn cần nắm chắc
          cấu tạo, chức năng của 2 thì này để làm nền tảng cho những phần tiếp theo.</p>
        <br>
        <strong>I. Dấu hiệu nhận biết</strong>
        <p>Một trong những manh mối rất quan trọng để phân biệt được đúng 2 thì này là căn
          cứ vào trạng từ. Thì hiện tại đơn và hiện tại tiếp diễn có 1 số trạng từ rất điển hình, nếu
          ta thấy chúng xuất hiện trong câu thì phải liên tưởng đến 2 thì này ngay lập tức. Các bạn
          hãy ghi nhớ các trạng từ điển hình của 2 thì này nhé:</p>
        <table>
          <tr>
            <th><b>HIỆN TẠI ĐƠN</b></th>
            <th><b>HIỆN TẠI TIẾP DIỄN</b></th>
          </tr>
          <tr>
            <td>
              <ul>
                <li>Often, usually, frequently</li>
                <li>Always, constantly</li>
                <li>Sometimes, occasionally</li>
                <li>Seldom, rarely</li>
                <li>Every day/ week/ month...</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>Now</li>
                <li>Right now</li>
                <li>At the moment</li>
                <li>At present</li>
                <li>Look! Listen!...</li>
              </ul>
            </td>
          </tr>
        </table>
        <br>
        <strong>II. Cấu Trúc</strong>
        <p>Chúng ta sẽ cùng xem cấu trúc của hiện tại đơn và hiện tại tiếp diễn trong bảng
          sau nhé:</p>
        <table class="diss">
          <tr>
            <th></th>
            <th><b>HIỆN TẠI ĐƠN</b></th>
            <th><b>HIỆN TẠI TIẾP DIỄN</b></th>
          </tr>
          <tr>
            <td>
              <ul>
                <li>+</li>
                <li>-</li>
                <li>?</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>S + V(s/es)<br>Ex: He frequently plays tennis.</li>
                <li>S + do/does not + V-inf<br>Ex: She doesn’t usually play tennis.</li>
                <li>Do/Does + S + V-inf?<br>Ex: Do you often play tennis?</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>S + am/is/are + V-ing<br>Ex: The children are playing football now.</li>
                <li>S + am/is/are + not + V-ing<br>Ex: The children are not playing football
                  right now.</li>
                <li>Am/Is/Are + S + V-ing?<br>Ex: Are the children playing football at
                  present?</li>
              </ul>
            </td>
          </tr>
        </table>
        <br>
        <strong>III. Cách sử dụng</strong>
        <table>
          <tr>
            <th><b>HIỆN TẠI ĐƠN</b></th>
            <th><b>HIỆN TẠI TIẾP DIỄN</b></th>
          </tr>
          <tr>
            <td>
              <ol>
                <li>
                  <i>Diễn tả một thói quen, một hành
                    động xảy ra thường xuyên lặp đi lặp
                    lại ở hiện tại.</i>
                  <ul>
                    <li>He <b>watches</b> TV every night.</li>
                    <li>What <b>do</b> you <b>do</b> every day?</li>
                    <li>I <b>go</b> to school by bicycle
                      occasionally.</li>
                  </ul>
                </li>
                <li>
                  <i>Diễn tả một chân lý, một sự thật
                    hiển nhiên.</i>
                  <ul>
                    <li>The sun <b>rises</b> in the East.</li>
                    <li>Tom <b>comes</b> from England.</li>
                    <li>I <b>am</b> a student.</li>
                  </ul>
                </li>
                <li>
                  <i>Diễn tả một lịch trình có sẵn, thời
                    khóa biểu, chương trình.</i>
                  <ul>
                    <li>The plane <b>leaves</b> for London at
                      12.30pm.</li>
                  </ul>
                </li>
                <li>
                  <i>Dùng sau các cụm từ chỉ thời
                    gian <b>when</b>, <b>as soon as</b>… và trong
                    câu điều kiện loại 1</i>
                  <ul>
                    <li>I will call you as soon as I <b>arrive</b>.</li>
                    <li>We will not believe you unless we
                      <b>see</b> it ourselves.</li>
                  </ul>
                </li>
              </ol>
            </td>
            <td>
              <ol>
                <li>
                  <i>Diễn tả một hành động đang xảy ra tại hiện
                    tại.</i>
                  <ul>
                    <li>The children <b>are playing</b> football now.</li>
                    <li>What <b>are</b> you <b>doing</b> at the moment?</li>
                  </ul>
                </li>
                <li>
                  <i>Dùng theo sau câu đề nghị, mệnh lệnh.</i>
                  <ul>
                    <li>Look! The child <b>is crying</b>.</li>
                    <li>Be quiet! The baby <b>is sleeping</b> in the next
                      room.</li>
                  </ul>
                </li>
                <li>
                  <i>Thì này còn được dùng để diễn tả một hành
                    động sắp xảy ra. <b>(THÌ TƯƠNG LAI GẦN)</b></i>
                  <ul>
                    <li>He <b>is coming</b> tomorrow.</li>
                    <li>My parents <b>are planting</b> trees tomorrow.</li>
                  </ul>
                  <p><b>Lưu ý: </b>Không dùng thì hiện tại tiếp diễn với các động từ chỉ nhận thức, tri giác như: <i>be, become, see, hear, understand, know, like, want, glance, feel, think, smell, love, hate, realize, seem, remember, forget, belong to, believe ...</i></p>
                  <p>Với các động từ này, ta thay bằng thì HIỆN TẠI
                    ĐƠN GIẢN</p>
                  <ul>
                    <li>I <b>am tired</b> now.</li>
                    <li>She <b>wants</b> to go for a walk at the moment.</li>
                  </ul>
                </li>
              </ol>
            </td>
          </tr>
        </table>
        <br>
        <strong>IV. Spelling</strong>
        <table>
          <tr>
            <th><b>HIỆN TẠI ĐƠN</b></th>
            <th><b>HIỆN TẠI TIẾP DIỄN</b></th>
          </tr>
          <tr>
            <td>
              <ol>
                <li>
                  <i>Ta thêm s để hình thành ngôi 3 số ít của hầu hết các động từ. Nhưng ta thêm <b>es</b> khi động từ có tận cùng là <b>o, sh, s, ch, x, z.</b></i>
                  <ul>
                    <li>teach --> teaches.</li>
                  </ul>
                </li>
                <li>
                  <i>Nếu động từ tận cùng là y và đứng
                    trước nó là <b>1 phụ âm</b>, thì ta đổi <b>y</b>
                    thành <b>i</b> trước khi thêm <b>es</b>.</i>
                  <ul>
                    <li>try --> tries.</li>
                    <li>study --> studies</li>
                  </ul>
                </li>
              </ol>
            </td>
            <td>
              <ol>
                <li>
                  <i>Nếu động từ tận cùng là <b>e</b> đơn thì bỏ <b>e</b> này đi trước khi thêm <b>ing</b>. (trừ các động từ : <b>to age</b> (già đi), <b>to dye</b> (nhuộm), <b>to singe</b> (cháy xém) và các động từ tận từ là <b>ee</b>)</i>
                  <ul>
                    <li>come --> coming</li>
                  </ul>
                </li>
                <li>
                  <i>Động từ tận cùng là <b>1 nguyên âm</b> ở giữa <b>2
                    phụ âm</b> thì nhân đôi phụ âm cuối lên rồi thêm
                    <b>ing</b>.</i>
                  <ul>
                    <li>run --> running</li>
                    <li>begin --> beginning</li>
                  </ul>
                </li>
                <li>
                  <i>Nếu động từ tận cùng là <b>ie</b> thì đổi thành <b>y</b> rồi
                    mới thêm <b>ing</b>.</i>
                  <ul>
                    <li>lie --> lying</li>
                  </ul>
                </li>
                <li>
                  <i>Nếu động từ tận cùng là <b>l</b> mà trước nó là <b>1
                    nguyên âm đơn</b> thì ta cũng nhân đôi <b>l</b> đó lên rồi
                    thêm <b>ing</b>.</i>
                  <ul>
                    <li>travel --> travelling</li>
                  </ul>
                </li>
              </ol>
            </td>
          </tr>
        </table>
      </div>
    </div>

    <!-- button -->
    <div class="container">
      <div class="btn-footer">
        <div class="btn-quiz">
          <el-row>
            <router-link class="alert-link" to="" style="width: 300px; font-weight: bold; font-size: 18px;">Homework Link</router-link>
            <router-link class="alert-link" to="/users/grammar/gramTopic/gramQuiz" style="width: 300px; font-weight: bold; font-size: 18px;">Quiz</router-link>
          </el-row>
        </div>
      </div>
    </div>

  </div>
</template>


<script lang="ts" src="./gramTopic.component.ts"></script>


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
  font-weight: bold;
  margin-top: 30px;
  margin-bottom: 15px;
}
.list {

}
.list p {
  text-indent: 30px;
  text-align: left;
  line-height: 20px;
}
.list table {
  border-collapse: collapse;
  width: 100%;
}
.list td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
  border:1px solid black;
}
.list th {
  text-align: center;
}
.list tr:nth-child(even) {
  background-color: #d6d5fc;
}
.list ul {

}
.list ul li {
}

.diss ul li {
  list-style: none;
}


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
  margin: 0 200px;
  padding: 10px 20px
}
</style>

