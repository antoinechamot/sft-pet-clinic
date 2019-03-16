package ancm.springframework.sftpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import ancm.springframework.sftpetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long>{

}
