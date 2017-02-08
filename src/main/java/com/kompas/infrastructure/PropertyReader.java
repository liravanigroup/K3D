package com.kompas.infrastructure;

import com.kompas.model.kompas.enums.kompasparam.KsHideMessage;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.kompas.model.kompas.enums.kompasparam.KsHideMessage.ksHideMessageNo;
import static com.kompas.model.kompas.enums.kompasparam.KsHideMessage.ksShowMessage;
import static com.kompas.model.kompas.enums.kompasparam.VisibleMode.HIDDEN_MODE;
import static com.kompas.model.kompas.enums.kompasparam.VisibleMode.VISIBLE_MODE;

/**
 * Created by White Stream on 08.02.2017.
 */
public class PropertyReader {

    private static final String PATH_TO_PROPERTIES = "src/main/resources/kompas.properties";
    private Properties properties;

    public PropertyReader() {
        readProperty();
    }

    private void readProperty() {
        try(FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException ignored) {

        }
    }

    public VisibleMode getVisibleMode() {
        return Boolean.getBoolean(properties.getProperty("visiblemode")) ? VISIBLE_MODE : HIDDEN_MODE;
    }

    public KsHideMessage getKsHideMessage(){
        return Boolean.getBoolean(properties.getProperty("hidemessages")) ? ksHideMessageNo : ksShowMessage;
    }
}
