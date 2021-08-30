<template>
  <div class="Echarts">
    <div id="main" style="width: 100%;height:100%"></div>
  </div>
</template>
<script>
// import axios from 'axios'
import graph from '../assets/netStructure.json'

const echarts = require('echarts/lib/echarts');
require('echarts/lib/component/title');
require('echarts/lib/component/tooltip');
require('echarts/lib/component/legend');
require('echarts/lib/chart/graph');

export default {
  name: 'Echarts',
  methods: {
    myEcharts() {
      // let ROOT_PATH = 'https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site/examples';
      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(document.getElementById('main'));
      let option;
      // myChart.showLoading();
      // axios.get(ROOT_PATH + '/data/asset/data/les-miserables.json').then(function (graph) {
      // myChart.hideLoading();
      // graph.nodes.forEach(function (node) {
      //   node.symbolSize = 5;
      // });
      option = {
        title: {
          text: '军事区块链网络结构图',
          subtext: '',
          top: 'bottom',
          left: 'right'
        },
        tooltip: {
          show: true,   //默认显示
          showContent: true, //是否显示提示框浮层
          trigger: 'item',//触发类型，默认数据项触发
          triggerOn: 'click',//提示触发条件，mousemove鼠标移至触发，还有click点击触发
          // alwaysShowContent: false, //默认离开提示框区域隐藏，true为一直显示
          showDelay: 0,//浮层显示的延迟，单位为 ms，默认没有延迟，也不建议设置。在 triggerOn 为 'mousemove' 时有效。
          hideDelay: 200,//浮层隐藏的延迟，单位为 ms，在 alwaysShowContent 为 true 的时候无效。
          enterable: false,//鼠标是否可进入提示框浮层中，默认为false，如需详情内交互，如添加链接，按钮，可设置为 true。
          position: 'right',//提示框浮层的位置，默认不设置时位置会跟随鼠标的位置。只在 trigger 为'item'的时候有效。
          confine: true,//是否将 tooltip 框限制在图表的区域内。外层的 dom 被设置为 'overflow: hidden'，或者移动端窄屏，导致 tooltip 超出外界被截断时，此配置比较有用。
          transitionDuration: 0.4,//提示框浮层的移动动画过渡时间，单位是 s，设置为 0 的时候会紧跟着鼠标移动。
          formatter: function (params) {
            if (params.dataType === "edge") {
              var a = params.data.source
              var b = params.data.target
              a = graph.nodes[a].name
              b = graph.nodes[b].name
              return a + '<span style="padding:0 8px;"> <--> </span>' + b
            }
            var ip = '<div style="display:inline-flex"><div style="padding-top: 10px;width:10px;height:10px;border-radius:50%;background-color:' + params.color + ';"></div><div style="line-height: 10px;margin-left: 10px">' + params.data.ip + '</div></div>'

            return ip
          }
        },
        legend: [{
          // selectedMode: 'single',
          data: graph.categories.map(function (a) {
            return a.name;
          }),
          orient: 'vertical',
          x: 'left',      //可设定图例在左、右、居中
          y: 'top',     //可设定图例在上、下、居中
          padding: [20, 0, 0, 10],   //可设定图例[距上方距离，距右方距离，距下方距离，距左方距离]
          // data: ['海军', '陆军', '空军', '指挥中心'], //此处的数据必须和关系网类别中name相对应
          textStyle: {
            color: 'white',
            fontSize: 13, // 文字的字体大小
            lineHeight: 20, // 行高
          },
        }],
        series: [
          {
            legendHoverLink: true,//是否启用图例 hover(悬停) 时的联动高亮。
            hoverAnimation: true,//是否开启鼠标悬停节点的显示动画
            focusNodeAdjacency: true,//是否在鼠标移到节点上的时候突出显示节点以及节点的边和邻接节点。
            // name: 'Les Miserables',
            type: 'graph',
            layout: 'force',
            data: graph.nodes,
            links: graph.links,
            categories: graph.categories,
            roam: true,
            label: {
              position: 'right',
              normal: {
                show: true,//是否显示标签。
                position: 'inside',//标签的位置。['50%', '50%'] [x,y]
                textStyle: { //标签的字体样式
                  color: 'black', //字体颜色
                  fontStyle: 'normal',//文字字体的风格 'normal'标准 'italic'斜体 'oblique' 倾斜
                  fontWeight: 'bolder',//'normal'标准'bold'粗的'bolder'更粗的'lighter'更细的或100 | 200 | 300 | 400...
                  fontFamily: 'sans-serif', //文字的字体系列
                  fontSize: 12, //字体大小
                }
              },
            },
            force: {
              edgeLength: 220, //连线的长度
              repulsion: 1000 //子节点之间的间距
            },
            // draggable: true,
          }
        ]
      };

      myChart.setOption(option);
      // });

      option && myChart.setOption(option);
    },
    // ec(){
    //   var myChart = this.$echarts.init(document.getElementById('main'));
    //   // var app = {};
    //   var option = null;
    //   var webkitDep = {
    //     "type": "force",
    //     "categories": [
    //       {
    //         "name": "海军",
    //         itemStyle: {
    //             color: "blue",
    //         },
    //       },
    //       {
    //         "name": "陆军",
    //         itemStyle: {
    //           color: "green",
    //         },
    //       },
    //       {
    //         "name": "空军",
    //         itemStyle: {
    //           color: "#5ccdef",
    //         },
    //       },
    //       {
    //         "name": "指挥中心",
    //         itemStyle: {
    //           color: "yellow",
    //         },
    //       },
    //     ],
    //     "nodes": [
    //       {
    //         "name": "navy",
    //         "value": 10,
    //         "symbolSize": 30,
    //         "category": 0
    //       },
    //       {
    //         "name": "navy",
    //         "value": 10,
    //         "symbolSize": 30,
    //         "category": 0,
    //       },
    //       {
    //         "name": "navy",
    //         "value": 10,
    //         "symbolSize": 30,
    //         "category": 0,
    //       },
    //       {
    //         "name": "army",
    //         "value": 10,
    //         "symbolSize": 30,
    //         "category": 1,
    //       },
    //       {
    //         "name": "army",
    //         "value": 110,
    //         "symbolSize": 30,
    //         "category": 1,
    //       },
    //       {
    //         "name": "army",
    //         "value": 100,
    //         "symbolSize": 30,
    //         "category": 1,
    //       },
    //       {
    //         "name": "air-force",
    //         "value": 1000,
    //         "symbolSize": 30,
    //         "category": 2,
    //       },
    //       {
    //         "name": "air-force",
    //         "value": 10,
    //         "symbolSize": 30,
    //         "category": 2,
    //       },
    //       {
    //         "name": "air-force",
    //         "value": 8,
    //         "symbolSize": 30,
    //         "category": 2,
    //       },
    //       {
    //         "name": "center",
    //         "value": 10,
    //         "symbolSize": 40,
    //         "category": 3,
    //       },
    //       {
    //         "name": "center",
    //         "value": 10,
    //         "symbolSize": 40,
    //         "category": 3,
    //       },
    //       {
    //         "name": "center",
    //         "value": 8,
    //         "symbolSize": 40,
    //         "category": 3,
    //       },
    //     ],
    //     "links": [
    //       {
    //         "source": 0,
    //         "target": 1
    //       },
    //       {
    //         "source": 1,
    //         "target": 2
    //       },
    //       {
    //         "source": 0,
    //         "target": 2
    //       },
    //       {
    //         "source": 3,
    //         "target": 4
    //       },
    //       {
    //         "source": 4,
    //         "target": 5
    //       },
    //       {
    //         "source": 3,
    //         "target": 5
    //       },
    //       {
    //         "source": 6,
    //         "target": 7
    //       },
    //       {
    //         "source": 7,
    //         "target": 8
    //       },
    //       {
    //         "source": 6,
    //         "target": 8
    //       },
    //       {
    //         "source": 9,
    //         "target": 10
    //       },
    //       {
    //         "source": 9,
    //         "target": 11
    //       },
    //       {
    //         "source": 10,
    //         "target": 11
    //       },
    //       {
    //         "source": 0,
    //         "target": 9
    //       },
    //       {
    //         "source": 0,
    //         "target": 10
    //       },
    //       {
    //         "source": 0,
    //         "target": 11
    //       },
    //       {
    //         "source": 1,
    //         "target": 9
    //       },
    //       {
    //         "source": 1,
    //         "target": 10
    //       },
    //       {
    //         "source": 1,
    //         "target": 11
    //       },
    //       {
    //         "source": 2,
    //         "target": 9
    //       },
    //       {
    //         "source": 2,
    //         "target": 10
    //       },
    //       {
    //         "source": 2,
    //         "target": 11
    //       },
    //
    //       {
    //         "source": 3,
    //         "target": 9
    //       },
    //       {
    //         "source": 3,
    //         "target": 10
    //       },
    //       {
    //         "source": 3,
    //         "target": 11
    //       },
    //       {
    //         "source": 4,
    //         "target": 9
    //       },
    //       {
    //         "source": 4,
    //         "target": 10
    //       },
    //       {
    //         "source": 4,
    //         "target": 11
    //       },
    //       {
    //         "source": 5,
    //         "target": 9
    //       },
    //       {
    //         "source": 5,
    //         "target": 10
    //       },
    //       {
    //         "source": 5,
    //         "target": 11
    //       },
    //       {
    //         "source": 6,
    //         "target": 9
    //       },
    //       {
    //         "source": 6,
    //         "target": 10
    //       },
    //       {
    //         "source": 6,
    //         "target": 11
    //       },
    //       {
    //         "source": 7,
    //         "target": 9
    //       },
    //       {
    //         "source": 7,
    //         "target": 10
    //       },
    //       {
    //         "source": 7,
    //         "target": 11
    //       },
    //       {
    //         "source": 8,
    //         "target": 9
    //       },
    //       {
    //         "source": 8,
    //         "target": 10
    //       },
    //       {
    //         "source": 8,
    //         "target": 11
    //       },
    //     ]
    //   };
    //   option = {
    //     legend: {
    //       orient: 'vertical',
    //       x:'left',      //可设定图例在左、右、居中
    //       y:'top',     //可设定图例在上、下、居中
    //       padding:[20,0,0,10],   //可设定图例[距上方距离，距右方距离，距下方距离，距左方距离]
    //       data: ['海军', '陆军', '空军', '指挥中心'], //此处的数据必须和关系网类别中name相对应
    //       textStyle: {
    //         color: 'white',
    //         fontSize: 13, // 文字的字体大小
    //         lineHeight: 20, // 行高
    //       },
    //     },
    //     animation: false,
    //     series: [{
    //       type: 'graph',
    //       layout: 'force',
    //       animation: false,
    //       label: {
    //         normal: {
    //           show: true,
    //           position: 'right'
    //         }
    //       },
    //       draggable: true,
    //       data: webkitDep.nodes.map(function (node, idx) {
    //         node.id = idx;
    //         return node;
    //       }),
    //       categories: webkitDep.categories,
    //       force: {
    //         edgeLength: 100, //连线的长度
    //         repulsion: 700 //子节点之间的间距
    //       },
    //       edges: webkitDep.links
    //     }]
    //   };
    //   myChart.setOption(option);
    //   if (option && typeof option === "object") {
    //     myChart.setOption(option, true);
    //   }
    // }
  },
  mounted() {
    this.myEcharts();
    // this.ec();
  }
}
</script>
<style lang="scss" scoped>
.Echarts {
  padding: 1rem;
  height: 78vh;
  min-width: 300px;
  border-radius: 5px;
}

.chart-wrapper {
  width: 100%;
  background: rgba(0, 0, 0, .3);
  border: 1px solid #255670;
  border-top: none;
  height: calc(100% - 90px);
}
</style>