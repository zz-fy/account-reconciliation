import request from '@/utils/request'

const URL_PREFIX = '/user'

export function userPageSearch(searchForm) {
  return request({
    url: URL_PREFIX + '/page/search',
    method: 'POST',
    data: searchForm
  })
}

export function userInsert(commitForm) {
  return request({
    url: URL_PREFIX + '/insert',
    method: 'POST',
    data: commitForm
  })
}

export function userDelete(userId) {
  return request({
    url: URL_PREFIX + '/delete/' + userId,
    method: 'DELETE'
  })
}
