package com.zcq.service;

import com.zcq.common.ServerResponse;

/**
 * @Author: zcq
 * @Date: 2019/4/24 20:45
 */
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId,String categoryName);
}
