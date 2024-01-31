package Com.First.ecommerce.organisation;

import Com.First.ecommerce.util.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organisation")
public class OrganisationController {
    @Autowired
    private OrganisationService organisationService;

    //Create
    @PostMapping("/create")
    public ResponseEntity<CustomResponse<Organisation>> createOrganisation(@RequestBody Organisation organisation){
        CustomResponse<Organisation> response = organisationService.createOrganisation(organisation);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    //Read
    @GetMapping
    public ResponseEntity<CustomResponse<List<Organisation>>> getAllOrganisation(){
        CustomResponse<List<Organisation>> response = organisationService.getAllOrganisation();
        return new ResponseEntity(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @GetMapping("/{organisationId}")
    public ResponseEntity<CustomResponse<Organisation>> getOrganisationById(@PathVariable Long organisationId){
        CustomResponse<Organisation> response = organisationService.getOrganisationById(organisationId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    //Update
    @PutMapping("/update")
    public ResponseEntity<CustomResponse<Organisation>> updateOrganisation(@RequestBody Organisation organisation){
        CustomResponse<Organisation> response = organisationService.updateOrganisation(organisation);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    //Delete
    @DeleteMapping("/{organisationId}")
    public ResponseEntity<CustomResponse<String>> deleteOrganisation(@PathVariable Long organisationId){
        CustomResponse<String> response = organisationService.deleteOrganisation(organisationId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

}
