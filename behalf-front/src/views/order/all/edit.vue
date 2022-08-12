<template>
  <el-dialog
    :visible="visible"
    title="编辑订单"
    width="55%"
    :modal="true"
    :close-on-click-modal="true"
    :show-closed="true"
    @close="doCancel"
  >
    <el-form ref="editForm" :rules="editRules" :model="editForm" :inline="true" label-width="110px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="订单时间:" prop="orderTime">
            <el-date-picker
              v-model="editForm.orderTime"
              value-format="yyyy-MM-dd HH:mm"
              format="yyyy-MM-dd HH:mm"
              type="datetime"
              style="width: 94%"
              placeholder="选择订单时间"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="订单状态:" prop="status">
            <el-select v-model="editForm.status" filterable clearable placeholder="请选择订单状态">
              <el-option
                v-for="(orderStatus,index) in orderStatusMap"
                :key="index"
                :value="index"
                :label="orderStatusMap[index.toString()]"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="代发平台/公司:">
            <el-cascader
              v-model="selectPlatformCompanyIdArr"
              placeholder="选择代发平台/公司"
              clearable
              :options="issuingPlatformTreeList"
              filterable
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="产品:" prop="productId">
            <el-select v-model="editForm.productId" filterable clearable placeholder="请选择产品">
              <el-option
                v-for="(product,index) in curIssuingCompanyProductList"
                :key="index"
                :label="product.name"
                :value="product.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="售出平台:" prop="soldPlatformId">
            <el-select v-model="editForm.soldPlatformId" filterable clearable placeholder="选择售出平台">
              <el-option
                v-for="(platform,index) in soldPlatformList"
                :key="index"
                :label="platform.name"
                :value="platform.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="售出账户:" prop="soldAccountId">
            <el-select v-model="editForm.soldAccountId" filterable clearable placeholder="选择售出账户">
              <el-option
                v-for="(account,index) in soldAccountList"
                :key="index"
                :label="account.name"
                :value="account.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="客户昵称:" prop="customerNickname">
            <el-input v-model="editForm.customerNickname" placeholder="客户昵称" style="width: 108%" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户姓名:" prop="customerRealName">
            <el-input v-model="editForm.customerRealName" placeholder="客户姓名" style="width: 108%" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="客户手机号:" prop="customerPhone">
            <el-input v-model="editForm.customerPhone" placeholder="客户手机号" style="width: 108%" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户地址:" prop="customerAddress">
            <el-input v-model="editForm.customerAddress" type="textarea" style="width: 108%" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="标价:" prop="markPrice">
            <el-input
              v-model="editForm.markPrice"
              oninput="if(isNaN(value)) { value = null } if(value.indexOf('.')>0){value=value.slice(0,value.indexOf('.')+3)}"
              placeholder="请输入标价(整数或小数)"
              style="width: 108%"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="售出数量:" prop="soldQuantity">
            <el-input-number v-model="editForm.soldQuantity" :min="1" label="售出数量" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="成本:" prop="costPrice">
            <el-input
              v-model="editForm.costPrice"
              oninput="if(isNaN(value)) { value = null } if(value.indexOf('.')>0){value=value.slice(0,value.indexOf('.')+3)}"
              placeholder="请输入标价(整数或小数)"
              style="width: 108%"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="售出价格:" prop="soldPrice">
            <el-input
              v-model="editForm.soldPrice"
              oninput="if(isNaN(value)) { value = null } if(value.indexOf('.')>0){value=value.slice(0,value.indexOf('.')+3)}"
              placeholder="请输入标价(整数或小数)"
              style="width: 108%"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="text-align: center">
        <el-form-item>
          <el-button type="primary" @click="doCommit()">确定</el-button>
          <el-button @click="resetForm('editForm')">重置</el-button>
          <el-button type="warning" @click="doCancel">取消</el-button>
        </el-form-item>
      </el-row>

    </el-form>
  </el-dialog>
</template>

<script>

import { getIssuingPlatformTree } from '../../../api/issuing-platform'
import { getProductList } from '../../../api/product'
import { getSoldAccountList } from '../../../api/sold-account'
import { getSoldPlatformList } from '../../../api/sold-platform'
import { orderModify } from '../../../api/order'

export default {
  name: 'EditOrder',
  data() {
    return {
      visible: false,
      editForm: {
        id: '',
        orderTime: '',
        productId: '',
        soldAccountId: '',
        soldPlatformId: '',
        markPrice: '',
        soldPrice: '',
        costPrice: '',
        soldQuantity: 1,
        status: '0',
        orderCustomerId: '',
        customerNickname: '',
        customerRealName: '',
        customerPhone: '',
        customerAddress: ''
      },
      issuingPlatformTreeList: [],
      selectPlatformCompanyIdArr: [],
      editRules: {
        orderTime: [
          { required: true, message: '请选择订单时间', trigger: 'blur' }
        ],
        productId: [
          { required: true, message: '请选择产品', trigger: 'change' }
        ],
        soldAccountId: [
          { required: true, message: '请选择售出账户', trigger: 'change' }
        ],
        soldPlatformId: [
          { required: true, message: '请选择售出平台', trigger: 'change' }
        ],
        markPrice: [
          { required: true, message: '请输入标价', trigger: 'blur' }
        ],
        soldPrice: [
          { required: true, message: '请输入卖出价格', trigger: 'blur' }
        ],
        costPrice: [
          { required: true, message: '请输入成本', trigger: 'blur' }
        ],
        customerNickname: [
          { required: true, message: '请输入客户昵称', trigger: 'blur' }
        ],
        customerRealName: [
          { required: true, message: '请输入客户真名', trigger: 'blur' }
        ],
        customerPhone: [
          { required: true, message: '请输入客户电话', trigger: 'blur' }
        ]
      },
      productList: [],
      soldAccountList: [],
      soldPlatformList: [],
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
    curIssuingCompanyProductList() {
      const length = this.selectPlatformCompanyIdArr.length
      if (length == 0) return this.productList
      if (length == 1) {
        const platformId = this.selectPlatformCompanyIdArr[0]
        const companyIds = this.issuingCompanyList.filter(item => item.issuingPlatformId == platformId).map(item => item.id)
        if (companyIds.length == 0) return this.productList
        return this.productList.filter(item => companyIds.includes(item.issuingCompanyId))
      }
      if (length == 2) {
        const companyId = this.selectPlatformCompanyIdArr[1]
        return this.productList.filter(item => item.issuingCompanyId == companyId)
      }
      return this.productList
    }
  },
  watch: {
    'editForm.markPrice'(newValue, oldValue) {
      if (oldValue == '' && newValue != '') return
      if (newValue == '') {
        this.editForm.soldPrice = ''
        return
      }
      this.editForm.soldPrice = newValue * this.editForm.soldQuantity
    },
    'editForm.soldQuantity'(newValue, oldValue) {
      if (this.editForm.markPrice == '') {
        this.editForm.soldPrice = ''
        return
      }
      this.editForm.soldPrice = this.editForm.markPrice * newValue
    }
  },
  methods: {
    load(val) {
      this.visible = val.visible
      this.editForm.id = val.id
      this.editForm.orderTime = val.orderTime
      this.editForm.productId = val.productId
      this.editForm.soldAccountId = val.soldAccountId
      this.editForm.soldPlatformId = val.soldPlatformId
      this.editForm.markPrice = val.markPrice
      this.editForm.soldPrice = val.soldPrice
      this.editForm.costPrice = val.costPrice
      this.editForm.soldQuantity = val.soldQuantity
      this.editForm.status = val.status.toString()
      this.editForm.orderCustomerId = val.orderCustomerId
      this.editForm.customerNickname = val.customerNickname
      this.editForm.customerRealName = val.customerRealName
      this.editForm.customerPhone = val.customerPhone
      this.editForm.customerAddress = val.customerAddress
      this.selectPlatformCompanyIdArr = [val.issuingPlatformId, val.issuingCompanyId]
      getIssuingPlatformTree().then(res => {
        const treeData = res.data
        if (treeData == null || treeData.length == 0) {
          this.$message.warning('代发平台都没得...')
          return
        }
        for (let i = 0; i < treeData.length; i++) {
          const platform = treeData[i]
          const issuingCompanyList = platform.issuingCompanyList
          const children = []
          for (let j = 0; j < issuingCompanyList.length; j++) {
            const company = issuingCompanyList[i]
            children.push({
              value: company.id,
              label: company.name
            })
          }
          this.issuingPlatformTreeList.push({
            value: platform.id,
            label: platform.name,
            children: children
          })
        }
      })
      getProductList().then(res => this.productList = res.data)
      getSoldAccountList().then(res => {
        this.soldAccountList = res.data
        if (res.data.length > 0) {
          this.editForm.soldAccountId = res.data[0].id
        }
      })
      getSoldPlatformList().then(res => {
        this.soldPlatformList = res.data
        if (res.data.length > 0) {
          this.editForm.soldPlatformId = res.data[0].id
        }
      })
    },
    doCancel() {
      this.$emit('cancel')
    },
    resetForm() {
      this.$refs['editForm'].resetFields()
    },
    doCommit() {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          orderModify(this.editForm).then((res) => {
            if (res.success) {
              this.$message.success('编辑成功')
              this.$emit('ok')
            }
          })
        } else {
          this.$message.warning('请按照要求完成对应的填写')
          return false
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
