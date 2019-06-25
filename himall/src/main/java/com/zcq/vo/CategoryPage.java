package com.zcq.vo;

import java.util.List;

/**
 * @Author: zcq
 * @Date: 2019/5/25 23:25
 */
public class CategoryPage {
    private Integer total;
    private List<CategoryVo> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<CategoryVo> getRows() {
        return rows;
    }

    public void setRows(List<CategoryVo> rows) {
        this.rows = rows;
    }
}
