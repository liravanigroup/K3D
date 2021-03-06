package com.kompas.api;

import com.kompas.model.dto.RasterParamDTO;
import com.kompas.model.kompas.DocumentMetaData;
import com.kompas.model.kompas.enums.KsStampEnum;
import com.kompas.model.kompas.enums.documentparam.DocType;
import com.kompas.model.kompas.enums.rasterparam.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by White Stream on 09.02.2017.
 */
public class DrawingProgramTest {

    private static DrawingProgram drawingProgram;
    private Path DRAWING_DWG = Paths.get("src/test/resources/fixtures/autocad.dwg");
    private Path DRAWING_FRW = Paths.get("C:\\Users\\Amsterdam\\IdeaProjects\\K3D\\K3D\\src\\test\\resources\\fixtures\\kompas\\fragments\\plan.frw");
    private Path DRAWING_CDW_1 = Paths.get("src/test/resources/fixtures/Корпус-А2.cdw");
    private Path DRAWING_CDW_2 = Paths.get("src/test/resources/fixtures/ГОТОВО.cdw");
    private Path DRAWING_CDW_METADATA = Paths.get("src/test/resources/fixtures/A4_metadata.cdw");
    private Path DRAWING_CDW_SIZES = Paths.get("src/test/resources/fixtures/A4_sizes.cdw");
    private Path DRAWING_CDW_STAMP = Paths.get("src/test/resources/fixtures/stamp_data.cdw");
    private Path DRAWING_CDW_STAMP_CLEAN = Paths.get("src/test/resources/fixtures/stamp_clean.cdw");
    private Path DRAWING_CDW_MANUAL_SIZE = Paths.get("src/test/resources/fixtures/manual_size.cdw");
    private Path DRAWING_CDW_TABLE = Paths.get("src\\test\\resources\\fixtures\\kompas\\drawings\\tables\\table_test.cdw");
    private Path DRAWING_CDW_EMPTY_TABLE = Paths.get("src\\test\\resources\\fixtures\\kompas\\drawings\\tables\\empty_table_test.cdw");
    private Path DRAWING_CDW_DIAMETER_SIZES = Paths.get("src/test/resources/fixtures/diameter_sizes.cdw");

    private Path SPECIFICATION = Paths.get("src/test/resources/fixtures/kompas/specifications/spec.spw");

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
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_1);

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_CDW_1);
        assertTrue(isOpened);
        assertTrue(isClosed);
    }

    @Test
    public void shouldOpenFRWDocument() {
        //when
        boolean isOpened = drawingProgram.openDocument(DRAWING_FRW);

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_FRW);
        assertTrue(isOpened);
        assertTrue(isClosed);
    }

    @Test
    @Ignore
    public void shouldOpenDWGDocument() {
        //when
        boolean isOpened = drawingProgram.openDocument(DRAWING_DWG);

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_DWG);
        assertTrue(isOpened);
        assertTrue(isClosed);
    }

    @Test
    public void shouldCloseDrawing() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_1);

        //when
        boolean isClosed = drawingProgram.closeDocument(DRAWING_CDW_1);

        //then
        assertTrue(isOpened);
        assertTrue(isClosed);
    }

    @Test
    public void shouldCloseOneOfBothDocuments() {
        //given
        boolean isOpened1 = drawingProgram.openDocument(DRAWING_CDW_1);
        boolean isOpened2 = drawingProgram.openDocument(DRAWING_CDW_2);

        //when
        boolean isClosed1 = drawingProgram.closeDocument(DRAWING_CDW_1);
        boolean isClosed2 = drawingProgram.closeDocument(DRAWING_CDW_2);

        //then
        assertTrue(isOpened1);
        assertTrue(isOpened2);
        assertTrue(isClosed1);
        assertTrue(isClosed2);
    }

    @Test
    public void shouldFindAllText() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_2);

        //when
        List<String> result = drawingProgram.getAllTextsFromDocument(DRAWING_CDW_2);

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_CDW_2);
        assertTrue(result.size() > 0);
    }

    @Test
    public void shouldFindAllSizes() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_DIAMETER_SIZES);

        //when
        List<String> result = drawingProgram.getAllSizesFromDocument(DRAWING_CDW_DIAMETER_SIZES);
        System.out.println(result);

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_CDW_DIAMETER_SIZES);
        assertTrue(isOpened);
        assertTrue(isClosed);
        assertTrue(result.size() > 0);
    }

    @Test
    public void shouldGetDrawingMetaData() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_METADATA);

        //when
        DocumentMetaData result = drawingProgram.getDrawingMetaData(DRAWING_CDW_METADATA);

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
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_STAMP);

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
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_STAMP);
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
    public void shouldCleanAndSave() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_STAMP_CLEAN);

        //when
        boolean isOpenedStamp = drawingProgram.openStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isCleanedStamp = drawingProgram.cleanStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isClosedStamp = drawingProgram.closeStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isSaved = drawingProgram.saveDocument(DRAWING_CDW_STAMP_CLEAN);

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_CDW_STAMP_CLEAN);
        assertTrue(isOpened);
        assertTrue(isOpenedStamp);
        assertTrue(isCleanedStamp);
        assertTrue(isClosedStamp);
        assertTrue(isSaved);
        assertTrue(isClosed);
    }

    @Test
    public void shouldCleanStampSellAndSave() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_STAMP_CLEAN);

        //when
        boolean isOpenedStamp = drawingProgram.openStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isCleanedStamp = drawingProgram.cleanStampCell(KsStampEnum.ksStPartNumber, DRAWING_CDW_STAMP_CLEAN);
        boolean isClosedStamp = drawingProgram.closeStamp(DRAWING_CDW_STAMP_CLEAN);
        boolean isSaved = drawingProgram.saveDocument(DRAWING_CDW_STAMP_CLEAN);

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_CDW_STAMP_CLEAN);
        assertTrue(isOpened);
        assertTrue(isOpenedStamp);
        assertTrue(isCleanedStamp);
        assertTrue(isClosedStamp);
        assertTrue(isSaved);
        assertTrue(isClosed);
    }

    @Test
    public void shouldSetDataToStamp() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_STAMP_CLEAN);

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
        boolean isSaved = drawingProgram.saveDocument(DRAWING_CDW_STAMP_CLEAN);

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_CDW_STAMP_CLEAN);
        assertTrue(isOpened);
        assertTrue(isOpenedStamp);
        assertTrue(isCleanedStamp);
        assertTrue(isClosedStamp);
        assertTrue(isSaved);
        assertTrue(isClosed);
    }

    @Test
    public void shouldGetAllStampData() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_STAMP);
        boolean isOpenedStamp = drawingProgram.openStamp(DRAWING_CDW_STAMP);

        //when
        System.out.println(drawingProgram.getStampData(DRAWING_CDW_STAMP));

        //then
        boolean isClosedStamp = drawingProgram.closeStamp(DRAWING_CDW_STAMP);
        boolean isClosed = drawingProgram.closeDocument(DRAWING_CDW_STAMP);
        assertTrue(isOpened);
        assertTrue(isOpenedStamp);
        assertTrue(isClosedStamp);
        assertTrue(isClosed);
    }

    @Test
    public void shouldGetDocumentType() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_METADATA);

        //when
        DocType docType = drawingProgram.getDocumentType(DRAWING_CDW_METADATA);

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_CDW_METADATA);
        assertTrue(isOpened);
        assertEquals(DocType.lt_DocSheetStandart, docType);
        assertTrue(isClosed);
    }

    @Test
    public void shouldGetDrawingSize() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_METADATA);

        //when
        System.out.println(drawingProgram.getDrawingSize(DRAWING_CDW_METADATA));

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_CDW_METADATA);
    }

    @Test
    public void shouldGetManualSize() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_MANUAL_SIZE);

        //when
        System.out.println(drawingProgram.getDrawingSize(DRAWING_CDW_MANUAL_SIZE));

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_CDW_MANUAL_SIZE);
    }

    @Test
    public void shouldGetFragmentSize() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_FRW);

        //when
        System.out.println(drawingProgram.getDrawingSize(DRAWING_FRW));

        //then
        boolean isClosed = drawingProgram.closeDocument(DRAWING_FRW);
    }

    @Test
    public void shouldSaveDrawingAsImage() throws IOException {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_FRW);
        Path path = Paths.get("C:\\Users\\Amsterdam\\IdeaProjects\\K3D\\K3D\\src\\test\\resources\\fixtures\\kompas\\fragments\\plan.jpeg");
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

        //when
        drawingProgram.saveDrawingAsImage(params, DRAWING_FRW);

        //then
        assertTrue(isOpened);
        assertTrue(Files.exists(path));

        //cleaning
        Files.delete(path);
    }

    @Test
    public void shouldGetTableData() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_TABLE);

        //when
        List<String> tableDateList = drawingProgram.getAllTableDataFromDocument(DRAWING_CDW_TABLE);

        //then
        assertEquals(2, tableDateList.size());

        //cleaning
        drawingProgram.closeDocument(DRAWING_CDW_TABLE);
    }

    @Test
    public void shouldGetEmptyListOfTableData() {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_CDW_EMPTY_TABLE);

        //when
        List<String> tableDateList = drawingProgram.getAllTableDataFromDocument(DRAWING_CDW_EMPTY_TABLE);

        //then
        assertEquals(0, tableDateList.size());

        //cleaning
        drawingProgram.closeDocument(DRAWING_CDW_EMPTY_TABLE);
    }

    @Test
    public void shouldOpenSpecification() {
        //when
        boolean isOpened = drawingProgram.openDocument(SPECIFICATION);

        //then
        assertTrue(isOpened);

        //cleaning
        boolean isClosed = drawingProgram.closeDocument(SPECIFICATION);
        assertTrue(isClosed);
    }

    @Test
    public void shouldCloseSpecification(){
        //given
        boolean isOpened = drawingProgram.openDocument(SPECIFICATION);
        assertTrue(isOpened);

        //when
        boolean isClosed = drawingProgram.closeDocument(SPECIFICATION);

        //then
        assertTrue(isClosed);
    }
}