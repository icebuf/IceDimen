package com.icebuf.icedimen;

public class ElementFormat {

    public static final int TYPE_PX2DP = 1;

    public static final int TYPE_PX2SP = 2;

    public String nameFormat;

    public int type;

    public int formPx;

    public int toPx;

    public ElementFormat(String nameFormat, int type, int formPx, int toPx) {
        this.nameFormat = nameFormat;
        this.type = type;
        this.formPx = formPx;
        this.toPx = toPx;
    }
}
