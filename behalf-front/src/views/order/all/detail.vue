<template>
  <el-dialog
    :visible="visible"
    title="订单详情"
    width="55%"
    :modal="true"
    :close-on-click-modal="true"
    :show-closed="true"
    @close="doCancel"
  >
    <el-form ref="detailInfo" :model="detailInfo" :inline="true" label-width="110px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="订单时间:">
            {{ detailInfo.orderTime }}
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="订单状态:">
            <el-tag effect="plain" :type="renderOrderStatusTagMap(detailInfo.status)">
              {{ orderStatusMap[detailInfo.status.toString()] }}
            </el-tag>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="代发平台/公司:">
            <span>{{ detailInfo.issuingPlatformName + '/' + detailInfo.issuingCompanyName }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="产品:">
            <span>{{ detailInfo.productName }}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="售出平台:">
            <span>{{ detailInfo.soldPlatformName }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="售出账户:">
            <span>{{ detailInfo.soldAccountName }}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="客户昵称:">
            <span>{{ detailInfo.customerNickname }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户姓名:">
            <span>{{ detailInfo.customerRealName }}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="客户手机号:">
            <span>{{ detailInfo.customerPhone }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户地址:">
            <el-input
              v-model="detailInfo.customerAddress"
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 3}"
              style="width: 108%"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="标价:">
            <span>{{ detailInfo.markPrice }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="售出数量:">
            <span>{{ detailInfo.soldQuantity }}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="成本:">
            <span>{{ detailInfo.costPrice }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="售出价格:">
            <span>{{ detailInfo.soldPrice }}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="text-align: center">
        <el-form-item>
          <el-button type="warning" @click="doCancel">退出</el-button>
        </el-form-item>
      </el-row>

    </el-form>
  </el-dialog>
</template>

<script>
import { getOrderDetail } from '../../../api/order'

export default {
  name: 'OrderDetail',
  data() {
    return {
      visible: false,
      detailInfo: {
        id: '',
        costPrice: '',
        createTime: '',
        customerAddress: '',
        customerNickname: '',
        customerPhone: '',
        customerRealName: '',
        issuingCompanyId: '',
        issuingCompanyName: '',
        issuingPlatformId: '',
        issuingPlatformName: '',
        issuingUrl: '',
        markPrice: '',
        orderCustomerId: '',
        orderTime: '',
        productId: '',
        productName: '',
        soldAccountId: '',
        soldAccountName: '',
        soldPlatformId: '',
        soldPlatformName: '',
        soldPrice: '',
        soldQuantity: '',
        status: '',
        updateTime: ''
      },
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
  methods: {
    doCancel() {
      this.$emit('cancel')
    },
    load(val) {
      this.visible = val.visible
      this.detailInfo.id = val.id
      getOrderDetail(val.id).then(res => {
        const detailInfo = res.data
        if (detailInfo == null) {
          this.$message.warning('未知订单信息...')
          return
        }
        this.detailInfo.costPrice = detailInfo.costPrice
        this.detailInfo.createTime = detailInfo.createTime
        this.detailInfo.customerAddress = detailInfo.customerAddress
        this.detailInfo.customerNickname = detailInfo.customerNickname
        this.detailInfo.customerPhone = detailInfo.customerPhone
        this.detailInfo.customerRealName = detailInfo.customerRealName
        this.detailInfo.id = detailInfo.id
        this.detailInfo.issuingCompanyId = detailInfo.issuingCompanyId
        this.detailInfo.issuingCompanyName = detailInfo.issuingCompanyName
        this.detailInfo.issuingPlatformId = detailInfo.issuingPlatformId
        this.detailInfo.issuingPlatformName = detailInfo.issuingPlatformName
        this.detailInfo.issuingUrl = detailInfo.issuingUrl
        this.detailInfo.orderCustomerId = detailInfo.orderCustomerId
        this.detailInfo.markPrice = detailInfo.markPrice
        this.detailInfo.orderTime = detailInfo.orderTime
        this.detailInfo.productId = detailInfo.productId
        this.detailInfo.productName = detailInfo.productName
        this.detailInfo.soldAccountId = detailInfo.soldAccountId
        this.detailInfo.soldAccountName = detailInfo.soldAccountName
        this.detailInfo.soldPlatformId = detailInfo.soldPlatformId
        this.detailInfo.soldPlatformName = detailInfo.soldPlatformName
        this.detailInfo.soldPrice = detailInfo.soldPrice
        this.detailInfo.soldQuantity = detailInfo.soldQuantity
        this.detailInfo.status = detailInfo.status
        this.detailInfo.updateTime = detailInfo.updateTime
      })
    }
  }
}
</script>

<style scoped>

</style>
