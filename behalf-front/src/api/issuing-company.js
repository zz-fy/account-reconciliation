import request from '@/utils/request'

const URL_PREFIX = '/issuing-company'

export function issuingCompanyPageSearch(searchForm) {
  return request({
    url: URL_PREFIX + '/page/search',
    method: 'POST',
    data: searchForm
  })
}

export function issuingCompanyInsert(commitForm) {
  return request({
    url: URL_PREFIX + '/insert',
    method: 'POST',
    data: commitForm
  })
}

export function issuingCompanyModify(commitForm) {
  return request({
    url: URL_PREFIX + '/modify',
    method: 'PUT',
    data: commitForm
  })
}

export function issuingCompanyDelete(issuingCompanyId) {
  return request({
    url: URL_PREFIX + '/delete/' + issuingCompanyId,
    method: 'DELETE'
  })
}

export function getIssuingCompanyList(commitForm) {
  return request({
    url: URL_PREFIX + '/list',
    method: 'GET'
  })
}
