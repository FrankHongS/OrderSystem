package com.qinyuan.ordersystem.vo;

import com.qinyuan.ordersystem.entity.Order;

import java.util.Collections;
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

    public static <T> PageItem<T> createPageItem(List<T> orderList, int page, int size) {
        int count = orderList.size();
        List<T> content;
        if (page >= 0 && page <= (count - 1) / size) {
            int toIndex = Math.min(page * size + size, count);
            content = orderList.subList(page * size, toIndex);
        } else {
            content = Collections.emptyList();
        }

        return new PageItem<>(count, content);
    }
}
