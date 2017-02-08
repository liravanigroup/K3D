package com.kompas.model.kompas.datastructure;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Variant;

/**
 * Created by Sergej Povzanyuk on 09.08.2016.
 */
public class KsDynamicArray {

    private ActiveXComponent ksDynamicArray;

    public KsDynamicArray(ActiveXComponent ksDynamicArray) {
        this.ksDynamicArray = ksDynamicArray;
    }

    /**
     * @param elementIndex - индекс элемента, перед которым нужно вставить новый элемент,
     *                     нумерация начинается с 0,
     *                     при index=-1 элемент добавляется в конец массива,
     * @param ksTextLineParam - указатель на интерфейс соответствующего типа.
     * @return 1 - в случае удачного завершения
     */
    public long ksAddArrayItem(int elementIndex, ActiveXComponent ksTextLineParam) {
        return ksDynamicArray.invoke("ksSetArrayItem", new Variant(elementIndex), new Variant(ksTextLineParam)).getInt();
    }

    /**
     * @return 1 - в случае удачного завершения
     * 0 - в случае неудачи
     */
    public long ksClearArray() {
        return ksDynamicArray.invoke("ksClearArray").getInt();
    }

    /**
     * @return 1 - в случае удачного завершения
     */
    public long ksDeleteArray() {
        return ksDynamicArray.invoke("ksDeleteArray").getInt();
    }

    /**
     * @return 1 - в случае удачного завершения
     * 0 - в случае неудачи
     */
    public long ksExcludeArrayItem(int elementIndex) {
        return ksDynamicArray.invoke("ksExcludeArrayItem", elementIndex).getInt();
    }

    /**
     * @return - количество элементов в массиве
     */
    public long ksGetArrayCount() {
        return ksDynamicArray.invoke("ksGetArrayCount").getInt();
    }

    /**
     * @param elementIndex - индекс элемента в массиве, нумерация начинается с 0
     * @param ksTextLineParam - указатель на интерфейс соответствующего типа (ksTextLineParam(29))
     * @return 1 - в случае удачного завершения
     * 0 - в случае неудачи
     */
    public long ksGetArrayItem(int elementIndex, ActiveXComponent ksTextLineParam) {
        return ksDynamicArray.invoke("ksGetArrayItem", new Variant(elementIndex), new Variant(ksTextLineParam)).getInt();
    }

    public long ksGetArrayType() {
        return ksDynamicArray.invoke("ksGetArrayType").getInt();
    }

    /**
     * @param elementIndex - индекс элемента в массиве, нумерация начинается с 0,
     * @param ksTextLineParam - указатель на интерфейс соответствующего типа
     * @return 1 - в случае удачного завершения.
     * 0 - в случае неудачи.
     */
    public long ksSetArrayItem(int elementIndex, ActiveXComponent ksTextLineParam) {
        return ksDynamicArray.invoke("ksSetArrayItem", new Variant(elementIndex), new Variant(ksTextLineParam)).getInt();
    }
}
