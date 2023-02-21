package by.kihtenkoolga.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name",length = 30)
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "discount_percentage")
    private byte discountPercentage = 0;

    public Product(Long id, String name, Double price, byte discountPercentage) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPercentage = discountPercentage;
    }

    public Product(String name, Double price, byte discountPercentage) {
        this.name = name;
        this.price = price;
        this.discountPercentage = discountPercentage;
    }

    public Product() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return discountPercentage == product.discountPercentage && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, discountPercentage);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(byte discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    /** Если товар на скидке, то вернется true, иначе false */
    public boolean isDiscount() {
        return (discountPercentage == 0) ? false : true;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
