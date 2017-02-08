package com.kompas.domain.kompas;

import com.idrawing.filemanager.domain.LocalFile;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.domain.dto.StampDTO;
import com.kompas.domain.kompas.enums.DynamicArrayType;
import com.kompas.domain.kompas.enums.StructType2DEnum;
import com.kompas.domain.kompas.enums.kompasparam.KsHideMessageEnum;
import com.kompas.domain.kompas.enums.kompasparam.VisibleMode;
import com.kompas.domain.kompas.enums.rasterparam.*;
import com.kompas.model.Drawing;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.Queue;

import static com.kompas.domain.kompas.enums.kompasparam.VisibleMode.HIDDEN_MODE;
import static com.kompas.domain.kompas.enums.rasterparam.ColorBPP.PP_COLOR_24;
import static com.kompas.domain.kompas.enums.rasterparam.ColorType.COLOROBJECT;
import static com.kompas.domain.kompas.enums.rasterparam.ExtScale.ONE_TO_ONE;
import static com.kompas.domain.kompas.enums.rasterparam.Format.FORMAT_JPG;
import static com.kompas.domain.kompas.enums.rasterparam.RangeIndex.ALL_PAGES;

public class Kompas3D implements AutoCloseable {

    private ActiveXComponent kompasObject;
    private Queue<LocalFile> processingFiles;

    private ActiveXComponent ksDocumentParam;
    private ActiveXComponent ksTextItemParam;
    private ActiveXComponent ksTextLineParam;
    private ActiveXComponent ksStandartSheet;
    private ActiveXComponent ksSheetOptions;
    private ActiveXComponent ksSheetPar;

    public Kompas3D(VisibleMode visibleMode, KsHideMessageEnum ksHideMessageEnum) {
        this.kompasObject = new ActiveXComponent("KOMPAS.Application.5");
        setVisibleMode(visibleMode);
        hideMessage(ksHideMessageEnum);
    }

    private void setVisibleMode(VisibleMode visibleMode) {
        kompasObject.setProperty("visible", visibleMode.getBoolVal());
    }

    private void hideMessage(KsHideMessageEnum ksHideMessageEnum) {
        ksGetApplication7().setProperty("HideMessage", ksHideMessageEnum.value());
    }

    private ActiveXComponent ksGetApplication7() {
        return kompasObject.invokeGetComponent("ksGetApplication7");
    }

    private void initStructTypes() {
        this.ksDocumentParam = getParamStruct(StructType2DEnum.ksDocumentParam);
        this.ksTextItemParam = getParamStruct(StructType2DEnum.ksTextItemParam);
        this.ksTextLineParam = getParamStruct(StructType2DEnum.ksTextLineParam);
        this.ksStandartSheet = getParamStruct(StructType2DEnum.ksStandartSheet);
        this.ksSheetOptions = getParamStruct(StructType2DEnum.ksSheetOptions);
        this.ksSheetPar = getParamStruct(StructType2DEnum.ksSheetPar);
    }

    public ActiveXComponent getParamStruct(StructType2DEnum structType) {
        return kompasObject.invokeGetComponent("GetParamStruct", new Variant(structType.getNumber()));
    }


    public boolean openDrawing(KsDocument2D ksDocument2D, String path, VisibleMode visibleMode) {
        return ksDocument2D.ksOpenDocument(path, visibleMode);
    }


    public void setProcessingFiles(Queue<LocalFile> processingFiles) {
        this.processingFiles = processingFiles;
    }

    public boolean ksMessage(String message) {
        return kompasObject.invoke("ksMessage", message).getBoolean();
    }

    public KsDocument2D getActiveDocument2D() {
        return new KsDocument2D(kompasObject.invokeGetComponent("ActiveDocument2D"));
    }

    public ActiveXComponent getDocument2D() {
        return kompasObject.invokeGetComponent("Document2D");
    }

    public KsDocument2D getKsDocument2D() {
        return new KsDocument2D(getDocument2D());
    }


    public ActiveXComponent getDynamicArray(DynamicArrayType dynamicArrayType) {
        return kompasObject.invokeGetComponent("GetDynamicArray", new Variant(dynamicArrayType.getNumber()));
    }

    private long ksResultNULL() {
        return kompasObject.invoke("ksResultNULL").getInt();
    }


    public void saveDrawingsAs(ColorBPP colorBPP, ColorType colorType, ExtResolution extResolution, ExtScale extScale,
                               Format format, boolean grayScale, boolean multiPageOutput, boolean onlyThinLine, RangeIndex rangeIndex) {
        while (!processingFiles.isEmpty()) {
            LocalFile file = processingFiles.poll();
            KsDocument2D ksDocument2D = getKsDocument2D();
            ActiveXComponent rasterParam = ksDocument2D.rasterFormatParam(colorBPP, colorType, extResolution, extScale, format, grayScale, multiPageOutput, onlyThinLine, rangeIndex);
            if (openDrawing(ksDocument2D, file.getPathString().getValue(), HIDDEN_MODE)) {

                String ext = ".jpg";
                String path = "D:/test/";

                ksDocument2D.saveAsToRasterFormat(path + file.getName() + ext, rasterParam);
                closeDrawing(ksDocument2D);
            }
        }
    }

    public void saveDrawingAs(ColorBPP colorBPP, ColorType colorType, ExtResolution extResolution, ExtScale extScale, Format format, boolean grayScale, boolean multiPageOutput, boolean onlyThinLine, RangeIndex rangeIndex, LocalFile file, KsDocument2D ksDocument2D) {
        ActiveXComponent rasterParam = ksDocument2D.rasterFormatParam(colorBPP, colorType, extResolution, extScale, format, grayScale, multiPageOutput, onlyThinLine, rangeIndex);
        ksDocument2D.saveAsToRasterFormat("C:\\Users\\Amsterdam\\IdeaProjects\\Kompas3D\\kompas3D\\src\\main\\resources\\images\\" + file.getName() + ".jpg", rasterParam);
    }

    public void createIndex(ObservableList<Drawing> drawingData) {
        initStructTypes();
        while (!processingFiles.isEmpty()) {
            KsDocument2D ksDocument2D = new KsDocument2D(getDocument2D());
            LocalFile file = processingFiles.poll();
            String path = file.getPathString().getValue();
            if (openDrawing(ksDocument2D, path, HIDDEN_MODE)) {
                try {
                    ksDocument2D.getFileData(ksDocumentParam);
                    KsStamp ksStamp = ksDocument2D.GetStamp();
                    StampDTO stampDTO = ksStamp.getStampDTO(ksTextLineParam, ksTextItemParam);

                    saveDrawingAs(PP_COLOR_24, COLOROBJECT, ExtResolution.$36_DPI, ONE_TO_ONE, FORMAT_JPG, false, false, false, ALL_PAGES, file, ksDocument2D);
                    drawingData.add(new Drawing(stampDTO, file));

                } finally {
                    closeDrawing(ksDocument2D);
                }
            }
        }
    }

    public void saveDrawingAsImage(String path, ColorBPP colorBPP, ColorType colorType, ExtResolution extResolution, ExtScale extScale, Format format, boolean grayScale, boolean multiPageOutput, boolean onlyThinLine, RangeIndex rangeIndex, LocalFile file) {
        KsDocument2D ksDocument2D = new KsDocument2D(getDocument2D());
        ActiveXComponent rasterParam = ksDocument2D.rasterFormatParam(colorBPP, colorType, extResolution, extScale, format, grayScale, multiPageOutput, onlyThinLine, rangeIndex);
        if (openDrawing(ksDocument2D, file.getPathString().getValue(), HIDDEN_MODE)) {
            ksDocument2D.saveAsToRasterFormat(path + file.getName() + format.getExtension(), rasterParam);
            closeDrawing(ksDocument2D);
        }
    }

    public void getGetSheetParam() {
//        System.out.println(ksSheetOptions.invoke("Init"));
//        System.out.println(ksSheetOptions.getProperty("layoutName"));
//        System.out.println(ksSheetOptions.getProperty("shtType"));
//        System.out.println(ksSheetOptions.getProperty("sheetType"));
//        ActiveXComponent ksStandartSheet = ksSheetOptions.invokeGetComponent("GetSheetParam", new Variant(false));
//        System.out.println(ksStandartSheet.getProperty("format"));
//        System.out.println(ksStandartSheet.getProperty("direct"));
//        System.out.println(ksStandartSheet.getProperty("multiply"));
//
//        ActiveXComponent ksSheetSize = ksSheetOptions.invokeGetComponent("GetSheetParam", new Variant(true));
//        System.out.println(ksSheetSize.getProperty("height"));
//        System.out.println(ksSheetSize.getProperty("width"));

//        ActiveXComponent ksStandartSheet2 = ksSheetPar.invokeGetComponent("GetSheetParam");
//        System.out.println(ksStandartSheet2.getProperty("format"));
//        System.out.println(ksStandartSheet2.getProperty("direct"));
//        System.out.println(ksStandartSheet2.getProperty("multiply"));
        ksSheetPar.getProperty("shtType");

    }


    public void getPreview(File file, Long window) throws InterruptedException {
        initStructTypes();
        KsDocument2D ksDocument2D = new KsDocument2D(getDocument2D());
//        System.out.println(openDrawing(ksDocument2D, file.getPath(), HIDDEN_MODE));

        System.out.println(kompasObject.invoke("ksDrawSlide", new Variant(window, true), new Variant(100)));
        System.out.println(kompasObject.invoke("ksDrawKompasDocument", new Variant(window, true), new Variant(file.getAbsolutePath(), true)));



//        System.out.println(closeDrawing(ksDocument2D));
    }

    public void getAllDrawingInfo(File drawing) {
        initStructTypes();
        KsDocument2D ksDocument2D = new KsDocument2D(getDocument2D());
        System.out.println(openDrawing(ksDocument2D, drawing.getPath(), HIDDEN_MODE));
//        DrawingMetaData drawingMetaData = ksDocument2D.getFileData(ksDocumentParam);
//        System.out.println(drawingMetaData);
//        KsStamp ksStamp = ksDocument2D.GetStamp();
//        StampDTO stampDTO = ksStamp.getStampDTO(ksTextLineParam, ksTextItemParam);
//        System.out.println(stampDTO);


//        System.out.println(ksStandartSheet.invoke("Init"));
//        ksDocument2D.ksGetObjParam(ParamType.ALLPARAM, ksStandartSheet);
//        System.out.println(ksStandartSheet.getProperty("direct"));
//        System.out.println(ksStandartSheet.getProperty("format"));
//        System.out.println(ksStandartSheet.getProperty("multiply"));

//        KsDocument2D doc2d = getActiveDocument2D();
//        ActiveXComponent so = getParamStruct(StructType2DEnum.ksSheetOptions);
//        doc2d.ksGetDocOptions(4, so);
//        if(Boolean.valueOf(String.valueOf(so.getProperty("sheetType")))){
//            ActiveXComponent sh = so.invokeGetComponent("GetSheetParam", new Variant(true));
//            System.out.println(sh.getProperty("width"));
//            System.out.println(sh.getProperty("height"));
//        }else{
//            ActiveXComponent sh = so.invokeGetComponent("GetSheetParam", new Variant(false));
//            System.out.println(sh.getProperty("format"));
//            System.out.println(sh.getProperty("multiply"));
//            System.out.println(sh.getProperty("direct"));
//        }


//        System.out.println(ksSheetOptions.invoke("Init"));
//        ksDocument2D.ksGetObjParam(ParamType.ALLPARAM, ksSheetOptions);
//        System.out.println(ksSheetOptions.getProperty("sheetType"));
//        System.out.println(ksSheetOptions.getProperty("layoutName"));
//        System.out.println(ksSheetOptions.getProperty("shtType"));
//
//        ActiveXComponent ksStandartSheet = ksSheetOptions.invokeGetComponent("GetSheetParam", new Variant(false));
//        System.out.println(ksStandartSheet.getProperty("direct"));
//        System.out.println(ksStandartSheet.getProperty("format"));


        System.out.println(closeDrawing(ksDocument2D));
    }


    private boolean closeDrawing(KsDocument2D ksDocument2D) {
        return ksDocument2D.ksCloseDocument();
    }

    @Override
    public void close() {
        kompasObject.invoke("Quit");
    }
}
