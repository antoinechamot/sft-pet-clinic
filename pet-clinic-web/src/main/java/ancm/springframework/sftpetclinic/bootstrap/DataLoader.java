package ancm.springframework.sftpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ancm.springframework.sftpetclinic.model.Owner;
import ancm.springframework.sftpetclinic.model.Vet;
import ancm.springframework.sftpetclinic.services.OwnerService;
import ancm.springframework.sftpetclinic.services.VetService;
import ancm.springframework.sftpetclinic.services.map.OwnerServiceMap;
import ancm.springframework.sftpetclinic.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
	private final VetService vetService;

	public DataLoader() {

		ownerService = new OwnerServiceMap();
		vetService = new VetServiceMap();
	}

	@Override
	public void run(String... args) throws Exception {
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("toto");
		owner1.setLastName("toto2");
		ownerService.save(owner1);
		
		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("tata");
		owner2.setLastName("tata2");
		ownerService.save(owner2);
		
	   System.out.println("Owners Loaded");
	   
	   Vet vet1 = new Vet();
	   vet1.setId(1L);
	   vet1.setFirstName("titi");
	   vet1.setLastName("titi2");
	   vetService.save(vet1);
	   
	   Vet vet2 = new Vet();
	   vet2.setId(2L);
	   vet2.setFirstName("titi");
	   vet2.setLastName("titi2");
	   vetService.save(vet2);
	   
	   System.out.println("Vet Loaded");
	}

}
