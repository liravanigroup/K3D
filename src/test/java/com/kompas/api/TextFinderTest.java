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
public class TextFinderTest {

    private static final int TEXT_OBJECTS_COUNT = 21;
    private static final Path DRAWING_WITH_TEXT = Paths.get("src/test/resources/fixtures/kompas/drawings/texts/text_test.cdw");
    private static final Path DRAWING_WITHOUT_TEXT = Paths.get("src/test/resources/fixtures/kompas/drawings/texts/empty_text_test.cdw");
    private static final Path DRAWING_NO_TEXT = Paths.get("src/test/resources/fixtures/kompas/drawings/texts/no_text_test.cdw");

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
    public void shouldGetListOfTexts() throws Exception {
        //given
        drawingProgram.openDocument(DRAWING_WITH_TEXT);

        //when
        List<String> textsList = drawingProgram.getAllTextsFromDocument(DRAWING_WITH_TEXT);

        //then
        assertEquals(TEXT_OBJECTS_COUNT, textsList.size());

        //cleaning
        drawingProgram.closeDocument(DRAWING_WITH_TEXT);
    }

    @Test
    public void shouldGetEmptyListOfTexts(){
        //given
        drawingProgram.openDocument(DRAWING_WITHOUT_TEXT);

        //when
        List<String> textsList = drawingProgram.getAllTextsFromDocument(DRAWING_WITHOUT_TEXT);

        //given
        assertEquals(0, textsList.size());

        //cleaning
        drawingProgram.closeDocument(DRAWING_WITHOUT_TEXT);
    }

    @Test
    public void shouldGetEmptyListOfTextsWhenDrawingHaveOtherObjects(){
        //given
        drawingProgram.openDocument(DRAWING_NO_TEXT);

        //when
        List<String> textsList = drawingProgram.getAllTextsFromDocument(DRAWING_NO_TEXT);

        //given
        assertEquals(0, textsList.size());

        //cleaning
        drawingProgram.closeDocument(DRAWING_NO_TEXT);
    }

    @Test
    public void shouldGetAllTextsFor05Second(){
        //given
        drawingProgram.openDocument(DRAWING_WITH_TEXT);

        //when
        long startTime = System.currentTimeMillis();
        List<String> textsList = drawingProgram.getAllTextsFromDocument(DRAWING_WITH_TEXT);
        long result = System.currentTimeMillis() - startTime;

        //then
        assertTrue(500 > result);

        //cleaning
        drawingProgram.closeDocument(DRAWING_WITH_TEXT);
    }
}
