package com.kompas.model.kompas.enums.sizes;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by White Stream on 18.02.2017.
 */
@AllArgsConstructor
@Getter
public enum SizeOption {
    _AUTONOMINAL(0x1, "автоматическое определение номинального значения размера"),
    _RECTTEXT(0x2, "текст в рамке"),
    _PREFIX(0x4, "есть текст до номинала"),
    _NOMINALOFF(0x8, "нет номинала"),
    _TOLERANCE(0x10, "проставлять квалитет"),
    _DEVIATION(0x20, "проставлять отклонения"),
    _UNIT(0x40, "единица измерения"),
    _SUFFIX(0x80, "есть текст после номинала"),
    _DEVIATION_INFORM(0x100, "при включенном флаге _DEVIATION отклонения есть в массиве текстов (даже если простановка отклонений - не ручная)."),
    _UNDER_LINE(0x200, "размер с подчеркиванием"),
    _BRACKETS(0x400, "размер в скобках"),
    _SQUARE_BRACKETS(0x800, "размер в квадратных скобках, используется вместе с _BRACKETS");

    private int value;
    private String name;
}
