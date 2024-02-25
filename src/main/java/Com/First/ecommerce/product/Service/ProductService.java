package Com.First.ecommerce.product.Service;

import Com.First.ecommerce.product.Model.Product;
import Com.First.ecommerce.product.Model.ProductDto;
import Com.First.ecommerce.product.Model.ProductDtoBuilder;
import Com.First.ecommerce.product.Repository.ProductRepository;
import Com.First.ecommerce.util.CustomResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public ProductDto createProduct(Product product) {
        Product p = productRepo.save(product);
        ProductDto data = productToDto(p);
        return data;
    }

    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepo.findAll();
        List<ProductDto> data = new ArrayList<>();
        for (Product product : products) {
            data.add(productToDto(product));
        }
        return data;
    }

    public ProductDto getProductById(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() ->
            new EntityNotFoundException("No Product found with id " + productId + ".")
        );
        ProductDto data = productToDto(product);
        return data;
    }

    public ProductDto updateProduct(Product product) {
        Long productId = product.getProductId();
        if (productId != null) {
            boolean isProductExist = productRepo.existsById(productId);
            if (isProductExist) {
                Product p = productRepo.save(product);
                ProductDto data = productToDto(p);
                return data;
            } else {
                throw new EntityNotFoundException(
                    "Product with id " + productId + " does not exist.");
            }
        } else {
            throw new NullPointerException("Product Id must not be null");
        }
    }

    public String deleteProduct(Long productId) {
        boolean isProductExist = productRepo.existsById(productId);
        if (isProductExist) {
            productRepo.deleteById(productId);
            return "Successfully deleted.";
        } else {
            throw new EntityNotFoundException("Product with id " + productId + " does not exist.");
        }
    }

    //DTO
    public ProductDto productToDto(Product product) {
        return new ProductDtoBuilder().setProductId(product.getProductId())
            .setName(product.getName()).setDescription(product.getDescription())
            .setPrice(product.getPrice()).setCategoryId(product.getProductId())
            .setQuantity(product.getQuantity()).build();
    }
}
