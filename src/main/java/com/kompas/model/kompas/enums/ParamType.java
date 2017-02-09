package com.kompas.model.kompas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
@AllArgsConstructor
@Getter
public enum ParamType {
    ALLPARAM(-1),
    SHEET_ALLPARAM(-2),
    NURBS_CLAMPED_ALLPARAM(-5),
    NURBS_CLAMPED_SHEET_ALLPARAM(-6),
    VIEW_ALLPARAM(-7);

    private int paramType;
}
