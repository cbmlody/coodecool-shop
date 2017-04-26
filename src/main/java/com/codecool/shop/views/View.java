package com.codecool.shop.views;

import java.util.List;

public interface View<E> {
    void displayOne(E element );
    void displayAll(List<E> list);
}
