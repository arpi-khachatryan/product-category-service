package am.itspace.productcategoryservice.dto;

import am.itspace.productcategoryservice.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private int id;
    private String title;
    private double price;
    private Category category;
}
