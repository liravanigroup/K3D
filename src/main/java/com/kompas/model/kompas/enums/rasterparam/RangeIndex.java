package com.kompas.model.kompas.enums.rasterparam;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Amsterdam on 29.06.2016.
 */
@Getter
@AllArgsConstructor
public enum RangeIndex {
    ALL_PAGES(0, "все страницы"),
    ODD_PAGES(1, "нечетные страницы"),
    EVEN_PAGES(2, "четные страницы");

    private int value;
    private String name;
}