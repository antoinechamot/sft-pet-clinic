package ancm.springframework.sftpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import ancm.springframework.sftpetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long>{

}
