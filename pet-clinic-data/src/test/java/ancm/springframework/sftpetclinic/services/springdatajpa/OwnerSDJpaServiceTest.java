package ancm.springframework.sftpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ancm.springframework.sftpetclinic.model.Owner;
import ancm.springframework.sftpetclinic.repositories.OwnerRepository;
import ancm.springframework.sftpetclinic.repositories.PetRepository;
import ancm.springframework.sftpetclinic.repositories.PetTypeRepository;


@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
	
	private static final String LAST_NAME = "TOTO";
	@Mock
	OwnerRepository ownerRepository;
	@Mock
	PetRepository petRepository;
	@Mock
	PetTypeRepository petTypeRepository;
	@InjectMocks
	OwnerSDJpaService ownerService;
	
	Owner returnOwner;

	@BeforeEach
	void setUp() throws Exception {
		returnOwner =  Owner.builder().id(1L).lastName(LAST_NAME).build();
	}
	
	@Test
	void testFindByLastName() {
		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
	    Owner owner = ownerService.findByLastName(LAST_NAME);
	    assertEquals(LAST_NAME, owner.getLastName());
	    verify(ownerRepository).findByLastName(any());
	}

	@Test
	void testFindAll() {
		Set<Owner> returnOwnersSet = new HashSet<Owner>();
		returnOwnersSet.add(Owner.builder().id(1L).build());
		returnOwnersSet.add(Owner.builder().id(2L).build());
		when(ownerRepository.findAll()).thenReturn(returnOwnersSet);
		Set<Owner> owners = ownerService.findAll();
		assertNotNull(owners);
		assertEquals(2, owners.size());
	}
	
	@Test
	void testFindById() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
		Owner owner = ownerService.findById(1L);
		assertNotNull(owner);
	}
	
	
	@Test
	void testFindByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		Owner owner = ownerService.findById(1L);
		assertNull(owner);
	}
	

	@Test
	void testSave() {
		Owner ownerToSave = Owner.builder().id(1L).build();
		when(ownerRepository.save(any())).thenReturn(returnOwner);
		Owner savedOwner = ownerService.save(ownerToSave);
		assertNotNull(savedOwner);
		verify(ownerRepository).save(any());
	}

	@Test
	void testDelete() {
		ownerService.delete(returnOwner);
		verify(ownerRepository,times(1)).delete(any());
	}

	@Test
	void testDeleteById() {
		ownerService.deleteById(1L);
		verify(ownerRepository,times(1)).deleteById(anyLong());
	}

	

}
