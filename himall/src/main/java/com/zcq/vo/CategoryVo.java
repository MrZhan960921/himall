package com.zcq.vo;

import com.zcq.pojo.Category;

/**
 * @Author: zcq
 * @Date: 2019/5/25 23:00
 */
public class CategoryVo extends Category {
    private String state="closed";

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
