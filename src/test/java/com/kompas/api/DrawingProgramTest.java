package com.kompas.api;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by White Stream on 09.02.2017.
 */
public class DrawingProgramTest {

    private static DrawingProgram drawingProgram;
    private static String PATH_TO_DRAWING = "src\\test\\resoursces\\fixtures\\Корпус-А2.cdw";
    private File drawing = new File(PATH_TO_DRAWING);

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
}