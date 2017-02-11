package com.kompas.api;

import com.kompas.infrastructure.PropertyReader;
import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.DrawingMetaData;
import com.kompas.model.kompas.Kompas3D;
import com.kompas.model.kompas.enums.KsStampEnum;

import java.io.File;
import java.util.List;

/**
 * Created by White Stream on 08.02.2017.
 */
public class Kompas implements DrawingProgram {

    private Kompas3D kompas3D;

    public Kompas() {
        this.kompas3D = new Kompas3D(new PropertyReader());
    }

    @Override
    public void open() {
        kompas3D.open();
    }

    @Override
    public boolean openDrawing(File drawing) {
        return kompas3D.openDrawing(drawing);
    }

    @Override
    public boolean saveDrawing(File drawing) {
        return kompas3D.saveDrawing(drawing);
    }

    @Override
    public boolean closeDrawing(File drawing) {
        return kompas3D.closeDrawing(drawing);
    }

    @Override
    public List<String> getAllTextsFromDocument(File drawing) {
        return kompas3D.getAllTextFromDocument(drawing);
    }

    @Override
    public List<Double> getAllSizesFromDocument(File drawing) {
        return kompas3D.getAllSizesFromDocument(drawing);
    }

    @Override
    public boolean openStamp(File drawing) {
        return kompas3D.openStamp(drawing);
    }

    @Override
    public boolean closeStamp(File drawing) {
        return kompas3D.closeStamp(drawing);
    }

    @Override
    public boolean cleanStamp(File drawing) {
        return kompas3D.cleanStamp(drawing);
    }

    @Override
    public boolean cleanStampCell(KsStampEnum cell, File drawing) {
        return kompas3D.cleanStampCell(cell, drawing);
    }

    @Override
    public boolean setStampCellValue(KsStampEnum cell, String value, File drawing) {
        return kompas3D.setStampCellValue(cell, value, drawing);
    }

    @Override
    public StampDTO getStampData(File drawing) {
        return kompas3D.getStampData(drawing);
    }

    @Override
    public DrawingMetaData getDrawingMetaData(File drawing) {
        return kompas3D.getDrawingMetaData(drawing);
    }

    @Override
    public void close() {
        kompas3D.close();
    }
}
