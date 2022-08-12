<template>
  <el-dialog
    :visible="visible"
    title="新增产品"
    width="33%"
    :modal="true"
    :close-on-click-modal="true"
    :show-closed="true"
    @close="doCancel"
  >
    <el-form ref="addForm" :rules="addRules" :model="addForm" label-width="26%">
      <el-form-item label="名称:" prop="name">
        <el-input v-model="addForm.name" />
      </el-form-item>
      <el-form-item label="代发公司:" prop="issuingCompanyId">
        <el-cascader
          v-model="selectPlatformCompanyIdArr"
          style="width: 100%"
          placeholder="选择代发平台和公司"
          clearable
          :options="issuingPlatformTreeList"
          filterable
        />
      </el-form-item>
      <el-form-item label="代发链接:" prop="issuingCompanyId">
        <el-input v-model="addForm.issuingUrl" />
      </el-form-item>
      <el-form-item label="是否可用:" prop="issuingCompanyId">
        <el-radio v-model="addForm.enabled" label="1">可用</el-radio>
        <el-radio v-model="addForm.enabled" label="0">不可用</el-radio>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doCommit()">确定</el-button>
        <el-button @click="resetForm('addForm')">重置</el-button>
        <el-button type="warning" @click="doCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>

import { getIssuingPlatformTree } from '../../../api/issuing-platform'
import { productInsert } from '../../../api/product'

export default {
  name: 'AddProduct',
  data() {
    return {
      visible: false,
      addForm: {
        name: '',
        issuingCompanyId: '',
        issuingUrl: '',
        enabled: '1'
      },
      issuingPlatformTreeList: [],
      selectPlatformCompanyIdArr: [],
      addRules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    load(val) {
      this.visible = val.visible
      getIssuingPlatformTree().then(res => {
        const treeData = res.data
        if (treeData == null || treeData.length == 0) {
          this.$message.warning('未找到代发平台....配置好了再来配置产品哈...')
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
    },
    doCancel() {
      this.$emit('cancel')
    },
    resetForm() {
      this.$refs['addForm'].resetFields()
    },
    doCommit() {
      this.$refs['addForm'].validate((valid) => {
        if (valid) {
          if (this.selectPlatformCompanyIdArr == null || this.selectPlatformCompanyIdArr.length < 2) {
            this.$message.error('请选择代发公司哈..')
            return
          }
          this.addForm.issuingCompanyId = this.selectPlatformCompanyIdArr[1]
          const enabled = this.addForm.enabled
          this.addForm.enabled = enabled == 1
          productInsert(this.addForm).then((res) => {
            if (res.success) {
              this.$message.success('添加成功')
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
