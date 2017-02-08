package com.kompas.model.kompas.enums;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
public enum ParamType {

    ALLPARAM(-1),
    SHEET_ALLPARAM(-2),
    NURBS_CLAMPED_ALLPARAM(-5),
    NURBS_CLAMPED_SHEET_ALLPARAM(-6),
    VIEW_ALLPARAM(-7);

    private int paramType;

    ParamType(int paramType) {
        this.paramType = paramType;
    }

    public int getNumber() {
        return paramType;
    }

}
