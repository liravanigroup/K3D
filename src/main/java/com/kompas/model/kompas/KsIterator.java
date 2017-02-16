package com.kompas.model.kompas;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;
import com.kompas.model.kompas.enums.NavigationMode;

/**
 * Created by White Stream on 15.02.2017.
 */

public class KsIterator {
    private ActiveXComponent ksIterator;

    public KsIterator(ActiveXComponent kompasObject, NavigationMode targetObjects, NavigationMode iterableArea) {
        this.ksIterator = kompasObject.invokeGetComponent("GetIterator");
        ksIterator.invoke("ksCreateIterator", new Variant(targetObjects.getIndex()), new Variant(iterableArea.getIndex()));
    }

    public Variant getFirstElement(){
        return ksIterator.invoke("ksMoveIterator", new Variant("F"));
    }

    public Variant getNextElement(){
        return ksIterator.invoke("ksMoveIterator", new Variant("N"));
    }

    public void deleteIterator(){
        ksIterator.invoke("ksDeleteIterator");
    }

}
