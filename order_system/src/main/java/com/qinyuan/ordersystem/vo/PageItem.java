package com.qinyuan.ordersystem.vo;

import java.util.List;

public class PageItem<T> {

    private int count;

    private List<T> content;

    public PageItem(int count, List<T> content) {
        this.count = count;
        this.content = content;
    }

    public int getCount() {
        return count;
    }

    public List<T> getContent() {
        return content;
    }
}
