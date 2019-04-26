package com.zcq.service;

import com.zcq.common.ServerResponse;

import java.util.Map;

/**
 * @Author: zcq
 * @Date: 2019/4/26 17:55
 */
public interface IOrderService {
    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String,String> params);

    ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);
}
