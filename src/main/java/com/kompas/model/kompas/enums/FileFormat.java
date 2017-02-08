package com.kompas.model.kompas.enums;

/**
 * Created by Sergej Povzaniuk on 06.01.2017.
 */

public enum FileFormat {

    CDW("cdw"),
    FRW("frw"),
    DXF("dxf"),
    CDR("cdr"),
    PDF("pdf"),
    SPL7("spl7"),
    DWG("dwg");

    String format;

    FileFormat(String format) {
        this.format = format;
    }

    public String value() {
        return format;
    }
}
