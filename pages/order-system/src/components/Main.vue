<template>
  <div class="main">
    <div class="title">订单管理</div>
    <div class="content">
      <button class="add-order" @click="add">增加</button>
    </div>
    <div class="popup" v-show="iframeState">
		</div>

    <div class="order-detail">
      <table id="main-table">
        <thead>
          <tr>
            <td>姓名</td>
            <td>手机号</td>
            <td>地址</td>
            <td>购买日期</td>
            <td>机器型号</td>
            <td>最近需换芯时间</td>
            <td>售后情况细节</td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="name">宇浩</td>
            <td class="phone-number">13589784858</td>
            <td class="address">江西抚州江西抚州江西抚州江西抚州江西抚州江西抚州</td>
            <td class="sold-date">2019-09-12</td>
            <td class="machine-type">xxxx-xxxx-xxxx</td>
            <td class="replace-date">2019-11-01</td>
            <td class="detail">...</td>
          </tr>
          <tr>
            <td class="name">宇浩</td>
            <td class="phone-number">13589784858</td>
            <td class="address">江西抚州江西抚州江西抚州江西抚州江西抚州江西抚州</td>
            <td class="sold-date">2019-09-12</td>
            <td class="machine-type">xxxx-xxxx-xxxx</td>
            <td class="replace-date">2019-11-01</td>
            <td class="detail">...</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      iframeState: false
    };
  },
  mounted() {
  },
  methods: {
    add() {
      this.iframeState = true;
      console.log("add new order...");
    }
  }
};
</script>

<style lang='scss' scoped>
@import "../assets/styles/global";

.main {
  .title {
    font-weight: bold;
    font-size: 1.5em;
    padding: px2rem(80) 0;
  }
  .content {
    display: flex;
    margin-left: px2rem(100);
    .add-order {
      height: px2rem(80);
      width: px2rem(200);
      font-size: px2rem(35);
      color: #ffffff;
      background: #009688;
      border: none;
      border-radius: 2px;
      letter-spacing: px2rem(5);
      cursor: pointer;
    }
  }

  .popup{
    position: absolute; 
    top: px2rem(100); 
    left: px2rem(50);
    right: px2rem(50);
    bottom: px2rem(100);
    z-index: 101;
    background: rgba($color: #000000, $alpha: 0.5)
  }

  .order-detail {
    padding: px2rem(60) 0;
    @include center;

    #main-table {
      width: 98%;
      text-align: center;
      border-collapse: collapse;
      border: 1px solid #0094ff;
      tr {
        td {
          border: 1px solid #0094ff;
        }
      }

      thead td {
        padding: px2rem(30) 0;
        font-weight: bold;
      }

      tbody td {
        padding: px2rem(40) px2rem(15);

        &.detail {
          cursor: pointer;
        }
      }
    }
  }
}
</style>
