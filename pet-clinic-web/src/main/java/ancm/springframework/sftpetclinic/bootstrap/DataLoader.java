package ancm.springframework.sftpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ancm.springframework.sftpetclinic.model.Owner;
import ancm.springframework.sftpetclinic.model.Pet;
import ancm.springframework.sftpetclinic.model.PetType;
import ancm.springframework.sftpetclinic.model.Speciality;
import ancm.springframework.sftpetclinic.model.Vet;
import ancm.springframework.sftpetclinic.model.Visit;
import ancm.springframework.sftpetclinic.services.OwnerService;
import ancm.springframework.sftpetclinic.services.PetTypeService;
import ancm.springframework.sftpetclinic.services.SpecialityService;
import ancm.springframework.sftpetclinic.services.VetService;
import ancm.springframework.sftpetclinic.services.VisitService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;
	private final  PetTypeService petTypeService;
	private final  SpecialityService specialityService;
	private final VisitService visitService;

	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
			SpecialityService specialityService, VisitService visitService) {
		super();
		this.ownerService = ownerService;
		this.vetService = vetService;
		this.petTypeService = petTypeService;
		this.specialityService = specialityService;
		this.visitService = visitService;
	}

	@Override
	public void run(String... args) throws Exception {
		
		int count = petTypeService.findAll().size();
		if(count == 0) {
			loadData();
		}
	}

	private void loadData() {
		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);
		
		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);
		
		Speciality spe1 = new Speciality();
		spe1.setDescription("spe1");
		Speciality savedSpe1 = specialityService.save(spe1);
		
		Speciality spe2 = new Speciality();
		spe2.setDescription("spe2");
		Speciality savedSpe2 = specialityService.save(spe2);
		
		Speciality spe3 = new Speciality();
		spe3.setDescription("spe3");
		Speciality savedSpe3 = specialityService.save(spe3);
		
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
		
		Visit visit1 = new Visit();
		visit1.setPet(pet2);
		visit1.setDate(LocalDate.now());
		visit1.setDescription("visit description");
		
		visitService.save(visit1);
		
	   System.out.println("Owners Loaded");
	   
	   Vet vet1 = new Vet();
	   vet1.setFirstName("titi");
	   vet1.setLastName("titi2");
	   vet1.getSpecialities().add(savedSpe1);
	   vetService.save(vet1);
	   
	   Vet vet2 = new Vet();
	   vet2.setFirstName("titi");
	   vet2.setLastName("titi2");
	   vetService.save(vet2);
	   vet2.getSpecialities().add(savedSpe2);
	   
	   System.out.println("Vet Loaded");
	}

}
