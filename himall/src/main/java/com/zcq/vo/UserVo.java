package com.zcq.vo;

import java.util.List;

/**
 * @Author: zcq
 * @Date: 2019/5/25 20:03
 */
public class UserVo<T> {
    private Integer total;
    private List<T> rows;


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
