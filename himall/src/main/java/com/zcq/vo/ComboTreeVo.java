package com.zcq.vo;

import java.util.List;

/**
 * @Author: zcq
 * @Date: 2019/5/26 12:15
 */
public class ComboTreeVo {
    private Integer id;
    private String text;
    private String state;
    private List<ComboTreeVo> children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ComboTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<ComboTreeVo> children) {
        this.children = children;
    }
}
