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
import static java.lang.Boolean.getBoolean;

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
        return getBoolean(properties.getProperty("kompas.window.visible")) ? VISIBLE_MODE : HIDDEN_MODE;
    }

    public KsHideMessage getKsHideMessage(){
        return getBoolean(properties.getProperty("kompas.messages.visible")) ? ksShowMessage : ksHideMessageNo;
    }

    public String getAPI5(){
        return properties.getProperty("kompas.api5.version");
    }

    public String getAPI7(){
        return properties.getProperty("kompas.api7.version");
    }
}
