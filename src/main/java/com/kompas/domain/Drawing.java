package com.kompas.domain;

import com.kompas.model.dto.DrawingCharacteristicsDTO;
import com.kompas.model.dto.StampDTO;
import com.kompas.model.kompas.DrawingMetaData;
import lombok.*;

import java.io.File;

/**
 * Created by Sergej Povzaniuk on 03.12.2016.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Drawing {
    private StampDTO stampDTO;
    private DrawingCharacteristicsDTO drawingCharacteristicsDTO;
    private DrawingMetaData drawingMetaData;
    private File drawingFile;
    private Iterable<String> texts, sizes, tables;
}
