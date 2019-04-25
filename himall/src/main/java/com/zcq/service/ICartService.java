package com.zcq.service;

import com.zcq.common.ServerResponse;
import com.zcq.vo.CartVo;

/**
 * @Author: zcq
 * @Date: 2019/4/25 16:55
 */
public interface ICartService {
    ServerResponse<CartVo> list (Integer userId);
}
