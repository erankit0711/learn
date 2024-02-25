package Com.First.ecommerce.address.controller;

import Com.First.ecommerce.address.dto.AddressDomainDto;
import Com.First.ecommerce.address.service.AddressService;
import Com.First.ecommerce.address.domain.Address;
import Com.First.ecommerce.util.CustomResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<AddressDomainDto>> createAddress(@RequestBody
    Address address){
        CustomResponse<AddressDomainDto> response = addressService.createAddress(address);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CustomResponse<List<AddressDomainDto>>> getAllAddress(){
        CustomResponse<List<AddressDomainDto>> response = addressService.getAllAddress();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<CustomResponse<AddressDomainDto>> getAddressById(@PathVariable String addressId){
        CustomResponse<AddressDomainDto> response = addressService.getAddressById(addressId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomResponse<AddressDomainDto>> updateAddress(@RequestBody Address address){
        CustomResponse<AddressDomainDto> response = addressService.updateAddress(address);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<CustomResponse<String>> deleteAddress(@PathVariable String addressId){
        CustomResponse<String> response = addressService.deleteAddress(addressId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
