package am.itspace.productcategoryservice.repository;

import am.itspace.productcategoryservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
