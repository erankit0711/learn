package Com.First.CrudApp.Order.Controller;
import Com.First.CrudApp.Order.Model.Order;
import Com.First.CrudApp.Order.Service.OrderService;
import Com.First.CrudApp.Util.CustomResponse;
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
