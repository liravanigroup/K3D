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
public class HeightMartSizeTest {

    private static final int SIZE_OBJECTS_COUNT = 10;
    private static final Path DRAWING_WITH_HEIGHT_MARK = Paths.get("C:\\Users\\Amsterdam\\IdeaProjects\\K3D\\K3D\\src\\test\\resources\\fixtures\\kompas\\drawings\\sizes\\height_mart_size\\height_mart_size.cdw");
    private static final Path DRAWING_WITHOUT_HEIGHT_MARK = Paths.get("C:\\Users\\Amsterdam\\IdeaProjects\\K3D\\K3D\\src\\test\\resources\\fixtures\\kompas\\drawings\\sizes\\height_mart_size\\empty_height_mart_size.cdw");

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
        boolean isOpened = drawingProgram.openDocument(DRAWING_WITH_HEIGHT_MARK);

        //when
        List<String> textsList = drawingProgram.getAllHeightMartSizesFromDocument(DRAWING_WITH_HEIGHT_MARK);

        //then
        assertTrue(isOpened);
        assertEquals(SIZE_OBJECTS_COUNT, textsList.size());

        //cleaning
        drawingProgram.closeDocument(DRAWING_WITH_HEIGHT_MARK);
    }

    @Test
    public void shouldGetEmptyListOfSizes() throws Exception {
        //given
        boolean isOpened = drawingProgram.openDocument(DRAWING_WITHOUT_HEIGHT_MARK);

        //when
        List<String> textsList = drawingProgram.getAllHeightMartSizesFromDocument(DRAWING_WITHOUT_HEIGHT_MARK);

        //then
        assertTrue(isOpened);
        assertEquals(0, textsList.size());

        //cleaning
        drawingProgram.closeDocument(DRAWING_WITHOUT_HEIGHT_MARK);
    }
}
