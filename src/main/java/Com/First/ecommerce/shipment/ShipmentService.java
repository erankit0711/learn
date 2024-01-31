package Com.First.ecommerce.shipment;

import Com.First.ecommerce.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {
    @Autowired
    ShipmentRepository shipmentRepository;
    public CustomResponse<Shipment> createShipment(Shipment shipment){
        try {
            Shipment data = shipmentRepository.save(shipment);
            return new CustomResponse<>(true, data, null, HttpStatus.CREATED.value());
        }
        catch (Exception e){
            e.printStackTrace();
            return new CustomResponse<>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }
}
