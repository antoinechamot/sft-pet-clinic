package ancm.springframework.sftpetclinic.services.map;

import ancm.springframework.sftpetclinic.model.Pet;
import ancm.springframework.sftpetclinic.services.PetService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService{

	@Override
	public Pet save(Pet object) {
		return super.save(object.getId(), object);
	}

}
