<template>
  <div id="centreRight1">
    <div class="bg-color-black">
      <div class="d-flex pt-2 pl-2" style="margin:0 0 10px 0">
        <span style="color:#5cd9e8">
          <icon name="inbox"></icon>
        </span>
        <div class="d-flex">
          <span class="fs-xl text mx-2">实时区块信息</span>
        </div>
      </div>
      <div class="d-flex jc-center body-box" style="width: 100%;height: calc(100% - 40px)">
        <!--        <dv-scroll-board :config="config" style="width:270px;height:360px" />-->
        <div class="b" style="width: 100%">

          <div class="header" style="background-color: rgb(15, 19, 37);width: 100%">
            <div align="center" class="header-item" style="width: 12.5%;">区块高度</div>
            <div align="center" class="header-item" style="width: 37.5%;">区块哈希</div>
            <div align="center" class="header-item" style="width: 37.5%;">数据哈希</div>
            <div align="center" class="header-item" style="width: 12.5%;">交易数量</div>
          </div>
          <dv-loading v-if="!showBlock">Loading...</dv-loading>
          <div v-if="showBlock" v-infinite-scroll="load" class="rows" style="height: 100%;overflow: auto;width: 100%"
               @touchmove.stop
               @mousewheel.stop>

            <div v-for="(i,k) in count" :key="k" :style="{'background-color': k%2===0?'':'rgb(23, 28, 51)'}"
                 class="row-item">
              <div align="center" class="ceil" style="width: 12.5%;overflow:hidden;white-space:nowrap

;text-overflow:ellipsis;">
                <span class="index" style=";width: 100%;">#{{ blockData.blockNumbers[k] }}</span>
              </div>
              <div :title="blockData.blocksHash[k]" align="center" class="ceil"
                   style="width: 37.5%;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;"><span
                  style="padding: 0 10px;width: 100%;">{{ blockData.blocksHash[k] }}</span></div>
              <div :title="blockData.datasHash[k]" align="center"
                   class="ceil"
                   style="width: 37.5%;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;"><span
                  style="padding: 0 10px;width: 100%;">{{ blockData.datasHash[k] }}</span></div>
              <div align="center" class="ceil" style="width: 12.5%;overflow: hidden"
                   @click="getTxInfo(blockData.blockNumbers[k])">
                <div class="colorGrass txCount" style="width: 100%;">{{ blockData.txCounts[k] }}
                </div>
              </div>
            </div>

          </div>
          <el-dialog
              :destroy-on-close=true
              :visible.sync="centerDialogVisible"
              center
              title="交易信息"
              width="90%"
          >
            <dv-loading v-if="showTx">Loading...</dv-loading>
            <div v-if="!showTx" class="json">
              <json-viewer
                  :expand-depth=5
                  :value="txData"
                  boxed
                  copyable
                  expanded
              ></json-viewer>
            </div>
          </el-dialog>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";

export default {
  data() {
    return {
      count: 0,
      blockData: null,
      showBlock: false,
      centerDialogVisible: false,
      showTx: false,
      txData: [],
    }
  },
  props: ['channel'],
  components: {},
  mounted() {
    this.getBlocksInfo();
  },
  filters: {
    format: function (val) {
      return moment.unix(val.slice(0, 10)).format("YYYY/MM/DD HH:mm:ss ");
    }
  },
  watch: {
    channel() {
      this.getBlocksInfo();
    }
  },
  methods: {
    load() {
      if (this.count < this.blockData.height) {
        this.count += 1
      }
    },
    getTxInfo(blockNumber) {
      this.showTx = true;
      this.centerDialogVisible = true
      this.txData = []
      let url = '/' + this.channel.slice(0, this.channel.indexOf('-')) + '/txs'
      axios.get(url, {
        params: {
          blockNumber: blockNumber
        }
      }).then(info => {
        this.showTx = false;
        let count = 0;
        info.data.txDatas.forEach(s => {
          if (s.value !== "" && s.value.indexOf("author") !== -1) {
            s.value = JSON.parse(s.value)
            s.value.value = JSON.parse(s.value.value)
          }
          let txd = {txHash: info.data.txIds[count], txData: {...s}}
          this.txData.push(txd)
          count++
        })
      })
    },
    getBlocksInfo() {
      this.count = 0
      this.showBlock = false
      let url = '/' + this.channel.slice(0, this.channel.indexOf('-')) + '/blocks'
      axios.get(url).then(info => {
        this.blockData = info.data
        this.showBlock = true
      })
    }
  }
};
</script>

<style lang="scss" scoped>
#centreRight1 {
  padding: 1rem;
  //height: 410px
  height: 78vh;
  min-width: 300px;
  border-radius: 5px;

  .bg-color-black {
    //height: 385px;
    height: 100%;
    border-radius: 10px;
  }

  .text {
    color: #c3cbde;
  }

  .body-box {
    border-radius: 10px;
    overflow: hidden;
  }

  .el-table__header-wrapper, .el-table__header {
    background: black;
  }

  .el-table_1_column_1 {
    background: black;
  }

  .b {
    display: block;

    .header {
      display: -webkit-box;
      display: -ms-flexbox;
      display: flex;
      justify-content: space-around;
      //display: grid;
      //grid-template-columns: 1fr 3fr 2fr 1fr;
      font-size: 1.3rem;
      line-height: 3rem;

      .header-item {
        height: 3rem;
      }
    }

    .rows {
      position: relative;
      width: 100%;
      height: 100%;
      color: #fff;
      overflow: hidden;
      font-size: 1.5rem;

      .txCount {
        &:hover {
          cursor: pointer;
        }
      }

      &::-webkit-scrollbar {
        width: 0 !important;
      }

      -ms-overflow-style: none;
      overflow: -moz-scrollbars-none;

      .row-item {
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        justify-content: space-around;
        //display: grid;
        //grid-template-columns: 1fr 3fr 3fr 1fr;
        font-size: 14px;
        -webkit-transition: all 0.3s;
        transition: all 0.3s;
        line-height: 3rem;

        .cell {
          width: 12.5%;
          padding: 0 10px;
          -webkit-box-sizing: border-box;
          box-sizing: border-box;
          white-space: nowrap;
          overflow: hidden;
          text-overflow: ellipsis;
        }
      }
    }
  }

  &::v-deep .el-dialog__wrapper {
    height: 100vh;
  }

  &::v-deep .el-dialog {
    height: 75%;
    //background-color: rgb(253, 253, 253);
    background-color: #353a4ca8;;
  }

  &::v-deep .el-dialog__body {
    padding: 0;
    height: 85%;

  }

  &::v-deep .el-dialog__title {
    color: white;
    font-weight: bold;
    font-size: 1.5rem;
  }

  .json {
    height: 100%;
    padding-top: 20px;
    display: flex;
    justify-content: center;
    text-align: left;

    .boxed {
      width: 90%;
    }

  }
}
</style>