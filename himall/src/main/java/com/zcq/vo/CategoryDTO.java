package com.zcq.vo;

import com.zcq.pojo.Category;

import java.util.List;

/**
 * @Author: zcq
 * @Date: 2019/5/23 23:16
 */
public class CategoryDTO  {
    private Category category;

    private List<CategoryDTO> children;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CategoryDTO> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryDTO> children) {
        this.children = children;
    }
}
