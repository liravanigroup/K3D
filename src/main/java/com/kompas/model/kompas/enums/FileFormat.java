package com.kompas.model.kompas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Sergej Povzaniuk on 06.01.2017.
 */
@AllArgsConstructor
@Getter
public enum FileFormat {
    CDW("cdw"),
    FRW("frw"),
    DXF("dxf"),
    CDR("cdr"),
    PDF("pdf"),
    SPL7("spl7"),
    DWG("dwg");

    String format;
}
