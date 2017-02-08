package com.kompas.infrastructure;

import com.kompas.model.kompas.enums.kompasparam.KsHideMessage;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by White Stream on 08.02.2017.
 */
public class PropertyReaderTest {

    private PropertyReader propertyReader;

    @Before
    public void setUp() throws Exception {
        propertyReader = new PropertyReader();
    }

    @Test
    public void shouldGetVisibleMode() throws Exception {
        //when
        VisibleMode visibleMode = propertyReader.getVisibleMode();
        int result = visibleMode.getIntVal();

        //then
        assertEquals(0, result);
    }

    @Test
    public void shouldGetKsHideMessage() throws Exception {
        //when
        KsHideMessage ksHideMessage = propertyReader.getKsHideMessage();
        int result = ksHideMessage.value();

        //then
        assertEquals(0, result);
    }

}