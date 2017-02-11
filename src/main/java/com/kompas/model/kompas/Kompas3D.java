package com.kompas.model.kompas;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.infrastructure.PropertyReader;
import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.enums.DrawingObjectType;
import com.kompas.model.kompas.enums.KsStampEnum;
import com.kompas.model.kompas.enums.ParamType;
import com.kompas.model.kompas.enums.StructType2D;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.kompas.model.kompas.enums.DrawingObjectType.ALL_OBJ;
import static com.kompas.model.kompas.enums.DrawingObjectType.TEXT_OBJ;

public class Kompas3D {

    private ActiveXComponent ksDocumentParam;
    private ActiveXComponent ksTextItemParam;
    private ActiveXComponent ksTextParam;
    private ActiveXComponent ksTextLineParam;
    private ActiveXComponent ksStandartSheet;
    private ActiveXComponent ksSheetOptions;
    private ActiveXComponent ksSheetPar;

    private ActiveXComponent ko_LDimParam;
    private ActiveXComponent ko_DimText;
    private ActiveXComponent ko_LDimSource;
    private ActiveXComponent ko_DimDrawing;
    private ActiveXComponent ko_DimensionPartsParam;
    private ActiveXComponent ko_DoubleValue;

    private ActiveXComponent ko_Char255;

    private ActiveXComponent kompasObject;
    private PropertyReader property;
    private List<KsDocument2D> documents = new ArrayList<>();
    private List<DrawingObjectType> sizeTypes;

    public Kompas3D(PropertyReader property) {
        this.property = property;
    }

    public void open() {
        this.kompasObject = new ActiveXComponent(property.getAPI5());
        kompasObject.setProperty("visible", new Variant(property.getVisibleMode().isWindowVisible()));
        kompasObject.invokeGetComponent(property.getAPI7()).setProperty("HideMessage", new Variant(property.getKsHideMessage().getValue()));

        this.ksDocumentParam = getParamStruct(StructType2D.ko_DocumentParam);
        this.ksTextItemParam = getParamStruct(StructType2D.ko_TextItemParam);
        this.ksTextParam = getParamStruct(StructType2D.ko_TextParam);
        this.ksTextLineParam = getParamStruct(StructType2D.ko_TextLineParam);
        this.ksStandartSheet = getParamStruct(StructType2D.ko_StandartSheet);
        this.ksSheetOptions = getParamStruct(StructType2D.ko_SheetOptions);
        this.ksSheetPar = getParamStruct(StructType2D.ko_SheetPar);

        this.ko_LDimParam = getParamStruct(StructType2D.ko_LDimParam);
        this.ko_DimText = getParamStruct(StructType2D.ko_DimText);
        this.ko_LDimSource = getParamStruct(StructType2D.ko_LDimSource);
        this.ko_DimDrawing = getParamStruct(StructType2D.ko_DimDrawing);
        this.ko_DimensionPartsParam = getParamStruct(StructType2D.ko_DimensionPartsParam);
        this.ko_DoubleValue = getParamStruct(StructType2D.ko_DoubleValue);

        this.ko_Char255 = getParamStruct(StructType2D.ko_Char255);
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

    public List<Double> getAllSizesFromDocument(File drawing) {
        List<DrawingObjectType> sizeTypes = getSizeTypes();
        ActiveXComponent ksIterator = kompasObject.invokeGetComponent("GetIterator");


        ksIterator.invoke("ksCreateIterator", new Variant(sizeTypes.get(0).getIndex()), new Variant(ALL_OBJ.getIndex()));


        List<Double> allSizes = new ArrayList<>();
        for (KsDocument2D document : documents) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.getAbsolutePath())) {
                Variant sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("F"));

                do {
                    document.ksGetObjParam(sizeObjectRef, ko_LDimParam, ParamType.ALLPARAM);
                    document.ksGetObjParam(sizeObjectRef, ko_DimText, ParamType.DIM_TEXT_PARAM);
                    document.ksGetObjParam(sizeObjectRef, ko_LDimSource, ParamType.DIM_SOURSE_PARAM);
                    document.ksGetObjParam(sizeObjectRef, ko_DimDrawing, ParamType.DIM_DRAW_PARAM);
                    document.ksGetObjParam(sizeObjectRef, ko_DimensionPartsParam, ParamType.DIM_PARTS);
                    document.ksGetObjParam(sizeObjectRef, ko_DoubleValue, ParamType.DIM_VALUE);

                    System.out.println("++++++++++++++++++");
                    Variant bitFlag = ko_DimText.getProperty("bitFlag");
                    System.out.println(ko_DimText.getProperty("sign"));
                    ko_DimText.getProperty("stringFlag");
                    ko_DimText.getProperty("style");

                    ko_DimText.invoke("GetBitFlagValue", bitFlag);
                    ActiveXComponent ksDynamicArray = ko_DimText.invokeGetComponent("GetTextArr");

                    int itemCount = ksDynamicArray.invoke("ksGetArrayCount").getInt();
                    System.out.println(ksDynamicArray.invoke("ksGetArrayType"));

                    for (int itemNumber = 0; itemNumber < itemCount; itemNumber++) {
                        ksDynamicArray.invoke("ksGetArrayItem", new Variant(itemNumber), new Variant(ko_Char255));
                        System.out.println(ko_Char255.invoke("str"));
                    }

                    System.out.println("++++++++++++++++++");


                    allSizes.add(ko_DoubleValue.invoke("value").getDouble());

                    sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("N"));
                } while (sizeObjectRef.getInt() != 0);
            }
        }
        ksIterator.invoke("ksDeleteIterator");
        return allSizes;
    }

    private List<DrawingObjectType> getSizeTypes() {
        List<DrawingObjectType> result = new ArrayList<>();

        result.add(DrawingObjectType.LDIMENSION_OBJ);
        result.add(DrawingObjectType.ADIMENSION_OBJ);
        result.add(DrawingObjectType.DDIMENSION_OBJ);
        result.add(DrawingObjectType.RDIMENSION_OBJ);
        result.add(DrawingObjectType.RBREAKDIMENSION_OBJ);
        result.add(DrawingObjectType.LBREAKDIMENSION_OBJ);
        result.add(DrawingObjectType.ABREAKDIMENSION_OBJ);
        result.add(DrawingObjectType.ORDINATEDIMENSION_OBJ);
        result.add(DrawingObjectType.ARCDIMENSION_OBJ);

        return result;
    }

    public boolean openStamp(File drawing) {
        for (KsDocument2D document : documents) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.getAbsolutePath())) {
                try {
                    return document.ksOpenStamp();
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean closeStamp(File drawing) {
        for (KsDocument2D document : documents) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.getAbsolutePath())) {
                try {
                    return document.ksCloseStamp();
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean cleanStamp(File drawing) {
        for (KsDocument2D document : documents) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.getAbsolutePath())) {
                try {
                    return document.ksCleanStamp();
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean saveDrawing(File drawing) {
        for (KsDocument2D document : documents) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.getAbsolutePath())) {
                try {
                    return document.ksSaveDocument(drawing);
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean cleanStampCell(KsStampEnum cell, File drawing) {
        for (KsDocument2D document : documents) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.getAbsolutePath())) {
                try {
                    return document.ksCleanStampCell(cell);
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean setStampCellValue(KsStampEnum cell, String value, File drawing) {
        for (KsDocument2D document : documents) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.getAbsolutePath())) {
                try {
                    return document.ksSetStampCell(ksTextItemParam, cell, value);
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public StampDTO getStampData(File drawing) {
        StampDTO result = new StampDTO();
        for (KsDocument2D document : documents) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.getAbsolutePath())) {
                try {
                    return document.getStampData(ksTextLineParam, ksTextItemParam);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    return result;
                }
            }
        }
        return result;
    }
}
