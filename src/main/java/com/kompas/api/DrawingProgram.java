package com.kompas.api;

import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.DrawingMetaData;
import com.kompas.model.kompas.enums.KsStampEnum;

import java.io.File;
import java.util.List;

/**
 * Created by White Stream on 08.02.2017.
 */
public interface DrawingProgram {
    void open();

    boolean openDrawing(File drawing);

    boolean saveDrawing(File drawing);

    boolean closeDrawing(File drawing);

    List<String> getAllTextsFromDocument(File drawing);

    List<Double> getAllSizesFromDocument(File drawing);

    boolean openStamp(File drawing);

    boolean closeStamp(File drawing);

    boolean cleanStamp(File drawing);

    boolean cleanStampCell(KsStampEnum cell, File drawing);

    boolean setStampCellValue(KsStampEnum cell, String value, File drawing);

    StampDTO getStampData(File drawing);

    DrawingMetaData getDrawingMetaData(File drawing);

    void close();

}
