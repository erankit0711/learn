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
public class ProductController
{
    @Autowired
    ProductService productService;

    //Create Product
    @PostMapping("/create")
    public ResponseEntity<CustomResponse<ProductDto>> createProduct(@RequestBody Product product)
    {
        CustomResponse<ProductDto> response = productService.createProduct(product);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    //Read Product
    @GetMapping
    public ResponseEntity<CustomResponse<List<ProductDto>>> getAllProduct()
    {
        CustomResponse<List<ProductDto>> response =  productService.getAllProduct();
        return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<CustomResponse<ProductDto>> getProductById(@PathVariable Long productId)
    {
        CustomResponse<ProductDto> response = productService.getProductById(productId);
        return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
    }

    //Update Product
    @PutMapping
    public ResponseEntity<CustomResponse<ProductDto>> updateProduct(@RequestBody Product model)
    {
        CustomResponse<ProductDto> response =  productService.updateProduct(model);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    //Delete Product
    @DeleteMapping("/{productId}")
    public ResponseEntity<CustomResponse<String>> deleteProduct(@PathVariable Long productId)
    {
        CustomResponse<String> response = productService.deleteProduct(productId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }
}
