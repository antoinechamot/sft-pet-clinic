package ancm.springframework.sftpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ancm.springframework.sftpetclinic.model.Owner;

class OwnerMapServiceTest {

	OwnerMapService ownerMapService;
	final Long ownerId = 1L;
	final String lastName = "TOTO";
	
	@BeforeEach
	void setUp() throws Exception {
		ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		Owner owner = Owner.builder().id(ownerId).lastName(lastName).build();
		ownerMapService.save(owner);
	}
	@Test
	void testSaveOwnerWithId() {
		Long id = 2L;
		Owner owner2 =  Owner.builder().id(id).build();
		Owner savedOwner = ownerMapService.save(owner2);
		assertEquals(id, savedOwner.getId());
	}
	@Test
	void testSaveOwnerWithNoId() {
		Owner owner2 =  Owner.builder().build();
		Owner savedOwner = ownerMapService.save(owner2);
		assertNotNull(savedOwner);
		assertNotNull(savedOwner.getId());
	}
	@Test
	void testFindByLastName() {
		Owner owner = ownerMapService.findByLastName(lastName);
		assertNotNull(owner);
		assertEquals(ownerId, owner.getId());
	}
	@Test
	void testFindByLastNameNotFound() {
		Owner owner = ownerMapService.findByLastName("foo");
		assertNull(owner);
	}
	@Test
	void testFindAll() {
		Set<Owner> owners = ownerMapService.findAll();
		assertEquals(1, owners.size());
	}
	@Test
	void testFindById() {
		Owner owner = ownerMapService.findById(ownerId);
		assertEquals(ownerId, owner.getId());
	}
	@Test
	void testDeleteById() {
		ownerMapService.deleteById(ownerId);
		assertEquals(0, ownerMapService.findAll().size());
	}
	@Test
	void testDelete() {
		ownerMapService.delete(ownerMapService.findById(ownerId));
		assertEquals(0, ownerMapService.findAll().size());
	}

}
