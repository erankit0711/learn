package Com.First.CrudApp.Order.Service;

import Com.First.CrudApp.Order.Model.Order;
import Com.First.CrudApp.Order.Repository.OrderRepository;
import Com.First.CrudApp.Util.CustomResponse;
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
            return new CustomResponse<>(true, data,null, HttpStatus.CREATED.value());
        }
        catch (Exception e){
            e.printStackTrace();
            return new CustomResponse<>(false, null,e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

}
