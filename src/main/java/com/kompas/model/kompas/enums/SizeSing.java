package com.kompas.model.kompas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by White Stream on 11.02.2017.
 */
@Getter
@AllArgsConstructor
public enum SizeSing {
    NO_SIGN(0, "нет знака"),
    DIAMETER(1, "диаметр"),
    SQUARE(2, "квадрат"),
    RADIUS(3, "радиус"),
    METRIC_THREAD(4, "метрическая резьба"),
    SPHERE(210, "сфера");

    private int index;
    private String name;
}
