package ancm.springframework.sftpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import ancm.springframework.sftpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long>{
	
	public Owner findByLastName(String lastName);

}
