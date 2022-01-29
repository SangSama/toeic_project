import { Component, Vue } from 'vue-property-decorator';

@Component
export default class Home extends Vue {
  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account?.login ?? '';
  }
}

