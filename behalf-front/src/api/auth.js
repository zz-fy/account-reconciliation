import request from '@/utils/request'

const URL_PREFIX = '/auth'

export function login(data) {
  return request({
    url: URL_PREFIX + '/login',
    method: 'POST',
    data
  })
}

export function getInfo() {
  return request({
    url: URL_PREFIX + '/info',
    method: 'GET'
  })
}

export function logout() {
  return request({
    url: URL_PREFIX + '/logout',
    method: 'GET'
  })
}

export function modifyPwd(commitForm) {
  return request({
    url: URL_PREFIX + '/pwd/modify',
    method: 'POST',
    data: commitForm
  })
}
