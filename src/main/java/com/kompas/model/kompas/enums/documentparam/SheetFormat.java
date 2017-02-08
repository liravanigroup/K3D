package com.kompas.model.kompas.enums.documentparam;

import lombok.AllArgsConstructor;

/**
 * Created by Sergej Povzaniuk on 07.01.2017.
 */
@AllArgsConstructor
public enum SheetFormat {
    A0(0, "формат А0"),
    A1(1, "формат А1"),
    A2(2, "формат А2"),
    A3(3, "формат А3"),
    A4(4, "формат А4");

    private int value;
    private String format;

    public int getValue(){
        return value;
    }

    public String getFormat(){
        return format;
    }
}
