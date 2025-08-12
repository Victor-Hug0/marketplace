package br.com.victor.Marketplace.repository;

import br.com.victor.Marketplace.entity.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
