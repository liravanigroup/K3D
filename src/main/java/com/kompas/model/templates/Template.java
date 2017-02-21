package com.kompas.model.templates;

import lombok.*;

import java.nio.file.Path;

/**
 * Created by White Stream on 18.02.2017.
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Template {
    private String templateName;
    private Integer templateIndex;
    private Path libraryPath;
    private String libraryName;
}
