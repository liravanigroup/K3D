package com.kompas.model.kompas;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @regime it is a variable can take next values:
 * Режим редактирования:
 * 0 - видимый
 * 1 - "слепой"
 * @type it is a variable can take next values:
 * lt_DocSheetStandart 1 - чертеж стандартного формата
 * lt_DocSheetUser 2 - чертеж нестандартного формата
 * lt_DocFragment 3 - фрагмент
 * lt_DocSpc 4 - спецификация
 * lt_DocPart3D 5 - деталь
 * lt_DocAssemble3D 6 - сборка
 * lt_DocTxtStandart 7 - текстовый документ стандартный
 * lt_DocTxtUser 8 - текстовый документ нестандартный
 * lt_DocSpcUser 9 - спецификация - нестандартный формат
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DrawingMetaData {
    private String author;
    private String company;
    private String comment;
    private String fileName;
    private int regime;
    private int type;
}
