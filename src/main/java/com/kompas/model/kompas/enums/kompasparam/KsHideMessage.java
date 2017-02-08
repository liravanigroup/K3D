package com.kompas.model.kompas.enums.kompasparam;

/**
 * Created by Sergej Povzanyuk on 09.08.2016.
 */
public enum KsHideMessage {
    ksShowMessage(0),
    ksHideMessageYes(1),
    ksHideMessageNo(2);

    private int value;

    KsHideMessage(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
