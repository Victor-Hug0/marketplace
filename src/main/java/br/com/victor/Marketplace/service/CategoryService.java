package br.com.victor.Marketplace.service;

import br.com.victor.Marketplace.entity.product.Category;
import br.com.victor.Marketplace.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class CategoryService {

    public Category findRootCategory(Category category) {
        if (category == null) {
            throw new ResourceNotFoundException("Category not found");
        }

        Category current = category;
        while (current.getParentCategory() != null) {
            current = current.getParentCategory();
        }

        return current;
    }

    public BigDecimal getMarketPlaceFeeForCategory(Category category, BigDecimal defaultFee) {

        if (category == null) {
            return defaultFee;
        }

        Category rootCategory = findRootCategory(category);

        return rootCategory.getMarketplaceFeePercentage();
    }
}
