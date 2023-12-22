package org.lesson6;

public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private String category;
    private Integer price;

    public Product(int id, String name, String category, Integer price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int compareTo(Product p1) {
        return Integer.compare(this.price, p1.price);
    }

    @Override
    public String toString() {
        return "Price of " + this.name + " is: " + this.price;
    }
}
