package com.zcq.service;

import com.zcq.common.ServerResponse;
import com.zcq.pojo.Category;

import java.util.List;

/**
 * @Author: zcq
 * @Date: 2019/4/24 20:45
 */
public interface ICategoryService {
    ServerResponse addCategory(String categoryName, Integer parentId);

    ServerResponse updateCategoryName(Integer categoryId,String categoryName);

    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);

    List<Category> selectCategoryByParentId();

    List<Category> findTop(Integer parentId);

    Integer count();
}
