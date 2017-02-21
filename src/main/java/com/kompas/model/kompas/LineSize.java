package com.kompas.model.kompas;

import com.kompas.model.kompas.enums.SizeSing;
import lombok.*;

/**
 * Created by White Stream on 18.02.2017.
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LineSize {
    private String before, value, under, unit, after;
    private SizeSing sizeSing;
}
