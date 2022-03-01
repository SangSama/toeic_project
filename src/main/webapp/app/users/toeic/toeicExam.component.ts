import { Component, Vue } from 'vue-property-decorator';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import FlipCountdown from 'vue2-flip-countdown';

Vue.use(ElementUI);

@Component
export default class ToeicExam extends Vue {
  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account?.login ?? '';
  }

}
