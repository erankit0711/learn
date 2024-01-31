package Com.First.ecommerce.product.Model;

import java.math.BigDecimal;

public class ProductDtoBuilder {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private double quantity;

    public ProductDtoBuilder() {
    }

    public ProductDtoBuilder setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public ProductDtoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductDtoBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductDtoBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductDtoBuilder setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public ProductDtoBuilder setQuantity(double quantity) {
        this.quantity = quantity;
        return this;
    }

    public ProductDto build(){
        return new ProductDto(productId,name,description,price,categoryId,quantity);
    }
}
