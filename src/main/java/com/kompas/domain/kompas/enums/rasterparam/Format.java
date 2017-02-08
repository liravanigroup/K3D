package com.kompas.domain.kompas.enums.rasterparam;

/**
 * Created by Amsterdam on 29.06.2016.
 */

/**
 * FORMAT_BMP 0 - BMP,
 * FORMAT_GIF 1 - GIF,
 * FORMAT_JPG 2 - JPEG,
 * FORMAT_PNG 3 - PNG,
 * FORMAT_TIF 4 - TIFF,
 * FORMAT_TGA 5 - TGA,
 * FORMAT_PCX 6 - PCX,
 * FORMAT_WMF 16 - WMF,
 * FORMAT_EMF 17 - EMF
 */
public enum Format {
    FORMAT_BMP(0, ".BMP"),
    FORMAT_GIF(1, ".GIF"),
    FORMAT_JPG(2, ".JPEG"),
    FORMAT_PNG(3, ".PNG"),
    FORMAT_TIF(4, ".TIFF"),
    FORMAT_TGA(5, ".TGA"),
    FORMAT_PCX(6, ".PCX"),
    FORMAT_WMF(16, ".WMF"),
    FORMAT_EMF(17, ".EMF");

    int index;
    String extension;

    Format(int index, String extension) {
        this.index = index;
        this.extension = extension;
    }

    public int value() {
        return index;
    }

    public String getExtension() {
        return extension.toLowerCase();
    }
}











