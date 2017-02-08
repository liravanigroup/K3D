package com.kompas.model.kompas.enums.rasterparam;

/**
 * Created by Amsterdam on 29.06.2016.
 */

/**
 * BPP_COLOR_01 1 - монохромный,
 * BPP_COLOR_02 2 - 4 цвета,
 * BPP_COLOR_04 4 - 16 цветов,
 * BPP_COLOR_08 8 - 256 цветов,
 * BPP_COLOR_16 16 - 16 разрядов,
 * BPP_COLOR_24 24 - 24 разряда,
 * BPP_COLOR_32 32 - 32 разряда.
 */
public enum ColorBPP {
    BPP_COLOR_01(1),
    BPP_COLOR_02(2),
    BPP_COLOR_04(4),
    BPP_COLOR_08(8),
    BPP_COLOR_16(16),
    PP_COLOR_24(24),
    BPP_COLOR_32(32);

    private int colorBPP;

    ColorBPP(int colorBPP) {
        this.colorBPP = colorBPP;
    }

    public int value() {
        return colorBPP;
    }
}