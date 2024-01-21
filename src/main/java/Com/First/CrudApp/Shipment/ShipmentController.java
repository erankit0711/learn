package Com.First.CrudApp.Shipment;

import Com.First.CrudApp.Util.CustomResponse;
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
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }
}
