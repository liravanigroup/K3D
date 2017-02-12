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
public enum DocType {
    ly_UnknownType(0, "Неизвестный формат"),
    lt_DocSheetStandart(1, "Чертеж стандартного формата"),
    lt_DocSheetUser(2, "Чертеж нестандартного формата"),
    lt_DocFragment(3, "фрагмент"),
    lt_DocSpc(4, "Спецификация"),
    lt_DocPart3D(5, "Деталь"),
    lt_DocAssemble3D(6, "Сборка"),
    lt_DocTxtStandart(7, "Текстовый документ стандартный"),
    lt_DocTxtUser(8, "Текстовый документ нестандартный"),
    lt_DocSpcUser(9, "Спецификация - нестандартный формат");

    private static Map<Integer, DocType> map = new HashMap<>();

    static {
        Stream.of(DocType.values()).forEach(dt -> map.put(dt.getValue(), dt));
    }

    public static DocType valueOf(int docType) {
        return map.get(docType);
    }

    private int value;
    private String name;
}
