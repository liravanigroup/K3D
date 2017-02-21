package com.kompas.model.templates;

import com.kompas.model.templates.enums.TemplateTypeGRAPHIC;

import java.nio.file.Paths;

import static com.google.common.io.Files.getNameWithoutExtension;

/**
 * Created by White Stream on 18.02.2017.
 */
public class GraphicTemplate extends Template {
    public GraphicTemplate(Integer index, String libraryPath) {
       super(
               TemplateTypeGRAPHIC.valueOf(index).getName(),
               TemplateTypeGRAPHIC.valueOf(index).getIndex(),
               Paths.get(libraryPath),
               getNameWithoutExtension(libraryPath)
       );
    }
}
