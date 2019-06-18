package ancm.springframework.sftpetclinic.services;

import java.util.List;

import ancm.springframework.sftpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

	public Owner findByLastName(String lastName);
	public List<Owner> findAllByLastNameLike(String lastName);
	
}
