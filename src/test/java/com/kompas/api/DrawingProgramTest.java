package com.kompas.api;

import org.junit.AfterClass;
import org.junit.BeforeClass;
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
    private static String PATH_TO_DRAWING = "src\\test\\resoursces\\fixtures\\Корпус-А2.cdw";
    private static String PATH_TO_DRAWING2 = "src\\test\\resoursces\\fixtures\\ГОТОВО.cdw";
    private File drawing = new File(PATH_TO_DRAWING);
    private File drawing2 = new File(PATH_TO_DRAWING2);

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
        boolean isOpened = drawingProgram.openDrawing(drawing);

        //then
        boolean isClosed = drawingProgram.closeDrawing(drawing);
        assertTrue(isOpened);
        assertTrue(isClosed);
    }

    @Test
    public void shouldCloseDrawing() {
        //given
        boolean isOpened = drawingProgram.openDrawing(drawing);

        //when
        boolean isClosed = drawingProgram.closeDrawing(drawing);

        //then
        assertTrue(isOpened);
        assertTrue(isClosed);
    }

    @Test
    public void shouldCloseOneOfBothDocuments(){
        //given
        boolean isOpened1 = drawingProgram.openDrawing(drawing);
        boolean isOpened2 = drawingProgram.openDrawing(drawing2);

        //when
        boolean isClosed1 = drawingProgram.closeDrawing(drawing);
        boolean isClosed2 = drawingProgram.closeDrawing(drawing2);

        //then
        assertTrue(isOpened1);
        assertTrue(isOpened2);
        assertTrue(isClosed1);
        assertTrue(isClosed2);
    }

    @Test
    public void shouldFindAllText(){
        //given
        boolean isOpened = drawingProgram.openDrawing(drawing2);

        //when
        List<String> result = drawingProgram.getAllTextsFromDocument(drawing2);

        //then
        System.out.println(result);

    }


}