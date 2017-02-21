package com.kompas.model.kompas.documents;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.model.Document;
import com.kompas.model.dto.RasterParamDTO;
import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.DocumentMetaData;
import com.kompas.model.kompas.Kompas3D;
import com.kompas.model.kompas.enums.KsStampEnum;
import com.kompas.model.kompas.enums.ParamType;
import com.kompas.model.kompas.enums.SettingsType;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;

import java.nio.file.Path;

/**
 * Created by White Stream on 21.02.2017.
 */
public class DocumentTxt implements Document{
    @Override
    public boolean openDocument(Path document, VisibleMode visibleMode) {
        return false;
    }

    @Override
    public boolean closeDocument() {
        return false;
    }

    @Override
    public long getReference() {
        return 0;
    }

    @Override
    public boolean saveDocument(Path document) {
        return false;
    }

    @Override
    public Path getPath(ActiveXComponent ksDocumentParam) {
        return null;
    }

    @Override
    public Document getActiveDocument(Kompas3D kompas) {
        return null;
    }

    @Override
    public void ksGetObjParam(Variant textObjRef, ActiveXComponent ksTextParam, ParamType allparam) {

    }

    @Override
    public DocumentMetaData getFileData(ActiveXComponent ksDocumentParam) {
        return null;
    }

    @Override
    public void ksGetDocOptions(SettingsType sheetOptionsEx, ActiveXComponent ksSheetOptions) {

    }

    @Override
    public boolean ksOpenTable(Variant tableObjectRef) {
        return false;
    }

    @Override
    public boolean ksGetTableColumnText(Variant variant, ActiveXComponent ksTextParam) {
        return false;
    }

    @Override
    public boolean ksEndObj() {
        return false;
    }

    @Override
    public boolean ksOpenStamp() {
        return false;
    }

    @Override
    public boolean ksCloseStamp() {
        return false;
    }

    @Override
    public boolean ksCleanStamp() {
        return false;
    }

    @Override
    public boolean ksCleanStampCell(KsStampEnum cell) {
        return false;
    }

    @Override
    public boolean ksSetStampCell(ActiveXComponent ksTextItemParam, KsStampEnum cell, String value) {
        return false;
    }

    @Override
    public StampDTO getStampData(ActiveXComponent ksTextLineParam, ActiveXComponent ksTextItemParam) {
        return null;
    }

    @Override
    public void ksGetObjGabaritRect(long reference, ActiveXComponent ko_rectParam) {

    }

    @Override
    public ActiveXComponent rasterFormatParam(RasterParamDTO params) {
        return null;
    }

    @Override
    public boolean saveAsToRasterFormat(String s, ActiveXComponent ksRasterFormatParam) {
        return false;
    }
}
