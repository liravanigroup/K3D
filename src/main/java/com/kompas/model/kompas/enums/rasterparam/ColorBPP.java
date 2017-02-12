package com.kompas.model.kompas.enums.rasterparam;

/**
 * Created by Amsterdam on 29.06.2016.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * орределения бит на пиксел для конвертации в растровый формат
 */
@Getter
@AllArgsConstructor
public enum ColorBPP {
    BPP_COLOR_01(1, "Черный"),
    BPP_COLOR_02(2, "4 цвета"),
    BPP_COLOR_04(4, "16 цветов"),
    BPP_COLOR_08(8, "256 цветов"),
    BPP_COLOR_16(16, "16 разрядов"),
    PP_COLOR_24(24, "24 разряда"),
    BPP_COLOR_32(32, "32 разряда");

    private int index;
    private String name;
}