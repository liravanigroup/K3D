package com.kompas.api;

import com.kompas.infrastructure.PropertyReader;
import com.kompas.model.kompas.DrawingMetaData;
import com.kompas.model.kompas.Kompas3D;

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
    public boolean closeDrawing(File drawing) {
        return kompas3D.closeDrawing(drawing);
    }

    @Override
    public List<String> getAllTextsFromDocument(File drawing) {
        return kompas3D.getAllTextFromDocument(drawing);
    }

    @Override
    public List<String> getAllSizesFromDocument(File drawing) {
        return kompas3D.getAllSizesFromDocument(drawing);
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
