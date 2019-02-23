package ancm.springframework.sftpetclinic.services;

import ancm.springframework.sftpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

	public Owner findByLastName(String lastName);
	
}
