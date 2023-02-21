package by.kihtenkoolga.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "discount_cards")
public class DiscountCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "number_card", nullable = false)
    private Long numberCard;
    @Column(name = "fio", length = 30)
    private String FIO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return Objects.equals(id, that.id) && Objects.equals(numberCard, that.numberCard) && Objects.equals(FIO, that.FIO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberCard, FIO);
    }

    public DiscountCard(Long numberCard) {
        this.numberCard = numberCard;
    }

    public DiscountCard(Long id, Long numberCard, String FIO) {
        this.id = id;
        this.numberCard = numberCard;
        this.FIO = FIO;
    }

    public DiscountCard() {
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(Long numberCard) {
        this.numberCard = numberCard;
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", numberCard=" + numberCard +
                ", FIO='" + FIO + '\'' +
                '}';
    }
}
