package org.example;

public enum ProductTestData {
    FIRST_ELEMENT(0, "Smartband Xiaomi Mi Band 5 czarny", 139.00f, 5.5f),
    SECOND_ELEMENT(1,"Smartband Xiaomi Mi Band 4 czarny", 59.99f, 5.7f),
    THIRD_ELEMENT(2, "Smartband Xiaomi Mi Band 6", 219.0f, 5.8f);


    private final int index;
    private final String name;
    private final float price;
    private final float rating;

    ProductTestData(int index, String name, float price, float rating) {
        this.index = index;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }
}
