package com.kompas.model.kompas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by White Stream on 12.02.2017.
 */
@Getter
@AllArgsConstructor
public enum SettingsType {
    DIMENTION_OPTIONS(1, "Настройки размера"),
    SNAP_OPTIONS(1, "Настройки привязок"),
    ARROWFILLING_OPTIONS(2, "Настройки зачернения стрелок"),
    SHEET_OPTIONS(3, "Настройка параметров оформления листа документа для новых документов"),
    SHEET_OPTIONS_EX(4, "Настройка параметров листа документа"),
    LENGTHUNITS_OPTIONS(5, "Настройки единиц измерений"),
    SNAP_OPTIONS_EX(6, "Настройки привязок документа"),
    VIEWCOLOR_OPTIONS(7, "Настройки цвета фона рабочего поля 2d - документов"),
    TEXTEDIT_VIEWCOLOR_OPTIONS(8, "Настройки цвета фона редактирования текста"),
    MODEL_VIEWCOLOR_OPTIONS(9, "Настройки цвета фона для моделей"),
    OVERLAP_OBJECT_OPTIONS(10, "Настройки перекрывающихся объектов"),
    DIMENTION_OPTIONS_EX(11, "Настройки размера");

    private int index;
    private String name;
}
