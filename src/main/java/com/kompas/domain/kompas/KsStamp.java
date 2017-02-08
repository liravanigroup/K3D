package com.kompas.domain.kompas;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.domain.dto.StampDTO;
import com.kompas.domain.kompas.datastructure.KsDynamicArray;
import com.kompas.domain.kompas.enums.KsStampEnum;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.kompas.domain.kompas.enums.KsStampEnum.*;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
public class KsStamp {
    private ActiveXComponent ksStamp;

    public KsStamp(ActiveXComponent stamp) {
        checkNotNull(stamp);
        this.ksStamp = stamp;
    }

    public Variant getReference() {
        return ksStamp.getProperty("reference");
    }

    public long ksClearStamp(KsStampEnum ksStampEnum) {
        return ksStamp.invoke("ksClearStamp", ksStampEnum.getStampFieldNumber()).getInt();
    }

    private long ksOpenStamp() {
        return ksStamp.invoke("ksOpenStamp").getInt();
    }

    private long ksCloseStamp() {
        return ksStamp.invoke("ksCloseStamp").getInt();
    }

    public long getSheetNumb() {
        return ksStamp.invoke("GetSheetNumb").getInt();
    }

    public long ksColumnNumber(KsStampEnum ksStampEnum) {
        return ksStamp.invoke("ksColumnNumber", ksStampEnum.getStampFieldNumber()).getInt();
    }

    /**
     * @param align 0 - по левому краю,
     *              1 - по центру,
     *              2 - по правому краю,
     *              3 - по ширине.
     */
    public void ksSetTextLineAlign(short align) {
        ksStamp.invoke("ksSetTextLineAlign", align);
    }

    /**
     * @param ksStampEnum
     * @param ksDynamicArray - TEXT_LINE_ARR
     * @return
     */
    public long ksSetStampColumnText(KsStampEnum ksStampEnum, ActiveXComponent ksDynamicArray) {
        return ksStamp.invoke("ksSetStampColumnText", new Variant(ksStampEnum.getStampFieldNumber()), new Variant(ksDynamicArray)).getInt();
    }

    /**
     * @param ksStampEnum
     * @return TEXT_LINE_ARR
     */
    public KsDynamicArray ksGetStampColumnText(KsStampEnum ksStampEnum) {
        KsDynamicArray result = null;
        if (ksOpenStamp() == 1) {
            ksColumnNumber(ksStampEnum);
            result = new KsDynamicArray(ksStamp.invokeGetComponent("ksGetStampColumnText", new Variant(ksStampEnum.getStampFieldNumber(), true)));
            ksCloseStamp();
        }
        return result;
    }

    /**
     * @param textItem - ksTextItemParam(31)
     * @return
     */
    private long ksTextLine(ActiveXComponent textItem) {
        return ksStamp.invoke("ksTextLine", new Variant(textItem)).getInt();
    }

    // Заполнение основной надписи
    public void setNewContentInCell(ActiveXComponent ksTextItemParam, KsStampEnum ksStampEnum, String content) {
        if (ksOpenStamp() == 1) {
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
            ksCloseStamp();
        }
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

//        result.setKsStFullFileName(getCellContent(ksStFullFileName, ksTextLineParam, ksTextItemParam));
//        result.setKsStShortFileName(getCellContent(ksStShortFileName, ksTextLineParam, ksTextItemParam));
//        result.setKsStMarkingLine(getCellContent(ksStMarkingLine, ksTextLineParam, ksTextItemParam));
//        result.setKsStDocumentName(getCellContent(ksStDocumentName, ksTextLineParam, ksTextItemParam));
//        result.setKsStDocumentCode(getCellContent(ksStDocumentCode, ksTextLineParam, ksTextItemParam));
//        result.setKsStOKPCode(getCellContent(ksStOKPCode, ksTextLineParam, ksTextItemParam));


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