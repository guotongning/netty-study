package com.ning.netty.server.ddz;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since
 */
public class Brand {
    private String value;
    private BrandFlower flower;

    public static void main(String[] args) {
        Brand.brands().forEach(System.out::println);
    }

    public static List<Brand> brands() {
        List<Brand> brands = new ArrayList<>();
        brands.add(new Brand("JOKER", BrandFlower.RED));
        brands.add(new Brand("JOKER", BrandFlower.BLACK));
        BrandFlower[] flowers = BrandFlower.values();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                String value = null;
                if (i == 0) value = "J";
                if (i == 1) value = "Q";
                if (i == 2) value = "K";
                brands.add(new Brand(value, flowers[j + 2]));
            }
        }
        for (int i = 0; i < 10; i++) {
            String value = i + 1 + "";
            for (int j = 0; j < 4; j++) {
                brands.add(new Brand(value, flowers[j + 2]));
            }
        }
        return brands;
    }

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
