package by.kihtenkoolga.dao;

import by.kihtenkoolga.model.DiscountCard;

import java.util.List;
import java.util.Optional;

public interface DiscountCardDAO  {

    DiscountCard save(DiscountCard discountCard);
    DiscountCard update(DiscountCard discountCard);

    Optional<DiscountCard> findById(Long id);

    Optional<DiscountCard> findByNumberCard(Long numberCard);

    List<DiscountCard> findAll();

    void delete(DiscountCard discountCard);

    void deleteById(Long id);
}
