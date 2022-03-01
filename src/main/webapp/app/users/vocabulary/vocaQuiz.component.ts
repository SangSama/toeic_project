import { Component, Vue } from 'vue-property-decorator';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);

@Component
export default class VocaQuiz extends Vue {
  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public get username(): string {
    return this.$store.getters.account?.login ?? '';
  }

  data() {
    return{
      question: [
        {
          question:'Abide by',
          suggestions: [
            {suggestion:'(v) Chứng minh, giải thích',},
            {suggestion:'(v) Tuân thủ, tuân theo',correct:true},
            {suggestion:'(n) Sự tổ chức sắp xếp',},
            {suggestion:'(v) Tập hợp, thu thập',},
          ]
        },
        {
          question:'Agreement',
          suggestions: [
            {suggestion:'(n) Hậu quả, kết quả',},
            {suggestion:'(n) Thông cảm'},
            {suggestion:'(n) Sự thỏa thuận',correct:true},
            {suggestion:'(n) Liên kết, kết hợp'},
          ]
        },
        {
          question:'Assurance',
          suggestions: [
            {suggestion:'(n) Bảo đảm, chắc chắn',correct:true},
            {suggestion:'(v) Chứng minh, giải thích',},
            {suggestion:'(n) Luật, quy tắc',},
            {suggestion:'(v) Tổng kết, thu thập',},
          ]
        },
        {
          question:'Cancellation',
          suggestions: [
            {suggestion:'(n) sự hủy bỏ',correct:true},
            {suggestion:'(n) Sự phổ biến',},
            {suggestion:'(adj) Đang thịnh hành, hiện tại',},
            {suggestion:'(v) Thuê, mướn',},
          ]
        },
        {
          question:'Determine',
          suggestions: [
            {suggestion:'(v) Thuyết phục',},
            {suggestion:'(v) Kết thúc, hết hiệu lực',},
            {suggestion:'(v) Giải quyết vấn đề',correct:true},
            {suggestion:'(v) Xóa, loại bỏ',},
          ]
        },
        {
          question:'Engage',
          suggestions: [
            {suggestion:'(v) Bảo vệ',},
            {suggestion:'(v) Tái diễn',},
            {suggestion:'(v) bắt buộc, ép buộc',},
            {suggestion:'(v) Thuê, mướn',correct:true},
          ]
        },
        {
          question:'Establish',
          suggestions: [
            {suggestion:'(v) Tổng kết, thu thập',},
            {suggestion:'(v) Thành lập',correct:true},
            {suggestion:'(v) Lảng tránh, lờ đi',},
            {suggestion:'(v) Hàm ý, nói bóng',},
          ]
        },
        {
          question:'Obligate',
          suggestions: [
            {suggestion:'(v) truyền cảm hứng',},
            {suggestion:'(v) Hứa, cam kết',},
            {suggestion:'(v) bắt buộc, ép buộc',correct:true},
            {suggestion:'(v) suy ra, nhận ra',},
          ]
        },
        {
          question:'Party',
          suggestions: [
            {suggestion:'(n) Sự nổi tiếng, danh tiếng',},
            {suggestion:'(n, v) Đơn xin, kiến nghị',},
            {suggestion:'(n) Nhóm làm việc chung',correct:true},
            {suggestion:'(n) sự thỏa mãn',},
          ]
        },
        {
          question:'Provision',
          suggestions: [
            {suggestion:'(n) Sự thuyết phục',},
            {suggestion:'(n) Sự cung cấp',correct:true},
            {suggestion:'(n) Sự nổi tiếng, danh tiếng',},
            {suggestion:'(n) Dự trù, vạch kế hoạch',},
          ]
        },
        {
          question:'Resolve',
          suggestions: [
            {suggestion:'(v) Kiên quyết, quyết định',correct:true},
            {suggestion:'(v) Tăng cường, củng cố',},
            {suggestion:'(v) Thay đổi, bất đồng',},
            {suggestion:'(n, v) Sự thực hành',},
          ]
        },
        {
          question:'Specify',
          suggestions: [
            {suggestion:'(v) Thay đổi, bất đồng',},
            {suggestion:'(v) định rõ, ghi rõ',correct:true},
            {suggestion:'(adj) bằng lời',},
            {suggestion:'(v) Sửa lại',},
          ]
        },
      ],

      a:0,
      b:1,
    }
  }
}
