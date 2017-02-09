package com.kompas.api;

import java.io.File;

/**
 * Created by White Stream on 08.02.2017.
 */
public interface DrawingProgram {
    void open();

    boolean openDrawing(File drawing);

    boolean closeDrawing(File drawing);

    void close();
}
