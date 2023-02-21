package by.kihtenkoolga.service;

import by.kihtenkoolga.model.DiscountCard;
import com.sun.istack.NotNull;

import java.util.List;
import java.util.Optional;

public interface DiscountCardService {
    DiscountCard create(DiscountCard discountCard);
    Optional<DiscountCard> findById(Long id);
    Optional<DiscountCard> findByNumberCard(Long numberCard);
    List<DiscountCard> findAll();

    void update(DiscountCard discountCard);

    void delete(DiscountCard discountCard);

    void deleteById(Long id);
}
