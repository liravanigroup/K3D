package com.kompas.api;

import com.kompas.model.dto.DrawingCharacteristicsDTO;
import com.kompas.model.dto.RasterParamDTO;
import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.DrawingMetaData;
import com.kompas.model.kompas.enums.KsStampEnum;
import com.kompas.model.kompas.enums.documentparam.DocType;

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

    List<String> getAllSizesFromDocument(File drawing);

    List<String> getAllTableDataFromDocument(File drawing);

    boolean openStamp(File drawing);

    boolean closeStamp(File drawing);

    boolean cleanStamp(File drawing);

    boolean cleanStampCell(KsStampEnum cell, File drawing);

    boolean setStampCellValue(KsStampEnum cell, String value, File drawing);

    boolean saveDrawingAsImage(RasterParamDTO params, File drawing);

    DocType getDocumentType(File drawing);

    StampDTO getStampData(File drawing);

    DrawingCharacteristicsDTO getDrawingSize(File drawing);

    DrawingMetaData getDrawingMetaData(File drawing);

    void getSystemVersion();

    void close();

}
