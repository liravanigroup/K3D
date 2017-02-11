package com.kompas.api;

import com.kompas.model.kompas.DrawingMetaData;

import java.io.File;
import java.util.List;

/**
 * Created by White Stream on 08.02.2017.
 */
public interface DrawingProgram {
    void open();

    boolean openDrawing(File drawing);

    boolean closeDrawing(File drawing);

    List<String> getAllTextsFromDocument(File drawing);

    List<String> getAllSizesFromDocument(File drawing);

    DrawingMetaData getDrawingMetaData(File drawing);

    void close();
}
