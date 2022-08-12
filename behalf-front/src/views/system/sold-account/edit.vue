<template>
  <el-dialog
    :visible="visible"
    title="编辑代发账户"
    width="33%"
    :modal="true"
    :close-on-click-modal="true"
    :show-closed="true"
    @close="doCancel"
  >
    <el-form ref="editForm" :rules="editRules" :model="editForm" label-width="26%">
      <el-form-item label="姓名:" prop="name">
        <el-input v-model="editForm.name" />
      </el-form-item>
      <el-form-item label="手机号:" prop="phone">
        <el-input v-model="editForm.phone" />
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

import { soldAccountModify } from '../../../api/sold-account'

export default {
  name: 'EditSoldAccount',
  data() {
    return {
      visible: false,
      editForm: {
        name: '',
        phone: ''
      },
      editRules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    load(val) {
      this.visible = val.visible
      this.editForm.id = val.id
      this.editForm.name = val.name
      this.editForm.phone = val.phone
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
          soldAccountModify(this.editForm).then((res) => {
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
