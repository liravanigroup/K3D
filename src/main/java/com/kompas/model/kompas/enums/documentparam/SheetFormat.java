package com.kompas.model.kompas.enums.documentparam;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by Sergej Povzaniuk on 07.01.2017.
 */
@Getter
@AllArgsConstructor
public enum SheetFormat {
    UNKNOWN(-1, "Неизвестный формат"),
    A0(0, "формат А0"),
    A1(1, "формат А1"),
    A2(2, "формат А2"),
    A3(3, "формат А3"),
    A4(4, "формат А4");

    private static Map<Integer, SheetFormat> map = new HashMap<>();

    static {
        Stream.of(SheetFormat.values()).forEach(dt -> map.put(dt.getIndex(), dt));
    }

    public static SheetFormat valueOf(int sheetFormat) {
        return map.get(sheetFormat);
    }

    private int index;
    private String name;
}
