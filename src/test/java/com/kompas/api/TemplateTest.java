package com.kompas.api;

import com.kompas.model.kompas.DocumentMetaData;
import com.kompas.model.templates.enums.TemplateTypeGRAPHIC;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by White Stream on 18.02.2017.
 */
public class TemplateTest {

    private static DrawingProgram drawingProgram = new KompasAPI5();
    private static final Path DRAWING_TEMPLATE_1 = Paths.get("src/test/resources/fixtures/drawings/templates/template_1.cdw");
    private static final Path DRAWING_TEMPLATE_14 = Paths.get("src/test/resources/fixtures/drawings/templates/template_14.cdw");

    @BeforeClass
    public static void initDrawingProgram() {
        drawingProgram.open();
    }

    @AfterClass
    public static void closeDrawingProgram() {
        drawingProgram.close();
    }

    @Test
    public void shouldGetDocumentTemplate1() throws Exception {
        //given
        drawingProgram.openDocument(DRAWING_TEMPLATE_1);

        //when
        DocumentMetaData documentMetaData = drawingProgram.getDrawingMetaData(DRAWING_TEMPLATE_1);

        //given
        assertEquals("Чертеж констр. Первый лист. ГОСТ 2.104-2006. (номер 1)", documentMetaData.getTemplate().getTemplateName());
    }

    @Test
    public void shouldGetDrawingTemplate14() throws Exception {
        //given
        drawingProgram.openDocument(DRAWING_TEMPLATE_14);

        //when
        DocumentMetaData documentMetaData = drawingProgram.getDrawingMetaData(DRAWING_TEMPLATE_14);

        //given
        assertEquals("Без основной надписи. (номер 14)", documentMetaData.getTemplate().getTemplateName());
    }


}
