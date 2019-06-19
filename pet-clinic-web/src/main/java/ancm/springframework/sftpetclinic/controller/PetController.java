package ancm.springframework.sftpetclinic.controller;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ancm.springframework.sftpetclinic.model.Owner;
import ancm.springframework.sftpetclinic.model.PetType;
import ancm.springframework.sftpetclinic.services.OwnerService;
import ancm.springframework.sftpetclinic.services.PetTypeService;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
	
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
	private final PetTypeService petTypeService;
	private final OwnerService ownerService;
	
	public PetController(PetTypeService petTypeService, OwnerService ownerService) {
		super();
		this.petTypeService = petTypeService;
		this.ownerService = ownerService;
	}

	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes(){
		return petTypeService.findAll();
	}
	
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
		return ownerService.findById(ownerId);
	}
	
	
	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

}
