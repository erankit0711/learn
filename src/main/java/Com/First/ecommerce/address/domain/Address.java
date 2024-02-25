package Com.First.ecommerce.address.domain;

import Com.First.ecommerce.order.Model.Order;
import Com.First.ecommerce.user.domain.UserDetail;
import Com.First.ecommerce.util.IdGenerator;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "address")
@Where(clause = "is_Deleted=0")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "addressId", nullable = false)
    private String addressId;

    @Column(name = "streetNo")
    private String streetNo;

    @Column(name = "addressLine1", nullable = false)
    private String addressLine1;

    @Column(name = "addressLine2")
    private String addressLine2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "pincode", nullable = false)
    private String pincode;

    @Column(name = "country", nullable = false)
    private String country;

    @ManyToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private List<UserDetail> userDetailIds;

    @OneToMany(mappedBy = "shippingAddress")
    private List<Order> orders;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted;

    public Address() {
    }

    public Address(String streetNo, String addressLine1, String addressLine2, String city,
        String state, String pincode, String country, List<UserDetail> userDetailIds) {
        this.addressId = IdGenerator.generate(this);
        this.streetNo = streetNo;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
        this.userDetailIds = userDetailIds;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getpincode() {
        return pincode;
    }

    public void setpincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<UserDetail> getUserDetailIds() {
        return userDetailIds;
    }

    public void setUserDetailIds(List<UserDetail> userDetailIds) {
        this.userDetailIds = userDetailIds;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public static final class AddressBuilder {
        private String streetNo;

        private String addressLine1;

        private String addressLine2;

        private String city;

        private String state;

        private String pincode;

        private String country;

        private List<UserDetail> userDetailIds;

        public AddressBuilder() {
        }

        public static AddressBuilder anAddress() {
            return new AddressBuilder();
        }

        public AddressBuilder withStreetNo(String streetNo) {
            this.streetNo = streetNo;
            return this;
        }

        public AddressBuilder withAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public AddressBuilder withAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder withState(String state) {
            this.state = state;
            return this;
        }

        public AddressBuilder withPincode(String pincode) {
            this.pincode = pincode;
            return this;
        }

        public AddressBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public AddressBuilder withUserDetailIds(List<UserDetail> userDetailIds) {
            this.userDetailIds = userDetailIds;
            return this;
        }

        public Address build() {
            return new Address(streetNo, addressLine1, addressLine2, city, state, pincode, country,
                userDetailIds);
        }
    }
}
