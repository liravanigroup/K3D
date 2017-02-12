package com.kompas.model.kompas.enums.rasterparam;

/**
 * Created by Amsterdam on 29.06.2016.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ColorType {
    BLACKWHITE(0, "черный"),
    COLORVIEW(1, "цвет, установленный для вида"),
    COLORLAYER(2, "цвет, установленный для слоя"),
    COLOROBJECT(3, "цвет, установленный для объекта");

    private int index;
    private String name;
}