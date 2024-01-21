package Com.First.CrudApp.Organisation;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganisationRepo extends MongoRepository<Organisation, Long> {
}
