package com.zcq.service;

import com.zcq.common.ServerResponse;
import com.zcq.vo.CartVo;

/**
 * @Author: zcq
 * @Date: 2019/4/25 16:55
 */
public interface ICartService {
    ServerResponse<CartVo> list (Integer userId);

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId,Integer productId,Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId,String productIds);

    ServerResponse<CartVo> selectOrUnSelect (Integer userId,Integer productId,Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
