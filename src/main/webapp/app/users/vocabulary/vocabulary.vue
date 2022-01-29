<template>
  <div id="userVocabulary">
    <div class="content container">
      <h1 class="display-4" v-text="$t('finalProjectApp.userVocabularyList.home.title')">VOCABULARY</h1>
      <p class="lead" v-text="$t('finalProjectApp.userVocabularyList.home.subtitle.sub01')">Vocabulary is the foundation for development skills.</p>
      <p class="lead" v-text="$t('finalProjectApp.userVocabularyList.home.subtitle.sub02')">Rich, abundant capital will support you a lot in developing and
        perfecting other skills more effectively.</p>
      <p class="lead" v-text="$t('finalProjectApp.userVocabularyList.home.subtitle.sub03')">Get capital from variety, you can easily understand the communication
        content even if your grammar is not too firm. And when I understand fast, understand it right, you will be able to reflect
        the reply quickly. The process of listening, understanding and saying there will be more and more fluent, fluent.</p>
      <p class="lead" v-text="$t('finalProjectApp.userVocabularyList.home.subtitle.sub04')">In addition to issues related to listening, speaking or reading skills,
        English vocabulary also helps develop the brain in the ability to write quickly, in the right context and not missing.
        From there, you can completely confidently write a letter or present a problem in English without worrying about anything.</p>
    </div>
    <div class="folder row">
      <div class="col-md-3">
        <div class="sub-folder">
          <div class="img-item">
            <img src="../../../content/images/vocabulary.jpg" alt="1" />
          </div>
          <div class="text-item">
            <span>600 Word</span>
            <p>Some topics in 600 basic TOEIC vocabulary. The common vocabulary will appear in the TOEIC exam.</p>
            <p><font-awesome-icon icon="home" />100</p>
            <p><font-awesome-icon icon="home" />100</p>
            <button>JOIN</button>
          </div>
        </div>
      </div>
      <div class="col-md-3">
        <div class="sub-folder">
          <div class="img-item">
            <img src="../../../content/images/vocabulary.jpg" alt="1" />
          </div>
          <div class="text-item">
            <span>600 Word</span>
            <p>Some topics in 600 basic TOEIC vocabulary. The common vocabulary will appear in the TOEIC exam.</p>
            <p><font-awesome-icon icon="home" />100</p>
            <p><font-awesome-icon icon="home" />100</p>
            <button>JOIN</button>
          </div>
        </div>
      </div>
      <div class="col-md-3">
        <div class="sub-folder">
          <div class="img-item">
            <img src="../../../content/images/vocabulary.jpg" alt="1" />
          </div>
          <div class="text-item">
            <span>600 Word</span>
            <p>Some topics in 600 basic TOEIC vocabulary. The common vocabulary will appear in the TOEIC exam.</p>
            <p><font-awesome-icon icon="home" />100</p>
            <p><font-awesome-icon icon="home" />100</p>
            <button>JOIN</button>
          </div>
        </div>
      </div>
      <div class="col-md-3">
        <div class="sub-folder">
          <div class="img-item">
            <img src="../../../content/images/vocabulary.jpg" alt="1" />
          </div>
          <div class="text-item">
            <span>600 Word</span>
            <p>Some topics in 600 basic TOEIC vocabulary. The common vocabulary will appear in the TOEIC exam.</p>
            <p><font-awesome-icon icon="home" />100</p>
            <p><font-awesome-icon icon="home" />100</p>
            <button>JOIN</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./vocabulary.component.ts"></script>


/* style */
<style scoped>
#userVocabulary {

}

.content {

}

.content h1 {
  text-align: center;
}

.content p {

}

.folder {

}

.folder .sub-folder {
  border: #000000 1px solid;
  background-color: #bbbbbb;
}

.folder .sub-folder .img-item {

}

.folder .sub-folder .img-item img {
  width: fit-content;
  width: 100%;
}

.folder .sub-folder .text-item {
  margin: 25px 10px;
}

.folder .sub-folder .text-item span {
  text-align: center;
  font-weight: bold;
  text-transform: uppercase;
  font-size: 18px !important;
}

.folder .sub-folder .text-item button {
  padding: 5px 30px 5px 30px;
  margin:auto;
  display:block;
}

</style>
