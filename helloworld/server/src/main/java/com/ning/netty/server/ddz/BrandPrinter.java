package com.ning.netty.server.ddz;

import com.ning.netty.server.ddz.entity.Brand;
import com.ning.netty.server.ddz.enums.BrandFlower;
import com.ning.netty.server.ddz.util.BrandUtils;

import java.util.Collections;
import java.util.List;

/**
 * TODO
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since
 */
public class BrandPrinter {

    public static void main(String[] args) {
        System.out.println(BrandPrinter.printBrands(new Brand("FUCKER", BrandFlower.BLACK)));
        System.out.println(BrandPrinter.printBrands(BrandUtils.brands()));
    }

    private static final String BR = "\r\n";
    private static final String INTERVAL = "   ";
    private static final int BR_LENGTH = 5;

    private final List<Brand> brands;

    public static String printBrands(Brand brands) {
        return toListBrandString(CollectionsUtil.splitListBySize(Collections.singletonList(brands), BR_LENGTH)).toString();
    }

    public static String printBrands(List<Brand> brands) {
        return toListBrandString(CollectionsUtil.splitListBySize(brands, BR_LENGTH)).toString();
    }

    private static StringBuilder toListBrandString(List<List<Brand>> lists) {
        StringBuilder sb = new StringBuilder();
        for (List<Brand> list : lists) {
            sb.append(new BrandPrinter(list).toString()).append(BR);
        }
        return sb;
    }

    private BrandPrinter(List<Brand> brands) {
        this.brands = brands;
    }

    private String head() {
        return "╔════════╗";
    }

    private String mid() {
        return "║        ║";
    }

    private String tail() {
        return "╚════════╝";
    }

    @Override
    public String toString() {
        String header = getHeader();
        String displayValue = getDisplayValue();
        String middle1 = getMiddle();
        String middle2 = getMiddle();
        String displayFlower = getDisplayFlower();
        String tail = getTail();
        return header +
                displayValue +
                middle1 +
                middle2 +
                displayFlower +
                tail;
    }

    private String getHeader() {
        StringBuilder head = new StringBuilder();
        for (int i = 0; i < brands.size(); i++) {
            head.append(head()).append(INTERVAL);
        }
        head.delete(head.length() - 3, head.length()).append(BR);
        return head.toString();
    }

    private String getDisplayValue() {
        StringBuilder displayValue = new StringBuilder();
        for (Brand brand : brands) {
            displayValue.append(getDisplayLineValue(brand.getValue())).append(INTERVAL);
        }
        displayValue.delete(displayValue.length() - 3, displayValue.length()).append(BR);
        return displayValue.toString();
    }

    private String getMiddle() {
        StringBuilder middle = new StringBuilder();
        for (int i = 0; i < brands.size(); i++) {
            middle.append(mid()).append(INTERVAL);
        }
        middle.delete(middle.length() - 3, middle.length()).append(BR);
        return middle.toString();
    }

    private String getDisplayFlower() {
        StringBuilder displayValue = new StringBuilder();
        for (Brand brand : brands) {
            displayValue.append(getDisplayLineFlower(brand.getFlower().getIcon())).append(INTERVAL);
        }
        displayValue.delete(displayValue.length() - 3, displayValue.length()).append(BR);
        return displayValue.toString();
    }

    private String getTail() {
        StringBuilder tail = new StringBuilder();
        for (int i = 0; i < brands.size(); i++) {
            tail.append(tail()).append(INTERVAL);
        }
        tail.delete(tail.length() - 3, tail.length()).append(BR);
        return tail.toString();
    }

    private String getDisplayLineFlower(String flower) {
        StringBuilder display = new StringBuilder();
        display.append("║");
        int length = flower.length();
        for (int i = 0; i < 7 - length; i++) {
            display.append(" ");
        }
        display.append(flower).append(" ");
        display.append("║");
        return display.toString();
    }

    private String getDisplayLineValue(String value) {
        StringBuilder display = new StringBuilder();
        display.append("║");
        int length = value.length();
        display.append(" ").append(value);
        for (int i = 0; i < 7 - length; i++) {
            display.append(" ");
        }
        display.append("║");
        return display.toString();
    }
}
