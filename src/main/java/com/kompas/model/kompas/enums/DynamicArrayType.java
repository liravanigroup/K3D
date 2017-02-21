package com.kompas.model.kompas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Sergej Povzanyuk on 09.08.2016.
 */
@AllArgsConstructor
@Getter
public enum DynamicArrayType {
    CHAR_STR_ARR(1, "ksChar255"),
    POINT_ARR(2, "ksMathPointParam"),
    CURVE_PATTERN_ARR(2, "ksCurvePattern"),
    TEXT_LINE_ARR(3, "ksTextLineParam"),
    TEXT_ITEM_ARR(4, "ksTextItemParam"),
    ATTR_COLUMN_ARR(5, "ksColumnInfoParam"),
    USER_ARR(6, "динамический пользовательский массив"),
    POLYLINE_ARR(7, "ksDynamicArray"),
    RECT_ARR(8, "ksRectParam"),
    LIBRARY_STYLE_ARR(9, "ksLibraryStyleParam"),
    VARIABLE_ARR(10, "ksVariable"),
    CURVE_PATTERN_ARR_EX(11, "ksCurvePatternEx"),
    LIBRARY_ATTR_TYPE_ARR(12, "ksLibraryAttrTypeParam"),
    NURBS_POINT_ARR(13, "ksNurbsPointParam"),
    DOUBLE_ARR(14, "ksDoubleValue"),
    CONSTRAINT_ARR(15, "ksConstraintParam"),
    CORNER_ARR(16, "ksCornerParam"),
    DOC_SPCOBJ_ARR(17, "ksDocAttachedSpcParam"),
    SPCSUBSECTION_ARR(18, "ksSpcSubSectionParam"),
    SPCTUNINGSEC_ARR(19, "ksSpcTuningSectionParam"),
    SPCSTYLECOLUMN_ARR(20, "ksSpcStyleColumnParam"),
    SPCSTYLESEC_ARR(21, "ksSpcStyleSectionParam"),
    QUALITYITEM_ARR(22, "ksQualityItemParam"),
    LTVARIANT_ARR(23, "ksLtVariant"),
    TOLERANCEBRANCH_ARR(24, "ksToleranceBranch"),
    HATCHLINE_ARR(25, "ksHatchLineParam"),
    TREENODEPARAM_ARR(26, "ksTreeNodeParam");

    private int index;
    private String name;
}
