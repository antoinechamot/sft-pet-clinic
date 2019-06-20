package ancm.springframework.sftpetclinic.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ancm.springframework.sftpetclinic.model.Owner;
import ancm.springframework.sftpetclinic.model.Pet;
import ancm.springframework.sftpetclinic.model.PetType;
import ancm.springframework.sftpetclinic.services.OwnerService;
import ancm.springframework.sftpetclinic.services.PetService;
import ancm.springframework.sftpetclinic.services.PetTypeService;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
	
	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
	private final PetTypeService petTypeService;
	private final OwnerService ownerService;
	private final PetService petService;
	
	public PetController(PetTypeService petTypeService, OwnerService ownerService,PetService petService) {
		super();
		this.petTypeService = petTypeService;
		this.ownerService = ownerService;
		this.petService = petService;
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
	
	@GetMapping("/pets/new")
	public String initCreationForm(Owner owner , Model model) {
		Pet pet = Pet.builder().build();
		owner.getPets().add(pet);
		pet.setOwner(owner);
		model.addAttribute("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/pets/new")
	public String processCreationForm(Owner owner,@Valid Pet pet,BindingResult result, Model model) {
		if(StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(),true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}
		owner.getPets().add(pet);
		if(result.hasErrors()) {
			model.addAttribute("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}else {
			petService.save(pet);
			return "redirect:/owners/" + owner.getId();
		}
	}
	
	@GetMapping("/pets/{petId}/edit")
	public String initUpdateForm(@PathVariable Long petId, Model model) {
		model.addAttribute("pet", petService.findById(petId));
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}
	
	@PostMapping("/pets/{petId}/edit")
	public String processUpdateForm(@Valid Pet pet,BindingResult result,Owner owner, Model model) {
		if(result.hasErrors()) {
			pet.setOwner(owner);
			model.addAttribute("pet",pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}else {
			owner.getPets().add(pet);
			petService.save(pet);
			return "redirect:/owners/" + owner.getId();
		}
	}
	
	

}
