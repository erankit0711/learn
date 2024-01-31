package Com.First.ecommerce.order.Controller;
import Com.First.ecommerce.order.Model.Order;
import Com.First.ecommerce.order.Service.OrderService;
import Com.First.ecommerce.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService ordersService;

    //Create Order
    @PostMapping("/create")
    public ResponseEntity<CustomResponse<Order>> createOrder(@RequestBody Order order){
        CustomResponse<Order> response = ordersService.createOrders(order);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));

    }
    @GetMapping("/GetOrder")
    public String getOrder(){
        return "Sample Order";
    }
}
