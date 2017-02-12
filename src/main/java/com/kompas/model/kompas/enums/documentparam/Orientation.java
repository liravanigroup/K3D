package com.kompas.model.kompas.enums.documentparam;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by White Stream on 12.02.2017.
 */
@Getter
@AllArgsConstructor
public enum Orientation {
    VERTICAL(0, false),
    HORIZONTAL(1, true);

    private static Map<Boolean, Orientation> map = new HashMap<>();

    static {
        Stream.of(Orientation.values()).forEach(dt -> map.put(dt.isHorizontal(), dt));
    }

    public static Orientation valueOf(boolean orientation) {
        return map.get(orientation);
    }

    private int index;
    private boolean horizontal;
}
