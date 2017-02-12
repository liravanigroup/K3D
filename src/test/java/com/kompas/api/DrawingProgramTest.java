package com.kompas.api;

import com.kompas.model.dto.RasterParamDTO;
import com.kompas.model.kompas.DrawingMetaData;
import com.kompas.model.kompas.enums.KsStampEnum;
import com.kompas.model.kompas.enums.documentparam.DocType;
import com.kompas.model.kompas.enums.rasterparam.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by White Stream on 09.02.2017.
 */
public class DrawingProgramTest {

    private static DrawingProgram drawingProgram;
    private File DRAWING_FRW = new File("src/test/resources/fixtures/plan.frw");
    private File DRAWING_DWG = new File("src/test/resources/fixtures/autocad.dwg");
    private File DRAWING_CDW_1 = new File("src/test/resources/fixtures/Корпус-А2.cdw");
    private File DRAWING_CDW_2 = new File("src/test/resources/fixtures/ГОТОВО.cdw");
    private File DRAWING_CDW_METADATA = new File("src/test/resources/fixtures/A4_metadata.cdw");
    private File DRAWING_CDW_SIZES = new File("src/test/resources/fixtures/A4_sizes.cdw");
    private File DRAWING_CDW_STAMP = new File("src/test/resources/fixtures/stamp_data.cdw");
    private File DRAWING_CDW_STAMP_CLEAN = new File("src/test/resources/fixtures/stamp_clean.cdw");
    private File DRAWING_CDW_MANUAL_SIZE = new File("src/test/resources/fixtures/manual_size.cdw");
    private File DRAWING_CDW_TABLE = new File("src/test/resources/fixtures/table_test.cdw");

    @BeforeClass
    public static void initDrawingProgram() {
        drawingProgram = new KompasAPI5();
        drawingProgram.open();
    }

    @AfterClass
    public static void closeDrawingProgram() {
        drawingProgram.close();
    }

    @Test
    public void shouldOpenDrawingProgram() throws Exception {
        assertNotNull(drawingProgram);
    }

    @Test
    public void shouldOpenDrawing() {
        //when
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_1);

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_CDW_1);
        assertTrue(isOpened);
        assertTrue(isClosed);
    }

    @Test
    public void shouldOpenFRWDocument(){
        //when
        boolean isOpened = drawingProgram.openDrawing(DRAWING_FRW);

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_FRW);
        assertTrue(isOpened);
        assertTrue(isClosed);
    }

    @Test
    @Ignore
    public void shouldOpenDWGDocument(){
        //when
        boolean isOpened = drawingProgram.openDrawing(DRAWING_DWG);

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_DWG);
        assertTrue(isOpened);
        assertTrue(isClosed);
    }

    @Test
    public void shouldCloseDrawing() {
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_1);

        //when
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_CDW_1);

        //then
        assertTrue(isOpened);
        assertTrue(isClosed);
    }

    @Test
    public void shouldCloseOneOfBothDocuments(){
        //given
        boolean isOpened1 = drawingProgram.openDrawing(DRAWING_CDW_1);
        boolean isOpened2 = drawingProgram.openDrawing(DRAWING_CDW_2);

        //when
        boolean isClosed1 = drawingProgram.closeDrawing(DRAWING_CDW_1);
        boolean isClosed2 = drawingProgram.closeDrawing(DRAWING_CDW_2);

        //then
        assertTrue(isOpened1);
        assertTrue(isOpened2);
        assertTrue(isClosed1);
        assertTrue(isClosed2);
    }

    @Test
    public void shouldFindAllText(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_2);

        //when
        List<String> result = drawingProgram.getAllTextsFromDocument(DRAWING_CDW_2);

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_CDW_2);
        assertTrue(result.size() > 0);
    }

    @Test
    public void shouldFindAllSizes(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_SIZES);

        //when
        List<Double> result = drawingProgram.getAllSizesFromDocument(DRAWING_CDW_SIZES);
        System.out.println(result);

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_CDW_SIZES);
        assertTrue(isOpened);
        assertTrue(isClosed);
        assertTrue(result.size() > 0);
    }

    @Test
    public void shouldGetDrawingMetaData(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_METADATA);

        //when
        DrawingMetaData result = drawingProgram.getDrawingMetaData(DRAWING_CDW_METADATA);

        System.out.println(result.getAuthor());
        System.out.println(result.getComment());
        System.out.println(result.getCompany());
        System.out.println(result.getFileName());
        System.out.println(result.getRegime());
        System.out.println(result.getDocType());
    }

    @Test
    public void shouldOpenStamp() {
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_STAMP);

        //when
        boolean isOpenedStamp = drawingProgram.openStamp(DRAWING_CDW_STAMP);

        //then
        boolean isClosedStamp = drawingProgram.closeStamp(DRAWING_CDW_STAMP);
        assertTrue(isOpened);
        assertTrue(isOpenedStamp);
        assertTrue(isClosedStamp);
    }

    @Test
    public void shouldCleanStamp() {
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_STAMP);
        boolean isOpenedStamp = drawingProgram.openStamp(DRAWING_CDW_STAMP);

        //when
        boolean isCleanedStamp = drawingProgram.cleanStamp(DRAWING_CDW_STAMP);

        //then
        boolean isClosedStamp = drawingProgram.closeStamp(DRAWING_CDW_STAMP);
        assertTrue(isOpened);
        assertTrue(isOpenedStamp);
        assertTrue(isCleanedStamp);
        assertTrue(isClosedStamp);
    }

    @Test
    public void shouldCleanAndSave(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_STAMP_CLEAN);

        //when
        boolean isOpenedStamp = drawingProgram.openStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isCleanedStamp = drawingProgram.cleanStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isClosedStamp = drawingProgram.closeStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isSaved = drawingProgram.saveDrawing(DRAWING_CDW_STAMP_CLEAN);

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_CDW_STAMP_CLEAN);
        assertTrue(isOpened);
        assertTrue(isOpenedStamp);
        assertTrue(isCleanedStamp);
        assertTrue(isClosedStamp);
        assertTrue(isSaved);
        assertTrue(isClosed);
    }

    @Test
    public void shouldCleanStampSellAndSave(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_STAMP_CLEAN);

        //when
        boolean isOpenedStamp = drawingProgram.openStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isCleanedStamp = drawingProgram.cleanStampCell(KsStampEnum.ksStPartNumber, DRAWING_CDW_STAMP_CLEAN);
        boolean isClosedStamp = drawingProgram.closeStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isSaved = drawingProgram.saveDrawing(DRAWING_CDW_STAMP_CLEAN);

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_CDW_STAMP_CLEAN);
        assertTrue(isOpened);
        assertTrue(isOpenedStamp);
        assertTrue(isCleanedStamp);
        assertTrue(isClosedStamp);
        assertTrue(isSaved);
        assertTrue(isClosed);
    }

    @Test
    public void shouldSetDataToStamp(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_STAMP_CLEAN);

        //when
        boolean isOpenedStamp = drawingProgram.openStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isCleanedStamp = drawingProgram.cleanStamp(DRAWING_CDW_STAMP_CLEAN);

        boolean isSetDataToCell1 = drawingProgram.setStampCellValue(KsStampEnum.ksStPartNumber, "TEXT", DRAWING_CDW_STAMP_CLEAN);
        boolean isSetDataToCell2 = drawingProgram.setStampCellValue(KsStampEnum.ksStDescription, "TEXT", DRAWING_CDW_STAMP_CLEAN);
        boolean isSetDataToCell3 = drawingProgram.setStampCellValue(KsStampEnum.ksStCompany, "TEXT", DRAWING_CDW_STAMP_CLEAN);
        boolean isSetDataToCell4 = drawingProgram.setStampCellValue(KsStampEnum.ksStDocumentCode, "TEXT", DRAWING_CDW_STAMP_CLEAN);
        boolean isSetDataToCell5 = drawingProgram.setStampCellValue(KsStampEnum.ksStScale, "TEXT", DRAWING_CDW_STAMP_CLEAN);
        boolean isSetDataToCell6 = drawingProgram.setStampCellValue(KsStampEnum.ksStAuthor, "TEXT", DRAWING_CDW_STAMP_CLEAN);
        boolean isSetDataToCell7 = drawingProgram.setStampCellValue(KsStampEnum.ksStApprovedBy, "TEXT", DRAWING_CDW_STAMP_CLEAN);
        boolean isSetDataToCell8 = drawingProgram.setStampCellValue(KsStampEnum.ksStCheckedBy, "TEXT", DRAWING_CDW_STAMP_CLEAN);
        boolean isSetDataToCell9 = drawingProgram.setStampCellValue(KsStampEnum.ksStDesigner, "TEXT", DRAWING_CDW_STAMP_CLEAN);
        boolean isSetDataToCell10 = drawingProgram.setStampCellValue(KsStampEnum.ksStMass, "TEXT", DRAWING_CDW_STAMP_CLEAN);
        boolean isSetDataToCell11 = drawingProgram.setStampCellValue(KsStampEnum.ksStMaterial, "TEXT", DRAWING_CDW_STAMP_CLEAN);
        boolean isSetDataToCell12 = drawingProgram.setStampCellValue(KsStampEnum.ksStDocumentName, "TEXT", DRAWING_CDW_STAMP_CLEAN);


        boolean isClosedStamp = drawingProgram.closeStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isSaved = drawingProgram.saveDrawing(DRAWING_CDW_STAMP_CLEAN);

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_CDW_STAMP_CLEAN);
        assertTrue(isOpened);
        assertTrue(isOpenedStamp);
        assertTrue(isCleanedStamp);
        assertTrue(isClosedStamp);
        assertTrue(isSaved);
        assertTrue(isClosed);
    }

    @Test
    public void shouldGetAllStampData(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_STAMP);
        boolean isOpenedStamp = drawingProgram.openStamp(DRAWING_CDW_STAMP);

        //when
        System.out.println(drawingProgram.getStampData(DRAWING_CDW_STAMP));

        //then
        boolean isClosedStamp = drawingProgram.closeStamp(DRAWING_CDW_STAMP);
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_CDW_STAMP);
        assertTrue(isOpened);
        assertTrue(isOpenedStamp);
        assertTrue(isClosedStamp);
        assertTrue(isClosed);
    }

    @Test
    public void shouldGetDocumentType(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_METADATA);

        //when
        DocType docType = drawingProgram.getDocumentType(DRAWING_CDW_METADATA);

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_CDW_METADATA);
        assertTrue(isOpened);
        assertEquals(DocType.lt_DocSheetStandart, docType);
        assertTrue(isClosed);
    }

    @Test
    public void shouldGetDrawingSize(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_METADATA);

        //when
        System.out.println(drawingProgram.getDrawingSize(DRAWING_CDW_METADATA));

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_CDW_METADATA);
    }

    @Test
    public void shouldGetManualSize(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_MANUAL_SIZE);

        //when
        System.out.println(drawingProgram.getDrawingSize(DRAWING_CDW_MANUAL_SIZE));

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_CDW_MANUAL_SIZE);
    }

    @Test
    public void shouldGetFragmentSize(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_FRW);

        //when
        System.out.println(drawingProgram.getDrawingSize(DRAWING_FRW));

        //then
        boolean isClosed = drawingProgram.closeDrawing(DRAWING_FRW);
    }

    @Test
    public void shouldSaveDrawingAsImage(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_FRW);

        //when
        RasterParamDTO params = new RasterParamDTO();

        params.setColorBPP(ColorBPP.PP_COLOR_24);
        params.setColorType(ColorType.COLORVIEW);
        params.setExtResolution(ExtResolution.$96_DPI);
        params.setExtScale(ExtScale.ONE_TO_ONE);
        params.setGrayScale(false);
        params.setImageFormat(ImageFormat.FORMAT_JPG);
        params.setMultiPageOutput(false);
        params.setOnlyThinLine(false);
        params.setRangeIndex(RangeIndex.ALL_PAGES);

        System.out.println(drawingProgram.saveDrawingAsImage(params, DRAWING_FRW));

        //then

    }

    @Test
    public void shouldGetTableData(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_TABLE);

        //when
        System.out.println(drawingProgram.getAllTableDataFromDocument(DRAWING_CDW_TABLE));

        //then


    }

    @Test
    public void shouldGetSystemVersion(){
        drawingProgram.getSystemVersion();
    }

}