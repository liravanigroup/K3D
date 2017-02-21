package com.kompas.model.kompas.enums.documentparam;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by White Stream on 18.02.2017.
 */
@AllArgsConstructor
@Getter
public enum SheetType {
    STANDARD_TYPE(false, "стандартный лист"),
    USER_TYPE(true, "пользовательский формат");

    private static Map<Boolean, SheetType> map = new HashMap<>();

    static {
        Stream.of(SheetType.values()).forEach(st -> map.put(st.isUserType(), st));
    }

    public static SheetType valueOf(boolean sheetType) {
        return map.get(sheetType);
    }

    private boolean isUserType;
    private String name;
}
