package com.github.gambusio.Desitjos.entities;

import java.util.Objects;

public class ItemBuilder {
    private final Item item = new Item();

    private ItemBuilder() {}
    public ItemBuilder(String sUrl) {
        item.setsUrl(sUrl);
    }

    public ItemBuilder withName(String sName) {
        item.setsName(sName);
        return this;
    }

    public ItemBuilder withDescription(String sDescription) {
        item.setsDescription(sDescription);
        return this;
    }

    public ItemBuilder withPicUrl(String sPicUrl) {
        item.setsPicUrl(sPicUrl);
        return this;
    }

    public ItemBuilder withType(ItemType itemType) {
        item.setItemType(Objects.requireNonNullElse(itemType, ItemType.UNKNOWN));
        return this;
    }

    public ItemBuilder withPrice(String sPrice) {
        item.setPrice(sPrice);
        return this;
    }

    public Item build(){
        return item;
    }
}
