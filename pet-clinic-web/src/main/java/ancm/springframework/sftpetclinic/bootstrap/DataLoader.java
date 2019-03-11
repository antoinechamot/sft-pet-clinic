package ancm.springframework.sftpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ancm.springframework.sftpetclinic.model.Owner;
import ancm.springframework.sftpetclinic.model.Pet;
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
		owner1.setAddress("123 Street");
		owner1.setCity("city1");
		owner1.setTelephone("tel1");
		
		Pet pet1 = new Pet();
		pet1.setPetType(savedDogPetType);
		pet1.setOwner(owner1);
		pet1.setBirthDate(LocalDate.now());
		pet1.setName("pet1Name");
		owner1.getPets().add(pet1);
		
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("tata");
		owner2.setLastName("tata2");
		owner2.setAddress("123 Street");
		owner2.setCity("city2");
		owner2.setTelephone("tel2");
		
		

		Pet pet2 = new Pet();
		pet2.setPetType(savedCatPetType);
		pet2.setOwner(owner2);
		pet2.setBirthDate(LocalDate.now());
		pet2.setName("pet2Name");
		owner2.getPets().add(pet2);
		
		
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
