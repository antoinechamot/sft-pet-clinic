package ancm.springframework.sftpetclinic.services.map;

import ancm.springframework.sftpetclinic.model.Vet;
import ancm.springframework.sftpetclinic.services.CrudService;

public class VetServiceMap  extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long>{



	@Override
	public Vet save(Vet object) {
		return super.save(object.getId(), object);
	}

}
