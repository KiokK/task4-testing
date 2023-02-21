package by.kihtenkoolga.service.impl;

import by.kihtenkoolga.dao.DiscountCardDAO;
import by.kihtenkoolga.exception.NullDiscountCardException;
import by.kihtenkoolga.model.DiscountCard;
import by.kihtenkoolga.util.DiscountCardTestBuilder;
import by.kihtenkoolga.util.ShopTestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

//With JUnit and Mockito
@ExtendWith(MockitoExtension.class)
class DiscountCardServiceImplTest {

    @Captor
    private ArgumentCaptor<DiscountCard> argumentCaptor;
    @InjectMocks
    private DiscountCardServiceImpl discountCardService;
    @Mock
    private DiscountCardDAO discountCardDAO;
    private List<DiscountCard> discountCards;

    @BeforeEach
    void setUp() {
        discountCards = ShopTestBuilder.listOfCards();
    }


    @Nested
    class Create {
        private DiscountCard dc;
        @BeforeEach
        void setUp() {
            dc = DiscountCardTestBuilder.aDiscountCard()
                    .fio("Kikimor I.K.")
                    .numberCard(4321L)
                    .build();
        }

        @Test
        void checkCreateWithVerify() {
            DiscountCard newDc = discountCardService.create(dc);
            Mockito.verify(discountCardDAO).save(dc);
        }

        @Test
        void checkCreateShouldReturnNewDiscountCardWithNewId() {
            dc.setId(92347819L);
            DiscountCard expectedDC = DiscountCardTestBuilder.aDiscountCard()
                    .id(11L)
                    .fio("Kikimor I.K.")
                    .numberCard(4321L)
                    .build();

            Mockito.doReturn(expectedDC)
                    .when(discountCardDAO)
                    .save(dc);
            DiscountCard newDc = discountCardService.create(dc);

            Assertions.assertThat(newDc.getId())
                    .isEqualTo(11L);
        }
        @Test
        void checkCreateWithIdWhichAlreadyExistsShouldReturnNull() {

            Mockito.doReturn(null)
                    .when(discountCardDAO)
                    .save(dc);
            DiscountCard newDc = discountCardService.create(dc);

            Assertions.assertThat(newDc)
                    .isNull();
        }

        @Test
        void checkCreateThrowsNullDiscountCardException() {
            org.junit.jupiter.api.
                    Assertions.assertThrows(NullDiscountCardException.class, () -> discountCardService.create(null));
        }
    }

    @Nested
    class FindById {
        @Test
        void checkFindByIdShouldReturnTrue() {
            final Long EXPECTED_ID = 1L;
            DiscountCard actualDC = DiscountCardTestBuilder.aRealDiscountCard().build();

            Mockito.doReturn(Optional.of(actualDC))
                    .when(discountCardDAO)
                    .findById(EXPECTED_ID);
            Optional<DiscountCard> findDC = discountCardService.findById(EXPECTED_ID);
            Assertions.assertThat(findDC.get().getId())
                    .isEqualTo(EXPECTED_ID);
        }

        @Test
        void checkFindByIdShouldReturnEmpty() {
            final Long EXPECTED_ID = 0L;//user not found

            Mockito.doReturn(Optional.empty())
                    .when(discountCardDAO).findById(EXPECTED_ID);
            Optional<DiscountCard> findDC = discountCardService.findById(EXPECTED_ID);
            Assertions.assertThat(findDC).isEmpty();
        }

        @Test
        void checkFindByIdDoesNotThrow() {
            org.junit.jupiter.api.
                    Assertions.assertDoesNotThrow(() -> discountCardService.findById(0L));
        }

        @Test
        void checkFindByIdThrowsNullDiscountCardException() {
            org.junit.jupiter.api.
                    Assertions.assertThrows(NullDiscountCardException.class, () -> discountCardService.findById(null));
        }
    }

    @Nested
    class FindByNumberCard {
        @Test
        void checkFindByNumberCardShouldReturnTrue() {
            final Long EXPECTED_NUMBER = 1111L;
            DiscountCard actualDC = new DiscountCard();
            actualDC.setNumberCard(EXPECTED_NUMBER);

            Mockito.doReturn(Optional.of(actualDC))
                    .when(discountCardDAO)
                    .findByNumberCard(EXPECTED_NUMBER);
            Optional<DiscountCard> findDC = discountCardService.findByNumberCard(EXPECTED_NUMBER);
            Assertions.assertThat(findDC.get().getNumberCard())
                    .isEqualTo(EXPECTED_NUMBER);
        }

        @Test
        void checkFindByNumberCardShouldReturnOptionalEmpty() {
            final Long EXPECTED_NUMBER = 0L;

            Mockito.doReturn(Optional.empty())
                    .when(discountCardDAO).findByNumberCard(EXPECTED_NUMBER);
            Optional<DiscountCard> findDC = discountCardService.findByNumberCard(EXPECTED_NUMBER);
            Assertions.assertThat(findDC).isEmpty();
        }

        @Test
        void checkFindByNumberCardDoesNotThrow() {
            org.junit.jupiter.api.
                Assertions.assertDoesNotThrow(() -> discountCardService.findByNumberCard(0L));
        }
        @Test
        void checkFindByNumberCardThrowsNullDiscountCardException() {
            org.junit.jupiter.api.
                    Assertions.assertThrows(NullDiscountCardException.class, () -> discountCardService.findByNumberCard(null));
        }
    }

    @Nested
    class FindAll {

        @Test
//        @Disabled("Should be 10 values in DB")
        void checkFindAllShouldReturn10() {
            int expectedLength = discountCards.size();

            Mockito.doReturn(discountCards)
                    .when(discountCardDAO)
                    .findAll();
            List<DiscountCard> findDC = discountCardService.findAll();
            Assertions.assertThat(findDC)
                    .hasSize(expectedLength);
        }

        @Test
        void checkFindAllDoesNotThrow() {
            org.junit.jupiter.api.
                Assertions.assertDoesNotThrow(() -> discountCardService.findAll());
        }

    }

    @Nested
    class Update {
        DiscountCard dc;

        @BeforeEach
        void tearDown() {
            dc = DiscountCardTestBuilder.aRealDiscountCard().build();
        }

        @Test
        void checkUpdateNumberCard() {
            Long numberCard = 9876L;
            dc.setNumberCard(numberCard);

            discountCardService.update(dc);

            Mockito.verify(discountCardDAO).update(dc);
        }
        @Test
        void checkUpdateFIO() {
            String FIO = "Kihtenko O.Y.";
            dc.setFIO(FIO);
            discountCardService.update(dc);
            Assertions.assertThat(dc.getFIO())
                    .isEqualTo(FIO);
        }
        @Test
        void checkUpdateCardNumberAndFIO() {
            String FIO = "Kihtenko O.Y.";
            Long cardNumber = 2345L;
            dc.setFIO(FIO);
            dc.setNumberCard(cardNumber);

            discountCardService.update(dc);
            Mockito.verify(discountCardDAO).update(argumentCaptor.capture());
            Assertions.assertThat(argumentCaptor.getValue())
                    .isEqualTo(new DiscountCard(1L, cardNumber, FIO));
        }

        @Test
        void checkUpdateNullShouldThrow() {
            org.junit.jupiter.api.
                    Assertions.assertThrows(NullDiscountCardException.class,
                            () -> discountCardService.update(null));
        }
        @Test
        void checkUpdateCardWithNullIdShouldThrowException() {
            org.junit.jupiter.api.
                    Assertions.assertThrows(NullDiscountCardException.class,
                            () -> discountCardService.update(DiscountCardTestBuilder.aCardWithNullId().build()));
        }
    }

    @Nested
    class Delete {
        @Test
        void checkDeleteWithExistObject() {
            DiscountCard dc = new DiscountCard(1L,1234L,"Viktorov I.A");

            Mockito.doNothing()
                    .when(discountCardDAO).delete(dc);
            discountCardService.delete(dc);
            Mockito.verify(discountCardDAO).delete(dc);
        }
        @Test
        void checkDeleteWithNoExistObject() {
            DiscountCard dc = new DiscountCard(311L,1231234L,"Viktorov I.A");

            Mockito.doNothing()
                    .when(discountCardDAO).delete(dc);
            discountCardService.delete(dc);
            Mockito.verify(discountCardDAO).delete(dc);
        }
        @Test
        void checkDeleteNullShouldThrowNullDiscountCard() {
            org.junit.jupiter.api.
                    Assertions.assertThrows(NullDiscountCardException.class,
                            () -> discountCardService.delete(null));
        }
    }

    @Nested
    class DeleteById {
        @Test
        void checkDeleteByRealId() {
            long deleteId = 1L;

            Mockito.doNothing()
                    .when(discountCardDAO).deleteById(deleteId);
            discountCardService.deleteById(deleteId);
            Mockito.verify(discountCardDAO).deleteById(deleteId);
        }
        @Test
        void checkDeleteByNullIdShouldThrowNullDiscountCard() {
            org.junit.jupiter.api.
                    Assertions.assertThrows(NullDiscountCardException.class,
                            () -> discountCardService.deleteById(null));
        }
    }
}
