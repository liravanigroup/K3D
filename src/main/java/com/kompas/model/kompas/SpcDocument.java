package com.kompas.model.kompas;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.model.kompas.enums.kompasparam.VisibleMode;

import java.nio.file.Path;

/**
 * Created by White Stream on 16.02.2017.
 */
public class SpcDocument {

    private ActiveXComponent ksSpcDocument;

    public SpcDocument(ActiveXComponent ksSpcDocument) {
        this.ksSpcDocument = ksSpcDocument;
    }


    public long getReference(){
        return ksSpcDocument.invoke("reference").getLong();
    }

    public boolean ksOpenDocument(Path document, VisibleMode visibleMode){
        return ksSpcDocument.invoke("ksOpenDocument", new Variant(document.toAbsolutePath().toString()), new Variant(visibleMode.getVisibleMode())).getBoolean();
    }

    public boolean ksCloseDocument(){
        return ksSpcDocument.invoke("ksCloseDocument").getBoolean();
    }


}
