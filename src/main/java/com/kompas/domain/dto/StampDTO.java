package com.kompas.domain.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StampDTO {

    private static final StringProperty EMPTY_STRING_VALUE = new SimpleStringProperty("");

    private StringProperty ksStPartNumber = EMPTY_STRING_VALUE;
    private StringProperty ksStDescription = EMPTY_STRING_VALUE;
    private StringProperty ksStMaterial = EMPTY_STRING_VALUE;
    private StringProperty ksStMass = EMPTY_STRING_VALUE;
    private StringProperty ksStScale = EMPTY_STRING_VALUE;
    private StringProperty ksStSheetNumber = EMPTY_STRING_VALUE;
    private StringProperty ksStNumberOfSheets = EMPTY_STRING_VALUE;
    private StringProperty ksStCompany = EMPTY_STRING_VALUE;
    private StringProperty ksStTypeOfWork = EMPTY_STRING_VALUE;
    private StringProperty ksStDocumentLetter1 = EMPTY_STRING_VALUE;
    private StringProperty ksStDocumentLetter2 = EMPTY_STRING_VALUE;
    private StringProperty ksStDocumentLetter3 = EMPTY_STRING_VALUE;
    private StringProperty ksStFullFileName = EMPTY_STRING_VALUE;
    private StringProperty ksStShortFileName = EMPTY_STRING_VALUE;
    private StringProperty ksStMarkingLine = EMPTY_STRING_VALUE;
    private StringProperty ksStDocumentName = EMPTY_STRING_VALUE;
    private StringProperty ksStDocumentCode = EMPTY_STRING_VALUE;
    private StringProperty ksStOKPCode = EMPTY_STRING_VALUE;
    private StringProperty ksStAuthor = EMPTY_STRING_VALUE;
    private StringProperty ksStCheckedBy = EMPTY_STRING_VALUE;
    private StringProperty ksStMfgApprovedBy = EMPTY_STRING_VALUE;
    private StringProperty ksStDesigner = EMPTY_STRING_VALUE;
    private StringProperty ksStRateOfInspection = EMPTY_STRING_VALUE;
    private StringProperty ksStApprovedBy = EMPTY_STRING_VALUE;
    private StringProperty ksStEndDesignDate = EMPTY_STRING_VALUE;
    private StringProperty ksStCheckedDate = EMPTY_STRING_VALUE;
    private StringProperty ksStMfgApprovedDate = EMPTY_STRING_VALUE;
    private StringProperty ksStExecutionDate = EMPTY_STRING_VALUE;
    private StringProperty ksStRateOfInspectionDate = EMPTY_STRING_VALUE;
    private StringProperty ksStApprovedDate = EMPTY_STRING_VALUE;
}