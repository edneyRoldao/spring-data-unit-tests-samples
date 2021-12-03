package com.edn.base.repositories;

import com.edn.base.dto.ProductFilterDTO;
import com.edn.base.models.Product;
import com.edn.base.repositories.specification.ProductSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void test() {
        List<Product> products = repository.findAll();
        assertEquals(products.size(), 9);
    }

    @Test
    void testSpecification() {
        List<Product> products = repository.findAll();

        ProductFilterDTO filter = ProductFilterDTO
                .builder()
                .page(0)
                .pageSize(10)
                .build();

        PageRequest pagination = PageRequest.of(filter.getPage(),
                filter.getPageSize(),
                Sort.by(Sort.Direction.fromString("DESC"), "id"));

        Specification<Product> specification = ProductSpecification.getSpecificationFilter(filter);

        Page<Product> productsPaged = repository.findAll(specification, pagination);

        assertNotNull(productsPaged);
        assertNotNull(productsPaged.getContent());
    }

}
