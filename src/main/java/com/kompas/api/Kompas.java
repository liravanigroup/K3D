package com.kompas.api;

import com.kompas.infrastructure.PropertyReader;
import com.kompas.model.kompas.Kompas3D;

import java.io.File;

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
    public void close() {
        kompas3D.close();
    }
}
