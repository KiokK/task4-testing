package by.kihtenkoolga.factory.model;

import by.kihtenkoolga.model.DiscountCard;
import by.kihtenkoolga.model.Product;
import by.kihtenkoolga.model.Shop;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс информации запроса создания чека::
 * магазин, список покупок, кассир, карта
 */
public class ReceiptRequestInfo {
    Shop shop;
    Map<Product, Integer> products = new HashMap<>();
    String cashierNumber;
    DiscountCard card;

    public ReceiptRequestInfo(Shop shop, Map<Product, Integer> products, String cashierNumber, DiscountCard card) {
        this.shop = shop;
        this.products = products;
        this.cashierNumber = cashierNumber;
        this.card = card;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public String getCashierNumber() {
        return cashierNumber;
    }

    public void setCashierNumber(String cashierNumber) {
        this.cashierNumber = cashierNumber;
    }

    public DiscountCard getCard() {
        return card;
    }

    public void setCard(DiscountCard card) {
        this.card = card;
    }
}
