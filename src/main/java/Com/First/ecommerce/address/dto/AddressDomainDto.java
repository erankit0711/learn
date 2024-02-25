package Com.First.ecommerce.address.dto;

public class AddressDomainDto {
    private String addressId;

    private String streetNo;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String pincode;

    private String country;

    public AddressDomainDto(String addressId, String streetNo, String addressLine1,
        String addressLine2, String city, String state, String pincode, String country) {
        this.addressId = addressId;
        this.streetNo = streetNo;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.country = country;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public static final class AddressDomainDtoBuilder {
        private String addressId;

        private String streetNo;

        private String addressLine1;

        private String addressLine2;

        private String city;

        private String state;

        private String pincode;

        private String country;

        public AddressDomainDtoBuilder() {
        }

        public static AddressDomainDtoBuilder anAddressDomainDto() {
            return new AddressDomainDtoBuilder();
        }

        public AddressDomainDtoBuilder withAddressId(String addressId) {
            this.addressId = addressId;
            return this;
        }

        public AddressDomainDtoBuilder withStreetNo(String streetNo) {
            this.streetNo = streetNo;
            return this;
        }

        public AddressDomainDtoBuilder withAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
            return this;
        }

        public AddressDomainDtoBuilder withAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
            return this;
        }

        public AddressDomainDtoBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressDomainDtoBuilder withState(String state) {
            this.state = state;
            return this;
        }

        public AddressDomainDtoBuilder withPincode(String pincode) {
            this.pincode = pincode;
            return this;
        }

        public AddressDomainDtoBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public AddressDomainDto build() {
            return new AddressDomainDto(addressId, streetNo, addressLine1, addressLine2, city,
                state, pincode, country);
        }
    }
}
