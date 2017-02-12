package com.kompas.model.kompas.enums.rasterparam;

/**
 * Created by Amsterdam on 29.06.2016.
 */
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ImageFormat {
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
}











