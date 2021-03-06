package com.kompas.model.kompas.documents;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.model.Document;
import com.kompas.model.dto.RasterParamDTO;
import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.DocumentMetaData;
import com.kompas.model.kompas.Kompas3D;
import com.kompas.model.kompas.KsStamp;
import com.kompas.model.kompas.enums.KsStampEnum;
import com.kompas.model.kompas.enums.ParamType;
import com.kompas.model.kompas.enums.SettingsType;
import com.kompas.model.kompas.enums.documentparam.DocType;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.kompas.model.kompas.enums.ParamType.ALLPARAM;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
@RequiredArgsConstructor
@EqualsAndHashCode
public class Document2D implements Document {
    @NonNull
    private ActiveXComponent ksDocument2D;
    private KsStamp ksStamp;

    public DocumentMetaData getFileData(ActiveXComponent ksDocumentParam) {
        ksDocumentParam.invoke("Init");
        ksGetObjParam(ALLPARAM, new Variant(ksDocumentParam));
        DocumentMetaData metaData = new DocumentMetaData();
        metaData.setAuthor(ksDocumentParam.getProperty("author").getString());
        metaData.setComment(ksDocumentParam.getProperty("comment").getString());
        metaData.setFileName(ksDocumentParam.getProperty("fileName").getString());
        metaData.setRegime(ksDocumentParam.getProperty("regime").getInt());
        metaData.setDocType(DocType.valueOf(ksDocumentParam.getProperty("type").getInt()));
        return metaData;
    }

    public ActiveXComponent rasterFormatParam(RasterParamDTO params) {
        ActiveXComponent ksRasterFormatParam = ksDocument2D.invokeGetComponent("RasterFormatParam");
        ksRasterFormatParam.invoke("Init");
        ksRasterFormatParam.setProperty("colorBPP", params.getColorBPP().getIndex());
        ksRasterFormatParam.setProperty("colorType", params.getColorType().getIndex());
        ksRasterFormatParam.setProperty("extResolution", params.getExtResolution().getValue());
        ksRasterFormatParam.setProperty("extScale", params.getExtScale().getValue());
        ksRasterFormatParam.setProperty("format", params.getImageFormat().getIndex());
        ksRasterFormatParam.setProperty("greyScale", params.isGrayScale());
        ksRasterFormatParam.setProperty("multiPageOutput", params.isMultiPageOutput());
        ksRasterFormatParam.setProperty("onlyThinLine", params.isOnlyThinLine());
        ksRasterFormatParam.setProperty("rangeIndex", params.getRangeIndex().getValue());
        return ksRasterFormatParam;
    }

    public boolean saveAsToRasterFormat(String fileName, ActiveXComponent ksRasterFormatParam) {
        return ksDocument2D.invoke("SaveAsToRasterFormat", new Variant(fileName), new Variant(ksRasterFormatParam)).getBoolean();
    }

    public void ksGetObjParam(Variant reference, ActiveXComponent ksDocumentParam, ParamType paramType) {
        ksDocument2D.invoke("ksGetObjParam", reference, new Variant(ksDocumentParam), new Variant(paramType.getIndex()));
    }

    public void ksGetObjParam(ParamType paramType, Variant ksDocumentParam) {
        ksDocument2D.invoke("ksGetObjParam", new Variant(getReference()), ksDocumentParam, new Variant(paramType.getIndex()));
    }

    public long ksTextLine(ActiveXComponent ksTextItemParam){
        return ksDocument2D.invoke("ksTextLine", new Variant(ksTextItemParam)).getInt();
    }

    public void ksGetDocOptions(SettingsType settingsType, ActiveXComponent param) {
        ksDocument2D.invoke("ksGetDocOptions", new Variant(settingsType.getIndex()), new Variant(param));
    }

    public boolean ksOpenStamp() {
        ksStamp = new KsStamp(ksDocument2D.invokeGetComponent("GetStamp"));
        return ksStamp.ksOpenStamp();
    }

    public boolean ksCloseStamp() {
        boolean result = ksStamp != null && ksStamp.ksCloseStamp();
        ksStamp = null;
        return result;
    }

    public boolean ksCleanStamp(){
        return ksStamp.ksClearStamp();
    }

    public boolean ksCleanStampCell(KsStampEnum cell) {
        return ksStamp.ksCleanStampCell(cell);
    }

    public boolean ksSetStampCell(ActiveXComponent ksTextItemParam, KsStampEnum cell, String value) {
        return ksStamp.ksSetStampCell(ksTextItemParam, cell, value);
    }

    public StampDTO getStampData(ActiveXComponent ksTextLineParam, ActiveXComponent ksTextItemParam) {
        return ksStamp.getStampDTO(ksTextLineParam, ksTextItemParam);
    }


    public void ksGetObjGabaritRect(long reference, ActiveXComponent ko_rectParam) {
        ksDocument2D.invoke("ksGetObjGabaritRect", new Variant(reference), new Variant(ko_rectParam));
    }

    public boolean ksOpenTable(Variant tableObjectRef) {
        return ksDocument2D.invoke("ksOpenTable", tableObjectRef).getInt() != 0;
    }

    public boolean ksEndObj() {
        return ksDocument2D.invoke("ksEndObj").getInt() != 0;
    }

    public boolean ksGetTableColumnText(Variant variant, ActiveXComponent ksTextParam) {
        return ksDocument2D.invoke("ksGetTableColumnText", variant, new Variant(ksTextParam)).getInt() != 0;
    }

    public Path getPath(ActiveXComponent ksDocumentParam) {
        ksDocumentParam.invoke("Init");
        ksGetObjParam(ALLPARAM, new Variant(ksDocumentParam));
        return Paths.get(ksDocumentParam.getProperty("fileName").getString()).toAbsolutePath();
    }

    @Override
    public Document getActiveDocument(Kompas3D kompas) {
        return kompas.getActiveDocument2D();
    }


    @Override
    public boolean openDocument(Path path, VisibleMode visibleMode) {
        return ksDocument2D.invoke("ksOpenDocument", path.toAbsolutePath().toString(), visibleMode.getVisibleMode()).getBoolean();
    }

    @Override
    public boolean closeDocument() {
        return ksDocument2D.invoke("ksCloseDocument").getBoolean();
    }

    @Override
    public long getReference() {
        return ksDocument2D.getProperty("reference").getLong();
    }

    @Override
    public boolean saveDocument(Path document) {
        return ksDocument2D.invoke("ksSaveDocument", document.toAbsolutePath().toString()).getBoolean();
    }

}
