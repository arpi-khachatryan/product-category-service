package am.itspace.productcategoryservice.repository;

import am.itspace.productcategoryservice.model.Category;
import am.itspace.productcategoryservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByCategoryId(Integer integer);

    boolean existsByTitleAndPriceAndCategory(String title, double price, Category category);
}
