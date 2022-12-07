package com.github.gambusio.Desitjos.entities;

import java.math.BigDecimal;

public class Item {
    private String sName ="";
    private String sDescription = "";
    private String sUrl = "";
    private String sPicUrl = "";
    private ItemType itemType;
    private BigDecimal price = BigDecimal.valueOf(0);

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsDescription() {
        return sDescription;
    }

    public void setsDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }

    public String getsPicUrl() {
        return sPicUrl;
    }

    public void setsPicUrl(String sPicUrl) {
        this.sPicUrl = sPicUrl;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(String price) {
        try {
            this.price = new BigDecimal(price);
        } catch (NumberFormatException e) {
            this.price = new BigDecimal(0);
        }
    }
}
