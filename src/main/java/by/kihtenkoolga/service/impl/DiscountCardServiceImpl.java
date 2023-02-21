package by.kihtenkoolga.service.impl;

import by.kihtenkoolga.dao.DiscountCardDAO;
import by.kihtenkoolga.exception.NullDiscountCardException;
import by.kihtenkoolga.model.DiscountCard;
import by.kihtenkoolga.service.DiscountCardService;

import java.util.List;
import java.util.Optional;

//@Validated
//@Service
public class DiscountCardServiceImpl implements DiscountCardService {

    private final String CAN_NOT_BE_FOUND_MESSAGE = "Card with null value!";

    private final DiscountCardDAO discountCardDAO;
    public DiscountCardServiceImpl(DiscountCardDAO discountCardDAO) {
        this.discountCardDAO = discountCardDAO;
    }
//    private final DiscountCardDAO repository;
//    @Autowired
//    public DiscountCardServiceImpl(DiscountCardDAO discountCardDAO) {
//        this.discountCardDAO = discountCardDAO;
//    }

    @Override
    public DiscountCard create(DiscountCard discountCard) {
        if (discountCard == null)
            throw new NullDiscountCardException(CAN_NOT_BE_FOUND_MESSAGE);
        discountCard.setId(null);
        return discountCardDAO.save(discountCard);
    }

    @Override
    public Optional<DiscountCard> findById(Long id) {
        if (id == null)
            throw new NullDiscountCardException(CAN_NOT_BE_FOUND_MESSAGE);
        return discountCardDAO.findById(id);
//                .orElseThrow(() -> new DiscountCardNotFoundException(CARD_NOT_FOUND_MESSAGE));
    }

    @Override
    public Optional<DiscountCard> findByNumberCard(Long numberCard) {
        if (numberCard == null)
            throw new NullDiscountCardException(CAN_NOT_BE_FOUND_MESSAGE);
        return discountCardDAO.findByNumberCard(numberCard);
    }

    @Override
    public List<DiscountCard> findAll() {
        return discountCardDAO.findAll();
    }

    @Override
    public void update(DiscountCard discountCard) {
        if (discountCard == null || discountCard.getId() == null)
            throw new NullDiscountCardException(CAN_NOT_BE_FOUND_MESSAGE);
        discountCardDAO.update(discountCard);
    }

    @Override
    public void delete(DiscountCard discountCard) {
        if (discountCard == null)
            throw new NullDiscountCardException(CAN_NOT_BE_FOUND_MESSAGE);
        discountCardDAO.delete(discountCard);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null)
            throw new NullDiscountCardException(CAN_NOT_BE_FOUND_MESSAGE);
        discountCardDAO.deleteById(id);
    }
}
