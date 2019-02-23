package ancm.springframework.sftpetclinic.services;

import java.util.Set;

import ancm.springframework.sftpetclinic.model.Vet;

public interface VetService {
	
	
	public Vet findById(Long id); 
	
	public Vet save(Vet vet);
	
	Set<Vet> findAll();

}
