package Com.First.ecommerce.organisation;


import Com.First.ecommerce.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganisationService {
    @Autowired
    private OrganisationRepo organisationRepo;

    //Create
    public CustomResponse<Organisation> createOrganisation(Organisation organisation){
        try{
            Organisation org = organisationRepo.save(organisation);
            return new CustomResponse<>(true, org, null, HttpStatus.CREATED.value());
        }catch (Exception e){
            e.printStackTrace();
            return new CustomResponse<>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    //Read
    public CustomResponse<List<Organisation>> getAllOrganisation(){
        try{
            List<Organisation> organisations = organisationRepo.findAll();
            return new CustomResponse<>(true, organisations, null, HttpStatus.OK.value());
        } catch (Exception e){
            e.printStackTrace();
            return new CustomResponse<>(false, null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    public CustomResponse<Organisation> getOrganisationById(Long id){
        try{
            boolean isOrganisationExist = organisationRepo.existsById(id);
            if(isOrganisationExist){
                Organisation organisation = organisationRepo.findById(id).orElse(null);
                return new CustomResponse<>(true, organisation, null, HttpStatus.OK.value());
            }else{
                throw new Exception("No organisation found with id "+ id+".");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CustomResponse<>(false, null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    //Update
    public CustomResponse<Organisation> updateOrganisation(Organisation organisation){
        try {
            Long orgId = organisation.getId();
            boolean isOrganisationExist = organisationRepo.existsById(orgId);
            if(isOrganisationExist){
                Organisation org = organisationRepo.save(organisation);
                return new CustomResponse<>(true, org, null, HttpStatus.OK.value());
            }else{
                throw new Exception("organisation does not exist with id "+ orgId+".");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CustomResponse<>(false, null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    //Delete
    public CustomResponse<String> deleteOrganisation(Long organisationId){
        try {
            boolean isOrganisationExist = organisationRepo.existsById(organisationId);
            if(isOrganisationExist){
                organisationRepo.deleteById(organisationId);
                return new CustomResponse<>(true, "Organisation deleted successfully.", null, HttpStatus.OK.value());
            }else {
                throw new Exception("Organisation does not exist with id "+organisationId+".");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CustomResponse<>(false, null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }
}
