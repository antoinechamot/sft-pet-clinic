package ancm.springframework.sftpetclinic.services;

import java.util.Set;

import ancm.springframework.sftpetclinic.model.Owner;

public interface OwnerService {

	public Owner findByLastName(String lastName);
	
	public Owner findById(Long id); 
	
	public Owner save(Owner owner);
	
	Set<Owner> findAll();
}
