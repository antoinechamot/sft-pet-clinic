package ancm.springframework.sftpetclinic.services.map;

import org.springframework.stereotype.Service;

import ancm.springframework.sftpetclinic.model.Pet;
import ancm.springframework.sftpetclinic.services.PetService;


@Service
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService{



}
