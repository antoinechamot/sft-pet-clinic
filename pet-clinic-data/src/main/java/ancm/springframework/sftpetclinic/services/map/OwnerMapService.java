package ancm.springframework.sftpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import ancm.springframework.sftpetclinic.model.Owner;
import ancm.springframework.sftpetclinic.model.Pet;
import ancm.springframework.sftpetclinic.services.OwnerService;
import ancm.springframework.sftpetclinic.services.PetService;
import ancm.springframework.sftpetclinic.services.PetTypeService;


@Service
@Profile({"default","map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService{

	private final PetTypeService petTypeService;
	private final PetService petService;

	public OwnerMapService(PetTypeService petTypeService, PetService petService) {
		super();
		this.petTypeService = petTypeService;
		this.petService = petService;
	}
	
	@Override
	public Owner save(Owner object) {
		
		Owner savedOwner = null;
		
		if(object != null) {
			
			object.getPets().forEach(pet -> {
				
				if(pet.getPetType() != null) {
					
					if(pet.getPetType().getId() == null) {
						pet.setPetType(petTypeService.save(pet.getPetType())); 
					}
					
				} else {
					throw new RuntimeException("Pet Type is Required");
				}
				
				if(pet.getId() == null) {
					Pet savedPet = petService.save(pet);
					pet.setId(savedPet.getId());
				}
			});
			
			return super.save(object);
		}
		
		return savedOwner;
		
	}

	@Override
	public Owner findByLastName(String lastName) {
		return this.findAll().stream()
				.filter(owner-> owner.getLastName().equalsIgnoreCase(lastName))
				.findFirst()
				.orElse(null);
	}

}
