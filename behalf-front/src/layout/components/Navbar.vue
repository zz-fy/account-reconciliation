<template>
  <div class="navbar">
    <hamburger
      id="hamburger-container"
      :is-active="sidebar.opened"
      class="hamburger-container"
      @toggleClick="toggleSideBar"
    />

    <breadcrumb id="breadcrumb-container" class="breadcrumb-container" />

    <div class="right-menu">
      <template v-if="device!=='mobile'">
        <error-log class="errLog-container right-menu-item hover-effect" />
        <screenfull id="screenfull" class="right-menu-item hover-effect" />
        <el-tooltip content="全局字体大小" effect="dark" placement="bottom">
          <size-select id="size-select" class="right-menu-item hover-effect" />
        </el-tooltip>

      </template>

      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar+'?imageView2/1/w/80/h/80'" class="user-avatar">
          <span style="vertical-align: top; margin-left: 10px;font-size:15px">{{ this.$store.getters.name }}</span>
        </div>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/">
            <el-dropdown-item>主页</el-dropdown-item>
          </router-link>
          <el-dropdown-item divided @click.native="logout">
            <span style="display:block;">退出</span>
          </el-dropdown-item>
          <el-dropdown-item divided>
            <span style="display:block;" @click="doModifyPwd">修改密码</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>

    <el-dialog
      :visible.sync="modifyPwdDialogVisible"
      title="修改密码"
      width="32%"
      :modal="true"
      :close-on-click-modal="true"
      :show-closed="true"
      @close="()=>{
        this.modifyPwdDialogVisible = false
      }"
    >
      <el-form ref="modifyPwdForm" :rules="modifyPwdRules" :model="modifyPwdForm" label-width="100px">
        <el-form-item label="旧密码:" prop="oldPassword">
          <el-input v-model="modifyPwdForm.oldPassword" show-password />
        </el-form-item>
        <el-form-item label="新密码:" prop="newPassword">
          <el-input v-model="modifyPwdForm.newPassword" show-password />
        </el-form-item>
        <el-form-item label="确认密码:" prop="confirmPassword">
          <el-input v-model="modifyPwdForm.confirmPassword" show-password />
          <span v-if="modifyPwdForm.newPassword != modifyPwdForm.confirmPassword" style="color: red">两次密码不一致</span>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="doCommit">确定</el-button>
          <el-button type="warning" @click="()=>{this.modifyPwdDialogVisible = false}">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import ErrorLog from '@/components/ErrorLog'
import Screenfull from '@/components/Screenfull'
import SizeSelect from '@/components/SizeSelect'
import Search from '@/components/HeaderSearch'
import { modifyPwd } from '../../api/auth'

export default {
  components: {
    Breadcrumb,
    Hamburger,
    ErrorLog,
    Screenfull,
    SizeSelect,
    Search
  },
  data() {
    return {
      modifyPwdDialogVisible: false,
      modifyPwdForm: {
        oldPassword: '',
        newPassword: '',
        confirmPassword: ''
      },
      modifyPwdRules: {
        oldPassword: [
          { required: true, message: '请输入场景名称', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: '请输入新密码', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: '请输入确认密码', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'avatar',
      'device'
    ]),
    passwordSame() {
      return this.modifyPwdForm.newPassword == this.modifyPwdForm.confirmPassword
    }
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login`)
    },
    doModifyPwd() {
      this.modifyPwdDialogVisible = true
    },
    doCommit() {
      this.$refs['modifyPwdForm'].validate((valid) => {
        if (valid) {
          if (this.passwordSame == false) {
            this.$message.error('两次密码不一致')
            return
          }
          const Base64 = require('js-base64').Base64
          const commitForm = {
            oldPassword: Base64.encode(this.modifyPwdForm.oldPassword),
            newPassword: Base64.encode(this.modifyPwdForm.newPassword.trim())
          }
          modifyPwd(commitForm).then((res) => {
            if (res.success) {
              this.modifyPwdDialogVisible = false
              this.modifyPwdForm.oldPassword = ''
              this.modifyPwdForm.newPassword = ''
              this.modifyPwdForm.confirmPassword = ''
              return
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

<style lang="scss" scoped>
  .navbar {
    height: 50px;
    overflow: hidden;
    position: relative;
    background: #fff;
    box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

    .hamburger-container {
      line-height: 46px;
      height: 100%;
      float: left;
      cursor: pointer;
      transition: background .3s;
      -webkit-tap-highlight-color: transparent;

      &:hover {
        background: rgba(0, 0, 0, .025)
      }
    }

    .breadcrumb-container {
      float: left;
    }

    .errLog-container {
      display: inline-block;
      vertical-align: top;
    }

    .right-menu {
      float: right;
      height: 100%;
      line-height: 50px;

      &:focus {
        outline: none;
      }

      .right-menu-item {
        display: inline-block;
        padding: 0 8px;
        height: 100%;
        font-size: 18px;
        color: #5a5e66;
        vertical-align: text-bottom;

        &.hover-effect {
          cursor: pointer;
          transition: background .3s;

          &:hover {
            background: rgba(0, 0, 0, .025)
          }
        }
      }

      .avatar-container {
        margin-right: 30px;

        .avatar-wrapper {
          margin-top: 5px;
          position: relative;

          .user-avatar {
            cursor: pointer;
            width: 40px;
            height: 40px;
            border-radius: 10px;
          }

          .el-icon-caret-bottom {
            cursor: pointer;
            position: absolute;
            right: -20px;
            top: 25px;
            font-size: 12px;
          }
        }
      }
    }
  }
</style>
