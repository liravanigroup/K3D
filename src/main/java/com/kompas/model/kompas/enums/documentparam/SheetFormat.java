package com.kompas.model.kompas.enums.documentparam;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Sergej Povzaniuk on 07.01.2017.
 */
@Getter
@AllArgsConstructor
public enum SheetFormat {
    A0(0, "формат А0"),
    A1(1, "формат А1"),
    A2(2, "формат А2"),
    A3(3, "формат А3"),
    A4(4, "формат А4");

    private int index;
    private String name;
}
