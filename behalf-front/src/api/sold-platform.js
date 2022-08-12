import request from '@/utils/request'

const URL_PREFIX = '/sold-platform'

export function soldPlatformPageSearch(searchForm) {
  return request({
    url: URL_PREFIX + '/page/search',
    method: 'POST',
    data: searchForm
  })
}

export function soldPlatformInsert(commitForm) {
  return request({
    url: URL_PREFIX + '/insert',
    method: 'POST',
    data: commitForm
  })
}

export function soldPlatformModify(commitForm) {
  return request({
    url: URL_PREFIX + '/modify',
    method: 'PUT',
    data: commitForm
  })
}

export function soldPlatformDelete(soldPlatformId) {
  return request({
    url: URL_PREFIX + '/delete/' + soldPlatformId,
    method: 'DELETE'
  })
}

export function getSoldPlatformList() {
  return request({
    url: URL_PREFIX + '/list',
    method: 'GET'
  })
}
