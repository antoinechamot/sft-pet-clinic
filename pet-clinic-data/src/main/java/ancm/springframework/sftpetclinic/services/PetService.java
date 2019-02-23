package ancm.springframework.sftpetclinic.services;

import java.util.Set;

import ancm.springframework.sftpetclinic.model.Pet;

public interface PetService {
	
	
	public Pet findById(Long id); 
	
	public Pet save(Pet pet);
	
	Set<Pet> findAll();

}
