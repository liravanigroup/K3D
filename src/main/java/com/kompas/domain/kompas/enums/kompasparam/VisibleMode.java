package com.kompas.domain.kompas.enums.kompasparam;

/**
 * Created by Sergej Povzanyuk on 10.08.2016.
 */
public enum VisibleMode {

    HIDDEN_MODE(false, 1),
    VISIBLE_MODE(true, 0);

    private final boolean isVisibleWindow;
    private final int isVisibleDrawing;

    VisibleMode(boolean isVisibleWindow, int isVisibleDrawing) {
        this.isVisibleWindow = isVisibleWindow;
        this.isVisibleDrawing = isVisibleDrawing;
    }

    public boolean getBoolVal() {
        return isVisibleWindow;
    }

    public int getIntVal() {
        return isVisibleDrawing;
    }
}
