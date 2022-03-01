<template>
  <div class="qna row">

    <!--tieu de-->
    <div class="title">
      <h1 class="display-4" v-text="$t('home.qna.title')">Question and Answer</h1>
      <div>
        <div class="el-breadcrumb">
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/" v-text="$t('home.about.home')">Home</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
          <span class="el-breadcrumb__item">
            <router-link class="item" to="/qna" v-text="$t('home.qna.qna')">Q&A</router-link>
            <span class="el-breadcrumb__separator">/</span>
          </span>
        </div>
      </div>
    </div>

    <!-- -->
    <div class="container header-content">
      <div class="col-md-5">
        <img src="../../../content/images/qna02.jpg" class="image">
      </div>
      <div class="col-md-7">
        <div class="text">
          <p>Bạn không hiểu một vấn đề ngữ pháp hay từ vựng tiếng Anh?</p>
          <p>Bạn có thắc mắc về một bài tập tiếng Anh hay cần dịch một câu khó?</p>
          <p>Hãy đặt câu hỏi ngay để được giải đáp</p>
          <p>và nhận thông báo qua email ngay khi có câu trả lời!</p>
        </div>
        <div>
          <el-row>
            <el-button class="qna-btn" type="primary" v-on:click="openLogin()" round plain>Send questions</el-button>
          </el-row>
        </div>
      </div>
    </div>

    <!-- -->
    <div class="container">
      <div class="list-qna">
        <el-collapse accordion>
          <el-collapse-item title="1. Time là danh từ đếm được hay không đếm được?" name="1">
            <div class="answer">
              <div>Time khi có nghĩa là “thời gian” thì nó là danh từ không đếm được, nhưng khi có nghĩa là “khoảng thời gian” thì nó là danh từ đếm được nhé em.</div>
              <div>Giống trong tiếng Việt, em có thể nói “Tôi không có đủ thời gian để chơi game” nhưng không thể nói “tôi không có đủ ba thời gian để chơi game”.</div>
              <div>Nhưng em có thể nói “Đó là ba khoảng thời gian đẹp nhất trong đời tôi.”</div>
              <br>
              <div>Ví dụ:</div>
              <div>○ We had a wonderful time in Paris.</div>
              <div>= Chúng tôi đã có một khoảng thời gian tuyệt vời ở Paris.</div>
            </div>
          </el-collapse-item>
          <el-collapse-item title="2. Thank hay thanks?" name="2">
            <div class="answer">
              <div>Khi nào có tân ngữ phía sau thì dùng thank, khi nào không có tân ngữ phía sau thì dùng thanks.</div>
              <br>
              <div>○ Thank you for everything. ← đúng</div>
              <div>○ Thanks for contacting us. ← đúng</div>
              <br>
              <div>○ Thanks you for everything. ← sai</div>
              <div>○ Thank for contacting us. ← sai</div>
            </div>
          </el-collapse-item>
          <el-collapse-item title="3. Phân biệt mệnh đề độc lập và mệnh đề phụ thuộc (câu ghép và câu phức)" name="3">
            <div class="answer">
              <div>Cách phân biệt câu ghép và câu phức không dựa vào nghĩa mà dựa vào ngữ pháp nhé em.</div>
              <br>
              <div>Nguyên tắc là for, and, but, or, yet, so được gọi là coordinating conjunctions (và nối hai mệnh đề độc lập trong câu ghép) bởi vì chúng phải nằm giữa hai vế và có dấu phẩy phía trước.</div>
              <br>
              <div>Ví dụ:<br>
                ○ He went to bed, for he was tired. ← đúng<br>
                ○ For he was tired, he went to bed ← sai</div>
              <br>
              <div>Do đó, for là coordinating conjunction.</div>
              <br>
              <div>Những liên từ khác như because, when, although, so that,… được gọi là subordinating conjunctions (và đứng đầu mệnh đề phụ thuộc / trạng ngữ trong câu phức) vì một trong hai lí do: 1. chúng có thể đứng đầu câu hoặc 2. chúng đứng giữa câu thì không có dấu phẩy phía trước.</div>
              <br>
              <div>Ví dụ:<br>
                ○ Because he was tired, he went to bed.<br>
                ○ He went to bed because he was tired.</div>
              <br>
              <div>Do đó, because là subordinating conjunction.</div>
              <br>
              <div>Đây là cách xếp loại rất ngẫu hứng của các nhà ngôn ngữ học, cũng giống như nhiều trường hợp khác. Do đó, để tiến bộ nhanh với tiếng Anh thì em nên bớt quan tâm về việc phân biệt và gọi tên các thành phần ngữ pháp đi nhé. Chỉ cần hiểu nghĩa và dùng đúng ngữ pháp là được. Nhận ra điều này càng sớm càng tốt.</div>
            </div>
          </el-collapse-item>
          <el-collapse-item title="4. Rút gọn khi có so sánh nhất: V-ing hay to-V?" name="4">
            <div class="answer">
              <div>Nếu sau so sánh nhất mà ta dùng to-V thì lúc đó:<br>
                Cái đó phải là cái lớn nhất, mới nhất, cao nhất, trẻ nhất, … mà đã từng làm hành động đó tính đến nay hoặc tính đến thời điểm hành động xảy ra.</div>
              <br>
              <div>Ví dụ:<br>
                ○ The biggest problem to face humanity is climate change.<br>
                = Vấn đề lớn nhất mà từng gây khó khăn cho nhân loại là thay đổi khí hậu.<br>
                (vấn đề lớn nhất từ đước đến nay)<br>
                <br>
                ○ He was the youngest scientist to win a Nobel Prize.<br>
                = Anh ấy là nhà khoa học trẻ nhất từng giành Giải Nobel.<br>
                (người trẻ nhất từng giành giải Nobel từ trước đó cho đến thời điểm anh ấy được giải)</div>
              <br>
              <div>Trong câu em hỏi thì vấn đề đó chỉ là vấn đề lớn nhất mà đang gây khó cho nền kinh tế ở thời điểm hiện tại, chứ không phải lớn nhất từ trước đến nay.</div>
              <br>
              <div>○ The biggest problem facing our economy is the increase of unemployment rate.<br>
                = Vấn đề lớn nhất mà đang gây khó cho nền kinh tế là sự gia tăng tỷ lệ thất nghiệp.</div>
              <br>
              <div>Do đó, ta không dùng to-V ở đây, mà dùng V-ing như rút gọn mệnh đề quan hệ bình thường.</div>
            </div>
          </el-collapse-item>
          <el-collapse-item title="5. none / anybody?" name="5">
            <div class="answer">Những chữ anybody, somebody, nobody, everybody không được dùng với of + danh từ chỉ người nhé em.</div>
          </el-collapse-item>
          <el-collapse-item title="6. Tính từ có đứng sau động từ được không?" name="6">
            <div class="answer">
              <div>Tính từ nằm đâu cũng được nhé em, miễn là nó mô tả cho một cụm danh từ.</div>
              <br>
              <div>○ Good service is rated very important by women of all ages.<br>
                = Dịch vụ tốt được đánh giá là rất quan trọng bởi phụ nữ ở tất cả mọi độ tuổi.</div>
              <br>
              <div>Trong câu này rõ ràng chữ important đang mô tả cho cụm danh từ good service nên nó là tính từ.</div>
              <br>
              <div>Nguyên tắc là mô tả cho danh từ thì là tính từ, còn mô tả cho loại từ khác thì mới là trạng từ. Đơn giản vậy thôi.</div>
            </div>
          </el-collapse-item>
        </el-collapse>
      </div>
    </div>

  </div>
</template>

<script  lang="ts" src="./qna.component.ts"></script>

<style scoped>
/**/
.qna {
  font-size: 15px;
}

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
.header-content {
  height: 300px;
  width: 100%;
  display: inline-flex;
  text-align:center;
  margin-top: 20px;
}
.header-content img.image {
  height: 100%;
  width: auto;
  display: block;
  margin-left: auto;
  margin-right: auto;
}
.header-content .text {
  padding-top: 30px;
  font-size: 16px;
  font-weight: bold;
  text-align: center;
}
.header-content .text p {
  line-height: 30px;
  margin-bottom: 10px;
}
.header-content .qna-btn {
  margin-top: 20px;
  display: inline-block;
}

/**/
.list-qna {
  margin-top: 15px;
  font-size: 16px;
  text-indent: 20px;
  font-weight: bold;
}
.list-qna .answer {
  text-indent: 30px;
  background-color: #d1e8ff;
  padding: 0 30px;
}

</style>
