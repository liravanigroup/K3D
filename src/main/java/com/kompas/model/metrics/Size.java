package com.kompas.model.metrics;

import lombok.*;

/**
 * Created by White Stream on 12.02.2017.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Size {
    private Double width, height;

    public boolean isHorizontal(){
        return width.compareTo(height) > 0;
    }
}
