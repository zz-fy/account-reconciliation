<template>
  <div class="app-container">
    <!--新增&搜索栏-->
    <el-row>
      <el-form ref="searchForm" :inline="true" :model="searchForm">
        <el-form-item label="提交时间:">
          <el-date-picker
            v-model="selectOrderTime"
            size="small"
            type="datetimerange"
            :picker-options="pickerOptions"
            format="yyyy-MM-dd HH:mm"
            value-format="yyyy-MM-dd HH:mm"
            range-separator="~"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            align="right"
          />
        </el-form-item>
        <el-form-item label="客户昵称:" prop="customerNickname">
          <el-input v-model="searchForm.customerNickname" size="small" placeholder="客户昵称" clearable />
        </el-form-item>
        <el-form-item label="姓名:" prop="customerRealName">
          <el-input v-model="searchForm.customerRealName" size="small" placeholder="客户姓名" clearable />
        </el-form-item>
        <el-form-item label="手机号:" prop="customerPhone">
          <el-input v-model="searchForm.customerPhone" size="small" placeholder="客户手机号" />
        </el-form-item>
        <el-form-item label="订单状态:" prop="status">
          <el-select v-model="searchForm.status" size="small" clearable filterable @change="flushPage(1)">
            <el-option value="" label="全部" />
            <el-option
              v-for="(orderStatus,index) in orderStatusMap"
              :key="index"
              :value="index"
              :label="orderStatusMap[index.toString()]"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="售出平台:" prop="soldPlatformId">
          <el-select v-model="searchForm.soldPlatformId" size="small" clearable filterable @change="flushPage(1)">
            <el-option value="" label="全部" />
            <el-option
              v-for="(platform,index) in soldPlatformList"
              :key="index"
              :label="platform.name"
              :value="platform.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="售出账户:" prop="soldAccountId">
          <el-select v-model="searchForm.soldAccountId" size="small" clearable filterable @change="flushPage(1)">
            <el-option value="" label="全部" />
            <el-option
              v-for="(account,index) in soldAccountList"
              :key="index"
              :label="account.name"
              :value="account.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="代发平台:" prop="issuingPlatformId">
          <el-select v-model="searchForm.issuingPlatformId" size="small" clearable filterable @change="flushPage(1)">
            <el-option value="" label="全部" />
            <el-option
              v-for="(platform,index) in issuingPlatformList"
              :key="index"
              :label="platform.name"
              :value="platform.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="代发公司:" prop="issuingCompanyId">
          <el-select v-model="searchForm.issuingCompanyId" size="small" clearable filterable @change="flushPage(1)">
            <el-option value="" label="全部" />
            <el-option
              v-for="(company,index) in curIssuingPlatformCompanyList"
              :key="index"
              :label="company.name"
              :value="company.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="产品:" prop="productId">
          <el-select v-model="searchForm.productId" size="small" clearable filterable @change="flushPage(1)">
            <el-option value="" label="全部" />
            <el-option
              v-for="(product,index) in curIssuingCompanyProductList"
              :key="index"
              :label="product.name"
              :value="product.id"
            />
          </el-select>
        </el-form-item>
        <el-button type="primary" @click="doQuery(1)">查询</el-button>
        <el-button @click="clearSearchForm()">清空</el-button>
        <el-button type="success" @click="doAdd()">添加订单</el-button>

      </el-form>
    </el-row>

    <!--数据表格-->
    <el-table
      border
      style="width: 100%"
      :data="pageData"
    >
      <el-table-column prop="orderTime" align="center" label="下单时间" min-width="150" show-overflow-tooltip show-tooltip-when-overflow />
      <el-table-column align="center" label="订单状态" show-overflow-tooltip show-tooltip-when-overflow>
        <template slot-scope="{row}">
          <el-tag effect="plain" :type="renderOrderStatusTagMap(row.status)">{{ orderStatusMap[row.status.toString()] }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="productName" align="center" label="产品" min-width="120" show-overflow-tooltip show-tooltip-when-overflow />
      <el-table-column
        prop="customerNickname"
        align="center"
        label="客户昵称"
        show-overflow-tooltip
        show-tooltip-when-overflow
      />
      <el-table-column
        prop="customerRealName"
        align="center"
        label="客户姓名"
        show-overflow-tooltip
        show-tooltip-when-overflow
      />
      <el-table-column
        prop="customerPhone"
        align="center"
        label="客户电话"
        show-overflow-tooltip
        min-width="120"
        show-tooltip-when-overflow
      />
      <el-table-column
        align="center"
        label="代发平台/公司"
        show-overflow-tooltip
        show-tooltip-when-overflow
        min-width="150"
      >
        <template slot-scope="{row}">
          <span>{{ row.issuingPlatformName + '/' + row.issuingCompanyName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="soldPlatformName"
        align="center"
        label="售出平台"
        show-overflow-tooltip
        show-tooltip-when-overflow
      />
      <el-table-column
        prop="soldAccountName"
        align="center"
        label="售出账户"
        show-overflow-tooltip
        show-tooltip-when-overflow
      />
      <el-table-column
        prop="markPrice"
        align="center"
        label="标价"
        show-overflow-tooltip
        show-tooltip-when-overflow
      />
      <el-table-column
        prop="costPrice"
        align="center"
        label="成本"
        show-overflow-tooltip
        show-tooltip-when-overflow
      />
      <el-table-column
        prop="soldPrice"
        align="center"
        label="售出价格"
        show-overflow-tooltip
        show-tooltip-when-overflow
      />
      <el-table-column
        prop="soldQuantity"
        align="center"
        label="数量"
        show-overflow-tooltip
        show-tooltip-when-overflow
      />
      <el-table-column prop="createTime" align="center" label="创建时间" show-overflow-tooltip show-tooltip-when-overflow />
      <el-table-column fixed="right" label="操作" min-width="125" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="doShowDetail(scope.row)">详情</el-button>
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

    <!--新增订单组件-->
    <add-order
      v-if="addDialogVisible"
      ref="addDialog"
      @ok="doQuery(1)"
      @cancel="addDialogVisible = false"
    />
    <edit-order
      v-if="editDialogVisible"
      ref="editDialog"
      @ok="doQuery(currentPage)"
      @cancel="editDialogVisible = false"
    />

    <order-detail
      v-if="detailDialogVisible"
      ref="detailDialog"
      @cancel="detailDialogVisible = false"
    />

  </div>
</template>

<script>
import { getProductList, productDelete } from '../../../api/product'
import { getIssuingPlatformList } from '../../../api/issuing-platform'
import { getSoldAccountList } from '../../../api/sold-account'
import { getSoldPlatformList } from '../../../api/sold-platform'
import { orderDelete, orderPageSearch } from '../../../api/order'
import { getIssuingCompanyList } from '../../../api/issuing-company'
import AddOrder from './add'
import EditOrder from './edit'
import OrderDetail from './detail'

export default {
  name: 'ProductIndex',
  components: { OrderDetail, EditOrder, AddOrder },
  data() {
    return {
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            picker.$emit('pick', [start, end])
          }
        }]
      },
      totalCount: 0,
      currentPage: 1,
      pageSize: 10,
      addDialogVisible: false,
      editDialogVisible: false,
      detailDialogVisible: false,
      searchForm: {
        orderStartTime: '',
        orderEndTime: '',
        productId: '',
        customerNickname: '',
        customerRealName: '',
        customerPhone: '',
        status: '',
        soldAccountId: '',
        soldPlatformId: '',
        issuingPlatformId: '',
        issuingCompanyId: ''
      },
      selectOrderTime: [],
      selectPlatformCompanyIdArr: [],
      soldAccountList: [],
      soldPlatformList: [],
      issuingPlatformList: [],
      issuingCompanyList: [],
      productList: [],
      pageData: [],
      orderStatusMap: {
        '0': '待发货',
        '1': '已发货',
        '2': '已签收',
        '3': '交易成功',
        '4': '退款处理'
      }
    }
  },
  computed: {
    curIssuingPlatformCompanyList() {
      const platformId = this.searchForm.issuingPlatformId
      if (platformId == null || platformId == '') return this.issuingCompanyList
      return this.issuingCompanyList.filter(item => item.issuingPlatformId = platformId)
    },
    curIssuingCompanyProductList() {
      const platformId = this.searchForm.issuingPlatformId
      const companyId = this.searchForm.issuingCompanyId
      const empPlatformId = platformId == null || platformId == ''
      const empCompanyId = companyId == null || companyId == ''
      if (empPlatformId && empCompanyId) return this.productList
      if (!empCompanyId) return this.productList.filter(item => item.issuingCompanyId == companyId)
      if (!empPlatformId) {
        const companyIds = this.issuingCompanyList.filter(item => item.issuingPlatformId == platformId).map(item => item.id)
        if (companyIds.length == 0) return this.productList
        return this.productList.filter(item => companyIds.includes(item.issuingCompanyId))
      }
      return this.productList
    },
    renderOrderStatusTagMap() {
      return function(orderStatus) {
        if (orderStatus == 0) {
          return 'info'
        }
        if (orderStatus == 1) {
          return 'waring'
        }
        if (orderStatus == 2) {
          return 'primary'
        }
        if (orderStatus == 3) {
          return 'success'
        }
        if (orderStatus == 4) {
          return 'danger'
        }
      }
    }
  },
  created() {
    this.flushPage(1)
    getSoldAccountList().then(res => this.soldAccountList = res.data)
    getSoldPlatformList().then(res => this.soldPlatformList = res.data)
    getIssuingPlatformList().then(res => this.issuingPlatformList = res.data)
    getIssuingCompanyList().then(res => this.issuingCompanyList = res.data)
    getProductList().then(res => this.productList = res.data)
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
      if (this.selectPlatformCompanyIdArr.length == 1) {
        queryParams.issuingPlatformId = this.selectPlatformCompanyIdArr[0]
      }
      if (this.selectPlatformCompanyIdArr.length == 2) {
        queryParams.issuingCompanyId = this.selectPlatformCompanyIdArr[1]
      }
      if (this.selectOrderTime != null && this.selectOrderTime.length > 0) {
        this.searchForm.orderStartTime = this.selectOrderTime[0]
        this.searchForm.orderEndTime = this.selectOrderTime[0]
      }
      orderPageSearch(queryParams).then(res => {
        this.totalCount = res.data.recordCount
        this.pageData = res.data.list
      })
    },
    /**
       * 清空查询
       */
    clearSearchForm() {
      this.$refs['searchForm'].resetFields()
      this.selectOrderTime = []
      this.flushPage(1)
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
       * 详情
       */
    doShowDetail(row) {
      this.detailDialogVisible = true
      setTimeout(() => {
        this.$refs.detailDialog.load({
          visible: true,
          id: row.id
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
          orderTime: row.orderTime,
          productId: row.productId,
          soldAccountId: row.soldAccountId,
          soldPlatformId: row.soldPlatformId,
          markPrice: row.markPrice,
          soldPrice: row.soldPrice,
          costPrice: row.costPrice,
          soldQuantity: row.soldQuantity,
          status: row.status,
          orderCustomerId: row.orderCustomerId,
          customerNickname: row.customerNickname,
          customerRealName: row.customerRealName,
          customerPhone: row.customerPhone,
          customerAddress: row.customerAddress,
          issuingPlatformId: row.issuingPlatformId,
          issuingCompanyId: row.issuingCompanyId
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
        orderDelete(row.id).then(res => {
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
