package ancm.springframework.sftpetclinic.services.map;

import org.springframework.stereotype.Service;

import ancm.springframework.sftpetclinic.model.Speciality;
import ancm.springframework.sftpetclinic.model.Vet;
import ancm.springframework.sftpetclinic.services.SpecialityService;
import ancm.springframework.sftpetclinic.services.VetService;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

	private SpecialityService specialityService;

	public VetServiceMap(SpecialityService specialityService) {
		super();
		this.specialityService = specialityService;
	}

	@Override
	public Vet save(Vet object) {
		
		if(!object.getSpecialities().isEmpty()) {
			object.getSpecialities().forEach(speciality ->{
				if(speciality.getId() == null) {
					Speciality savedSpeciality = specialityService.save(speciality);
					speciality.setId(savedSpeciality.getId());
				}
			});
		}
		return super.save(object);
	}

}
