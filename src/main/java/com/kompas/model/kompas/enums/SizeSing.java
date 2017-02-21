package com.kompas.model.kompas.enums;

import com.kompas.model.kompas.enums.documentparam.SheetFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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

    private static Map<Integer, SizeSing> map = new HashMap<>();

    static {
        Stream.of(SizeSing.values()).forEach(ss -> map.put(ss.getIndex(), ss));
    }

    public static SizeSing valueOf(int sizeSing) {
        return map.get(sizeSing);
    }

    private Integer index;
    private String name;
}
