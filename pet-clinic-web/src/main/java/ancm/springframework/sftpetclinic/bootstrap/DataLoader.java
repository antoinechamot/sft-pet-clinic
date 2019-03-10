package ancm.springframework.sftpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ancm.springframework.sftpetclinic.model.Owner;
import ancm.springframework.sftpetclinic.model.PetType;
import ancm.springframework.sftpetclinic.model.Vet;
import ancm.springframework.sftpetclinic.services.OwnerService;
import ancm.springframework.sftpetclinic.services.PetTypeService;
import ancm.springframework.sftpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final  PetTypeService petTypeService;



	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
	}






	@Override
	public void run(String... args) throws Exception {
		
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Owner owner1 = new Owner();
		owner1.setFirstName("toto");
		owner1.setLastName("toto2");
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("tata");
		owner2.setLastName("tata2");
		ownerService.save(owner2);
		
	   System.out.println("Owners Loaded");
	   
	   Vet vet1 = new Vet();
	   vet1.setFirstName("titi");
	   vet1.setLastName("titi2");
	   vetService.save(vet1);
	   
	   Vet vet2 = new Vet();
	   vet2.setFirstName("titi");
	   vet2.setLastName("titi2");
	   vetService.save(vet2);
	   
	   System.out.println("Vet Loaded");
	}

}
