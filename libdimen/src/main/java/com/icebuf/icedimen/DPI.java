package com.icebuf.icedimen;

/**
 * Created by IceTang
 * 日期：2019-06-11
 * 邮箱：jie.tang.kty@tcl.com
 */
public enum DPI {

    LDPI(120),

    MDPI(160),

    HDPI(240),

    XHDPI(320),

    XXHDPI(480),

    XXXHDPI(640),

    DEFAULT(HDPI.value);

    private int value;

    DPI(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }

    public static float getScale(DPI dpi) {
        return dpi.value / (float) MDPI.value;
    }
}
