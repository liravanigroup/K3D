package com.kompas.api;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by White Stream on 18.02.2017.
 */
public class LineSizeTest {
    private static final int SIZE_OBJECTS_COUNT = 6;
    private static final Path DRAWING_WITH_LINE_SIZES = Paths.get("C:\\Users\\Amsterdam\\IdeaProjects\\K3D\\K3D\\src\\test\\resources\\fixtures\\kompas\\drawings\\sizes\\line_sizes\\line_sizes.cdw");

    private static DrawingProgram drawingProgram = new KompasAPI5();

    @BeforeClass
    public static void initDrawingProgram() {
        drawingProgram.open();
    }

    @AfterClass
    public static void closeDrawingProgram() {
        drawingProgram.close();
    }

    @Test
    public void shouldGetListOfHeightMarkSizes() throws Exception {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_WITH_LINE_SIZES);

        //when
        List<String> textsList = drawingProgram.getAllLineSizesFromDocument(DRAWING_WITH_LINE_SIZES);

        //then
        assertTrue(isOpened);
        assertEquals(SIZE_OBJECTS_COUNT, textsList.size());

        //cleaning
        drawingProgram.closeDocument(DRAWING_WITH_LINE_SIZES);
    }
}
