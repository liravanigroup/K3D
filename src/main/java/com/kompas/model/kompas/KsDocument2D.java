package com.kompas.model.kompas;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.model.kompas.enums.ParamType;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;
import com.kompas.model.kompas.enums.rasterparam.*;

import static com.kompas.model.kompas.enums.ParamType.ALLPARAM;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
public class KsDocument2D {
    private ActiveXComponent ksDocument2D;

    public KsDocument2D(ActiveXComponent ksDocument2D) {
        this.ksDocument2D = ksDocument2D;
    }

    private long getReference() {
        return ksDocument2D.getProperty("reference").getInt();
    }

    public KsStamp GetStamp() {
        return new KsStamp(ksDocument2D.invokeGetComponent("GetStamp"));
    }

    public boolean ksOpenDocument(String path, VisibleMode visibleMode) {
        return ksDocument2D.invoke("ksOpenDocument", path, visibleMode.getIntVal()).getBoolean();
    }

    public boolean ksSaveDocument(String path) {
        return ksDocument2D.invoke("ksSaveDocument", path).getBoolean();
    }

    public boolean ksCloseDocument() {
        return ksDocument2D.invoke("ksCloseDocument").getBoolean();
    }

    /**
     * @param  - reference of ActiveDocument
     *            lt_DocSheetStandart 1 - чертеж стандартного формата
     *            lt_DocSheetUser 2 - чертеж нестандартного формата
     *            lt_DocFragment 3 - фрагмент
     *            lt_DocSpc 4 - спецификация
     *            lt_DocPart3D 5 - деталь
     *            lt_DocAssemble3D 6 - сборка
     *            lt_DocTxtStandart 7 - текстовый документ стандартный
     *            lt_DocTxtUser 8 - текстовый документ нестандартный
     *            lt_DocSpcUser 9 - спецификация - нестандартный формат
     * @return long
     */
    public long ksGetDocumentType() {
        return ksDocument2D.invoke("ksGetDocumentType", new Variant(getReference())).getLong();
    }

    public ActiveXComponent rasterFormatParam(ColorBPP colorBPP, ColorType colorType, ExtResolution extResolution, ExtScale extScale,
                                              Format format, boolean grayScale, boolean multiPageOutput, boolean onlyThinLine, RangeIndex rangeIndex) {
        ActiveXComponent ksRasterFormatParam = ksDocument2D.invokeGetComponent("RasterFormatParam");
        ksRasterFormatParam.invoke("Init");

        ksRasterFormatParam.setProperty("colorBPP", colorBPP.value());
        ksRasterFormatParam.setProperty("colorType", colorType.value());
        ksRasterFormatParam.setProperty("extResolution", extResolution.value());
        ksRasterFormatParam.setProperty("extScale", extScale.value());
        ksRasterFormatParam.setProperty("format", format.value());
        ksRasterFormatParam.setProperty("greyScale", grayScale);
        ksRasterFormatParam.setProperty("multiPageOutput", multiPageOutput);
        ksRasterFormatParam.setProperty("onlyThinLine", onlyThinLine);
        ksRasterFormatParam.setProperty("rangeIndex", rangeIndex.value());

        return ksRasterFormatParam;
    }

    public boolean saveAsToRasterFormat(String fileName, ActiveXComponent ksRasterFormatParam) {
        return ksDocument2D.invoke("SaveAsToRasterFormat", new Variant(fileName), new Variant(ksRasterFormatParam)).getBoolean();
    }


    public DrawingMetaData getFileData(ActiveXComponent ksDocumentParam) {
        ksDocumentParam.invoke("Init");
        ksGetObjParam(ALLPARAM, ksDocumentParam);

        DrawingMetaData metaData = new DrawingMetaData();

        metaData.setAuthor(ksDocumentParam.getProperty("author").getString());
        metaData.setComment(ksDocumentParam.getProperty("comment").getString());
        metaData.setFileName(ksDocumentParam.getProperty("fileName").getString());
        metaData.setRegime(ksDocumentParam.getProperty("regime").getInt());
        metaData.setType(ksDocumentParam.getProperty("type").getInt());

        return metaData;
    }

    public void ksGetObjParam(ParamType paramType, ActiveXComponent ksDocumentParam) {
        ksDocument2D.invoke("ksGetObjParam", new Variant(getReference()), new Variant(ksDocumentParam), new Variant(paramType.getNumber()));
    }

    public long ksTextLine(ActiveXComponent ksTextItemParam){
        return ksDocument2D.invoke("ksTextLine", new Variant(ksTextItemParam)).getInt();
    }

    public void ksGetDocOptions(int optionsType, ActiveXComponent param) {
        ksDocument2D.invoke("ksGetDocOptions", new Variant(optionsType), new Variant(param));
    }
}
