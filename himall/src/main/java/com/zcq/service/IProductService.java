package com.zcq.service;

import com.github.pagehelper.PageInfo;
import com.zcq.common.ServerResponse;
import com.zcq.pojo.Product;
import com.zcq.vo.ProductDetailVo;

/**
 * @Author: zcq
 * @Date: 2019/4/24 22:58
 */
public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId,Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchProduct(String productName,Integer productId,int pageNum,int pageSize);
}
