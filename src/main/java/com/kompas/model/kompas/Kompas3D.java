package com.kompas.model.kompas;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.infrastructure.PropertyReader;
import com.kompas.model.kompas.enums.ParamType;
import com.kompas.model.kompas.enums.StructType2D;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.kompas.model.kompas.enums.DrawingObjectType.ALL_OBJ;
import static com.kompas.model.kompas.enums.DrawingObjectType.LDIMENSION_OBJ;
import static com.kompas.model.kompas.enums.DrawingObjectType.TEXT_OBJ;

public class Kompas3D {

    private ActiveXComponent ksDocumentParam;
    private ActiveXComponent ksTextItemParam;
    private ActiveXComponent ksTextParam;
    private ActiveXComponent ksTextLineParam;
    private ActiveXComponent ksStandartSheet;
    private ActiveXComponent ksSheetOptions;
    private ActiveXComponent ksSheetPar;

    private ActiveXComponent ksDimTextParam;

    private ActiveXComponent kompasObject;
    private PropertyReader property;
    private List<KsDocument2D> documents = new ArrayList<>();

    public Kompas3D(PropertyReader property) {
        this.property = property;
    }

    public void open() {
        this.kompasObject = new ActiveXComponent(property.getAPI5());
        kompasObject.setProperty("visible", property.getVisibleMode().isWindowVisible());
        kompasObject.invokeGetComponent(property.getAPI7()).setProperty("HideMessage", property.getKsHideMessage().getValue());

        this.ksDocumentParam = getParamStruct(StructType2D.ko_DocumentParam);
        this.ksTextItemParam = getParamStruct(StructType2D.ko_TextItemParam);
        this.ksTextParam = getParamStruct(StructType2D.ko_TextParam);
        this.ksTextLineParam = getParamStruct(StructType2D.ko_TextLineParam);
        this.ksStandartSheet = getParamStruct(StructType2D.ko_StandartSheet);
        this.ksSheetOptions = getParamStruct(StructType2D.ko_SheetOptions);
        this.ksSheetPar = getParamStruct(StructType2D.ko_SheetPar);
        this.ksDimTextParam = getParamStruct(StructType2D.ko_DimText);
    }

    private ActiveXComponent getParamStruct(StructType2D structType) {
        return kompasObject.invokeGetComponent("GetParamStruct", new Variant(structType.getIndex()));
    }

    public boolean openDrawing(File drawing) {
        return openDrawing(getKsDocument2D(), drawing.getAbsolutePath(), property.getVisibleMode());
    }

    private boolean openDrawing(KsDocument2D ksDocument2D, String path, VisibleMode visibleMode) {
        boolean result = ksDocument2D.ksOpenDocument(path, visibleMode);
        documents.add(ksDocument2D);
        return result;
    }

    private KsDocument2D getKsDocument2D() {
        return new KsDocument2D(kompasObject.invokeGetComponent("Document2D"));
    }

    public boolean closeDrawing(File drawing) {
        for (KsDocument2D document : documents) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.getAbsolutePath())) {
                boolean result = document.ksCloseDocument();
                documents.remove(document);
                return result;
            }
        }
        return false;
    }

    public List<String> getAllTextFromDocument(File drawing) {
        ActiveXComponent ksIterator = kompasObject.invokeGetComponent("GetIterator");
        ksIterator.invoke("ksCreateIterator", new Variant(TEXT_OBJ.getIndex()), new Variant(ALL_OBJ.getIndex()));
        List<String> allTexts = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (KsDocument2D document : documents) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.getAbsolutePath())) {
                Variant textObjRef = ksIterator.invoke("ksMoveIterator", new Variant("F"));
                do {
                    document.ksGetObjParam(textObjRef, ksTextParam, ParamType.ALLPARAM);
                    ActiveXComponent TEXT_LINE_ARR = ksTextParam.invokeGetComponent("GetTextLineArr");
                    int lineCount = TEXT_LINE_ARR.invoke("ksGetArrayCount").getInt();
                    for (int lineNumber = 0; lineNumber < lineCount; lineNumber++) {
                        TEXT_LINE_ARR.invoke("ksGetArrayItem", new Variant(lineNumber), new Variant(ksTextLineParam));
                        ActiveXComponent TEXT_ITEM_ARR = ksTextLineParam.invokeGetComponent("GetTextItemArr");
                        int itemCount = TEXT_ITEM_ARR.invoke("ksGetArrayCount").getInt();
                        for (int itemNumber = 0; itemNumber < itemCount; itemNumber++) {
                            TEXT_ITEM_ARR.invoke("ksGetArrayItem", new Variant(itemNumber), new Variant(ksTextItemParam));
                            sb.append(ksTextItemParam.invoke("s").getString()).append(" ");
                        }
                    }
                    textObjRef = ksIterator.invoke("ksMoveIterator", new Variant("N"));
                    allTexts.add(sb.toString().replaceAll("[\\s]{2,}", " "));
                    sb.setLength(0);
                } while (textObjRef.getInt() != 0);
            }
        }
        ksIterator.invoke("ksDeleteIterator");
        return allTexts;
    }

    public void close() {
        kompasObject.invoke("Quit");
        documents.clear();
    }

    public DrawingMetaData getDrawingMetaData(File drawing) {
        DrawingMetaData result = new DrawingMetaData();
        for (KsDocument2D document : documents) {
            result = document.getFileData(ksDocumentParam);
            if (result.getFileName().equals(drawing.getAbsolutePath())) {
                break;
            }
        }
        return result;
    }

    public List<String> getAllSizesFromDocument(File drawing) {
        ActiveXComponent ksIterator = kompasObject.invokeGetComponent("GetIterator");



        ksIterator.invoke("ksCreateIterator", new Variant(LDIMENSION_OBJ.getIndex()), new Variant(ALL_OBJ.getIndex()));
        List<String> allSizes = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (KsDocument2D document : documents) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.getAbsolutePath())) {
                Variant sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("F"));
                do {
                    document.ksGetObjParam(sizeObjectRef, ksDimTextParam, ParamType.ALLPARAM);
                    ActiveXComponent TEXT_LINE_ARR = ksTextParam.invokeGetComponent("GetTextLineArr");
                    int lineCount = TEXT_LINE_ARR.invoke("ksGetArrayCount").getInt();
                    for (int lineNumber = 0; lineNumber < lineCount; lineNumber++) {
                        TEXT_LINE_ARR.invoke("ksGetArrayItem", new Variant(lineNumber), new Variant(ksTextLineParam));
                        ActiveXComponent TEXT_ITEM_ARR = ksTextLineParam.invokeGetComponent("GetTextItemArr");
                        int itemCount = TEXT_ITEM_ARR.invoke("ksGetArrayCount").getInt();
                        for (int itemNumber = 0; itemNumber < itemCount; itemNumber++) {
                            TEXT_ITEM_ARR.invoke("ksGetArrayItem", new Variant(itemNumber), new Variant(ksTextItemParam));
                            sb.append(ksTextItemParam.invoke("s").getString()).append(" ");
                        }
                    }
                    sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("N"));
                    allSizes.add(sb.toString().replaceAll("[\\s]{2,}", " "));
                    sb.setLength(0);
                } while (sizeObjectRef.getInt() != 0);
            }
        }
        return allSizes;
    }
}
