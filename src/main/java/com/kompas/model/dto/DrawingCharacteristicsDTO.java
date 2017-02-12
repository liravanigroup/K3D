package com.kompas.model.dto;

import com.kompas.model.kompas.enums.documentparam.DocType;
import com.kompas.model.kompas.enums.documentparam.Orientation;
import com.kompas.model.kompas.enums.documentparam.SheetFormat;
import com.kompas.model.metrics.Size;
import lombok.*;

/**
 * Created by White Stream on 12.02.2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DrawingCharacteristicsDTO {
    private Orientation orientation;
    private SheetFormat sheetFormat;
    private short multiplicity;
    private Size size;
    private DocType docType;
}
