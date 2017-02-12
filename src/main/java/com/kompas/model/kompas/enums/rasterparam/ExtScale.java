package com.kompas.model.kompas.enums.rasterparam;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Amsterdam on 29.06.2016.
 */
@Getter
@AllArgsConstructor
public enum ExtScale {
    ONE_TO_ONE(1),
    ONE_TO_DOUBLE(2),;

    private int value;
}
