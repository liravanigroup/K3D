package com.kompas.model.kompas;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.datastructure.KsDynamicArray;
import com.kompas.model.kompas.enums.KsStampEnum;
import lombok.AllArgsConstructor;

import static com.kompas.model.kompas.enums.KsStampEnum.*;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
@AllArgsConstructor
public class KsStamp {
    private ActiveXComponent ksStamp;

    public boolean ksClearStamp() {
        return ksStamp.invoke("ksClearStamp", ALL_STAMP.getIndex()).getInt() == 1;
    }

    public boolean ksCleanStampCell(KsStampEnum ksStampEnum) {
        return ksStamp.invoke("ksClearStamp", ksStampEnum.getIndex()).getInt() == 1;
    }

    public boolean ksSetStampCell(ActiveXComponent ksTextItemParam, KsStampEnum cell, String value) {
        return setNewContentInCell(ksTextItemParam, cell, value);
    }

    public boolean ksOpenStamp() {
        return ksStamp.invoke("ksOpenStamp").getInt() == 1;
    }

    public boolean ksCloseStamp() {
        return ksStamp.invoke("ksCloseStamp").getInt() != 0;
    }


    public Variant getReference() {
        return ksStamp.getProperty("reference");
    }

    public long getSheetNumb() {
        return ksStamp.invoke("GetSheetNumb").getInt();
    }



    private boolean ksColumnNumber(KsStampEnum ksStampEnum) {
        return ksStamp.invoke("ksColumnNumber", ksStampEnum.getIndex()).getInt() != 0;
    }

    public boolean ksSetStampColumnText(KsStampEnum ksStampEnum, ActiveXComponent ksDynamicArray) {
        return ksStamp.invoke("ksSetStampColumnText", new Variant(ksStampEnum.getIndex()), new Variant(ksDynamicArray)).getInt() != 0;
    }

    private long ksTextLine(ActiveXComponent textItem) {
        return ksStamp.invoke("ksTextLine", new Variant(textItem)).getInt();
    }

    private boolean setNewContentInCell(ActiveXComponent ksTextItemParam, KsStampEnum ksStampEnum, String content) {
        ksColumnNumber(ksStampEnum);
        if (ksTextItemParam != null) {
            ksTextItemParam.invoke("Init");
            ActiveXComponent ksTextItemFont = ksTextItemParam.invokeGetComponent("GetItemFont");
            if (ksTextItemFont != null) {
                ksTextItemFont.invoke("SetBitVectorValue", new Variant(0x1000), new Variant(true));
                ksTextItemParam.setProperty("s", content);
                ksTextLine(ksTextItemParam);
            }
        }
        return false;
    }

    public String getCellContent(KsStampEnum ksStampEnum, ActiveXComponent ksTextLineParam, ActiveXComponent ksTextItemParam) {
        ksTextLineParam.invoke("Init");
        KsDynamicArray ksDynamicArray = ksGetStampColumnText(ksStampEnum);
        long lineCount = ksDynamicArray.ksGetArrayCount();
        StringBuilder sb = new StringBuilder();

        for (int index = 0; index < lineCount; index++) {
            ksTextItemParam.invoke("Init");
            ksDynamicArray.ksGetArrayItem(index, ksTextLineParam);
            KsDynamicArray dynamicArray = new KsDynamicArray(ksTextLineParam.invokeGetComponent("GetTextItemArr"));
            if (dynamicArray.ksGetArrayCount() != 0) {
                dynamicArray.ksGetArrayItem(0, ksTextItemParam);
                sb.append(ksTextItemParam.invoke("s"));
                if (index < lineCount - 1)
                    sb.append(" ");
            }
            dynamicArray.ksDeleteArray();
        }
        ksDynamicArray.ksDeleteArray();
        return sb.toString();
    }

    public KsDynamicArray ksGetStampColumnText(KsStampEnum ksStampEnum) {
        KsDynamicArray result = null;
        if (ksOpenStamp()) {
            ksColumnNumber(ksStampEnum);
            result = new KsDynamicArray(ksStamp.invokeGetComponent("ksGetStampColumnText", new Variant(ksStampEnum.getIndex(), true)));
            ksCloseStamp();
        }
        return result;
    }


    public StampDTO getStampDTO(ActiveXComponent ksTextLineParam, ActiveXComponent ksTextItemParam) {
        StampDTO result = new StampDTO();

        result.setKsStPartNumber(getCellContent(ksStPartNumber, ksTextLineParam, ksTextItemParam));
        result.setKsStDescription(getCellContent(ksStDescription, ksTextLineParam, ksTextItemParam));
        result.setKsStMaterial(getCellContent(ksStMaterial, ksTextLineParam, ksTextItemParam));
        result.setKsStMass(getCellContent(ksStMass, ksTextLineParam, ksTextItemParam));
        result.setKsStScale(getCellContent(ksStScale, ksTextLineParam, ksTextItemParam));
        result.setKsStSheetNumber(getCellContent(ksStSheetNumber, ksTextLineParam, ksTextItemParam));
        result.setKsStNumberOfSheets(getCellContent(ksStNumberOfSheets, ksTextLineParam, ksTextItemParam));
        result.setKsStCompany(getCellContent(ksStCompany, ksTextLineParam, ksTextItemParam));
        result.setKsStTypeOfWork(getCellContent(ksStTypeOfWork, ksTextLineParam, ksTextItemParam));
        result.setKsStDocumentLetter1(getCellContent(ksStDocumentLetter1, ksTextLineParam, ksTextItemParam));
        result.setKsStDocumentLetter2(getCellContent(ksStDocumentLetter2, ksTextLineParam, ksTextItemParam));
        result.setKsStDocumentLetter3(getCellContent(ksStDocumentLetter3, ksTextLineParam, ksTextItemParam));

//        result.setKsStFullFileName(getCellContent(ksStFullFileName, ko_TextLineParam, ko_TextItemParam));
//        result.setKsStShortFileName(getCellContent(ksStShortFileName, ko_TextLineParam, ko_TextItemParam));
//        result.setKsStMarkingLine(getCellContent(ksStMarkingLine, ko_TextLineParam, ko_TextItemParam));
//        result.setKsStDocumentName(getCellContent(ksStDocumentName, ko_TextLineParam, ko_TextItemParam));
//        result.setKsStDocumentCode(getCellContent(ksStDocumentCode, ko_TextLineParam, ko_TextItemParam));
//        result.setKsStOKPCode(getCellContent(ksStOKPCode, ko_TextLineParam, ko_TextItemParam));


        result.setKsStAuthor(getCellContent(ksStAuthor, ksTextLineParam, ksTextItemParam));
        result.setKsStCheckedBy(getCellContent(ksStCheckedBy, ksTextLineParam, ksTextItemParam));
        result.setKsStMfgApprovedBy(getCellContent(ksStMfgApprovedBy, ksTextLineParam, ksTextItemParam));
        result.setKsStDesigner(getCellContent(ksStDesigner, ksTextLineParam, ksTextItemParam));
        result.setKsStRateOfInspection(getCellContent(ksStRateOfInspection, ksTextLineParam, ksTextItemParam));
        result.setKsStApprovedBy(getCellContent(ksStApprovedBy, ksTextLineParam, ksTextItemParam));
        result.setKsStEndDesignDate(getCellContent(ksStEndDesignDate, ksTextLineParam, ksTextItemParam));
        result.setKsStCheckedDate(getCellContent(ksStCheckedDate, ksTextLineParam, ksTextItemParam));
        result.setKsStMfgApprovedDate(getCellContent(ksStMfgApprovedDate, ksTextLineParam, ksTextItemParam));
        result.setKsStExecutionDate(getCellContent(ksStExecutionDate, ksTextLineParam, ksTextItemParam));
        result.setKsStRateOfInspectionDate(getCellContent(ksStRateOfInspectionDate, ksTextLineParam, ksTextItemParam));
        result.setKsStApprovedDate(getCellContent(ksStApprovedDate, ksTextLineParam, ksTextItemParam));

        return result;
    }
}