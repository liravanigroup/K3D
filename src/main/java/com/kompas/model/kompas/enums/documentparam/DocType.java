package com.kompas.model.kompas.enums.documentparam;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by White Stream on 12.02.2017.
 */
@Getter
@AllArgsConstructor
public enum DocType {
    lt_DocSheetStandart(1, "чертеж стандартного формата"),
    lt_DocSheetUser(2, "чертеж нестандартного формата"),
    lt_DocFragment(3, "фрагмент"),
    lt_DocSpc(4, "спецификация"),
    lt_DocPart3D(5, "деталь"),
    lt_DocAssemble3D(6, "сборка"),
    lt_DocTxtStandart(7, "текстовый документ стандартный"),
    lt_DocTxtUser(8, "текстовый документ нестандартный"),
    lt_DocSpcUser(9, "спецификация - нестандартный формат");

    private int value;
    private String name;
}
