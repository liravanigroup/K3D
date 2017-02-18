package com.kompas.api;

import com.kompas.model.dto.DrawingCharacteristicsDTO;
import com.kompas.model.dto.RasterParamDTO;
import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.DrawingMetaData;
import com.kompas.model.kompas.enums.KsStampEnum;
import com.kompas.model.kompas.enums.documentparam.DocType;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by White Stream on 08.02.2017.
 */
public interface DrawingProgram {
    void open();

    boolean openDocument(Path document);

    boolean saveDocument(Path document);

    boolean closeDocument(Path document);

    List<String> getAllTextsFromDocument(Path document);

    List<String> getAllSizesFromDocument(Path document);

    List<String> getAllTableDataFromDocument(Path document);

    boolean openStamp(Path document);

    boolean closeStamp(Path document);

    boolean cleanStamp(Path document);

    boolean cleanStampCell(KsStampEnum cell, Path document);

    boolean setStampCellValue(KsStampEnum cell, String value, Path document);

    boolean saveDrawingAsImage(RasterParamDTO params, Path document);

    DocType getDocumentType(Path document);

    StampDTO getStampData(Path document);

    DrawingCharacteristicsDTO getDrawingSize(Path document);

    DrawingMetaData getDrawingMetaData(Path document);

    void getSystemVersion();

    void close();

    void paint(long winRef);
}
