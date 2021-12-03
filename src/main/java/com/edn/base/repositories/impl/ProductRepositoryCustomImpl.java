package com.edn.base.repositories.impl;

import com.edn.base.dto.ProductFilterDTO;
import com.edn.base.models.Product;
import com.edn.base.repositories.ProductRepository;
import com.edn.base.repositories.ProductRepositoryCustom;
import com.edn.base.repositories.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final String arbitraryValue;
    private final ProductRepository repository;

    public ProductRepositoryCustomImpl(ProductRepository repository,
                                       @Value("${teste.url}") String arbitraryValue) {
        this.repository = repository;
        this.arbitraryValue = arbitraryValue;
    }

    @Override
    public Page<Product> findProductsByFilter(ProductFilterDTO filter) {
        PageRequest pagination = PageRequest.of(filter.getPage(),
                filter.getPageSize(),
                Sort.by(Sort.Direction.fromString("DESC"), "id"));

        Specification<Product> specification = ProductSpecification.getSpecificationFilter(filter);

        return repository.findAll(specification, pagination);
    }

}
