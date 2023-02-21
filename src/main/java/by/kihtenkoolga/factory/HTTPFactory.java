package by.kihtenkoolga.factory;

import by.kihtenkoolga.dao.impl.DiscountCardDAOImpl;
import by.kihtenkoolga.factory.model.ReceiptRequestInfo;
import by.kihtenkoolga.model.DiscountCard;
import by.kihtenkoolga.model.Product;
import by.kihtenkoolga.model.Shop;
import by.kihtenkoolga.service.impl.DiscountCardServiceImpl;
import by.kihtenkoolga.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
/** Реализация "фабричного метода" для преобразования данных из HTTP запроса
 *  в нужную форму и фильтра данных чека, которые храняться в бд */
@Component
public class HTTPFactory implements Factory {
    @Autowired
    private ProductServiceImpl productService;

    //    @Autowired
    private DiscountCardServiceImpl discountCardService =
            new DiscountCardServiceImpl(new DiscountCardDAOImpl());
    /**
     * Метод создания коллекции (ключ-значение) товаров (idProduct-count)
     * @param data массив, где каждый элемент - "id" товара или строка вида "card-(number)"
     * @return коллекция объектов, содержащихся в бд
     */
    @Override
    public ReceiptRequestInfo createCashRegister(String[] data) {

        Shop shop = new Shop("GROSHIK", "Minsk, Surganova, 56", "+375660001233",
                discountCardService.findAll());

        Map<Product, Integer> cashRegInfo  = new HashMap<>();
        DiscountCard cardNumber = null;
        for (String s : data) {
            if (!s.contains("card")) {
                try {
                    Optional<Product> newProd = productService.findById(Long.valueOf(s));
                    if (newProd.isPresent()) {
                        if (cashRegInfo.containsKey(newProd.get()))
                            cashRegInfo.put(newProd.get(), cashRegInfo.get(newProd.get()) + 1);
                        else
                            cashRegInfo.put(newProd.get(), 1);
                    }
                }catch (NumberFormatException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                if (s.contains("card")) {
                    String[] parts = s.split("-");
                    if (parts.length == 2 && parts[0].equals("card")) {
                        try {
                            Long serialNumberCard = Long.parseLong(parts[1]);
                            Optional<DiscountCard> findCard = discountCardService.findByNumberCard(serialNumberCard);
                            if (!findCard.isEmpty() && shop.isCardContains(findCard.get()) ) {
                                cardNumber = findCard.get();
                            }
                        }catch (NumberFormatException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        }

        return new ReceiptRequestInfo(shop, cashRegInfo, "3259873", cardNumber);
    }



}
