package Com.First.ecommerce.shipment;

import Com.First.ecommerce.util.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shipment")
public class ShipmentController {
    ShipmentService shipmentService;
    @PostMapping("/create")
    public ResponseEntity<CustomResponse<Shipment>> createShipment(@RequestBody Shipment shipment){
        CustomResponse<Shipment> response = shipmentService.createShipment(shipment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
