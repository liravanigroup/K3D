package com.kompas.domain.kompas.enums.rasterparam;

/**
 * Created by Amsterdam on 29.06.2016.
 */

/**
 * 0 - все страницы,
 * 1 - нечетные страницы,
 * 2 - четные страницы
 */
public enum RangeIndex {

    ALL_PAGES(0),
    ODD_PAGES(1),
    EVEN_PAGES(2);


    private int ramge;

    RangeIndex(int ramge) {
        this.ramge = ramge;
    }

    public int value() {
        return ramge;
    }
}