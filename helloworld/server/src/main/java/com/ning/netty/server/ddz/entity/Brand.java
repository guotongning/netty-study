package com.ning.netty.server.ddz.entity;

import com.ning.netty.server.ddz.enums.BrandFlower;

public class Brand {
    private String value;
    private BrandFlower flower;

    public Brand(String value, BrandFlower flower) {
        this.value = value;
        this.flower = flower;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BrandFlower getFlower() {
        return flower;
    }

    public void setFlower(BrandFlower flower) {
        this.flower = flower;
    }
}
