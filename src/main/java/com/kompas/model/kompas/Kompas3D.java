package com.kompas.model.kompas;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.infrastructure.PropertyReader;
import com.kompas.model.Document;
import com.kompas.model.dto.DrawingCharacteristicsDTO;
import com.kompas.model.dto.RasterParamDTO;
import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.datastructure.KsDynamicArray;
import com.kompas.model.kompas.documents.Document2D;
import com.kompas.model.kompas.documents.SpcDocument;
import com.kompas.model.kompas.enums.KsStampEnum;
import com.kompas.model.kompas.enums.ParamType;
import com.kompas.model.kompas.enums.SizeSing;
import com.kompas.model.kompas.enums.StructType2D;
import com.kompas.model.kompas.enums.documentparam.DocType;
import com.kompas.model.kompas.enums.documentparam.SheetType;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;
import com.kompas.model.metrics.Size;
import com.kompas.model.templates.GraphicTemplate;

import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.io.Files.getFileExtension;
import static com.kompas.model.kompas.enums.DynamicArrayType.CHAR_STR_ARR;
import static com.kompas.model.kompas.enums.DynamicArrayType.TEXT_LINE_ARR;
import static com.kompas.model.kompas.enums.NavigationMode.*;
import static com.kompas.model.kompas.enums.ParamType.DIM_VALUE;
import static com.kompas.model.kompas.enums.SettingsType.SHEET_OPTIONS_EX;
import static com.kompas.model.kompas.enums.StructType2D.ko_SheetOptions;
import static com.kompas.model.kompas.enums.documentparam.DocType.*;
import static com.kompas.model.kompas.enums.documentparam.Orientation.valueOf;
import static com.kompas.model.kompas.enums.documentparam.SheetFormat.valueOf;
import static com.kompas.model.kompas.enums.sizes.SizeOption._AUTONOMINAL;
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

    private List<Document> openedDocuments = new ArrayList<>();

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

    public Document getActiveDocument2D() {
        return new Document2D(kompasObject.invokeGetComponent("ActiveDocument2D"));
    }

    public boolean openDocument(Path drawing) {
        String extension = getFileExtension(drawing.getFileName().toString()).toLowerCase();
        switch (extension) {
            case "cdw":
                return openDocument(getKsDocument2D(), drawing, property.getVisibleMode());
            case "frw":
                return openDocument(getKsDocument2D(), drawing, property.getVisibleMode());
            case "spw":
                return openDocument(getSpcDocument(), drawing, property.getVisibleMode());
        }
        return false;
    }

    private boolean openDocument(Document document, Path drawing, VisibleMode visibleMode) {
        openedDocuments.add(document);
        return document.openDocument(drawing, visibleMode);
    }

    private SpcDocument getSpcDocument() {
        return new SpcDocument(kompasObject.invokeGetComponent("SpcDocument"));
    }

    private Document2D getKsDocument2D() {
        return new Document2D(kompasObject.invokeGetComponent("Document2D"));
    }

    public boolean closeDocument(Path document) {

        String extension = getFileExtension(document.toAbsolutePath().toString()).toLowerCase();
        switch (extension) {
            case "cdw":
                for (Document doc : openedDocuments) {
                    if (doc.getPath(ksDocumentParam).toAbsolutePath().equals(document.toAbsolutePath())) {
                        boolean result = doc.closeDocument();
                        openedDocuments.remove(doc);
                        return result;
                    }
                }
            case "frw":
                for (Document doc : openedDocuments) {
                    if (doc.getPath(ksDocumentParam).toAbsolutePath().equals(document.toAbsolutePath())) {
                        boolean result = doc.closeDocument();
                        openedDocuments.remove(doc);
                        return result;
                    }
                }
            case "spw":
                for (Document doc : openedDocuments) {
//                    if (doc.getPath(ksDocumentParam).toAbsolutePath().equals(openedDocuments.toAbsolutePath())) {
                        boolean result = doc.closeDocument();
                        openedDocuments.remove(doc);
                        return result;
//                    }
                }
        }
        return false;
    }

    public List<String> getAllTextFromDocument(Path drawing) {
        List<String> allTexts = new ArrayList<>();
        for (Document document : openedDocuments) {
            if (document.getPath(ksDocumentParam).equals(drawing.toAbsolutePath())) {
                KsIterator iterator = new KsIterator(kompasObject, TEXT_OBJ, ALL_OBJ);
                Variant textObjRef = iterator.getFirstElement();
                while (textObjRef.getInt() != 0) {
                    StringBuilder sb = new StringBuilder();
                    document.ksGetObjParam(textObjRef, ksTextParam, ParamType.ALLPARAM);
                    KsDynamicArray textArray = new KsDynamicArray(ksTextParam.invokeGetComponent("GetTextLineArr"));
                    for (int lineNumber = 0; lineNumber < textArray.ksGetArrayCount(); lineNumber++) {
                        textArray.ksGetArrayItem(lineNumber, ksTextLineParam);
                        KsDynamicArray textItemArray = new KsDynamicArray(ksTextLineParam.invokeGetComponent("GetTextItemArr"));
                        for (int itemNumber = 0; itemNumber < textItemArray.ksGetArrayCount(); itemNumber++) {
                            textItemArray.ksGetArrayItem(itemNumber, ksTextItemParam);
                            sb.append(ksTextItemParam.invoke("s").getString()).append(" ");
                        }
                        textItemArray.ksDeleteArray();
                    }
                    textArray.ksDeleteArray();
                    textObjRef = iterator.getNextElement();
                    allTexts.add(sb.toString().replaceAll("[\\s]{2,}", " "));
                    sb.setLength(0);
                }
                iterator.deleteIterator();
            }
        }
        return allTexts;
    }


    public DocumentMetaData getDrawingMetaData(Path drawing) {
        DocumentMetaData result = new DocumentMetaData();
        for (Document document : this.openedDocuments) {
            result = document.getFileData(ksDocumentParam);
            if (result.getFileName().equals(drawing.toAbsolutePath().toString())) {
                document.ksGetDocOptions(SHEET_OPTIONS_EX, ksSheetOptions);

                result.setSheetType(SheetType.valueOf(ksSheetOptions.getProperty("sheetType").getBoolean()));
                result.setTemplate(new GraphicTemplate(ksSheetOptions.getProperty("shtType").getInt(), ksSheetOptions.getProperty("layoutName").getString()));

                return result;
            }
        }
        return result;
    }

    public List<String> getAllSizesFromDocument(Path drawing) {
        List<String> allSizes = new ArrayList<>();
        for (Document document : this.openedDocuments) {
            if (document.getPath(ksDocumentParam).equals(drawing.toAbsolutePath())) {
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

    private List<String> getLdimensionObjectList(Document document) {
        List<String> allSizes = new ArrayList<>();
        KsIterator iterator = new KsIterator(kompasObject, LDIMENSION_OBJ, ALL_OBJ);
        Variant elementReference = iterator.getFirstElement();
        while (elementReference.getInt() != 0) {
            document.ksGetObjParam(elementReference, ko_DimText, ParamType.DIM_TEXT_PARAM);
            StringBuilder sb = new StringBuilder();
            if (ko_DimText.invoke("GetBitFlagValue", new Variant(_AUTONOMINAL.getValue())).getBoolean()) {
                document.ksGetObjParam(elementReference, ko_DoubleValue, DIM_VALUE);
                sb.append(SizeSing.valueOf(ko_DimText.invoke("sign").getInt())).append(" ");
                sb.append(ko_DoubleValue.invoke("value").toString()).append(" ");
                if (ko_DimText.getProperty("stringFlag").getBoolean()) {

                } else {
                    KsDynamicArray ksDynamicArray = new KsDynamicArray(ko_DimText.invokeGetComponent("GetTextArr"));
                    for (int itemNumber = 0; itemNumber < ksDynamicArray.ksGetArrayCount(); itemNumber++) {
                        ksDynamicArray.ksGetArrayItem(itemNumber, ko_Char255);
                        sb.append(ko_Char255.invoke("str").toString()).append(" ");
                    }
                    allSizes.add(sb.toString().replaceAll("[\\s]{2,}", " "));
                    ksDynamicArray.ksDeleteArray();
                }
            } else {
                sb.append(SizeSing.valueOf(ko_DimText.invoke("sign").getInt())).append(" ");
                if (ko_DimText.getProperty("stringFlag").getBoolean()) {

                } else {
                    KsDynamicArray ksDynamicArray = new KsDynamicArray(ko_DimText.invokeGetComponent("GetTextArr"));
                    for (int itemNumber = 0; itemNumber < ksDynamicArray.ksGetArrayCount(); itemNumber++) {
                        ksDynamicArray.ksGetArrayItem(itemNumber, ko_Char255);
                        sb.append(ko_Char255.invoke("str").toString()).append(" ");
                    }
                    allSizes.add(sb.toString().replaceAll("[\\s]{2,}", " "));
                    ksDynamicArray.ksDeleteArray();
                }
            }
            sb.setLength(0);
            elementReference = iterator.getNextElement();
        }
        iterator.deleteIterator();
        return allSizes;
    }

    private List<String> getAdimensionObjectList(Document document) {
        List<String> allSizes = new ArrayList<>();
        KsIterator ksIterator = new KsIterator(kompasObject, ADIMENSION_OBJ, ALL_OBJ);
        Variant sizeObjectRef = ksIterator.getFirstElement();
        while (sizeObjectRef.getInt() != 0) {
            document.ksGetObjParam(sizeObjectRef, ko_DimText, ParamType.DIM_TEXT_PARAM);
            document.ksGetObjParam(sizeObjectRef, ko_DoubleValue, DIM_VALUE);

            Variant bitFlag = ko_DimText.getProperty("bitFlag");
            System.out.println(ko_DimText.getProperty("sign"));
            ko_DimText.getProperty("stringFlag");
            ko_DimText.getProperty("style");

            ko_DimText.invoke("GetBitFlagValue", bitFlag);
            KsDynamicArray ksDynamicArray = new KsDynamicArray(ko_DimText.invokeGetComponent("GetTextArr"));

            for (int itemNumber = 0; itemNumber < ksDynamicArray.ksGetArrayCount(); itemNumber++) {
                ksDynamicArray.ksGetArrayItem(itemNumber, ko_Char255);
                System.out.println(ko_Char255.invoke("str"));
            }

            allSizes.add(String.valueOf(ko_DoubleValue.invoke("value").getDouble()));
            sizeObjectRef = ksIterator.getNextElement();
        }
        ksIterator.deleteIterator();
        return allSizes;
    }

    private List<String> getDdimensionObjectList(Document document) {
        List<String> allSizes = new ArrayList<>();
        KsIterator ksIterator = new KsIterator(kompasObject, DDIMENSION_OBJ, ALL_OBJ);
        Variant sizeObjectRef = ksIterator.getFirstElement();

        while (sizeObjectRef.getInt() != 0) {
            document.ksGetObjParam(sizeObjectRef, ko_DimText, ParamType.DIM_TEXT_PARAM);
            document.ksGetObjParam(sizeObjectRef, ko_DoubleValue, DIM_VALUE);

            Variant bitFlag = ko_DimText.getProperty("bitFlag");
            System.out.println(ko_DimText.getProperty("sign"));
            ko_DimText.getProperty("stringFlag");
            ko_DimText.getProperty("style");

            ko_DimText.invoke("GetBitFlagValue", bitFlag);
            KsDynamicArray ksDynamicArray = new KsDynamicArray(ko_DimText.invokeGetComponent("GetTextArr"));

            for (int itemNumber = 0; itemNumber < ksDynamicArray.ksGetArrayCount(); itemNumber++) {
                ksDynamicArray.ksGetArrayItem(itemNumber, ko_Char255);
                System.out.println(ko_Char255.invoke("str"));
            }

            allSizes.add(String.valueOf(ko_DoubleValue.invoke("value").getDouble()));
            sizeObjectRef = ksIterator.getNextElement();
        }
        ksIterator.deleteIterator();
        return allSizes;
    }

    private List<String> getRdimensionObjectList(Document document) {
        List<String> allSizes = new ArrayList<>();
        ActiveXComponent ksIterator = kompasObject.invokeGetComponent("GetIterator");
        ksIterator.invoke("ksCreateIterator", new Variant(RDIMENSION_OBJ.getIndex()), new Variant(ALL_OBJ.getIndex()));
        Variant sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("F"));
        while (sizeObjectRef.getInt() != 0) {
            document.ksGetObjParam(sizeObjectRef, ko_DimText, ParamType.DIM_TEXT_PARAM);
            document.ksGetObjParam(sizeObjectRef, ko_DoubleValue, DIM_VALUE);

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

    private List<String> getRbreakDimensionObjectList(Document document) {
        List<String> allSizes = new ArrayList<>();
        ActiveXComponent ksIterator = kompasObject.invokeGetComponent("GetIterator");
        ksIterator.invoke("ksCreateIterator", new Variant(RBREAKDIMENSION_OBJ.getIndex()), new Variant(ALL_OBJ.getIndex()));
        Variant sizeObjectRef = ksIterator.invoke("ksMoveIterator", new Variant("F"));
        while (sizeObjectRef.getInt() != 0) {
            document.ksGetObjParam(sizeObjectRef, ko_DimText, ParamType.DIM_TEXT_PARAM);
            document.ksGetObjParam(sizeObjectRef, ko_DoubleValue, DIM_VALUE);

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

    private List<String> getLbreakDimensionObjectList(Document document) {
        return new ArrayList<>();
    }

    private List<String> getAbreakDimensionObjectList(Document document) {
        return new ArrayList<>();
    }

    private List<String> getOrdinateDimensionObjectList(Document document) {
        List<String> allTexts = new ArrayList<>();
        KsIterator iterator = new KsIterator(kompasObject, ORDINATEDIMENSION_OBJ, ALL_OBJ);
        Variant elementReference = iterator.getFirstElement();
        while (elementReference.getInt() != 0) {
            document.ksGetObjParam(elementReference, ko_DimText, ParamType.DIM_TEXT_PARAM);
            if (ko_DimText.invoke("GetBitFlagValue", new Variant(_AUTONOMINAL.getValue())).getBoolean()) {
                document.ksGetObjParam(elementReference, ko_DoubleValue, DIM_VALUE);
                allTexts.add(String.valueOf(new DecimalFormat("###.###").format(ko_DoubleValue.invoke("value").getDouble() / 1000.)));
            } else {
                KsDynamicArray ksDynamicArray = new KsDynamicArray(ko_DimText.invokeGetComponent("GetTextArr"));
                if (ksDynamicArray.ksGetArrayType() == CHAR_STR_ARR.getIndex()) {
                    for (int itemNumber = 0; itemNumber < ksDynamicArray.ksGetArrayCount(); itemNumber++) {
                        ksDynamicArray.ksGetArrayItem(itemNumber, ko_Char255);
                        allTexts.add(ko_Char255.invoke("str").toString());
                    }
                } else if (ksDynamicArray.ksGetArrayType() == TEXT_LINE_ARR.getIndex()) {
                    StringBuilder sb = new StringBuilder();
                    for (int lineNumber = 0; lineNumber < ksDynamicArray.ksGetArrayCount(); lineNumber++) {
                        ksDynamicArray.ksGetArrayItem(lineNumber, ksTextLineParam);
                        KsDynamicArray textItemArray = new KsDynamicArray(ksTextLineParam.invokeGetComponent("GetTextItemArr"));
                        for (int itemNumber = 0; itemNumber < textItemArray.ksGetArrayCount(); itemNumber++) {
                            textItemArray.ksGetArrayItem(itemNumber, ksTextItemParam);
                            sb.append(ksTextItemParam.invoke("s").getString()).append(" ");
                        }
                        textItemArray.ksDeleteArray();
                    }
                    allTexts.add(sb.toString().replaceAll("[\\s]{2,}", " "));
                }
                ksDynamicArray.ksDeleteArray();
            }
            elementReference = iterator.getNextElement();
        }
        iterator.deleteIterator();
        return allTexts;
    }

    private List<String> getArcDimensionObjectList(Document document) {
        return new ArrayList<>();
    }

    public List<String> getAllTableDataFromDocument(Path drawing) {
        List<String> allTableData = new ArrayList<>();
        KsIterator iterator = new KsIterator(kompasObject, TABLE_OBJ, ALL_OBJ);
        for (Document document : this.openedDocuments) {
            if (document.getPath(ksDocumentParam).equals(drawing.toAbsolutePath())) {
                Variant tableObjectRef = iterator.getFirstElement();
                while (tableObjectRef.getInt() != 0) {
                    document.ksOpenTable(tableObjectRef);
                    StringBuilder sb = new StringBuilder();
                    while (document.ksGetTableColumnText(new Variant(0, true), ksTextParam)) {
                        KsDynamicArray textLineArr = new KsDynamicArray(ksTextParam.invokeGetComponent("GetTextLineArr"));
                        for (int index = 0; index < textLineArr.ksGetArrayCount(); index++) {
                            textLineArr.ksGetArrayItem(index, ksTextLineParam);
                            KsDynamicArray textItemArr = new KsDynamicArray(ksTextLineParam.invokeGetComponent("GetTextItemArr"));
                            for (int itemNumber = 0; itemNumber < textItemArr.ksGetArrayCount(); itemNumber++) {
                                textItemArr.ksGetArrayItem(itemNumber, ksTextItemParam);
                                sb.append(ksTextItemParam.invoke("s").getString()).append(" ");
                            }
                            textItemArr.ksDeleteArray();
                        }
                        textLineArr.ksDeleteArray();
                    }
                    document.ksEndObj();
                    tableObjectRef = iterator.getNextElement();
                    allTableData.add(sb.toString().replaceAll("[\\s]{2,}", " "));
                    sb.setLength(0);
                }
            }
        }
        iterator.deleteIterator();
        return allTableData;
    }


    public List<String> getAllLineSizesFromDocument(Path document) {
        for (Document drawing : openedDocuments) {
            if (drawing.getPath(ksDocumentParam).equals(document.toAbsolutePath())) {
                return getLdimensionObjectList(drawing);
            }
        }
        return new ArrayList<>();
    }

    public List<String> getAllHeightMartSizesFromDocument(Path document) {
        for (Document drawing : this.openedDocuments) {
            if (drawing.getPath(ksDocumentParam).equals(document.toAbsolutePath())) {
                return getOrdinateDimensionObjectList(drawing);
            }
        }
        return new ArrayList<>();
    }


    public boolean openStamp(Path drawing) {
        for (Document document : this.openedDocuments) {
            DocumentMetaData documentMetaData = document.getFileData(ksDocumentParam);
            if (documentMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
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
        for (Document document : this.openedDocuments) {
            DocumentMetaData documentMetaData = document.getFileData(ksDocumentParam);
            if (documentMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
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
        for (Document document : this.openedDocuments) {
            DocumentMetaData documentMetaData = document.getFileData(ksDocumentParam);
            if (documentMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
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
        for (Document document : this.openedDocuments) {
            DocumentMetaData documentMetaData = document.getFileData(ksDocumentParam);
            if (documentMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                try {
                    return document.saveDocument(drawing);
                } catch (Exception ex) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean cleanStampCell(KsStampEnum cell, Path drawing) {
        for (Document document : this.openedDocuments) {
            DocumentMetaData documentMetaData = document.getFileData(ksDocumentParam);
            if (documentMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
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
        for (Document document : this.openedDocuments) {
            DocumentMetaData documentMetaData = document.getFileData(ksDocumentParam);
            if (documentMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
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
        for (Document document : this.openedDocuments) {
            DocumentMetaData documentMetaData = document.getFileData(ksDocumentParam);
            if (documentMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
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
        for (Document document : this.openedDocuments) {
            DocumentMetaData documentMetaData = document.getFileData(ksDocumentParam);
            if (documentMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                return DocType.valueOf(kompasObject.invoke("ksGetDocumentType", new Variant(document.getReference())).getInt());
            }
        }
        throw new IllegalArgumentException("Unknown drawing format");
    }

    public DrawingCharacteristicsDTO getDrawingSize(Path drawing) {
        DrawingCharacteristicsDTO characteristics = new DrawingCharacteristicsDTO();
        for (Document document : this.openedDocuments) {
            DocumentMetaData documentMetaData = document.getFileData(ksDocumentParam);
            if (documentMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
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
        for (Document document : this.openedDocuments) {
            DocumentMetaData documentMetaData = document.getFileData(ksDocumentParam);
            if (documentMetaData.getFileName().equals(drawing.toAbsolutePath().toString())) {
                ActiveXComponent ksRasterFormatParam = document.rasterFormatParam(params);
                return document.saveAsToRasterFormat(drawing.toAbsolutePath().toString().split("\\.")[0] + params.getImageFormat().getExtension().toLowerCase(), ksRasterFormatParam);
            }
        }
        return false;
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


    public void drawDocumentInWindow(long winRef, Path document) {
        kompasObject.invoke("ksDrawKompasDocument", new Variant(winRef), new Variant(document.toAbsolutePath().toString()));
    }

    public void close() {
        kompasObject.invoke("Quit");
        openedDocuments.clear();
    }
}
