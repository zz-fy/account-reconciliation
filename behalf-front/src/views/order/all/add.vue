<template>
  <el-dialog
    :visible="visible"
    title="添加订单"
    width="55%"
    :modal="true"
    :close-on-click-modal="true"
    :show-closed="true"
    @close="doCancel"
  >
    <el-form ref="addForm" :rules="addRules" :model="addForm" :inline="true" label-width="110px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="订单时间:" prop="orderTime">
            <el-date-picker
              v-model="addForm.orderTime"
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
            <el-select v-model="addForm.status" filterable clearable placeholder="请选择订单状态">
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
            <el-select v-model="addForm.productId" filterable clearable placeholder="请选择产品">
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
            <el-select v-model="addForm.soldPlatformId" filterable clearable placeholder="选择售出平台">
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
            <el-select v-model="addForm.soldAccountId" filterable clearable placeholder="选择售出账户">
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
            <el-input v-model="addForm.customerNickname" placeholder="客户昵称" style="width: 108%" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户姓名:" prop="customerRealName">
            <el-input v-model="addForm.customerRealName" placeholder="客户姓名" style="width: 108%" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="客户手机号:" prop="customerPhone">
            <el-input v-model="addForm.customerPhone" placeholder="客户手机号" style="width: 108%" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户地址:" prop="customerAddress">
            <el-input v-model="addForm.customerAddress" type="textarea" style="width: 108%" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="标价:" prop="markPrice">
            <el-input
              v-model="addForm.markPrice"
              oninput="if(isNaN(value)) { value = null } if(value.indexOf('.')>0){value=value.slice(0,value.indexOf('.')+3)}"
              placeholder="请输入标价(整数或小数)"
              style="width: 108%"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="售出数量:" prop="soldQuantity">
            <el-input-number v-model="addForm.soldQuantity" :min="1" label="售出数量" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="成本:" prop="costPrice">
            <el-input
              v-model="addForm.costPrice"
              oninput="if(isNaN(value)) { value = null } if(value.indexOf('.')>0){value=value.slice(0,value.indexOf('.')+3)}"
              placeholder="请输入标价(整数或小数)"
              style="width: 108%"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="售出价格:" prop="soldPrice">
            <el-input
              v-model="addForm.soldPrice"
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
          <el-button @click="resetForm('addForm')">重置</el-button>
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
import { orderCheckExist, orderInsert } from '../../../api/order'

export default {
  name: 'AddOrder',
  data() {
    return {
      visible: false,
      addForm: {
        orderTime: '',
        productId: '',
        soldAccountId: '',
        soldPlatformId: '',
        markPrice: '',
        soldPrice: '',
        costPrice: '',
        soldQuantity: 1,
        status: '0',
        customerNickname: '',
        customerRealName: '',
        customerPhone: '',
        customerAddress: ''
      },
      tmp: {
        costPrice: 12,
        createTime: '2022-07-12 02:59:48',
        customerAddress: '12312312',
        customerNickname: 'zfy',
        customerPhone: '13123123',
        customerRealName: 'zf',
        id: 2,
        issuingCompanyId: 3,
        issuingCompanyName: '中嘉东晟科技',
        issuingPlatformId: 2,
        issuingPlatformName: '1688',
        issuingUrl: '',
        markPrice: 1231,
        orderCustomerId: 1,
        orderTime: '2022-07-12 10:57',
        productId: 1,
        productName: '扩展坞(八合一)',
        soldAccountId: 2,
        soldAccountName: '爆Q',
        soldPlatformId: 3,
        soldPlatformName: '闲鱼',
        soldPrice: 1231,
        soldQuantity: 1,
        status: 0,
        updateTime: null
      },
      addRules: {
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
      selectPlatformCompanyIdArr: [],
      issuingPlatformTreeList: [],
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
    'addForm.markPrice'(newValue, oldValue) {
      if (newValue == '') {
        this.addForm.soldPrice = ''
        return
      }
      this.addForm.soldPrice = newValue * this.addForm.soldQuantity
    },
    'addForm.soldQuantity'(newValue, oldValue) {
      if (this.addForm.markPrice == '') {
        this.addForm.soldPrice = ''
        return
      }
      this.addForm.soldPrice = this.addForm.markPrice * newValue
    }
  },
  methods: {
    load(val) {
      this.visible = val.visible
      getIssuingPlatformTree().then(res => {
        const treeData = res.data
        if (treeData == null || treeData.length == 0) {
          this.$message.warning('代发平台都没得....')
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
          this.addForm.soldAccountId = res.data[0].id
        }
      })
      getSoldPlatformList().then(res => {
        this.soldPlatformList = res.data
        if (res.data.length > 0) {
          this.addForm.soldPlatformId = res.data[0].id
        }
      })
    },
    doCancel() {
      this.$emit('cancel')
    },
    resetForm() {
      this.addForm = {
        orderTime: '',
        productId: '',
        soldAccountId: '',
        soldPlatformId: '',
        markPrice: '',
        soldPrice: '',
        costPrice: '',
        soldQuantity: 1,
        status: '0',
        customerNickname: '',
        customerRealName: '',
        customerPhone: '',
        customerAddress: ''
      }
      this.selectPlatformCompanyIdArr = []
      if (this.soldAccountList.length > 0) {
        this.addForm.soldAccountId = this.soldAccountList[0].id
      }
      if (this.soldPlatformList.length > 0) {
        this.addForm.soldPlatformId = this.soldPlatformList[0].id
      }
    },
    doCommit() {
      this.$refs['addForm'].validate((valid) => {
        if (valid) {
          orderCheckExist(this.addForm.productId, this.addForm.customerPhone).then(res => {
            if (res.data.length > 0) {
              this.$confirm('系统中存在用户与这条产品的关系,(如果再次购买的话请忽略提示并继续), 是否继续添加?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.httpOrderInsert()
              })
            } else {
              this.httpOrderInsert()
            }
          })
        } else {
          this.$message.warning('请按照要求完成对应的填写')
          return false
        }
      })
    },
    httpOrderInsert() {
      orderInsert(this.addForm).then((res) => {
        if (res.success) {
          this.$message.success('添加成功')
          this.$emit('ok')
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
