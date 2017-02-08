package com.kompas.api;

import com.kompas.model.kompas.Kompas3D;

/**
 * Created by White Stream on 08.02.2017.
 */
public class Kompas implements DrawingProgram {

    private Kompas3D kompas3D = new Kompas3D();

    @Override
    public void open() {
        kompas3D.open();
    }
}
