package com.zcq.service;

import com.zcq.common.ServerResponse;

/**
 * @Author: zcq
 * @Date: 2019/4/26 17:55
 */
public interface IOrderService {
    ServerResponse pay(Long orderNo, Integer userId, String path);
}
