package Com.First.ecommerce.organisation;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganisationRepo extends MongoRepository<Organisation, Long> {
}
