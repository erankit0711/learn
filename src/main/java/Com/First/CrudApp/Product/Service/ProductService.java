package Com.First.CrudApp.Product.Service;

import Com.First.CrudApp.Product.Model.Product;
import Com.First.CrudApp.Product.Model.ProductDto;
import Com.First.CrudApp.Product.Model.ProductDtoBuilder;
import Com.First.CrudApp.Product.Repository.ProductRepository;
import Com.First.CrudApp.Util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepo;

    //Create Product
    public CustomResponse<ProductDto> createProduct(Product product)
    {
        try
        {
            Product p = productRepo.save(product);
            ProductDto data = productToDto(p);
            return new CustomResponse<>(true, data, null, HttpStatus.CREATED.value());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new CustomResponse<>(false,null,e.getMessage(),HttpStatus.BAD_REQUEST.value());
        }
    }


    //Read Product
    public CustomResponse<List<ProductDto>> getAllProduct()
    {
        try
        {
            List<Product> products = productRepo.findAll();
            List<ProductDto> data = new ArrayList<>();
            for(Product product:products){
                data.add(productToDto(product));
            }
            return new CustomResponse<>(true,data,null,HttpStatus.OK.value());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new CustomResponse<>(false,null,"Resource not found",HttpStatus.NOT_FOUND.value());
        }
    }
    public CustomResponse<ProductDto> getProductById(Long productId)
    {
        try
        {
            boolean isProductExist = productRepo.existsById(productId);
            if(isProductExist)
            {
               Product product = productRepo.findById(productId).orElse(null);
               ProductDto data = productToDto(product);
                return new CustomResponse<>(true,data,null,HttpStatus.OK.value());
            }
            else
            {
                throw new Exception("No Product found with id "+productId+".");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new CustomResponse<>(false,null,e.getMessage(),HttpStatus.NOT_FOUND.value());
        }
    }


    //Update Product
    public CustomResponse<ProductDto> updateProduct(Product product)
    {
        try
        {
            Long productId = product.getProductId();
            if(productId != null)
            {
                boolean isProductExist = productRepo.existsById(productId);
                if(isProductExist)
                {
                    Product p = productRepo.save(product);
                    ProductDto data = productToDto(p);
                    return new CustomResponse<>(true,data,null, HttpStatus.OK.value());
                }
                else
                {
                    throw new Exception("Product with id "+productId+" does not exist.");
                }
            }
            else
            {
                throw new NullPointerException("Product Id must not be null");
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            return new CustomResponse<>(false,null,e.getMessage(),HttpStatus.NOT_FOUND.value());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new CustomResponse<>(false,null,e.getMessage(),HttpStatus.BAD_REQUEST.value());
        }
    }


    //Delete Product
    public CustomResponse<String> deleteProduct(Long productId)
    {
        try
        {
            boolean isProductExist = productRepo.existsById(productId);
            if(isProductExist)
            {
                productRepo.deleteById(productId);
                return new CustomResponse<>(true,"Successfully deleted.",null,HttpStatus.OK.value());
            }
            else
            {
                throw new Exception("Product with id "+productId+" does not exist.");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new CustomResponse<>(false,null,e.getMessage(),HttpStatus.NOT_FOUND.value());
        }

    }

    //DTO
    public ProductDto productToDto(Product product){
        return new ProductDtoBuilder().setProductId(product.getProductId())
                .setName(product.getName()).setDescription(product.getDescription())
                .setPrice(product.getPrice()).setCategoryId(product.getProductId())
                .setQuantity(product.getQuantity()).build();
    }
}
