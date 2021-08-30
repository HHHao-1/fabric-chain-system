<template>
  <div id="centreRight1">
    <div class="operate">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="Type">
          <el-input v-model="formInline.user" placeholder="请输入类型"></el-input>
        </el-form-item>
        <el-form-item label="Value">
          <el-input v-model="formInline.user" placeholder="请输入值"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="margin-left: 20px" type="primary" @click="onSubmit">添加</el-button>
        </el-form-item>
        <el-form-item>
          <el-button style="margin-left: 5px" type="primary" @click="onSubmit">查找</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="bg-color-black">
      <div class="d-flex pt-2 pl-2" style="margin:0 0 10px 0">
              <span style="color:#5cd9e8">
                <icon name="inbox"></icon>
              </span>
        <div class="d-flex">
          <span class="fs-xl text mx-2">数据展示</span>
        </div>
      </div>
      <div class="d-flex jc-center body-box" style="width: 100%;height: calc(100% - 40px)">
        <!--        <dv-scroll-board :config="config" style="width:270px;height:360px" />-->
        <div class="b" style="width: 100%">

          <div class="header" style="background-color: rgb(15, 19, 37);width: 100%">
            <div align="center" class="header-item">#</div>
            <div align="center" class="header-item">Key</div>
            <div align="center" class="header-item">Type</div>
            <div align="center" class="header-item">Value</div>
            <div align="center" class="header-item">历史记录</div>
            <div align="center" class="header-item">操作</div>
          </div>
          <div v-infinite-scroll="load" class="rows" style="height: 100%;overflow: auto;width: 100%" @touchmove.stop
               @mousewheel.stop>

            <div v-for="(i,k) in count" :key="k" :style="{'background-color': k%2===0?'':'rgb(23, 28, 51)'}"
                 class="row-item">
              <div align="center" class="ceil">1</div>
              <div align="center" class="ceil">key</div>
              <div align="center" class="ceil">type</div>
              <div align="center" class="ceil">value</div>
              <div align="center" class="ceil">历史</div>
              <div align="center" class="ceil">操作</div>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      count: 10,
      formInline: {
        user: '',
        region: ''
      }
    }
  },
  components: {},
  mounted() {
  },
  methods: {
    onSubmit() {
      // console.log('submit!');
    },
    load() {
      this.count += 2
    }
  }
};
</script>

<style lang="scss" scoped>
#centreRight1 {
  padding: 1rem;
  height: 78vh;
  min-width: 300px;
  border-radius: 5px;

  .operate {
    height: 15%;
    border-radius: 10px;

    .demo-form-inline {
      padding: 20px 0 30px 20px;

      &::v-deep .el-form-item__label {
        color: #ffffff;
        font-weight: bold;
      }

      .el-button--primary {
        background-color: #409EFF;
        border-color: #409EFF;
      }
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

  .el-table__header-wrapper, .el-table__header {
    background: black;
  }

  .el-table_1_column_1 {
    background: black;
  }

  .b {
    display: block;

    .header {
      display: grid;
      grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
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

      &::-webkit-scrollbar {
        width: 0 !important;
      }

      -ms-overflow-style: none;
      overflow: -moz-scrollbars-none;

      .row-item {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr;
        font-size: 14px;
        -webkit-transition: all 0.3s;
        transition: all 0.3s;
        line-height: 3rem;

        .cell {
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
}
</style>