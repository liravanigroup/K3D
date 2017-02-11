package com.kompas.model.kompas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
@AllArgsConstructor
@Getter
public enum KsStampEnum {
    ALL_STAMP(0, "Все ячейки"),
    ksStPartNumber(1, "Наименование изделия"),
    ksStDescription(2, "Обозначение документа"),
    ksStMaterial(3, "Обозначение материала"),
    ksStMass(5, "Масса изделия"),
    ksStScale(6, "Масштаб"),
    ksStSheetNumber(7, "Номер листа"),
    ksStNumberOfSheets(8, "Количество листов"),
    ksStCompany(9, "Индекс предприятия"),
    ksStTypeOfWork(10, "Характер работы"),
    ksStDocumentLetter1(40, "Литера документа (графа 1)"),
    ksStDocumentLetter2(41, "Литера документа (графа 2)"),
    ksStDocumentLetter3(42, "Литера документа (графа 3)"),
    ksStFullFileName(43, "Имя файла (полное)"),
    ksStShortFileName(44, "Имя файла (короткое)"),
    ksStMarkingLine(45, "Строка обозначения и дефис"),
    ksStDocumentName(51, "Наименование документа"),
    ksStDocumentCode(52, "Код документа"),
    ksStOKPCode(53, "Код ОКП"),
    ksStAuthor(110, "Фамилия разработавшего"),
    ksStCheckedBy(111, "Фамилия проверившего"),
    ksStMfgApprovedBy(112, "Фамилия тех. контр"),
    ksStDesigner(113, "Фамилия вып. работу"),
    ksStRateOfInspection(114, "Фамилия норм. контр"),
    ksStApprovedBy(115, "Фамилия утверждающего"),
    ksStEndDesignDate(130, "Дата окончания разработки"),
    ksStCheckedDate(131, "Дата проверки"),
    ksStMfgApprovedDate(132, "Дата тех. контр"),
    ksStExecutionDate(133, "Дата выполнения"),
    ksStRateOfInspectionDate(134, "Дата норм. контр"),
    ksStApprovedDate(135, "Дата утверждения");

    private int index;
    private String name;
}
