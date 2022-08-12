<template>
  <el-dialog
    :visible="visible"
    title="新增用户"
    width="33%"
    :modal="true"
    :close-on-click-modal="true"
    :show-closed="true"
    @close="doCancel"
  >
    <el-form ref="addForm" :rules="addRules" :model="addForm" label-width="26%">
      <el-form-item label="登录名:" prop="username">
        <el-input v-model="addForm.username" />
      </el-form-item>
      <el-form-item label="姓名:" prop="name">
        <el-input v-model="addForm.name" />
      </el-form-item>
      <el-form-item label="手机号:" prop="phone">
        <el-input v-model="addForm.phone" />
      </el-form-item>
      <el-form-item>
        <span style="color: red">新增后登录密码默认为 123456</span>
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
import { userInsert } from '../../../api/user'

export default {
  name: 'AddUser',
  data() {
    return {
      visible: false,
      addForm: {
        username: '',
        name: '',
        phone: ''
      },
      addRules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入登录名', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入电话号码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    load(val) {
      this.visible = val.visible
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
          userInsert(this.addForm).then((res) => {
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
