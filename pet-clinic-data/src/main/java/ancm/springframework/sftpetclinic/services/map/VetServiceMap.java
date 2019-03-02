package ancm.springframework.sftpetclinic.services.map;

import org.springframework.stereotype.Service;

import ancm.springframework.sftpetclinic.model.Vet;
import ancm.springframework.sftpetclinic.services.VetService;


@Service
public class VetServiceMap  extends AbstractMapService<Vet, Long> implements VetService{



	@Override
	public Vet save(Vet object) {
		return super.save(object.getId(), object);
	}

}
