package com.kompas.model.kompas.enums.kompasparam;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ksShowMessage 0 Показывать все сообщения и диалоги
 * ksHideMessageYes 1 Скрывать сообщения и диалоги с выбором ОК или Да,
 * если сообщение или диалог предусматривают такой выбор, с перестроением документа.
 * ksHideMessageNo 2 Скрывать сообщения с ОК, если имеется только кнопка ОК,
 * сообщения и диалоги с выбором Нет, если сообщение или диалог предусматривают такой выбор, без перестроения документа
 */
@AllArgsConstructor
@Getter
public enum KsHideMessage {
    ksShowMessage(0),
    ksHideMessageYes(1),
    ksHideMessageNo(2);

    private int value;
}
