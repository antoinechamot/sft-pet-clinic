package ancm.springframework.sftpetclinic.services.map;

import ancm.springframework.sftpetclinic.model.Pet;
import ancm.springframework.sftpetclinic.services.CrudService;

public class PetServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long>{

	@Override
	public Pet save(Pet object) {
		return super.save(object.getId(), object);
	}

}
