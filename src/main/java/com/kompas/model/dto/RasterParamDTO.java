package com.kompas.model.dto;

import com.kompas.model.kompas.enums.rasterparam.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by White Stream on 12.02.2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RasterParamDTO {
    private ColorBPP colorBPP;
    private ColorType colorType;
    private ExtResolution extResolution;
    private ExtScale extScale;
    private ImageFormat imageFormat;
    private RangeIndex rangeIndex;
    private boolean grayScale;
    private boolean multiPageOutput;
    private boolean onlyThinLine;
}
