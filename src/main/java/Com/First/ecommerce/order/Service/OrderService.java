package Com.First.ecommerce.order.Service;

import Com.First.ecommerce.order.Model.Order;
import Com.First.ecommerce.order.Repository.OrderRepository;
import Com.First.ecommerce.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrderService
{
    @Autowired
    OrderRepository ordersRepository;

    public CustomResponse<Order> createOrders(Order order){
        try{
            Order data = ordersRepository.save(order);
            return new CustomResponse<>(true, data,null);
        }
        catch (Exception e){
            e.printStackTrace();
            return new CustomResponse<>(false, null,e.getMessage());
        }
    }

}
