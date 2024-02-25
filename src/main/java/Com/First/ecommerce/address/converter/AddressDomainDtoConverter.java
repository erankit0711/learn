package Com.First.ecommerce.address.converter;

import Com.First.ecommerce.address.domain.Address;
import Com.First.ecommerce.address.dto.AddressDomainDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressDomainDtoConverter implements Converter<Address, AddressDomainDto> {
    @Override
    public AddressDomainDto convert(Address address) {
        if (Objects.isNull(address)) {
            return null;
        }
        return new AddressDomainDto.AddressDomainDtoBuilder()
            .withAddressId(address.getAddressId())
            .withStreetNo(address.getStreetNo())
            .withAddressLine1(address.getAddressLine1())
            .withAddressLine2(address.getAddressLine2())
            .withCity(address.getCity())
            .withState(address.getState())
            .withPincode(address.getpincode())
            .withCountry(address.getCountry())
            .build();
    }

    public List<AddressDomainDto> convertList(List<Address> addressList) {
        List<AddressDomainDto> addressDomainDtoList = new ArrayList<>();
        addressList.forEach((address) -> addressDomainDtoList.add(convert(address)));
        return addressDomainDtoList;
    }
}
