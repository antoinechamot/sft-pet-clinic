package ancm.springframework.sftpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import ancm.springframework.sftpetclinic.model.PetType;
import ancm.springframework.sftpetclinic.services.PetTypeService;


@Service
@Profile({"default","map"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService{

}
