package com.mittens.medbud;

/**
 * Created by Bhargav on 9/9/2017.
 */

public class ListItems {

    private String head;
    private String desc;

    public ListItems(String head, String desc) {
        this.head = head;
        this.desc = desc;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }
}
