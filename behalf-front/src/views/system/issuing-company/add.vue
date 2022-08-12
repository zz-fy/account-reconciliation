<template>
  <el-dialog
    :visible="visible"
    title="新增代发公司"
    width="33%"
    :modal="true"
    :close-on-click-modal="true"
    :show-closed="true"
    @close="doCancel"
  >
    <el-form ref="addForm" :rules="addRules" :model="addForm" label-width="26%">
      <el-form-item label="代发平台:" prop="issuingPlatformId">
        <el-select v-model="addForm.issuingPlatformId" placeholder="选择代发平台" clearable filterable style="width: 100%">
          <el-option
            v-for="(platform,index) in issuingPlatformList"
            :key="index"
            :label="platform.name"
            :value="platform.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="名称:" prop="name">
        <el-input v-model="addForm.name" />
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

import { issuingCompanyInsert } from '../../../api/issuing-company'
import { getIssuingPlatformList } from '../../../api/issuing-platform'

export default {
  name: 'AddIssuingCompany',
  data() {
    return {
      visible: false,
      issuingPlatformList: [],
      addForm: {
        issuingPlatformId: '',
        name: ''
      },
      addRules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ],
        issuingPlatformId: [
          { required: true, message: '请选择代发公司的平台', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    load(val) {
      this.visible = val.visible
      getIssuingPlatformList().then(res => {
        this.issuingPlatformList = res.data
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
          issuingCompanyInsert(this.addForm).then((res) => {
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
