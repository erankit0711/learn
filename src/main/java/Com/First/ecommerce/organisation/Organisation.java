package Com.First.ecommerce.organisation;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "organisation")
public class Organisation {
    private Long id;
    private String organisationName;
    private String email;
    private String address;
    private Boolean isActive;

    public Organisation(Long id, String organisationName, String email, String address, Boolean isActive) {
        this.id = id;
        this.organisationName = organisationName;
        this.email = email;
        this.address = address;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}