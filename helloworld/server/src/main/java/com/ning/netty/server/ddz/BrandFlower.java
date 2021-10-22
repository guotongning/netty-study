package com.ning.netty.server.ddz;

/**
 * TODO
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since
 */
public enum BrandFlower {
    RED("BIG"),
    BLACK("SMALL"),
    SPADE("♠"),
    HEART("♥"),
    PLUM_BLOSSOM("️♣"),
    SQUARE_SLICE("♦"),
    ;
    private String icon;

    BrandFlower(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
