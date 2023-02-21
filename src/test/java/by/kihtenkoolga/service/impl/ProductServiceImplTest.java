package by.kihtenkoolga.service.impl;

import by.kihtenkoolga.dao.ProductDAO;
import by.kihtenkoolga.model.Product;
import by.kihtenkoolga.service.ProductService;
import by.kihtenkoolga.util.ProductTestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
//@TestPropertySource(properties =
//        {"application-test.properties"})
//@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"},
//        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProductServiceImplTest {
    @MockBean
    private ProductDAO productDAO;
    @Autowired
    private ProductService productService;

    @Nested
    class Create {
        private Product newProduct;
        private Product expectedProduct;

        @BeforeEach
        void setUp() {
            newProduct = ProductTestBuilder.aProduct().build();
            expectedProduct = ProductTestBuilder.aProduct().withId(8L).build();
        }

        @AfterEach
        void tearDown() {
            productService.delete(expectedProduct);
        }

        @Test
        void checkCreateNewProductWithoutId() {
            Mockito.doReturn(expectedProduct)
                    .when(productDAO)
                    .save(newProduct);

            newProduct = productService.create(newProduct);
            Assertions.assertThat(newProduct)
                    .isEqualTo(expectedProduct);
        }
    }

    @Nested
    class FindById {
        private Product expectedProduct;

        @BeforeEach
        void setUp() {
            expectedProduct = ProductTestBuilder.apple;
        }

        @Test
        void checkFindByIdReturnId1() {
            final Long REAL_ID = 1L;
            Mockito.doReturn(Optional.of(expectedProduct))
                    .when(productDAO)
                    .findById(REAL_ID);
            Optional<Product> product = productService.findById(REAL_ID);
            Assertions.assertThat(product.get().getId())
                    .isEqualTo(REAL_ID);
        }

        @Test
        void checkFindByIdReturnEmpty() {
            final long NO_REAL_ID = 912481902L;
            Mockito.doReturn(Optional.empty())
                    .when(productDAO)
                    .findById(NO_REAL_ID);
            Optional<Product> product = productService.findById(NO_REAL_ID);
            Assertions.assertThat(product)
                    .isEmpty();
        }
    }

    @Nested
    class FindAll {
        private List<Product> products;

        @BeforeEach
        void setUp() {
            products = List.of(
                    new Product(1L,"Apple", 1.0, (byte) 0),
                    new Product(2L,"Pineapple", 10.0, (byte) 0),
                    new Product(3L,"Milk", 1.0, (byte) 0),
                    new Product(4L,"Chocolate Alpenhold", 2.3, (byte) 10),
                    new Product(5L,"Water AURA", 50.0, (byte) 20),
                    new Product(6L,"Chocolate Alenka", 2.0, (byte) 0),
                    new Product(7L,"Plat", 10.3, (byte) 0)
            );
        }

        @Test
        void checkFindAllReturn7() {
            Mockito.doReturn(products)
                    .when(productDAO)
                    .findAll();
            List<Product> findDC = productService.findAll();
            Assertions.assertThat(findDC).hasSize(7);
        }

        @Test
        void checkFindAllReturnNo0() {
            Mockito.doReturn(products)
                    .when(productDAO)
                    .findAll();
            List<Product> findDC = productService.findAll();
            Assertions.assertThat(findDC)
                    .hasSizeGreaterThan(0);
        }
    }

    @Nested
    class Update {

        private Product appleWithDisc10;

        @BeforeEach
        void setUp() {
            appleWithDisc10 = ProductTestBuilder.aRealProductApple().withDiscountPercentage((byte) 10).build();
        }

        @AfterEach
        void tearDown() {
            productService.update(ProductTestBuilder.apple);
        }

        @Test
        void checkUpdateReturnProductWithSameId() {
            long expectedId = ProductTestBuilder.apple.getId();
            productService.update(appleWithDisc10);

            Assertions.assertThat(appleWithDisc10.getId())
                    .isEqualTo(expectedId);
        }
        @Test
        void checkUpdateReturnProductWithNewDiscount() {
            byte expectedDiscount = 10;
            productService.update(appleWithDisc10);

            Assertions.assertThat(appleWithDisc10.getDiscountPercentage())
                    .isEqualTo(expectedDiscount);
        }
    }

    @Nested
    class Delete {
        private Product dellProduct;

        @BeforeEach
        void setUp() {
            dellProduct = productService.create(ProductTestBuilder.aProduct().build());
        }

        @Test
        void checkDeleteRealObject() {
            Product apple = ProductTestBuilder.apple;
            productService.delete(apple);
            Mockito.verify(productDAO).delete(apple);
        }
        @Test
        void checkDeleteAfterCreate() {
            productService.delete(dellProduct);
            Mockito.verify(productDAO).delete(dellProduct);
        }
    }

    @Nested
    class DeleteById {
        @Test
        void checkDeleteByRealId() {
            Long REAL_ID = 1L;
            Mockito.doNothing()
                    .when(productDAO).deleteById(REAL_ID);
            productService.deleteById(REAL_ID);
            Mockito.verify(productDAO).deleteById(REAL_ID);
        }
        @Test
        void checkDeleteByNoRealId() {
            Long NO_REAL_ID = 19809L;
            Mockito.doNothing()
                    .when(productDAO).deleteById(NO_REAL_ID);
            productService.deleteById(NO_REAL_ID);
            Mockito.verify(productDAO).deleteById(NO_REAL_ID);
        }
    }
}
