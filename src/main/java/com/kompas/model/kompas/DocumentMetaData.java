package com.kompas.model.kompas;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
import com.kompas.model.kompas.enums.documentparam.DocType;
import com.kompas.model.kompas.enums.documentparam.SheetType;
import com.kompas.model.templates.Template;
import com.kompas.model.templates.enums.TemplateTypeGRAPHIC;
import lombok.*;

import java.nio.file.Path;

import static com.kompas.model.kompas.enums.documentparam.DocType.ly_UnknownType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DocumentMetaData {
    private static final String EMPTY_VALUE = "";
    private String author = EMPTY_VALUE;
    private String company = EMPTY_VALUE;
    private String comment = EMPTY_VALUE;
    private String fileName = EMPTY_VALUE;
    private int regime;
    private DocType docType = ly_UnknownType;
    private SheetType sheetType;
    private Template template;
}
