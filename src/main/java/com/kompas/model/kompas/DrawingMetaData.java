package com.kompas.model.kompas;

/**
 * Created by Sergej Povzanyuk on 08.08.2016.
 */

import com.google.common.base.MoreObjects;

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
public class DrawingMetaData {
    private String author;
    private String company;
    private String comment;
    private String fileName;
    private int regime;
    private int type;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getRegime() {
        return regime;
    }

    public void setRegime(int regime) {
        this.regime = regime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("author", author)
                .add("comment", comment)
                .add("company", company)
                .add("fileName", fileName)
                .add("regime", regime)
                .add("type", type)
                .toString();
    }
}
