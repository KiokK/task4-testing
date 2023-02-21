package by.kihtenkoolga.dao.impl;

import by.kihtenkoolga.dao.DiscountCardDAO;
import by.kihtenkoolga.model.DiscountCard;

import java.util.List;
import java.util.Optional;

public class DiscountCardDAOImpl implements DiscountCardDAO {

    private long nextId = 11L;

    private List<DiscountCard> discountCardsStorage = List.of(
            new DiscountCard(1L,1234L,"Viktorov I.A"),
            new DiscountCard(2L,1111L,"Kihtenko O.Y."),
            new DiscountCard(3L,2222L,"Bondor I.K."),
            new DiscountCard(4L,3333L,"Manin I.A."),
            new DiscountCard(5L,4444L,"Vikont I.A."),
            new DiscountCard(6L,5555L,"Molotkov I.A."),
            new DiscountCard(7L,6666L,"Druid I.A."),
            new DiscountCard(8L,7777L,"Vedmak I.A."),
            new DiscountCard(9L,8888L,"Krasni I.A."),
            new DiscountCard(10L,9999L,"Kokolov I.A.")
    );
    @Override
    public DiscountCard save(DiscountCard discountCard) {
        if (discountCard.getId() == null ||
                findById(discountCard.getId()).isEmpty()) {
            discountCard.setId(nextId++);
            discountCardsStorage.add(discountCard);
            return discountCard;
        }
        return null;
    }
    @Override
    public DiscountCard update(DiscountCard discountCard) {
        return findById(discountCard.getId())
                .map(it -> {
                    it.setId(nextId++);
                    it.setNumberCard(discountCard.getNumberCard());
                    it.setFIO(discountCard.getFIO());
                    return it;
                })
                .get();
    }

    @Override
    public Optional<DiscountCard> findById(Long id) {
        return discountCardsStorage.stream()
                .filter(discountCard -> discountCard.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<DiscountCard> findByNumberCard(Long numberCard) {
        return discountCardsStorage.stream()
                .filter(discountCard -> discountCard.getNumberCard().equals(numberCard))
                .findFirst();
    }

    @Override
    public List<DiscountCard> findAll() {
        return discountCardsStorage;
    }

    @Override
    public void delete(DiscountCard discountCard) {
        findById(discountCard.getId())
                .ifPresent(discountCardsStorage::remove);
    }

    @Override
    public void deleteById(Long id) {
        findById(id)
                .ifPresent(discountCardsStorage::remove);
    }
}
