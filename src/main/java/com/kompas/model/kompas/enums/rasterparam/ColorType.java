package com.kompas.model.kompas.enums.rasterparam;

/**
 * Created by Amsterdam on 29.06.2016.
 */

/**
 * BLACKWHITE  0 - черный,
 * COLORVIEW 1 - цвет, установленный для вида,
 * COLORLAYER 2 - цвет, установленный для слоя,
 * COLOROBJECT 3 - цвет, установленный для объекта.
 */
public enum ColorType {
    BLACKWHITE(0),
    COLORVIEW(1),
    COLORLAYER(2),
    COLOROBJECT(3);

    private int colorType;

    ColorType(int colorType) {
        this.colorType = colorType;
    }

    public int value() {
        return colorType;
    }
}