package com.kompas.api;

import com.kompas.model.kompas.DrawingMetaData;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.util.List;

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

    @BeforeClass
    public static void initDrawingProgram() {
        drawingProgram = new Kompas();
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
        assertTrue(result.size() > 0);
    }

    @Test
    public void shouldFindAllSizes(){
        //given
        boolean isOpened = drawingProgram.openDrawing(DRAWING_CDW_SIZES);

        //when
        List<String> result = drawingProgram.getAllTextsFromDocument(DRAWING_CDW_2);

        //then
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
        System.out.println(result.getType());
    }
}