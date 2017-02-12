package com.kompas.domain;

import com.idrawing.filemanager.domain.LocalFile;
import com.kompas.model.dto.StampDTO;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Sergej Povzaniuk on 03.12.2016.
 */
@Getter
@Setter
@EqualsAndHashCode
public class Drawing {
    private final StampDTO stampDTO;
    private final StringProperty pathToDrawingImage;
    private final StringProperty pathToDrawing;
    private BooleanProperty isActive;
    private final LocalFile localFile;

    public Drawing(StampDTO stampDTO, LocalFile localFile) {
        this.stampDTO = stampDTO;
        this.pathToDrawingImage = new SimpleStringProperty("file:/C:/Users/Amsterdam/IdeaProjects/KompasAPI5/kompas3D/src/main/resources/images/" + localFile.getName().replace(" ", "%20") + ".jpg");
        this.pathToDrawing = new SimpleStringProperty(localFile.getPath().toString());
        this.localFile = localFile;
        this.isActive = new SimpleBooleanProperty(false);
    }

    @Override
    public String toString() {
        return localFile.getPath().toString();
    }
}
