package ancm.springframework.sftpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import ancm.springframework.sftpetclinic.model.Speciality;
import ancm.springframework.sftpetclinic.services.SpecialityService;

@Service
@Profile({"default","map"})
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService{

	

}
