package com.yoji.elementeventinterception;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({Key.ITEM_KEY, Key.TEXT_KEY, Key.NUM_OF_SYMBOL_KEY, Key.ITEM_QTY_KEY})
public @interface Key {
    String TEXT_KEY = "text_key";
    String NUM_OF_SYMBOL_KEY = "num_of_symbol_key";
    String ITEM_QTY_KEY = "item_qty_key";
    String ITEM_KEY = "item_";
}
