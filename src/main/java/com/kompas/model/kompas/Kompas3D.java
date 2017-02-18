package com.kompas.model.kompas;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.infrastructure.PropertyReader;
import com.kompas.model.dto.DrawingCharacteristicsDTO;
import com.kompas.model.dto.RasterParamDTO;
import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.datastructure.KsDynamicArray;
import com.kompas.model.kompas.enums.KsStampEnum;
import com.kompas.model.kompas.enums.NavigationMode;
import com.kompas.model.kompas.enums.ParamType;
import com.kompas.model.kompas.enums.StructType2D;
import com.kompas.model.kompas.enums.documentparam.DocType;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;
import com.kompas.model.metrics.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.io.Files.getFileExtension;
import static com.kompas.model.kompas.enums.NavigationMode.*;
import static com.kompas.model.kompas.enums.SettingsType.SHEET_OPTIONS_EX;
import static com.kompas.model.kompas.enums.StructType2D.ko_SheetOptions;
import static com.kompas.model.kompas.enums.documentparam.DocType.*;
import static com.kompas.model.kompas.enums.documentparam.Orientation.valueOf;
import static com.kompas.model.kompas.enums.documentparam.SheetFormat.valueOf;
import static com.kompas.service.SizeConverter.*;

public class Kompas3D {

    private ActiveXComponent ksDocumentParam;
    private ActiveXComponent ksTextItemParam;
    private ActiveXComponent ksTextParam;
    private ActiveXComponent ksTextLineParam;
    private ActiveXComponent ksStandartSheet;
    private ActiveXComponent ksSheetOptions;
    private ActiveXComponent ksSheetPar;
    private ActiveXComponent ko_RectParam;

    private ActiveXComponent ko_LDimParam;
    private ActiveXComponent ko_DimText;
    private ActiveXComponent ko_LDimSource;
    private ActiveXComponent ko_DimDrawing;
    private ActiveXComponent ko_DimensionPartsParam;
    private ActiveXComponent ko_DoubleValue;

    private ActiveXComponent ko_Char255;

    private ActiveXComponent kompasObject;
    private ActiveXComponent kompasAPI7;

    private PropertyReader property;

    private List<KsDocument2D> drawings = new ArrayList<>();
    private List<SpcDocument> specifications = new ArrayList<>();

    public Kompas3D(PropertyReader property) {
        this.property = property;
    }

    public void open() {
        this.kompasObject = new ActiveXComponent(property.getAPI5());
        kompasObject.setProperty("visible", new Variant(property.getVisibleMode().isWindowVisible()));
        kompasObject.invokeGetComponent(property.getAPI7()).setProperty("HideMessage", new Variant(property.getKsHideMessage().getValue()));
        kompasAPI7 = kompasObject.invokeGetComponent("ksGetApplication7");

        this.ksDocumentParam = getParamStruct(StructType2D.ko_DocumentParam);
        this.ksTextItemParam = getParamStruct(StructType2D.ko_TextItemParam);
        this.ksTextParam = getParamStruct(StructType2D.ko_TextParam);
        this.ksTextLineParam = getParamStruct(StructType2D.ko_TextLineParam);
        this.ksStandartSheet = getParamStruct(StructType2D.ko_StandartSheet);
        this.ksSheetOptions = getParamStruct(ko_SheetOptions);
        this.ksSheetPar = getParamStruct(StructType2D.ko_SheetPar);

        this.ko_LDimParam = getParamStruct(StructType2D.ko_LDimParam);
        this.ko_DimText = getParamStruct(StructType2D.ko_DimText);
        this.ko_LDimSource = getParamStruct(StructType2D.ko_LDimSource);
        this.ko_DimDrawing = getParamStruct(StructType2D.ko_DimDrawing);
        this.ko_DimensionPartsParam = getParamStruct(StructType2D.ko_DimensionPartsParam);
        this.ko_DoubleValue = getParamStruct(StructType2D.ko_DoubleValue);

        this.ko_Char255 = getParamStruct(StructType2D.ko_Char255);
        this.ko_RectParam = getParamStruct(StructType2D.ko_RectParam);
    }

    private ActiveXComponent getParamStruct(StructType2D structType) {
        return kompasObject.invokeGetComponent("GetParamStruct", new Variant(structType.getIndex()));
    }

    public KsDocument2D getActiveDocument2D() {
        return new KsDocument2D(kompasObject.invokeGetComponent("ActiveDocument2D"));
    }

    public boolean openDrawing(Path drawing) {
        String extension = getFileExtension(drawing.getFileName().toString()).toLowerCase();
        switch (extension){
            case "cdw":
                return openDrawing(getKsDocument2D(), drawing, property.getVisibleMode());
            case "spw":
                return openDocument(getSpcDocument(), drawing, property.getVisibleMode());
        }
        return false;
    }

    private boolean openDrawing(KsDocument2D ksDocument2D, Path drawing, VisibleMode visibleMode) {
        drawings.add(ksDocument2D);
        return ksDocument2D.ksOpenDocument(drawing, visibleMode);
    }

    private boolean openDocument(SpcDocument spcDocument, Path path, VisibleMode visibleMode){
        specifications.add(spcDocument);
        return spcDocument.ksOpenDocument(path, visibleMode);
    }

    private SpcDocument getSpcDocument(){
        return new SpcDocument(kompasObject.invokeGetComponent("SpcDocument"));
    }

    private KsDocument2D getKsDocument2D() {
        return new KsDocument2D(kompasObject.invokeGetComponent("Document2D"));
    }

    public boolean closeDrawing(Path drawing) {
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                boolean result = document.ksCloseDocument();
                drawings.remove(document);
                return result;
            }
        }
        return false;
    }

    public List<String> getAllTextFromDocument(Path drawing) {
        List<String> allTexts = new ArrayList<>();
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                ActiveXComponent ksIterator = kompasObject.invokeGetComponent("GetIterator");
                ksIterator.invoke("ksCreateIterator", new Variant(TEXT_OBJ.getIndex()), new Variant(ALL_OBJ.getIndex()));
                StringBuilder sb = new StringBuilder();
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
                ksIterator.invoke("ksDeleteIterator");
            }
        }
        return allTexts;
    }

    public void close() {
        kompasObject.invoke("Quit");
        drawings.clear();
    }

    public DrawingMetaData getDrawingMetaData(Path drawing) {
        DrawingMetaData result = new DrawingMetaData();
        for (KsDocument2D document : drawings) {
            result = document.getFileData(ksDocumentParam);
            if (result.getFileName().equals(drawing.toAbsolutePath().toString())) {
                document.ksGetDocOptions(SHEET_OPTIONS_EX, ksSheetOptions);

                System.out.println(ksSheetOptions.getProperty("layoutName"));
                System.out.println(ksSheetOptions.getProperty("sheetType"));
                System.out.println(ksSheetOptions.getProperty("shtType"));

                return result;
            }
        }
        return result;
    }

    public List<String> getAllSizesFromDocument(Path drawing) {
        List<String> allSizes = new ArrayList<>();
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                allSizes.addAll(getLdimensionObjectList(document));
                allSizes.addAll(getAdimensionObjectList(document));
                allSizes.addAll(getDdimensionObjectList(document));
                allSizes.addAll(getRdimensionObjectList(document));
                allSizes.addAll(getRbreakDimensionObjectList(document));
                allSizes.addAll(getLbreakDimensionObjectList(document));
                allSizes.addAll(getAbreakDimensionObjectList(document));
                allSizes.addAll(getOrdinateDimensionObjectList(document));
                allSizes.addAll(getArcDimensionObjectList(document));
            }
        }
        return allSizes;
    }

    private List<NavigationMode> getNavigationModes() {
        List<NavigationMode> result = new ArrayList<>();

        result.add(LDIMENSION_OBJ);
        result.add(ADIMENSION_OBJ);
        result.add(DDIMENSION_OBJ);
        result.add(RDIMENSION_OBJ);
        result.add(RBREAKDIMENSION_OBJ);
        result.add(LBREAKDIMENSION_OBJ);
        result.add(ABREAKDIMENSION_OBJ);
        result.add(ORDINATEDIMENSION_OBJ);
        result.add(ARCDIMENSION_OBJ);

        return result;
    }

    private List<String> getLdimensionObjectList(KsDocument2D document) {
        List<String> allSizes = new ArrayList<>();
        KsIterator iterator = new KsIterator(kompasObject, LDIMENSION_OBJ, ALL_OBJ);
        Variant elementReference = iterator.getFirstElement();

        while (elementReference.getInt() != 0) {
            document.ksGetObjParam(elementReference, ko_LDimParam, ParamType.ALLPARAM);
            document.ksGetObjParam(elementReference, ko_DimText, ParamType.DIM_TEXT_PARAM);
            document.ksGetObjParam(elementReference, ko_LDimSource, ParamType.DIM_SOURSE_PARAM);
            document.ksGetObjParam(elementReference, ko_DimDrawing, ParamType.DIM_DRAW_PARAM);
            document.ksGetObjParam(elementReference, ko_DimensionPartsParam, ParamType.DIM_PARTS);
            document.ksGetObjParam(elementReference, ko_DoubleValue, ParamType.DIM_VALUE);

            System.out.println("++++++++++++++++++");

            Variant bitFlag = ko_DimText.getProperty("bitFlag");

            ko_DimText.getProperty("sign");
            ko_DimText.getProperty("stringFlag");
            ko_DimText.getProperty("style");
            ko_DimText.invoke("GetBitFlagValue", bitFlag);


            KsDynamicArray ksDynamicArray = new KsDynamicArray(ko_DimText.invokeGetComponent("GetTextArr"));



            System.out.println(ksDynamicArray.ksGetArrayType());

            for (int itemNumber = 0; itemNumber < ksDynamicArray.ksGetArrayCount(); itemNumber++) {
                ksDynamicArray.ksGetArrayItem(itemNumber, ko_Char255);
                System.out.println(ko_Char255.invoke("str"));
            }

            System.out.println("++++++++++++++++++");
            allSizes.add(String.valueOf(ko_DoubleValue.invoke("value").getDouble()));

            ksDynamicArray.ksDeleteArray();
            elementReference = iterator.getNextElement();
        }

        iterator.deleteIterator();
        return allSizes;
    }

    private List<String> getAdimensionObjectList(KsDocument2D document) {
        List<String> allSizes = new ArrayList<>();
        ActiveXComponent ksIterator = kompasObject.invokeGetComponent("GetIterator");
        ksIterator.invoke("ksCreateIterator", new Variant(ADIMENSION_OBJ.getIndex()), new Variant(ALL_OBJ.getIndex()));
        Variant sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("F"));
        while (sizeObjectRef.getInt() != 0) {
            document.ksGetObjParam(sizeObjectRef, ko_DimText, ParamType.DIM_TEXT_PARAM);
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
            allSizes.add(String.valueOf(ko_DoubleValue.invoke("value").getDouble()));
            sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("N"));
        }
        ksIterator.invoke("ksDeleteIterator");
        return allSizes;
    }

    private List<String> getDdimensionObjectList(KsDocument2D document) {
        List<String> allSizes = new ArrayList<>();
        ActiveXComponent ksIterator = kompasObject.invokeGetComponent("GetIterator");
        ksIterator.invoke("ksCreateIterator", new Variant(DDIMENSION_OBJ.getIndex()), new Variant(ALL_OBJ.getIndex()));
        Variant sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("F"));
        while (sizeObjectRef.getInt() != 0) {
            document.ksGetObjParam(sizeObjectRef, ko_DimText, ParamType.DIM_TEXT_PARAM);
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
            allSizes.add(String.valueOf(ko_DoubleValue.invoke("value").getDouble()));
            sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("N"));
        }
        ksIterator.invoke("ksDeleteIterator");
        return allSizes;
    }

    private List<String> getRdimensionObjectList(KsDocument2D document) {
        List<String> allSizes = new ArrayList<>();
        ActiveXComponent ksIterator = kompasObject.invokeGetComponent("GetIterator");
        ksIterator.invoke("ksCreateIterator", new Variant(RDIMENSION_OBJ.getIndex()), new Variant(ALL_OBJ.getIndex()));
        Variant sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("F"));
        while (sizeObjectRef.getInt() != 0) {
            document.ksGetObjParam(sizeObjectRef, ko_DimText, ParamType.DIM_TEXT_PARAM);
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
            allSizes.add(String.valueOf(ko_DoubleValue.invoke("value").getDouble()));
            sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("N"));
        }
        ksIterator.invoke("ksDeleteIterator");
        return allSizes;
    }

    private List<String> getRbreakDimensionObjectList(KsDocument2D document) {
        List<String> allSizes = new ArrayList<>();
        ActiveXComponent ksIterator = kompasObject.invokeGetComponent("GetIterator");
        ksIterator.invoke("ksCreateIterator", new Variant(RBREAKDIMENSION_OBJ.getIndex()), new Variant(ALL_OBJ.getIndex()));
        Variant sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("F"));
        while (sizeObjectRef.getInt() != 0) {
            document.ksGetObjParam(sizeObjectRef, ko_DimText, ParamType.DIM_TEXT_PARAM);
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
            allSizes.add(String.valueOf(ko_DoubleValue.invoke("value").getDouble()));
            sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("N"));
        }
        ksIterator.invoke("ksDeleteIterator");
        return allSizes;
    }

    private List<String> getLbreakDimensionObjectList(KsDocument2D document) {
        return new ArrayList<>();
    }

    private List<String> getAbreakDimensionObjectList(KsDocument2D document) {
        return new ArrayList<>();
    }

    private List<String> getOrdinateDimensionObjectList(KsDocument2D document) {
        return new ArrayList<>();
    }

    private List<String> getArcDimensionObjectList(KsDocument2D document) {
        return new ArrayList<>();
    }




    public boolean openStamp(Path drawing) {
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                try {
                    return document.ksOpenStamp();
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean closeStamp(Path drawing) {
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                try {
                    return document.ksCloseStamp();
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean cleanStamp(Path drawing) {
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                try {
                    return document.ksCleanStamp();
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean saveDrawing(Path drawing) {
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                try {
                    return document.ksSaveDocument(drawing);
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean cleanStampCell(KsStampEnum cell, Path drawing) {
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                try {
                    return document.ksCleanStampCell(cell);
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean setStampCellValue(KsStampEnum cell, String value, Path drawing) {
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                try {
                    return document.ksSetStampCell(ksTextItemParam, cell, value);
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public StampDTO getStampData(Path drawing) {
        StampDTO result = new StampDTO();
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                try {
                    return document.getStampData(ksTextLineParam, ksTextItemParam);
                } catch (Exception ex) {
                    return result;
                }
            }
        }
        return result;
    }

    public DocType getDocumentType(Path drawing) {
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                return DocType.valueOf(kompasObject.invoke("ksGetDocumentType", new Variant(document.getReference())).getInt());
            }
        }
        throw new IllegalArgumentException("Unknown drawing format");
    }

    public DrawingCharacteristicsDTO getDrawingSize(Path drawing) {
        DrawingCharacteristicsDTO characteristics = new DrawingCharacteristicsDTO();
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                DocType type = DocType.valueOf(kompasObject.invoke("ksGetDocumentType", new Variant(document.getReference())).getInt());
                if (type == lt_DocFragment) {
                    document.ksGetObjGabaritRect(document.getReference(), ko_RectParam);
                    ActiveXComponent ksMathPointParamB = ko_RectParam.invokeGetComponent("GetpBot");
                    ActiveXComponent ksMathPointParamT = ko_RectParam.invokeGetComponent("GetpTop");
                    Size size = new Size(
                            ksMathPointParamT.invoke("x").getDouble() - ksMathPointParamB.invoke("x").getDouble(),
                            ksMathPointParamT.invoke("y").getDouble() - ksMathPointParamB.invoke("y").getDouble()
                    );
                    characteristics.setOrientation(valueOf(size.isHorizontal()));
                    characteristics.setSheetFormat(getFormat(size));
                    characteristics.setMultiplicity(getMultiplicity(size));
                    characteristics.setDocType(type);
                    characteristics.setSize(size);

                } else if (type == lt_DocSheetStandart || type == lt_DocSheetUser) {
                    ActiveXComponent so = getParamStruct(ko_SheetOptions);
                    document.ksGetDocOptions(SHEET_OPTIONS_EX, so);
                    if (Boolean.valueOf(String.valueOf(so.getProperty("sheetType")))) {
                        ActiveXComponent ksSheetSize = so.invokeGetComponent("GetSheetParam", new Variant(true));
                        Size size = new Size(ksSheetSize.getProperty("width").getDouble(), ksSheetSize.getProperty("height").getDouble());
                        characteristics.setOrientation(valueOf(size.isHorizontal()));
                        characteristics.setSheetFormat(getFormat(size));
                        characteristics.setMultiplicity(getMultiplicity(size));
                        characteristics.setDocType(type);
                        characteristics.setSize(size);
                    } else {
                        ActiveXComponent ksStandartSheet = so.invokeGetComponent("GetSheetParam", new Variant(false));
                        characteristics.setOrientation(valueOf(ksStandartSheet.getProperty("direct").getBoolean()));
                        characteristics.setSheetFormat(valueOf(ksStandartSheet.getProperty("format").getInt()));
                        characteristics.setMultiplicity(ksStandartSheet.getProperty("multiply").getShort());
                        characteristics.setDocType(type);
                        characteristics.setSize(getSize(valueOf(ksStandartSheet.getProperty("format").getInt()), valueOf(ksStandartSheet.getProperty("direct").getBoolean()), ksStandartSheet.getProperty("multiply").getShort()));
                    }
                }
            }
        }
        return characteristics;
    }

    public boolean saveDrawingAsImage(RasterParamDTO params, Path drawing) {
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                ActiveXComponent ksRasterFormatParam = document.rasterFormatParam(params);
                return document.saveAsToRasterFormat(drawing.toAbsolutePath().toString().split("\\.")[0] + params.getImageFormat().getExtension().toLowerCase(), ksRasterFormatParam);
            }
        }
        return false;
    }

    public List<String> getAllTableDataFromDocument(Path drawing) {
        List<String> allTableData = new ArrayList<>();
        ActiveXComponent ksIterator = kompasObject.invokeGetComponent("GetIterator");
        ksIterator.invoke("ksCreateIterator", new Variant(TABLE_OBJ.getIndex()), new Variant(ALL_OBJ.getIndex()));
        for (KsDocument2D document : drawings) {
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if (drawingMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                Variant tableObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("F"));
                while (tableObjectRef.getInt() != 0) {
                    document.ksOpenTable(tableObjectRef);
                    StringBuilder sb = new StringBuilder();
                    while (document.ksGetTableColumnText(new Variant(0, true), ksTextParam)) {
                        KsDynamicArray arrpLineText = new KsDynamicArray(ksTextParam.invokeGetComponent("GetTextLineArr"));
                        for (int index = 0; index < arrpLineText.ksGetArrayCount(); index++) {
                            arrpLineText.ksGetArrayItem(index, ksTextLineParam);
                            ActiveXComponent arrTextItem = ksTextLineParam.invokeGetComponent("GetTextItemArr");
                            int itemCount = arrTextItem.invoke("ksGetArrayCount").getInt();
                            for (int itemNumber = 0; itemNumber < itemCount; itemNumber++) {
                                arrTextItem.invoke("ksGetArrayItem", new Variant(itemNumber), new Variant(ksTextItemParam));
                                sb.append(ksTextItemParam.invoke("s").getString()).append(" ");
                            }
                            arrTextItem.invoke("ksDeleteArray");
                        }
                        arrpLineText.ksDeleteArray();
                    }
                    document.ksEndObj();
                    tableObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("N"));
                    allTableData.add(sb.toString().replaceAll("[\\s]{2,}", " "));
                    sb.setLength(0);
                }
            }
        }
        ksIterator.invoke("ksDeleteIterator");
        return allTableData;
    }

    public Object getSystemVersion() {
        Integer Major = Integer.valueOf(0);
        Integer Minor = 5;
        Integer Release = 5;
        Integer Build = 5;

        System.out.println(kompasObject.invoke("ksGetSystemVersion", new Variant(Major, true), new Variant(0, true), new Variant(0, true), new Variant(0, true)));

        System.out.println(Major);
        System.out.println(Minor);
        System.out.println(Release);
        System.out.println(Build);
        return null;
    }

    public void paint(long winRef) {
        kompasObject.invoke("ksDrawKompasDocument", new Variant(winRef), new Variant("C:\\Users\\Amsterdam\\IdeaProjects\\K3D\\K3D\\src\\test\\resources\\fixtures\\A4_metadata.cdw"));
    }
}
