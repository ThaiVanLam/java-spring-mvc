package vn.hoidanit.laptopshop.domain.dto;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

public class ProductCriteriaDTO {
    private Optional<String> page;
    private Optional<String> name;
    private Optional<String> minPrice;
    private Optional<String> maxPrice;
    private Optional<List<String>> factoryList;
    private Optional<List<String>> price;
    private Optional<List<String>> target;
    private Optional<String> sort;

    public Optional<String> getPage() {
        return page;
    }

    public void setPage(Optional<String> page) {
        this.page = page;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Optional<String> minPrice) {
        this.minPrice = minPrice;
    }

    public Optional<String> getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Optional<String> maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Optional<List<String>> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(Optional<List<String>> factoryList) {
        this.factoryList = factoryList;
    }

    public Optional<List<String>> getPrice() {
        return price;
    }

    public void setPrice(Optional<List<String>> price) {
        this.price = price;
    }

    public Optional<List<String>> getTarget() {
        return target;
    }

    public void setTarget(Optional<List<String>> target) {
        this.target = target;
    }

    public Optional<String> getSort() {
        return sort;
    }

    public void setSort(Optional<String> sort) {
        this.sort = sort;
    }

}
