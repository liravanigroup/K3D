package com.kompas.model.kompas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
@AllArgsConstructor
@Getter
public enum ParamType {
    ALLPARAM(-1, "все параметры объекта"),
    SHEET_ALLPARAM(-2, "все параметры объекта в СК листа"),
    NURBS_CLAMPED_ALLPARAM(-5, "параметры NURBS; преобразовать узловой вектор в зажатый"),
    NURBS_CLAMPED_SHEET_ALLPARAM(-6, "параметры NURBS в СК листа; преобразовать узловой вектор в зажатый"),
    VIEW_ALLPARAM(-7, "все параметры объекта в СК вида"),
    ANGLE_ARC_PARAM(0, "параметры дуги по углам (для дуги и эллиптической дуги)"),
    POINT_ARC_PARAM(1, "параметры дуги по точкам (для дуги и эллиптической дуги)"),
    VIEW_LAYER_STATE(1, "состояние слоя, вида"),
    DOCUMENT_STATE(1, "состояние документа"),
    ANGLE_ARC_SHEET_PARAM(2, "параметры дуги по углам ( для дуги и эллиптической дуги ) в СК листа"),
    POINT_ARC_SHEET_PARAM(3, "параметры дуги по точкам ( для дуги и эллиптической дуги ) в СК листа"),
    ANGLE_ARC_VIEW_PARAM(4, "параметры дуги по углам ( для дуги и эллиптической дуги ) в СК вида"),
    POINT_ARC_VIEW_PARAM(5, "параметры дуги по точкам ( для дуги и эллиптической дуги ) в СК вида"),
    DOCUMENT_SIZE(0, "размер листа"),
    DIM_TEXT_PARAM(0, "параметры текста для размеров"),
    DIM_SOURSE_PARAM(1, "параметры привязки размера"),
    DIM_DRAW_PARAM(2, "параметры отрисовки размера"),
    DIM_VALUE(3, "значение размера"),
    DIM_PARTS(4, "составляющие части для размеров (структура DimensionPartsParam)"),
    SHEET_DIM_PARTS(5, "составляющие части для размеров (структура DimensionPartsParam в СК листа)"),
    TECHNICAL_DEMAND_PAR(-1, "параметры технических требований"),
    TT_FIRST_STR(1000, "начало отсчета для получения или замены текста техтребований по строкам"),
    CONIC_PARAM(2, "параметры конического сечения (для эллипса и эллиптической дуги)"),
    SPC_TUNING_PARAM(0, "параметры настроек для стиля спецификации"),
    ALL_OBJ(0, "все объекты, которые могут входить в вид, кроме вспомогательных"),
    ALLPARAM_W(-20, "все параметры объекта в СК объекта владельца для структур со строками wchar_t"),
    SHEET_ALLPARAM_W(-21, "тоже что и ALLPARAM но параметры объекта в СК листа для структур со строками wchar_t"),
    VIEW_ALLPARAM_W(-22, "тоже, что и ALLPARAM, но параметры объекта в СК вида для структур со строками wchar_t"),
    ASSOCIATION_VIEW_PARAM_W(-23, "параметры ассоциативного вида для структуры со строками wchar_t"),
    DIM_TEXT_PARAM_W(6, "параметры текста для размеров для структуры со строками wchar_t"),
    DIM_SOURSE_VIEWPARAM(7, "параметры привязки размера в системе координат вида"),
    DIM_DRAW_VIEWPARAM(8, "параметры отрисовки размера в ситеме координат вида"),
    DIM_SOURSE_SHEETPARAM(9, "параметры привязки размера в ситеме координат листа"),
    DIM_DRAW_SHEETPARAM(10, "параметры отрисовки размера в ситеме координат листа");

    private int index;
    private String name;
}
