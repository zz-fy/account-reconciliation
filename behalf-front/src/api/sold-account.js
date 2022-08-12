import request from '@/utils/request'

const URL_PREFIX = '/sold-account'

export function soldAccountPageSearch(searchForm) {
  return request({
    url: URL_PREFIX + '/page/search',
    method: 'POST',
    data: searchForm
  })
}

export function soldAccountInsert(commitForm) {
  return request({
    url: URL_PREFIX + '/insert',
    method: 'POST',
    data: commitForm
  })
}

export function soldAccountModify(commitForm) {
  return request({
    url: URL_PREFIX + '/modify',
    method: 'PUT',
    data: commitForm
  })
}

export function soldAccountDelete(soldAccountId) {
  return request({
    url: URL_PREFIX + '/delete/' + soldAccountId,
    method: 'DELETE'
  })
}

export function getSoldAccountList() {
  return request({
    url: URL_PREFIX + '/list',
    method: 'GET'
  })
}
