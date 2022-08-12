import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/home',
    children: [
      {
        path: '/home',
        component: () => import('@/views/home-page/index'),
        name: 'HomePage',
        meta: { title: '主页' }
      }
    ]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [

  {
    path: '/lay/order',
    name: 'LayOrder',
    component: Layout,
    redirect: '/lay/order/all',
    alwaysShow: true,
    meta: {
      title: '订单管理'
    },
    children: [
      {
        path: '/lay/order/all',
        name: 'LayOrderAll',
        component: () => import('@/views/order/all/index'),
        meta: {
          title: '所有订单'
        }
      }
    ]
  },
  {
    path: '/lay/system',
    name: 'LaySystem',
    component: Layout,
    redirect: '/lay/system/user',
    alwaysShow: true,
    meta: {
      title: '系统管理'
    },
    children: [
      {
        path: '/lay/system/user',
        name: 'LaySystemUser',
        component: () => import('@/views/system/user/index'),
        meta: {
          title: '用户管理'
        }
      },
      {
        path: '/lay/system/sold-account',
        name: 'LaySystemSoldAccount',
        component: () => import('@/views/system/sold-account/index'),
        meta: {
          title: '代发账户'
        }
      },
      {
        path: '/lay/system/sold-platform',
        name: 'LaySystemSoldPlatform',
        component: () => import('@/views/system/sold-platform/index'),
        meta: {
          title: '售出平台'
        }
      },
      {
        path: '/lay/system/issuing-platform',
        name: 'LaySystemIssuingPlatform',
        component: () => import('@/views/system/issuing-platform/index'),
        meta: {
          title: '代发平台'
        }
      },
      {
        path: '/lay/system/issuing-company',
        name: 'LaySystemIssuingCompany',
        component: () => import('@/views/system/issuing-company/index'),
        meta: {
          title: '代发公司'
        }
      },
      {
        path: '/lay/system/product',
        name: 'LaySystemProduct',
        component: () => import('@/views/system/product/index'),
        meta: {
          title: '代发产品'
        }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
