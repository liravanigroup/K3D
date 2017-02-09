package com.kompas.model.kompas.enums.kompasparam;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VisibleMode {

    HIDDEN_MODE(false, 1),
    VISIBLE_MODE(true, 0);

    private boolean isWindowVisible;
    private int visibleMode;
}
