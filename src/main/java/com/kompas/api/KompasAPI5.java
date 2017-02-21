package com.kompas.api;

import com.kompas.infrastructure.PropertyReader;
import com.kompas.model.dto.DrawingCharacteristicsDTO;
import com.kompas.model.dto.RasterParamDTO;
import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.DocumentMetaData;
import com.kompas.model.kompas.Kompas3D;
import com.kompas.model.kompas.enums.KsStampEnum;
import com.kompas.model.kompas.enums.documentparam.DocType;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by White Stream on 08.02.2017.
 */
public class KompasAPI5 implements DrawingProgram {

    private Kompas3D kompas3D;

    public KompasAPI5() {
        this.kompas3D = new Kompas3D(new PropertyReader());
    }

    @Override
    public void open() {
        kompas3D.open();
    }

    @Override
    public boolean openDocument(Path document) {
        return kompas3D.openDocument(document);
    }

    @Override
    public boolean saveDocument(Path document) {
        return kompas3D.saveDrawing(document);
    }

    @Override
    public boolean closeDocument(Path document) {
        return kompas3D.closeDocument(document);
    }

    @Override
    public List<String> getAllTextsFromDocument(Path document) {
        return kompas3D.getAllTextFromDocument(document);
    }

    @Override
    public List<String> getAllHeightMartSizesFromDocument(Path document) {
        return kompas3D.getAllHeightMartSizesFromDocument(document);
    }

    @Override
    public List<String> getAllLineSizesFromDocument(Path document) {
        return kompas3D.getAllLineSizesFromDocument(document);
    }

    @Override
    public List<String> getAllSizesFromDocument(Path document) {
        return kompas3D.getAllSizesFromDocument(document);
    }

    @Override
    public List<String> getAllTableDataFromDocument(Path document) {
        return kompas3D.getAllTableDataFromDocument(document);
    }

    @Override
    public boolean openStamp(Path drawing) {
        return kompas3D.openStamp(drawing);
    }

    @Override
    public boolean closeStamp(Path drawing) {
        return kompas3D.closeStamp(drawing);
    }

    @Override
    public boolean cleanStamp(Path drawing) {
        return kompas3D.cleanStamp(drawing);
    }

    @Override
    public boolean cleanStampCell(KsStampEnum cell, Path drawing) {
        return kompas3D.cleanStampCell(cell, drawing);
    }

    @Override
    public boolean setStampCellValue(KsStampEnum cell, String value, Path drawing) {
        return kompas3D.setStampCellValue(cell, value, drawing);
    }

    @Override
    public boolean saveDrawingAsImage(RasterParamDTO params, Path drawing) {
        return kompas3D.saveDrawingAsImage(params, drawing);
    }

    @Override
    public DocType getDocumentType(Path drawing) {
        return kompas3D.getDocumentType(drawing);
    }

    @Override
    public StampDTO getStampData(Path drawing) {
        return kompas3D.getStampData(drawing);
    }

    @Override
    public DrawingCharacteristicsDTO getDrawingSize(Path drawing) {
        return kompas3D.getDrawingSize(drawing);
    }

    @Override
    public DocumentMetaData getDrawingMetaData(Path drawing) {
        return kompas3D.getDrawingMetaData(drawing);
    }

    @Override
    public void getSystemVersion() {
        kompas3D.getSystemVersion();
    }

    @Override
    public void close() {
        kompas3D.close();
    }

    @Override
    public void drawDocumentInWindow(long winRef, Path document) {
       kompas3D.drawDocumentInWindow(winRef, document);
    }

    @Override
    public boolean isSameDocument(Path document1, Path document2) {
        return false;
    }
}
