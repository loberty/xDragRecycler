package com.example.loberty.xdragrecycler.drag;

/**
 * Create by WangChen on 2020-04-21
 */
public class ItemBean {

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_DIVIDER = 1;
    public static final int TYPE_DIVIDER_1 = 2;
    public static final int TYPE_HOLDER = 3;

    private String text;
    private int type;
    private boolean isAdd;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}
