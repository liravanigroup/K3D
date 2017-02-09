package com.kompas.model.kompas;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.infrastructure.PropertyReader;
import com.kompas.model.kompas.enums.StructType2DEnum;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.kompas.model.kompas.enums.kompasparam.VisibleMode.HIDDEN_MODE;

public class Kompas3D {

    private ActiveXComponent ksDocumentParam;
    private ActiveXComponent ksTextItemParam;
    private ActiveXComponent ksTextLineParam;
    private ActiveXComponent ksStandartSheet;
    private ActiveXComponent ksSheetOptions;
    private ActiveXComponent ksSheetPar;

    private ActiveXComponent kompasObject;
    private PropertyReader property;
    private List<KsDocument2D> documents = new ArrayList<>();

    public Kompas3D(PropertyReader property) {
        this.property = property;
    }

    public void open(){
        this.kompasObject = new ActiveXComponent(property.getAPI5());
        kompasObject.setProperty("visible", property.getVisibleMode().isWindowVisible());
        kompasObject.invokeGetComponent(property.getAPI7()).setProperty("HideMessage", property.getKsHideMessage().getValue());

        this.ksDocumentParam = getParamStruct(StructType2DEnum.ksDocumentParam);
        this.ksTextItemParam = getParamStruct(StructType2DEnum.ksTextItemParam);
        this.ksTextLineParam = getParamStruct(StructType2DEnum.ksTextLineParam);
        this.ksStandartSheet = getParamStruct(StructType2DEnum.ksStandartSheet);
        this.ksSheetOptions = getParamStruct(StructType2DEnum.ksSheetOptions);
        this.ksSheetPar = getParamStruct(StructType2DEnum.ksSheetPar);
    }

    private ActiveXComponent getParamStruct(StructType2DEnum structType) {
        return kompasObject.invokeGetComponent("GetParamStruct", new Variant(structType.getStructTypeNum()));
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
        for (KsDocument2D document : documents){
            DrawingMetaData drawingMetaData = document.getFileData(ksDocumentParam);
            if(drawingMetaData.getFileName().equals(drawing.getAbsolutePath())){
                return document.ksCloseDocument();
            }
        }
        return false;
    }

    public void close() {
        kompasObject.invoke("Quit");
    }
}
