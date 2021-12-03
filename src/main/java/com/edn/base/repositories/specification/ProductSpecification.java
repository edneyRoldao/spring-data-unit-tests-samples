package com.edn.base.repositories.specification;

import com.edn.base.dto.ProductFilterDTO;
import com.edn.base.enums.Category;
import com.edn.base.models.Product;
import com.edn.base.util.DateUtil;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class ProductSpecification {

    private ProductSpecification() {}

    public static Specification<Product> getSpecificationFilter(ProductFilterDTO filter) {
        return Specification
                .where(addCategoryCriteria(filter))
                .and(addNameCriteria(filter))
                .and(addCreatedCriteria(filter))
                .and(addDescriptionCriteria(filter))
                .and(addSkuCriteria(filter));
    }

    private static Specification<Product> addSkuCriteria(ProductFilterDTO filter) {
        return isBlank(filter.getSku()) ? null : (Root<Product> root, CriteriaQuery<?> q, CriteriaBuilder cb) ->
                cb.like(root.get("sku"), "%" + filter.getSku() + "%");
    }

    private static Specification<Product> addNameCriteria(ProductFilterDTO filter) {
        return isBlank(filter.getName()) ? null : (Root<Product> root, CriteriaQuery<?> q, CriteriaBuilder cb) ->
                cb.like(root.get("name"), "%" + filter.getName() + "%");
    }

    private static Specification<Product> addCategoryCriteria(ProductFilterDTO filter) {
        Category category = Category.forName(filter.getCategory());
        return Objects.isNull(category) ? null : (Root<Product> root, CriteriaQuery<?> q, CriteriaBuilder cb) ->
                cb.equal(root.get("category"), category);
    }

    private static Specification<Product> addDescriptionCriteria(ProductFilterDTO filter) {
        return isBlank(filter.getDescription()) ? null : (Root<Product> root, CriteriaQuery<?> q, CriteriaBuilder cb) ->
                cb.like(root.get("description"), "%" + filter.getDescription() + "%");
    }

    private static Specification<Product> addCreatedCriteria(ProductFilterDTO filter) {
        LocalDateTime ini = isBlank(filter.getInitialDate()) ?
                LocalDateTime.now().minusMonths(1) :
                DateUtil.toLocalDateTime(filter.getInitialDate(), "yyyy-MM-dd HH:mm:ss");

        LocalDateTime end = isBlank(filter.getFinalDate()) ?
                LocalDateTime.now() :
                DateUtil.toLocalDateTime(filter.getFinalDate(), "yyyy-MM-dd HH:mm:ss");

        return (Root<Product> r, CriteriaQuery<?> q, CriteriaBuilder cb) -> cb.between(r.get("created"), ini, end);
    }

}
