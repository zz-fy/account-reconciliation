<template>
  <el-dialog
    :visible="visible"
    title="编辑代发公司"
    width="33%"
    :modal="true"
    :close-on-click-modal="true"
    :show-closed="true"
    @close="doCancel"
  >
    <el-form ref="editForm" :rules="editRules" :model="editForm" label-width="26%">
      <el-form-item label="代发平台:" prop="issuingPlatformId">
        <el-select v-model="editForm.issuingPlatformId" placeholder="选择代发平台" clearable filterable style="width: 100%">
          <el-option
            v-for="(platform,index) in issuingPlatformList"
            :key="index"
            :label="platform.name"
            :value="platform.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="名称:" prop="name">
        <el-input v-model="editForm.name" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doCommit()">确定</el-button>
        <el-button @click="resetForm('editForm')">重置</el-button>
        <el-button type="warning" @click="doCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>

import { issuingCompanyModify } from '../../../api/issuing-company'
import { getIssuingPlatformList } from '../../../api/issuing-platform'

export default {
  name: 'EditIssuingCompany',
  data() {
    return {
      visible: false,
      issuingPlatformList: [],
      editForm: {
        issuingPlatformId: '',
        name: ''
      },
      editRules: {
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
      this.editForm.id = val.id
      this.editForm.name = val.name
      this.editForm.issuingPlatformId = val.issuingPlatformId
      getIssuingPlatformList().then(res => {
        this.issuingPlatformList = res.data
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
          issuingCompanyModify(this.editForm).then((res) => {
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
