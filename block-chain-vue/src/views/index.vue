<template>
  <div id="index" @touchmove.prevent @mousewheel.prevent>
    <!--    <dv-full-screen-container class="bg">-->
    <dv-loading v-if="loading">Loading...</dv-loading>
    <div v-else class="host-body">
      <div class="d-flex jc-center">
        <dv-decoration-10 style="width:33.3%;height:5px;"/>
        <div class="d-flex jc-center">
          <dv-decoration-8 :color="['#568aea', '#000000']" style="width:200px;height:50px;"/>
          <div class="title">
            <span class="title-text">数据中心</span>
            <dv-decoration-6
                :color="['#50e3c2', '#67a1e5']"
                :reverse="true"
                class="title-bototm"
                style="width:250px;height:8px;"
            />
          </div>
          <dv-decoration-8
              :color="['#568aea', '#000000']"
              :reverse="true"
              style="width:200px;height:50px;"
          />
        </div>
        <dv-decoration-10 style="width:33.3%;height:5px;"/>
      </div>

      <!-- 第二行 -->
      <div class="d-flex jc-between px-2">
        <div class="d-flex" style="width: 40%">
          <div :class="page===1?'bg-color-blue':''"
               :style="{'background-color': page===1?'':'#0f1325'}"
               class="react-right ml-4" style="width: 500px; text-align: left;background-color: #0f1325;"
               @click="netPage"
          >
            <span :style="{'background-color': page===1?'#1A5CD7':''}" class="react-before"></span>
            <!--              <span class="text fw-b">网络结构</span>-->
            <span :class="page===1?'':'colorBlue'" class="text fw-b">区块链网络结构</span>
          </div>
          <!--            <div class="react-right ml-3 " style="background-color: #0f1325;">-->
          <div :class="page===2?'bg-color-blue':''" :style="{'background-color': page===2?'':'#0f1325'}"
               class="react-right ml-3"
               @click="viewPage">
            <span :class="page===2?'':'colorBlue'" class="text fw-b">区块信息</span>
          </div>
        </div>
        <div class="d-flex" style="width: 40%">
          <div :class="page===3?'bg-color-blue':''" :style="{'background-color': page===3?'':'#0f1325'}"
               class="react-left mr-3"
               @click="operatePage">
            <span :class="page===3?'':'colorBlue'" class="text fw-b">数据操作</span>
          </div>
          <div
              class="react-left bg-color-blue mr-4"
              style="width: 500px; text-align: right;"
          >
            <span class="react-after bg-color-blue"></span>
            <!--              <span class="text">2020年03月16日 周一 12:00</span>-->
            <!--            <span class="text">{{ now }}</span>-->
            <span class="text fw-b">
              <el-dropdown placement="bottom-end" trigger="click">
                <el-button class="channel" type="primary">
                  {{ channel }}<i class="el-icon-arrow-down el-icon--right"></i>
                </el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="channelChange(0)">navy-channel</el-dropdown-item>
                  <el-dropdown-item @click.native="channelChange(1)">army-channel</el-dropdown-item>
                  <el-dropdown-item @click.native="channelChange(2)">air-force-channel</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </span>
          </div>
        </div>
      </div>

      <div class="body-box">
        <!-- 第三行数据 -->
        <div class="content-box">
          <!--            <div>-->
          <!--              <dv-border-box-12>-->
          <!--&lt;!&ndash;                <centreLeft1 />&ndash;&gt;-->
          <!--              </dv-border-box-12>-->
          <!--            </div>-->
          <!--            <div>-->
          <!--              <dv-border-box-12>-->
          <!--&lt;!&ndash;                <centreLeft2 />&ndash;&gt;-->
          <!--              </dv-border-box-12>-->
          <!--            </div>-->
          <!--            &lt;!&ndash; 中间 &ndash;&gt;-->
          <!--            <div>-->
          <!--&lt;!&ndash;              <center />&ndash;&gt;-->
          <!--            </div>-->
          <!--            &lt;!&ndash; 中间 &ndash;&gt;-->
          <!--            <div>-->
          <!--&lt;!&ndash;              <centreRight2 />&ndash;&gt;-->
          <!--            </div>-->
          <div>
            <dv-border-box-13>
              <!--                <centreRight1 />-->
              <component :is="pageName" :channel="channel"/>
            </dv-border-box-13>
          </div>
        </div>

        <!-- 第四行数据 -->
        <!--          <div class="bototm-box">-->
        <!--            <dv-border-box-13>-->
        <!--&lt;!&ndash;              <bottomLeft />&ndash;&gt;-->
        <!--            </dv-border-box-13>-->
        <!--            <dv-border-box-12>-->
        <!--&lt;!&ndash;              <bottomRight />&ndash;&gt;-->
        <!--            </dv-border-box-12>-->
        <!--          </div>-->
      </div>
    </div>
    <!--    </dv-full-screen-container>-->
    <div style="text-align: right;padding:15px 20px 0 0; font-size: 1.2rem;font-weight: bold"><span class="text">{{
        now
      }}</span></div>
  </div>
</template>

<script>
// import centreLeft1 from "./centreLeft1";
// import centreLeft2 from "./centreLeft2";
// import centreRight1 from "./centreRight1";
// import centreRight2 from "./centreRight2";
// import center from "./center";
// import bottomLeft from "./bottomLeft";
// import bottomRight from "./bottomRight";
import netPage from "./netPage";
import viewPage from "./viewPage";
import operatePage from "./operatePage";
import dataOpPage from "./data";
import * as moment from 'moment';

export default {
  data() {
    return {
      loading: true,
      now: '',
      page: 2,
      pageName: 'viewPage',
      channel: 'navy-channel'
    };
  },
  components: {
    // centreLeft1,
    // centreLeft2,
    // centreRight1,
    // centreRight2,
    // center,
    // bottomLeft,
    // bottomRight
    viewPage,
    netPage,
    operatePage,
    dataOpPage,
  },
  mounted() {
    this.cancelLoading();
    this.nowDate();
  },
  methods: {
    channelChange(index) {
      switch (index) {
        case 0:
          this.channel = 'navy-channel'
          break
        case 1:
          this.channel = 'army-channel'
          break
        case 2:
          this.channel = 'air-force-channel'
          break
      }
    },
    nowDate() {
      setInterval(() => {
        this.now = moment().format("YYYY年MM月DD日 HH:mm:ss ");
      }, 500)
    },
    netPage() {
      this.page = 1;
      this.pageName = 'netPage';
    },
    viewPage() {
      this.page = 2;
      this.pageName = 'viewPage';
    },
    operatePage() {
      this.page = 3;
      this.pageName = 'dataOpPage';
    },
    cancelLoading() {
      setTimeout(() => {
        this.loading = false;
      }, 2000);
    }
  }
};
</script>

<style lang="scss" scoped>
@import "../assets/scss/index.scss";

.el-dropdown {
  vertical-align: top;
}

.el-dropdown + .el-dropdown {
  margin-left: 15px;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.channel {
  background: rgb(255, 255, 255, 0);
  border: 0 solid #1A5CD7;
  font-weight: bold;
  font-size: 1.3rem;
}

.mr-4 {
  cursor: auto !important;
}

.el-popper {
  background: rgb(15, 19, 37, 0.7);
  border-color: #0f1325;
  width: 15rem;

  .el-dropdown-menu__item {
    padding: 0 10px;
    color: #fff;
    text-align: center
  }
}
</style>