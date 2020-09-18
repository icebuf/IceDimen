package com.icebuf.icedimen;

/**
 * @author IceTang
 * @version 1.0
 * Data: 2020/8/21
 * E-mail：bflyff@hotmail.com
 */
public enum Resolution {
    SD(480),
    HD(720),
    FHD(1080),
    R2K(1440),
    ;

    private int line;

    Resolution(int w) {
        line = w;
    }

    public int getWidth() {
        return line;
    }
}
