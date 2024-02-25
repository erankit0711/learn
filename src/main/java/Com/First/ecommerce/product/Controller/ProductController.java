package Com.First.ecommerce.product.Controller;

import Com.First.ecommerce.product.Model.Product;
import Com.First.ecommerce.product.Model.ProductDto;
import Com.First.ecommerce.product.Service.ProductService;
import Com.First.ecommerce.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    //Create Product
    @PostMapping("/create")
    public ResponseEntity<CustomResponse<ProductDto>> createProduct(@RequestBody Product product) {
        ProductDto response = productService.createProduct(product);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.CREATED);
    }

    //Read Product
    @GetMapping
    public ResponseEntity<CustomResponse<List<ProductDto>>> getAllProduct() {
        List<ProductDto> response = productService.getAllProduct();
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<CustomResponse<ProductDto>> getProductById(@PathVariable Long productId) {
        ProductDto response = productService.getProductById(productId);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }

    //Update Product
    @PutMapping
    public ResponseEntity<CustomResponse<ProductDto>> updateProduct(@RequestBody Product model) {
        ProductDto response = productService.updateProduct(model);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }

    //Delete Product
    @DeleteMapping("/{productId}")
    public ResponseEntity<CustomResponse<String>> deleteProduct(@PathVariable Long productId) {
        String response = productService.deleteProduct(productId);
        return new ResponseEntity<>(CustomResponse.success(response, null), HttpStatus.OK);
    }
}
