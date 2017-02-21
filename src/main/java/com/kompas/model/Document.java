package com.kompas.model;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
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
 * Created by White Stream on 20.02.2017.
 */
public interface Document {

    boolean openDocument(Path document, VisibleMode visibleMode);

    boolean closeDocument();

    long getReference();

    boolean saveDocument(Path document);

    Path getPath(ActiveXComponent ksDocumentParam);

    Document getActiveDocument(Kompas3D kompas);

    void ksGetObjParam(Variant textObjRef, ActiveXComponent ksTextParam, ParamType allparam);

    DocumentMetaData getFileData(ActiveXComponent ksDocumentParam);

    void ksGetDocOptions(SettingsType sheetOptionsEx, ActiveXComponent ksSheetOptions);

    boolean ksOpenTable(Variant tableObjectRef);

    boolean ksGetTableColumnText(Variant variant, ActiveXComponent ksTextParam);

    boolean ksEndObj();

    boolean ksOpenStamp();

    boolean ksCloseStamp();

    boolean ksCleanStamp();

    boolean ksCleanStampCell(KsStampEnum cell);

    boolean ksSetStampCell(ActiveXComponent ksTextItemParam, KsStampEnum cell, String value);

    StampDTO getStampData(ActiveXComponent ksTextLineParam, ActiveXComponent ksTextItemParam);

    void ksGetObjGabaritRect(long reference, ActiveXComponent ko_rectParam);

    ActiveXComponent rasterFormatParam(RasterParamDTO params);

    boolean saveAsToRasterFormat(String s, ActiveXComponent ksRasterFormatParam);

}
