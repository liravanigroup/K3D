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
 * Created by White Stream on 12.02.2017.
 */
public class KompasAPI7 implements DrawingProgram {
    @Override
    public void open() {

    }

    @Override
    public boolean openDrawing(File drawing) {
        return false;
    }

    @Override
    public boolean saveDrawing(File drawing) {
        return false;
    }

    @Override
    public boolean closeDrawing(File drawing) {
        return false;
    }

    @Override
    public List<String> getAllTextsFromDocument(File drawing) {
        return null;
    }

    @Override
    public List<Double> getAllSizesFromDocument(File drawing) {
        return null;
    }

    @Override
    public List<String> getAllTableDataFromDocument(File drawing) {
        return null;
    }

    @Override
    public boolean openStamp(File drawing) {
        return false;
    }

    @Override
    public boolean closeStamp(File drawing) {
        return false;
    }

    @Override
    public boolean cleanStamp(File drawing) {
        return false;
    }

    @Override
    public boolean cleanStampCell(KsStampEnum cell, File drawing) {
        return false;
    }

    @Override
    public boolean setStampCellValue(KsStampEnum cell, String value, File drawing) {
        return false;
    }

    @Override
    public boolean saveDrawingAsImage(RasterParamDTO params, File drawing) {
        return false;
    }

    @Override
    public DocType getDocumentType(File drawing) {
        return null;
    }

    @Override
    public StampDTO getStampData(File drawing) {
        return null;
    }

    @Override
    public DrawingCharacteristicsDTO getDrawingSize(File drawing) {
        return null;
    }

    @Override
    public DrawingMetaData getDrawingMetaData(File drawing) {
        return null;
    }

    @Override
    public void close() {

    }
}
