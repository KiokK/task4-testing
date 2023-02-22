package by.kihtenkoolga.util;

import by.kihtenkoolga.model.Product;

import java.util.List;

public class ProductTestBuilder {

    public static final String DEFAULT_NAME = "Mangogo";
    public static final Double DEFAULT_PRISE = 100.0;
    public static final byte DEFAULT_DISCOUNT_PERCENTAGE = 0;
    
    private Long id = null;
    private String name = DEFAULT_NAME;
    private Double price = DEFAULT_PRISE;
    private byte discountPercentage = DEFAULT_DISCOUNT_PERCENTAGE;

    public static final Product APPLE = aRealProductApple().build();

    public static final Product APPLE_WITH_DISC_10 = ProductTestBuilder.aRealProductApple()
            .withDiscountPercentage((byte) 10).build();

    public static final List<Product> TESTS_PRODUCTS = List.of(
            new Product(1L,"Apple", 1.0, (byte) 0),
            new Product(2L,"Pineapple", 10.0, (byte) 0),
            new Product(3L,"Milk", 1.0, (byte) 0),
            new Product(4L,"Chocolate Alpenhold", 2.3, (byte) 10),
            new Product(5L,"Water AURA", 50.0, (byte) 20),
            new Product(6L,"Chocolate Alenka", 2.0, (byte) 0),
            new Product(7L,"Plat", 10.3, (byte) 0)
    );

    private ProductTestBuilder() {
    }

    public static ProductTestBuilder aProduct() {
        return new ProductTestBuilder();
    }

    public ProductTestBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductTestBuilder withPrice(Double price) {
        this.price = price;
        return this;
    }

    public ProductTestBuilder withDiscountPercentage(byte discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public ProductTestBuilder but() {
        return ProductTestBuilder
                .aProduct()
                .withName(name)
                .withDiscountPercentage(discountPercentage)
                .withPrice(price);
    }
    public Product build() {
        return new Product(id, name, price, discountPercentage);
    }

    /** Id=1, Name=Apple, Price=1.0, Discount=0 */
    public static ProductTestBuilder aRealProductApple() {
        return ProductTestBuilder.aProduct()
                .withId(1L)
                .withName("Apple")
                .withPrice(1.0);
    }
}
