package com.kompas.domain.kompas;

import com.kompas.domain.kompas.enums.kompasparam.KsHideMessageEnum;
import com.kompas.domain.kompas.enums.kompasparam.VisibleMode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Sergej Povzaniuk on 08.01.2017.
 */
public class Kompas3DTest {

    private static Kompas3D kompas3D;

    @BeforeClass
    public static void startKompas(){
        kompas3D = new Kompas3D(VisibleMode.HIDDEN_MODE, KsHideMessageEnum.ksHideMessageNo);
    }

    @Test
    public void shouldCreateKompas() {
        assertNotNull(kompas3D);
    }

    @Test
    public void shouldGetAllDrawingInfo() {
        File drawing = new File("C:\\Users\\Amsterdam\\IdeaProjects\\Kompas3D\\kompas3D\\src\\test\\resoursces\\fixtures\\drawing_test.cdw");
        kompas3D.getAllDrawingInfo(drawing);
    }

    @AfterClass
    public static void closeKompas(){
        kompas3D.close();
    }

}