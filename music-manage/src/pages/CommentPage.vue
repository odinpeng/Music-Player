<template>
<!-- songListPage点击评论后的页面 -->
  <div class="table">
    <div class="crumbs"><i class="el-icon-tickets"></i>评论信息</div>

    <div class="container">
      <div class="handle-box">
        <!-- 批量删除评论 -->
        <el-button type="primary" size="mini" @click="delAll"
          >批量删除</el-button
        >
        <!-- 评论搜索框 -->
        <el-input
          v-model="select_word"
          size="mini"
          placeholder="筛选关键字"
          class="handle-input"
        ></el-input>
      </div>
    </div>
    <!-- 评论列表 -->
    <el-table
      size="mini"
      ref="multipleTable"
      border
      style="width: 100%"
      height="680px"
      :data="tableData"
      @selection-change="handleSelectionChange"
    >
      <!-- 多选框 -->
      <el-table-column type="selection" width="40"></el-table-column>
      <!-- 歌手-歌名 -->
      <el-table-column
        prop="name"
        label="用户名"
        align="center"
      ></el-table-column>
      <!-- 评论内容 -->
      <el-table-column
        prop="content"
        label="评论内容"
        align="center"
      ></el-table-column>
      <!-- 修改歌曲按钮 -->
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 删除歌曲按钮 -->
    <el-dialog title="删除评论" :visible.sync="delVisible" width="300px" center>
      <div align="center">删除不可恢复，是否确定删除？</div>
      <span slot="footer">
        <el-button size="mini" @click="delVisible = false">取消</el-button>
        <el-button size="mini" @click="deleteRow">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>



<script>
// 引入提示信息
import { mixin } from "../mixins/index";
// 引入接口方法
import {getCommentOfSongListId,deleteComment,getUserOfId} from "../api/index";

export default {
  mixins: [mixin],
  props:['id'],
  data() {
    return {
      delVisible: false, //删除弹窗默认是否显示
      tableData: [],
      tempData: [],
      select_word: '', //已经输入的文字
      idx: -1, //当前选择项
      multipleSelection: [], //当前选择的id
    };
  },
  // 监控
  watch: {
    //搜索框里面的内容发生变化的时候，搜索结果table列表的内容跟着它的内容发生变化
    select_word: function () {
      if (this.select_word == "") {
        this.tableData = this.tempData;
      } else {
        this.tableData = [];
        for (let item of this.tempData) {
          if (item.name.includes(this.select_word)) {
            this.tableData.push(item);
          }
        }
      }
    },
  },
  created() {
    this.getData();
  },
  methods: {
    //查询指定歌单的评论列表
    getData(){
        this.tempData = [];
        this.tableData = [];
        getCommentOfSongListId(this.$route.query.id).then(res => {
            for(let item of res){
                this.getUsers(item.userId,item);
            }
        })
    },
    //获取用户名，连同本对象放到tempData和tableData里面
    getUsers(id,item){
        getUserOfId(id)
        .then(res => {
            let o = item;
            o.name = res.username;
            this.tempData.push(o);
            this.tableData.push(o);
        })
        .catch(err => {
            console.log(err);
        });
    },
    //删除一条评论
    deleteRow(){
        deleteComment(this.idx.id)
        .then(res => {
            if(res){
                this.getData();
                this.notify("删除成功","success");
            }else{
                this.notify("删除失败","error");
            }
        })
        .catch(err => {
            console.log(err);
        });
        this.delVisible = false;
    },
    //批量删除已经选择的项
    delAll(){
        for(let item of this.multipleSelection){
            this.handleDelete(item);
            this.deleteRow();
        }
        this.multipleSelection = [];
    },
  }, 
};
</script>




<style scoped>
.handle-box {
  margin-bottom: 20px;
}

.handle-input {
  width: 300px;
  display: inline-block;
}
</style>

