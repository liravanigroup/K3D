package com.kompas.model.kompas.enums;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
public enum StructType2DEnum {
    ksTextParam(28),
    ksTextLineParam(29),
    ksTextItemParam(31),
    ksStandartSheet(32),
    ksSheetSize(33),
    ksSheetPar(34),
    ksDocumentParam(35),
    ksColumnInfoParam(36),
    ksRasterParam(96),
    ksSheetOptions(118),
    ksTextDocumentParam(124);

    private int structTypeNum;

    StructType2DEnum(int numberOfStructType) {
        this.structTypeNum = numberOfStructType;
    }

    public int getNumber() {
        return structTypeNum;
    }
}
