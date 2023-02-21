package by.kihtenkoolga.model;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private List<String> cashierNumbers; //список кассиров, пока нигде не используется -> To Do: сделать

    private List<DiscountCard> cardsNumbers = new ArrayList<>();

    public Shop(String name, String address, String phone, List<DiscountCard> cardsNumbers) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cardsNumbers = cardsNumbers;
    }

    public boolean isCardContains(DiscountCard discountCard) {
        for (DiscountCard dc : cardsNumbers) {
            if (dc.equals(discountCard))
                return true;
        }
        return false;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<DiscountCard> getCardsNumbers() {
        return cardsNumbers;
    }

    public void setCardsNumbers(List<DiscountCard> cardsNumbers) {
        this.cardsNumbers = cardsNumbers;
    }
}
