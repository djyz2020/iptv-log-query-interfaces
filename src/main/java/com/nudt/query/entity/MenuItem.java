package com.nudt.query.entity;

/**
 * Created by Administrator on 2018/11/16.
 */
public class MenuItem {
    String itemName;
    String itemUrl;
    String itemIcon;

    public MenuItem(){}

    public MenuItem(String itemName, String itemUrl, String itemIcon) {
        this.itemName = itemName;
        this.itemUrl = itemUrl;
        this.itemIcon = itemIcon;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(String itemIcon) {
        this.itemIcon = itemIcon;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemName='" + itemName + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                ", itemIcon='" + itemIcon + '\'' +
                '}';
    }

}