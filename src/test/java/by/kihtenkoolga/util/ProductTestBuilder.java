package by.kihtenkoolga.util;

import by.kihtenkoolga.model.Product;

public class ProductTestBuilder {

    public static final String DEFAULT_NAME = "Mangogo";
    public static final Double DEFAULT_PRISE = 100.0;
    public static final byte DEFAULT_DISCOUNT_PERCENTAGE = 0;
    
    private Long id = null;
    private String name = DEFAULT_NAME;
    private Double price = DEFAULT_PRISE;
    private byte discountPercentage = DEFAULT_DISCOUNT_PERCENTAGE;

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

    public static Product apple = aRealProductApple().build();
}
