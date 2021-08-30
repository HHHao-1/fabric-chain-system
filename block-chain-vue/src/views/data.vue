<template>
  <div id="centreRight1">
    <div class="operate1">
      <el-form :inline="true" :model="select" class="demo-form-inline">
        <el-form-item>
          <el-button style="margin: 0 20px 0 0" type="primary" @click="onSubmitS">查找</el-button>
        </el-form-item>
        <el-form-item label="部队">
          <el-input v-model="select.type" placeholder="请输入部队名称"></el-input>
        </el-form-item>
        <el-form-item label="时间戳">
          <el-input v-model="select.time" placeholder="请输入时间戳"></el-input>
        </el-form-item>
      </el-form>
    </div>

    <dv-loading v-if="!showAdd">Loading...</dv-loading>
    <div v-if="showAdd" class="body_1" style="height: 100%">
      <div class="add_b">
        <el-button style="margin: 0 20px 0 0" type="primary" @click="onSubmitAdd">添加</el-button>
      </div>

      <div class="line2" style="height: 80%">
        <div class="rows" style="height: 100%;overflow: auto;width: 100%"
             @touchmove.stop
             @mousewheel.stop>
          <div class="operate1">
            <el-form :inline="true" :model="add" class="demo-form-inline" style="padding-top: 0">
              <div class="line2_title" style="font-size: 1.3rem;font-weight: bold;">属性信息</div>
              <el-form-item label="部队名称">
                <el-input v-model="add.type"></el-input>
              </el-form-item>
              <el-form-item label="对象名称">
                <el-input v-model="add.属性信息.对象名称"></el-input>
              </el-form-item>
              <el-form-item label="对象代号">
                <el-input v-model="add.属性信息.对象代号"></el-input>
              </el-form-item>
              <el-form-item label="经纬度">
                <el-input v-model="add.属性信息.经纬度"></el-input>
              </el-form-item>
              <el-form-item label="速度">
                <el-input v-model="add.属性信息.速度"></el-input>
              </el-form-item>
              <el-form-item label="加速度">
                <el-input v-model="add.属性信息.加速度"></el-input>
              </el-form-item>
              <el-form-item label="防护状态">
                <el-input v-model="add.属性信息.防护状态"></el-input>
              </el-form-item>
              <el-form-item label="伪装状态">
                <el-input v-model="add.属性信息.伪装状态"></el-input>
              </el-form-item>
              <el-form-item label="物理状态">
                <el-input v-model="add.属性信息.物理状态"></el-input>
              </el-form-item>
              <el-form-item label="备战状态">
                <el-input v-model="add.属性信息.备战状态"></el-input>
              </el-form-item>
              <el-form-item label="通信状态">
                <el-input v-model="add.属性信息.通信状态"></el-input>
              </el-form-item>
              <el-form-item label="工作状态">
                <el-input v-model="add.属性信息.工作状态"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>

      <div class="line2" style="height: 82%">
        <div class="rows" style="height: 100%;overflow: auto;width: 100%"
             @touchmove.stop
             @mousewheel.stop>
          <div class="operate1">
            <el-form :inline="true" :model="add" class="demo-form-inline" style="padding-top: 0">
              <div class="line2_title" style="font-size: 1.3rem;font-weight: bold;">性能信息</div>
              <el-form-item label="射速">
                <el-input v-model="add.性能信息.射速"></el-input>
              </el-form-item>
              <el-form-item label="最大射程">
                <el-input v-model="add.性能信息.最大射程"></el-input>
              </el-form-item>
              <el-form-item label="最大射高">
                <el-input v-model="add.性能信息.最大射高"></el-input>
              </el-form-item>
              <el-form-item label="炮口初速">
                <el-input v-model="add.性能信息.炮口初速"></el-input>
              </el-form-item>
              <el-form-item label="行军转作战时间">
                <el-input v-model="add.性能信息.行军转作战时间"></el-input>
              </el-form-item>
              <el-form-item label="作战转行军时间">
                <el-input v-model="add.性能信息.作战转行军时间"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>

      <div class="line2" style="height: 82%">
        <div class="rows" style="height: 100%;overflow: auto;width: 100%"
             @touchmove.stop
             @mousewheel.stop>
          <div class="operate1">
            <el-form :inline="true" :model="add" class="demo-form-inline" style="padding-top: 0">
              <div class="line2_title" style="font-size: 1.3rem;font-weight: bold;">上报信息</div>
              <el-form-item label="状态:指挥车状态">
                <el-input v-model="add.上报信息.状态信息.发射车状态"></el-input>
              </el-form-item>
              <el-form-item label="状态:发射车状态">
                <el-input v-model="add.上报信息.状态信息.发射车状态"></el-input>
              </el-form-item>
              <el-form-item label="状态：经纬度">
                <el-input v-model="add.上报信息.状态信息.经纬度"></el-input>
              </el-form-item>
              <el-form-item label="空情:发送方">
                <el-input v-model="add.上报信息.空情信息.发送方"></el-input>
              </el-form-item>
              <el-form-item label="空情:目标批次">
                <el-input v-model="add.上报信息.空情信息.目标批次"></el-input>
              </el-form-item>
              <el-form-item label="空情:目标速度">
                <el-input v-model="add.上报信息.空情信息.目标速度"></el-input>
              </el-form-item>
              <el-form-item label="空情:敌我属性">
                <el-input v-model="add.上报信息.空情信息.敌我属性"></el-input>
              </el-form-item>
              <el-form-item label="空情:目标类型">
                <el-input v-model="add.上报信息.空情信息.目标类型"></el-input>
              </el-form-item>
              <el-form-item label="空情:战果">
                <el-input v-model="add.上报信息.空情信息.战果"></el-input>
              </el-form-item>
              <el-form-item label="射击信息:发送方">
                <el-input v-model="add.上报信息.开火射击信息.发送方"></el-input>
              </el-form-item>
              <el-form-item label="射击信息:打击目标">
                <el-input v-model="add.上报信息.开火射击信息.打击目标"></el-input>
              </el-form-item>
              <el-form-item label="射击信息:高炮部队编号">
                <el-input v-model="add.上报信息.开火射击信息.高炮部队编码"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>

      <div class="line2" style="height: 82%">
        <div class="rows" style="height: 100%;overflow: auto;width: 100%"
             @touchmove.stop
             @mousewheel.stop>
          <div class="operate1">
            <el-form :inline="true" :model="add" class="demo-form-inline" style="padding-top: 0">
              <div class="line2_title" style="font-size: 1.3rem;font-weight: bold;">指控信息</div>
              <el-form-item label="发布者">
                <el-input v-model="add.指控信息.发布订阅信息.发布者"></el-input>
              </el-form-item>
              <el-form-item label="订阅者">
                <el-input v-model="add.指控信息.发布订阅信息.订阅者"></el-input>
              </el-form-item>
              <el-form-item label="空情:空情判断">
                <el-input v-model="add.指控信息.空情信息.空情判断"></el-input>
              </el-form-item>
              <el-form-item label="空情:防空指挥">
                <el-input v-model="add.指控信息.空情信息.防空指挥"></el-input>
              </el-form-item>
              <el-form-item label="命令:控制命令">
                <el-input v-model="add.指控信息.命令信息.控制命令"></el-input>
              </el-form-item>
              <el-form-item label="命令:目标分配">
                <el-input v-model="add.指控信息.命令信息.目标分配"></el-input>
              </el-form-item>
              <el-form-item label="命令:射击命令">
                <el-input v-model="add.指控信息.命令信息.射击命令"></el-input>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>

    </div>

    <el-dialog
        :destroy-on-close=true
        :visible.sync="centerDialogVisible"
        center
        title="数据信息"
        width="90%"
    >
      <dv-loading v-if="showTx">Loading...</dv-loading>
      <div v-if="!showTx" class="json">
        <json-viewer
            :value="txData"
            boxed
            copyable
        ></json-viewer>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import axios from "axios";
import moment from "moment";
import qs from "qs";

export default {
  data() {
    return {
      txData: [],
      centerDialogVisible: false,
      showTx: false,
      showAdd: true,
      count: 10,
      select: {
        type: "",
        time: "",
      },
      add: {
        type: "",
        time: "",
        属性信息: {
          对象名称: "",
          对象代号: "",
          经纬度: "",
          速度: "",
          加速度: "",
          防护状态: "",
          伪装状态: "",
          物理状态: "",
          备战状态: "",
          通信状态: "",
          工作状态: ""
        },
        性能信息: {
          射速: "",
          最大射程: "",
          最大射高: "",
          炮口初速: "",
          行军转作战时间: "",
          作战转行军时间: "",
        },
        上报信息: {
          状态信息: {
            指挥军状态: "",
            发射车状态: "",
            经纬度: "",
          },
          空情信息: {
            发送方: "",
            目标批次: "",
            目标速度: "",
            敌我属性: "",
            目标类型: "",
            战果: ""
          },
          开火射击信息: {
            发送方: "",
            打击目标: "",
            高炮部队编码: "",
          }
        },
        指控信息: {
          发布订阅信息: {
            发布者: "",
            订阅者: "",
          },
          空情信息: {
            空情判断: "",
            防空指挥: "",
          },
          命令信息: {
            控制命令: "",
            目标分配: "",
            射击命令: "",
          }
        },
      }
    }
  },
  components: {},
  mounted() {
  },
  methods: {
    onSubmitS() {
      this.showTx = true
      this.txData = []
      let param = {
        pageSize: 100,
        currentPage: 0,
        type: this.select.type,
        value: ''
      }
      let url = "navy/list?" + qs.stringify(param)
      axios.get(url).then(res => {
        this.showTx = false
        this.centerDialogVisible = true
        res.data.forEach(s => {
          this.txData.push(JSON.parse(s))
        })
      })

    },
    onSubmitAdd() {
      this.showAdd = false;
      let value = this.add;
      let tt = moment.now();

      let values = {
        timestamp: tt,
        time: moment(tt).format("YYYY/MM/DD HH:mm:ss "),
        type: this.add.type,
        author: "center",
        value: JSON.stringify(value)
      }

      let param = {
        id: tt,
        type: this.add.type,
        values: values
      }
      // let url = "/navy/add?" + qs.stringify(param)
      let url = "navy/add?" + qs.stringify(param)
      axios.get(url).then(res => {
        if (res.data.status === 1) {
          this.$message.success("数据上链成功")
          this.showAdd = true
        } else {
          this.$message.error("数据上链失败")
          this.showAdd = true
        }
      })
    },
  }
};
</script>

<style lang="scss" scoped>
#centreRight1 {
  padding: 1rem;
  height: 78vh;
  min-width: 300px;
  border-radius: 5px;

  .operate1 {
    //height: 15%;
    border-radius: 10px;
  }

  .demo-form-inline {
    padding: 20px 0 0 20px;

    &::v-deep .el-form-item__label {
      color: #ffffff;
      font-weight: bold;
      margin-left: 15px;
    }

    &::v-deep .el-button--primary {
      background-color: #409EFF;
      border-color: #409EFF;
      margin-right: 8px;
    }
  }

  .add_b {
    padding: 20px 0 0 20px;

    .el-button--primary {
      background-color: #409EFF;
      border-color: #409EFF;
    }
  }

  .bg-color-black {
    height: 85%;
    border-radius: 10px;
  }

  .text {
    color: #c3cbde;
  }

  .body-box {
    border-radius: 10px;
    overflow: hidden;
  }

  /*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
  ::-webkit-scrollbar {
    width: 16px;
    height: 16px;
    background-color: rgb(255, 255, 255, 0);
  }

  /*定义滚动条轨道 内阴影+圆角*/
  ::-webkit-scrollbar-track {
    //-webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: inset 0 0 0 rgba(0, 0, 0, 0);
    border-radius: 10px;
    background-color: rgb(0, 0, 0, 0);
  }

  /*定义滑块 内阴影+圆角*/
  ::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, .3);
    //background-color: #555;
    background-color: rgb(85, 85, 85, 0.3);
  }

  .el-input {
    width: 220px;
  }

  &::v-deep .el-input__inner {
    background-color: rgb(41, 48, 78, 0.5);
    color: #fff;
    border: 0 solid #DCDFE6;
  }

  .line2-h {
    &::v-deep .el-form-item__label {
      margin-left: 138px;
      margin-right: 54px;
    }

  }

  .line2 {
    width: 270px;
    margin-right: 60px;

    &::v-deep .el-form {
      display: flex;
      flex-direction: column;
    }

  }

  .body_1 {
    margin-top: 25px;
    display: flex;
    flex-direction: row;

    .line2_title {
      margin: 0 0 15px 0;
      text-align: center;
    }

    .el-form-item {
      margin-bottom: 10px;;
    }

    .add_b {
      padding-top: 0;
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