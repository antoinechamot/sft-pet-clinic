package ancm.springframework.sftpetclinic.services.map;

import ancm.springframework.sftpetclinic.model.Owner;
import ancm.springframework.sftpetclinic.services.CrudService;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements CrudService<Owner, Long>{

	@Override
	public Owner save(Owner object) {
		return this.save(object.getId(),object);
	}

}
