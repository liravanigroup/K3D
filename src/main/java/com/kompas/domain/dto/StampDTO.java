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

    public void setKsStPartNumber(String ksStPartNumber) {
        this.ksStPartNumber = new SimpleStringProperty(ksStPartNumber);
    }

    public void setKsStDescription(String ksStDescription) {
        this.ksStDescription = new SimpleStringProperty(ksStDescription);
    }

    public void setKsStMaterial(String ksStMaterial) {
        this.ksStMaterial = new SimpleStringProperty(ksStMaterial);
    }

    public void setKsStMass(String ksStMass) {
        this.ksStMass = new SimpleStringProperty(ksStMass);
    }

    public void setKsStScale(String ksStScale) {
        this.ksStScale = new SimpleStringProperty(ksStScale);
    }

    public void setKsStSheetNumber(String ksStSheetNumber) {
        this.ksStSheetNumber = new SimpleStringProperty(ksStSheetNumber);
    }

    public void setKsStNumberOfSheets(String ksStNumberOfSheets) {
        this.ksStNumberOfSheets= new SimpleStringProperty(ksStNumberOfSheets);
    }

    public void setKsStCompany(String ksStCompany) {
        this.ksStCompany = new SimpleStringProperty(ksStCompany);
    }

    public void setKsStTypeOfWork(String ksStTypeOfWork) {
        this.ksStTypeOfWork = new SimpleStringProperty(ksStTypeOfWork);
    }

    public void setKsStDocumentLetter1(String ksStDocumentLetter1) {
        this.ksStDocumentLetter1 = new SimpleStringProperty(ksStDocumentLetter1);
    }

    public void setKsStDocumentLetter2(String ksStDocumentLetter2) {
        this.ksStDocumentLetter2 = new SimpleStringProperty(ksStDocumentLetter2);
    }

    public void setKsStDocumentLetter3(String ksStDocumentLetter3) {
        this.ksStDocumentLetter3 = new SimpleStringProperty(ksStDocumentLetter3);
    }

    public void setKsStFullFileName(String ksStFullFileName) {
        this.ksStFullFileName = new SimpleStringProperty(ksStFullFileName);
    }

    public void setKsStShortFileName(String ksStShortFileName) {
        this.ksStShortFileName = new SimpleStringProperty(ksStShortFileName);
    }

    public void setKsStMarkingLine(String ksStMarkingLine) {
        this.ksStMarkingLine = new SimpleStringProperty(ksStMarkingLine);
    }

    public void setKsStDocumentName(String ksStDocumentName) {
        this.ksStDocumentName = new SimpleStringProperty(ksStDocumentName);
    }

    public void setKsStDocumentCode(String ksStDocumentCode) {
        this.ksStDocumentCode = new SimpleStringProperty(ksStDocumentCode);
    }

    public void setKsStOKPCode(String ksStOKPCode) {
        this.ksStOKPCode = new SimpleStringProperty(ksStOKPCode);
    }

    public void setKsStAuthor(String ksStAuthor) {
        this.ksStAuthor = new SimpleStringProperty(ksStAuthor);
    }

    public void setKsStCheckedBy(String ksStCheckedBy) {
        this.ksStCheckedBy = new SimpleStringProperty(ksStCheckedBy);
    }

    public void setKsStMfgApprovedBy(String ksStMfgApprovedBy) {
        this.ksStMfgApprovedBy = new SimpleStringProperty(ksStMfgApprovedBy);
    }

    public void setKsStDesigner(String ksStDesigner) {
        this.ksStDesigner = new SimpleStringProperty(ksStDesigner);
    }

    public void setKsStRateOfInspection(String ksStRateOfInspection) {
        this.ksStRateOfInspection = new SimpleStringProperty(ksStRateOfInspection);
    }

    public void setKsStApprovedBy(String ksStApprovedBy) {
        this.ksStApprovedBy = new SimpleStringProperty(ksStApprovedBy);
    }

    public void setKsStEndDesignDate(String ksStEndDesignDate) {
        this.ksStEndDesignDate = new SimpleStringProperty(ksStEndDesignDate);
    }

    public void setKsStCheckedDate(String ksStCheckedDate) {
        this.ksStCheckedDate = new SimpleStringProperty(ksStCheckedDate);
    }

    public void setKsStMfgApprovedDate(String ksStMfgApprovedDate) {
        this.ksStMfgApprovedDate = new SimpleStringProperty(ksStMfgApprovedDate);
    }

    public void setKsStExecutionDate(String ksStExecutionDate) {
        this.ksStExecutionDate = new SimpleStringProperty(ksStExecutionDate);
    }

    public void setKsStRateOfInspectionDate(String ksStRateOfInspectionDate) {
        this.ksStRateOfInspectionDate = new SimpleStringProperty(ksStRateOfInspectionDate);
    }

    public void setKsStApprovedDate(String ksStApprovedDate) {
        this.ksStApprovedDate = new SimpleStringProperty(ksStApprovedDate);
    }

    @Override
    public String toString() {
        return "StampDTO{" +
                "ksStPartNumber=" + ksStPartNumber + "\n"+
                ", ksStDescription=" + ksStDescription + "\n"+
                ", ksStMaterial=" + ksStMaterial + "\n"+
                ", ksStMass=" + ksStMass + "\n"+
                ", ksStScale=" + ksStScale + "\n"+
                ", ksStSheetNumber=" + ksStSheetNumber + "\n"+
                ", ksStNumberOfSheets=" + ksStNumberOfSheets + "\n"+
                ", ksStCompany=" + ksStCompany + "\n"+
                ", ksStTypeOfWork=" + ksStTypeOfWork + "\n"+
                ", ksStDocumentLetter1=" + ksStDocumentLetter1 + "\n"+
                ", ksStDocumentLetter2=" + ksStDocumentLetter2 + "\n"+
                ", ksStDocumentLetter3=" + ksStDocumentLetter3 + "\n"+
                ", ksStFullFileName=" + ksStFullFileName + "\n"+
                ", ksStShortFileName=" + ksStShortFileName + "\n"+
                ", ksStMarkingLine=" + ksStMarkingLine + "\n"+
                ", ksStDocumentName=" + ksStDocumentName + "\n"+
                ", ksStDocumentCode=" + ksStDocumentCode + "\n"+
                ", ksStOKPCode=" + ksStOKPCode + "\n"+
                ", ksStAuthor=" + ksStAuthor + "\n"+
                ", ksStCheckedBy=" + ksStCheckedBy + "\n"+
                ", ksStMfgApprovedBy=" + ksStMfgApprovedBy + "\n"+
                ", ksStDesigner=" + ksStDesigner + "\n"+
                ", ksStRateOfInspection=" + ksStRateOfInspection + "\n"+
                ", ksStApprovedBy=" + ksStApprovedBy + "\n"+
                ", ksStEndDesignDate=" + ksStEndDesignDate + "\n"+
                ", ksStCheckedDate=" + ksStCheckedDate + "\n"+
                ", ksStMfgApprovedDate=" + ksStMfgApprovedDate + "\n"+
                ", ksStExecutionDate=" + ksStExecutionDate + "\n"+
                ", ksStRateOfInspectionDate=" + ksStRateOfInspectionDate + "\n"+
                ", ksStApprovedDate=" + ksStApprovedDate + "\n"+
                '}';
    }
}