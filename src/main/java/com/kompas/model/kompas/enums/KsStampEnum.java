package com.kompas.model.kompas.enums;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */

public enum KsStampEnum {

    ALL_STAMP(0),
    ksStPartNumber(1),
    ksStDescription(2),
    ksStMaterial(3),
    ksStMass(5),
    ksStScale(6),
    ksStSheetNumber(7),
    ksStNumberOfSheets(8),
    ksStCompany(9),
    ksStTypeOfWork(10),
    ksStDocumentLetter1(40),
    ksStDocumentLetter2(41),
    ksStDocumentLetter3(42),
    ksStFullFileName(43),
    ksStShortFileName(44),
    ksStMarkingLine(45),
    ksStDocumentName(51),
    ksStDocumentCode(52),
    ksStOKPCode(53),
    ksStAuthor(110),
    ksStCheckedBy(111),
    ksStMfgApprovedBy(112),
    ksStDesigner(113),
    ksStRateOfInspection(114),
    ksStApprovedBy(115),
    ksStEndDesignDate(130),
    ksStCheckedDate(131),
    ksStMfgApprovedDate(132),
    ksStExecutionDate(133),
    ksStRateOfInspectionDate(134),
    ksStApprovedDate(135);

    private int stampFieldNumber;

    KsStampEnum(int StampFieldNumber) {
        stampFieldNumber = StampFieldNumber;
    }

    public int getStampFieldNumber() {
        return stampFieldNumber;
    }

}
