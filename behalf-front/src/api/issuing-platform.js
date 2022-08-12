import request from '@/utils/request'

const URL_PREFIX = '/issuing-platform'

export function issuingPlatformPageSearch(searchForm) {
  return request({
    url: URL_PREFIX + '/page/search',
    method: 'POST',
    data: searchForm
  })
}

export function issuingPlatformInsert(commitForm) {
  return request({
    url: URL_PREFIX + '/insert',
    method: 'POST',
    data: commitForm
  })
}

export function issuingPlatformModify(commitForm) {
  return request({
    url: URL_PREFIX + '/modify',
    method: 'PUT',
    data: commitForm
  })
}

export function issuingPlatformDelete(issuingPlatformId) {
  return request({
    url: URL_PREFIX + '/delete/' + issuingPlatformId,
    method: 'DELETE'
  })
}

export function getIssuingPlatformList() {
  return request({
    url: URL_PREFIX + '/list',
    method: 'GET'
  })
}

export function getIssuingPlatformTree() {
  return request({
    url: URL_PREFIX + '/tree',
    method: 'GET'
  })
}
