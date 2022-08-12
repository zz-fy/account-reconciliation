<template>
  <div class="app-container">
    <!--新增&搜索栏-->
    <el-row>
      <el-col :span="6">
        <el-button type="primary" @click="doAdd()">新增</el-button>
      </el-col>
      <el-col :span="18" style="text-align: right">
        <el-form :inline="true" :model="searchForm">
          <el-form-item label="名称:">
            <el-input v-model="searchForm.name" placeholder="名称" clearable />
          </el-form-item>
          <el-button icon="el-icon-search" type="primary" @click="doQuery(1)">查询</el-button>
        </el-form>
      </el-col>
    </el-row>

    <!--数据表格-->
    <el-table
      border
      style="width: 100%"
      :data="pageData"
    >
      <el-table-column prop="name" align="center" label="名称" show-overflow-tooltip show-tooltip-when-overflow />
      <el-table-column prop="createTime" align="center" label="创建时间" show-overflow-tooltip show-tooltip-when-overflow />
      <el-table-column prop="updateTime" align="center" label="更新时间" show-overflow-tooltip show-tooltip-when-overflow />
      <el-table-column fixed="right" label="操作" min-width="80%" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="doEdit(scope.row)">编辑</el-button>
          <el-button type="text" size="small" @click="doRemove(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <el-pagination
      background
      style="margin-top: 1%"
      :current-page="currentPage"
      :page-sizes="[10, 15, 20, 50, 100]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalCount"
      @size-change="(val)=>{
        this.pageSize = val;
        this.flushPage(currentPage);
      }"
      @current-change="(val)=>{
        this.currentPage = val;
        this.flushPage(currentPage)
      }"
    />

    <!--新增编辑组件-->
    <add-issuing-platform
      v-if="addDialogVisible"
      ref="addDialog"
      @ok="doQuery(1)"
      @cancel="addDialogVisible = false"
    />
    <edit-issuing-platform
      v-if="editDialogVisible"
      ref="editDialog"
      @ok="doQuery(currentPage)"
      @cancel="editDialogVisible = false"
    />

  </div>
</template>

<script>
import AddIssuingPlatform from './add'
import EditIssuingPlatform from './edit'
import { issuingPlatformDelete, issuingPlatformPageSearch } from '../../../api/issuing-platform'

export default {
  name: 'IssuingPlatformIndex',
  components: { EditIssuingPlatform, AddIssuingPlatform },
  data() {
    return {
      totalCount: 0,
      currentPage: 1,
      pageSize: 10,
      addDialogVisible: false,
      editDialogVisible: false,
      searchForm: {
        name: ''
      },
      pageData: []
    }
  },
  created() {
    this.flushPage(1)
  },
  methods: {
    /**
       * 刷新页面
       * @param pageIndex
       */
    flushPage(pageIndex) {
      const queryParams = this.searchForm
      queryParams.current = pageIndex
      queryParams.size = this.pageSize
      issuingPlatformPageSearch(queryParams).then(res => {
        this.totalCount = res.data.recordCount
        this.pageData = res.data.list
      })
    },
    /**
       * 查询按钮
       */
    doQuery(currentPage) {
      this.addDialogVisible = false
      this.editDialogVisible = false
      this.flushPage(currentPage != null ? currentPage : 1)
    },
    /**
       * 新增按钮
       */
    doAdd() {
      this.addDialogVisible = true
      setTimeout(() => {
        this.$refs.addDialog.load({
          visible: true
        })
      }, 0)
    },
    /**
       * 编辑
       */
    doEdit(row) {
      this.editDialogVisible = true
      setTimeout(() => {
        this.$refs.editDialog.load({
          visible: true,
          id: row.id,
          name: row.name,
          phone: row.phone
        })
      }, 0)
    },
    /**
       * 删除
       */
    doRemove(row) {
      this.$confirm(
        '确认要删除该条记录吗?',
        '是否继续',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
      ).then(() => {
        issuingPlatformDelete(row.id).then(res => {
          if (res.success) {
            this.$message.success('删除成功')
            this.doQuery(1)
          }
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
