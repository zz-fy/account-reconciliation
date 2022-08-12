import request from '@/utils/request'

const URL_PREFIX = '/product'

export function productPageSearch(searchForm) {
  return request({
    url: URL_PREFIX + '/page/search',
    method: 'POST',
    data: searchForm
  })
}

export function productInsert(commitForm) {
  return request({
    url: URL_PREFIX + '/insert',
    method: 'POST',
    data: commitForm
  })
}

export function productModify(commitForm) {
  return request({
    url: URL_PREFIX + '/modify',
    method: 'PUT',
    data: commitForm
  })
}

export function productDelete(productId) {
  return request({
    url: URL_PREFIX + '/delete/' + productId,
    method: 'DELETE'
  })
}

export function getProductList() {
  return request({
    url: URL_PREFIX + '/list',
    method: 'GET'
  })
}
