package com.kompas.infrastructure;

import com.kompas.model.kompas.enums.kompasparam.KsHideMessage;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;
import org.junit.Before;
import org.junit.Test;

import static com.kompas.model.kompas.enums.kompasparam.KsHideMessage.ksHideMessageNo;
import static com.kompas.model.kompas.enums.kompasparam.VisibleMode.HIDDEN_MODE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
    public void shouldGetIsWindowVisible() throws Exception {
        //when
        VisibleMode visibleMode = propertyReader.getVisibleMode();

        //then
        assertEquals(HIDDEN_MODE, visibleMode);
        assertFalse(visibleMode.isWindowVisible());
    }

    @Test
    public void shouldGetVisibleMode() throws Exception {
        //when
        VisibleMode visibleMode = propertyReader.getVisibleMode();

        //then
        assertEquals(HIDDEN_MODE, visibleMode);
        assertEquals(1, visibleMode.getVisibleMode());
    }

    @Test
    public void shouldGetKsHideMessage() throws Exception {
        //when
        KsHideMessage ksHideMessage = propertyReader.getKsHideMessage();

        //then
        assertEquals(ksHideMessageNo, ksHideMessage);
        assertEquals(2, ksHideMessage.getValue());
    }

}