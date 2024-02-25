package Com.First.ecommerce.address.repository;

import Com.First.ecommerce.address.domain.Address;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<Address> findByAddressId(String addressId);
}
