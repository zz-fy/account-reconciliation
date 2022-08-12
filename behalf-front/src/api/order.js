import request from '@/utils/request'

const URL_PREFIX = '/order'

export function orderPageSearch(searchForm) {
  return request({
    url: URL_PREFIX + '/page/search',
    method: 'POST',
    data: searchForm
  })
}

export function orderInsert(commitForm) {
  return request({
    url: URL_PREFIX + '/insert',
    method: 'POST',
    data: commitForm
  })
}

export function orderModify(commitForm) {
  return request({
    url: URL_PREFIX + '/modify',
    method: 'PUT',
    data: commitForm
  })
}

export function orderDelete(productId) {
  return request({
    url: URL_PREFIX + '/delete/' + productId,
    method: 'DELETE'
  })
}

export function orderCheckExist(productId, customerPhone) {
  return request({
    url: URL_PREFIX + '/exist-check',
    method: 'GET',
    params: { productId, customerPhone }
  })
}

export function getOrderDetail(orderId) {
  return request({
    url: URL_PREFIX + '/detail/' + orderId,
    method: 'GET'
  })
}

