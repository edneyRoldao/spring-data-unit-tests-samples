package com.edn.base.repositories;

import com.edn.base.dto.ProductFilterDTO;
import com.edn.base.models.Product;
import org.springframework.data.domain.Page;

public interface ProductRepositoryCustom {

    Page<Product> findProductsByFilter(ProductFilterDTO filter);

}
