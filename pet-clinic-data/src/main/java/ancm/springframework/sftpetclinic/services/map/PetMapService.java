package ancm.springframework.sftpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import ancm.springframework.sftpetclinic.model.Pet;
import ancm.springframework.sftpetclinic.services.PetService;


@Service
@Profile({"default","map"})
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService{



}
