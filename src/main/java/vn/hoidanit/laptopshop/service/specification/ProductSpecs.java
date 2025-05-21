package vn.hoidanit.laptopshop.service.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;

public class ProductSpecs {
    public static Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.NAME), "%" + name + "%");
    }

    public static Specification<Product> factoryLike(String factory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.FACTORY), "%" + factory + "%");
    }

    public static Specification<Product> priceBetween(double minPrice, double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get(Product_.PRICE), minPrice, maxPrice);
    }

    public static Specification<Product> minPrice(double minPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(Product_.PRICE),
                minPrice);
    }

    public static Specification<Product> maxPrice(double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(Product_.PRICE),
                maxPrice);
    }

    public static Specification<Product> factoryIn(List<String> listFactory) {
        if (listFactory == null || listFactory.isEmpty()) {
            return null; // no filtering by factory
        }
        return (root, query, criteriaBuilder) -> root.get(Product_.FACTORY).in(listFactory);
    }

    public static Specification<Product> targetIn(List<String> listTarget) {
        if (listTarget == null || listTarget.isEmpty()) {
            return null; // no filtering by target
        }
        return (root, query, criteriaBuilder) -> root.get(Product_.TARGET).in(listTarget);
    }

    public static Specification<Product> priceBetween(List<String> priceRanges) {
        return (root, query, criteriaBuilder) -> {
            if (priceRanges == null || priceRanges.isEmpty()) {
                return null; // no filtering by price range
            }
            List<Predicate> predicates = new ArrayList<>();
            for (String range : priceRanges) {
                switch (range) {
                    case "duoi-10-trieu":
                        predicates.add(criteriaBuilder.lessThan(root.get(Product_.PRICE), 10_000_000d));
                        break;
                    case "10-15-trieu":
                        predicates.add(criteriaBuilder.between(root.get(Product_.PRICE), 10_000_000d, 15_000_000d));
                        break;
                    case "15-20-trieu":
                        predicates.add(criteriaBuilder.between(root.get(Product_.PRICE), 15_000_000d, 20_000_000d));
                        break;
                    case "tren-20-trieu":
                        predicates.add(criteriaBuilder.greaterThan(root.get(Product_.PRICE), 20_000_000d));
                        break;
                    default:
                        break;
                }
            }
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

}