package com.kompas.model.kompas;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
import com.kompas.model.kompas.enums.documentparam.DocType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.kompas.model.kompas.enums.documentparam.DocType.ly_UnknownType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DrawingMetaData {
    private static final String EMPTY_VALUE = "";
    private String author = EMPTY_VALUE;
    private String company = EMPTY_VALUE;
    private String comment = EMPTY_VALUE;
    private String fileName = EMPTY_VALUE;
    private int regime;
    private DocType docType = ly_UnknownType;
}
