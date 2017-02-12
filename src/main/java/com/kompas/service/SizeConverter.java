package com.kompas.service;

import com.kompas.model.kompas.enums.documentparam.Orientation;
import com.kompas.model.kompas.enums.documentparam.SheetFormat;
import com.kompas.model.metrics.Size;

/**
 * Created by White Stream on 12.02.2017.
 */
public class SizeConverter {

    public static SheetFormat getFormat(Size size){
        SheetFormat sheetFormat = SheetFormat.UNKNOWN;
        if(size.isHorizontal()){

        }else {

        }
        return sheetFormat;
    }

    public static Size getSize(SheetFormat format, Orientation orientation, short multiplicity){
        Size size = null;
        if(orientation.isHorizontal()){
            switch (format.getIndex()){
                case 0:
                    size = new Size((double)1189, (double)841);
                    break;
                case 1:
                    size = new Size((double)841, (double)594);
                    break;
                case 2:
                    size = new Size((double)594, (double)420);
                    break;
                case 3:
                    size = new Size((double)420, (double)297);
                    break;
                case 4:
                    size = new Size((double)297, (double)210);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown drawing format");
            }
        }else {
            switch (format.getIndex()){
                case 0:
                    size = new Size((double)841, (double)1189);
                    break;
                case 1:
                    size = new Size((double)594, (double)841);
                    break;
                case 2:
                    size = new Size((double)420, (double)594);
                    break;
                case 3:
                    size = new Size((double)297, (double)420);
                    break;
                case 4:
                    size = new Size((double)210, (double)297);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown drawing format");
            }
        }
        return size;
    }

    public static short getMultiplicity(Size size) {
        return 0;
    }
}
