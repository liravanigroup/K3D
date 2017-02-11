package com.kompas.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class StampDTO {
    private static final String EMPTY_STRING_VALUE = "";

    private String ksStPartNumber = EMPTY_STRING_VALUE;
    private String ksStDescription = EMPTY_STRING_VALUE;
    private String ksStMaterial = EMPTY_STRING_VALUE;
    private String ksStMass = EMPTY_STRING_VALUE;
    private String ksStScale = EMPTY_STRING_VALUE;
    private String ksStSheetNumber = EMPTY_STRING_VALUE;
    private String ksStNumberOfSheets = EMPTY_STRING_VALUE;
    private String ksStCompany = EMPTY_STRING_VALUE;
    private String ksStTypeOfWork = EMPTY_STRING_VALUE;
    private String ksStDocumentLetter1 = EMPTY_STRING_VALUE;
    private String ksStDocumentLetter2 = EMPTY_STRING_VALUE;
    private String ksStDocumentLetter3 = EMPTY_STRING_VALUE;
    private String ksStFullFileName = EMPTY_STRING_VALUE;
    private String ksStShortFileName = EMPTY_STRING_VALUE;
    private String ksStMarkingLine = EMPTY_STRING_VALUE;
    private String ksStDocumentName = EMPTY_STRING_VALUE;
    private String ksStDocumentCode = EMPTY_STRING_VALUE;
    private String ksStOKPCode = EMPTY_STRING_VALUE;
    private String ksStAuthor = EMPTY_STRING_VALUE;
    private String ksStCheckedBy = EMPTY_STRING_VALUE;
    private String ksStMfgApprovedBy = EMPTY_STRING_VALUE;
    private String ksStDesigner = EMPTY_STRING_VALUE;
    private String ksStRateOfInspection = EMPTY_STRING_VALUE;
    private String ksStApprovedBy = EMPTY_STRING_VALUE;
    private String ksStEndDesignDate = EMPTY_STRING_VALUE;
    private String ksStCheckedDate = EMPTY_STRING_VALUE;
    private String ksStMfgApprovedDate = EMPTY_STRING_VALUE;
    private String ksStExecutionDate = EMPTY_STRING_VALUE;
    private String ksStRateOfInspectionDate = EMPTY_STRING_VALUE;
    private String ksStApprovedDate = EMPTY_STRING_VALUE;
}