package com.kompas.model.kompas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Sergej Povzanyuk on 09.08.2016.
 */
@AllArgsConstructor
@Getter
public enum DynamicArrayType {
    CHAR_STR_ARR(1),
    POINT_ARR(2),
    CURVE_PATTERN_ARR(2),
    TEXT_LINE_ARR(3),
    TEXT_ITEM_ARR(4),
    ATTR_COLUMN_ARR(5),;

    private int arrayType;
}
