package by.kihtenkoolga.util;

import by.kihtenkoolga.model.DiscountCard;

import java.util.List;

public class DiscountCardTestBuilder {

    public static final String DEFAULT_FIO = "Ivanov I.I.";
    public static final Long DEFAULT_NUMBER = 11110L;


    private Long id;
    private String fio = DEFAULT_FIO;
    private Long numberCard = DEFAULT_NUMBER;

    private DiscountCardTestBuilder() {
    }

    public static DiscountCardTestBuilder aDiscountCard() {
        return new DiscountCardTestBuilder();
    }

    public DiscountCardTestBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public DiscountCardTestBuilder fio(String FIO) {
        this.fio = FIO;
        return this;
    }

    public DiscountCardTestBuilder numberCard(Long numberCard) {
        this.numberCard = numberCard;
        return this;
    }


    public DiscountCardTestBuilder but() {
        return DiscountCardTestBuilder
                .aDiscountCard()
                .numberCard(DEFAULT_NUMBER)
                .fio(DEFAULT_FIO);
    }
    public DiscountCard build() {
        return new DiscountCard(id, numberCard, fio);
    }

    public static DiscountCardTestBuilder aRealDiscountCard() {
        return DiscountCardTestBuilder.aDiscountCard()
                .id(1L)
                .fio("Viktorov I.A")
                .numberCard(1234L);
    }
    public static DiscountCardTestBuilder aCardWithNullId() {
        return DiscountCardTestBuilder.aDiscountCard()
                .id(null);
    }

}
