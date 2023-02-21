package by.kihtenkoolga.builder;

import by.kihtenkoolga.model.DiscountCard;
import by.kihtenkoolga.model.Product;
import by.kihtenkoolga.model.Shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Абстрактный класс чека с информационными полями*/
public abstract class CashRegister {
    Double price = 0.0;
    Double discount = 0.0;
    List<Product> onDiscount = new ArrayList<>();
    int onDiscountCount = 0;
    String receipt = "";
    Shop shop;
    Map<Product, Integer> products = new HashMap<>();
    String cashierNumber;
    DiscountCard card;

    /**Метод расчета полной цены и скидки */
    void calculatePrices() {
        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            //prod_price * count
            price += product.getKey().getPrice() * product.getValue();
            if (card != null && product.getKey().isDiscount()) {
                onDiscount.add(product.getKey());
                onDiscountCount+=product.getValue();
                //discount += prod_price * (disc%)
                discount += product.getKey().getPrice() *
                        product.getKey().getDiscountPercentage() / 100.0 * product.getValue();
            }
        }
    }
    /** Метод расчета этоговой цены с учетом скидок */
    double getTotalPrise() {
        double totalPrice = price - discount;
        if (onDiscountCount >= 5) {
            for (Product pr : onDiscount)
                totalPrice -= pr.getPrice()*0.1;
        }
        return totalPrice;
    }

    /////////////////////get||set////////////////////////////////////////////
    public String getReceipt() {
        return receipt;
    }

    public List<Product> getOnDiscount() {
        return onDiscount;
    }

    public void setOnDiscount(List<Product> onDiscount) {
        this.onDiscount = onDiscount;
    }

    public Double getPrice() {
        return price;
    }

    public Double getDiscount() {
        return discount;
    }

    public Shop getShop() {
        return shop;
    }

    public DiscountCard getCard() {
        return card;
    }
    public Map<Product, Integer> getProducts() {
        return products;
    }

    public String getCashierNumber() {
        return cashierNumber;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public void setCashierNumber(String cashierNumber) {
        this.cashierNumber = cashierNumber;
    }

    public void setCard(DiscountCard card) {
        this.card = card;
    }
}
