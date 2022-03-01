<template>
  <div id="footer" class="footer">
    <p v-text="$t('footer')">--- HUST - SET - BME - Trương Thị Sáng - 20172785 - Kỳ 2021.1 ---</p>
  </div>
</template>


<script lang="ts" src="./jhi-footer.component.ts"></script>


<style>
#footer {
  /*position:absolute;*/
  bottom:0;
  width:100%;
  height:40px;
}

.footer {
  background-color: #003c72;
  color:#ffffff ;
}

p {
  line-height: 40px;
  text-align: center;
}
</style>
