package com.kompas.model.kompas.enums.rasterparam;

/**
 * Created by Amsterdam on 29.06.2016.
 */
public enum ExtScale {
    ONE_TO_ONE(1),
    ONE_TO_DOUBLE(2),;

    private int scale;

    ExtScale(int scale) {
        this.scale = scale;
    }

    public int value() {
        return scale;
    }
}
