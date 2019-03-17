package ancm.springframework.sftpetclinic.services.map;

import org.springframework.stereotype.Service;

import ancm.springframework.sftpetclinic.model.PetType;
import ancm.springframework.sftpetclinic.services.PetTypeService;


@Service
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService{

}
