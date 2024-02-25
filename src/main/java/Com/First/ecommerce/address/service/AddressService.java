package Com.First.ecommerce.address.service;

import Com.First.ecommerce.address.converter.AddressDomainDtoConverter;
import Com.First.ecommerce.address.domain.Address;
import Com.First.ecommerce.address.dto.AddressDomainDto;
import Com.First.ecommerce.address.repository.AddressRepository;
import Com.First.ecommerce.util.CustomResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressDomainDtoConverter addressDomainDtoConverter;

    public CustomResponse<AddressDomainDto> createAddress(Address addressRequest) {
        try {
            Address address =
                new Address.AddressBuilder()
                    .withStreetNo(addressRequest.getStreetNo())
                    .withAddressLine1(addressRequest.getAddressLine1())
                    .withAddressLine2(addressRequest.getAddressLine2())
                    .withCity(addressRequest.getCity())
                    .withState(addressRequest.getState())
                    .withPincode(addressRequest.getpincode())
                    .withCountry(addressRequest.getCountry())
                    .build();

            addressRepository.save(address);
            AddressDomainDto addressDto = addressDomainDtoConverter.convert(address);
            return CustomResponse.success(addressDto, null);
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage());
        }
    }

    public CustomResponse<List<AddressDomainDto>> getAllAddress() {
        try {
            List<Address> addressList = addressRepository.findAll();
            List<AddressDomainDto> addressDtoList =
                addressDomainDtoConverter.convertList(addressList);
            return CustomResponse.success(addressDtoList, null);
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage());
        }
    }

    public CustomResponse<AddressDomainDto> getAddressById(String addressId) {
        try {
            Address address = addressRepository.findByAddressId(addressId)
                .orElseThrow(
                    () -> new NullPointerException("No Address found with id " + addressId + "."));
            AddressDomainDto addressDomainDto = addressDomainDtoConverter.convert(address);
            return CustomResponse.success(addressDomainDto, null);
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage());
        }
    }

    public CustomResponse<AddressDomainDto> updateAddress(Address addressRequest) {
        try {
            String addressId = addressRequest.getAddressId();
            Address address = addressRepository.findByAddressId(addressId).orElseThrow(
                    () -> new NullPointerException("No Address found with id " + addressId + "."));
            Address updatedAddress =
                new Address.AddressBuilder()
                    .withStreetNo(addressRequest.getStreetNo())
                    .withAddressLine1(addressRequest.getAddressLine1())
                    .withAddressLine2(addressRequest.getAddressLine2())
                    .withCity(addressRequest.getCity())
                    .withState(addressRequest.getState())
                    .withPincode(addressRequest.getpincode())
                    .withCountry(addressRequest.getCountry())
                    .build();

            updatedAddress.setId(address.getId());
            updatedAddress.setAddressId(address.getAddressId());
            Address savedAddress = addressRepository.save(updatedAddress);
            AddressDomainDto addressDomainDto = addressDomainDtoConverter.convert(savedAddress);
            return CustomResponse.success(addressDomainDto, null);
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage());
        }
    }

    public CustomResponse<String> deleteAddress(String addressId) {
        try {
            Address address = addressRepository.findByAddressId(addressId)
                .orElseThrow(
                    () -> new NullPointerException("No Address found with id " + addressId + "."));
            address.setDeleted(true);
            addressRepository.save(address);
            return CustomResponse.success("Successfully deleted", null);
        } catch (Exception e) {
            e.printStackTrace();
            return CustomResponse.failure(null, e.getMessage());
        }
    }
}
