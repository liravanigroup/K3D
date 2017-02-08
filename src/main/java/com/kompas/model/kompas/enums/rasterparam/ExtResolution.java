package com.kompas.model.kompas.enums.rasterparam;

/**
 * Created by Amsterdam on 29.06.2016.
 */
public enum ExtResolution {
    $9_DPI(9),
    $18_DPI(18),
    $36_DPI(36),
    $72_DPI(72),
    $96_DPI(96),
    $150_DPI(150),
    $300_DPI(300),
    $450_DPI(450),
    $600_DPI(600);

    private int resolution;

    ExtResolution(int resolution) {
        this.resolution = resolution;
    }

    public int value() {
        return resolution;
    }
}
