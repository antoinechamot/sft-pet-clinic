package ancm.springframework.sftpetclinic.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ancm.springframework.sftpetclinic.model.Owner;
import ancm.springframework.sftpetclinic.services.OwnerService;


@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
	
	@Mock
	OwnerService ownerService;
	@InjectMocks
	OwnerController ownerController;
	Set<Owner> owners;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());
		mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
	}


	@Test
	void testFindOwner() throws Exception {
		mockMvc.perform(get("/owners/find"))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/findOwners"))
		.andExpect(model().attributeExists("owner"));
		verifyZeroInteractions(ownerService);
	}
	
	@Test
	void testProcessFindFormReturnMany() throws Exception {
		List<Owner> returnOwners = new ArrayList<Owner>();
		returnOwners.add(Owner.builder().id(1L).build());
		returnOwners.add(Owner.builder().id(2L).build());
		when(ownerService.findAllByLastNameLike(Mockito.anyString())).thenReturn(returnOwners);
		mockMvc.perform(get("/owners"))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/ownersList"))
		.andExpect(model().attribute("selections", hasSize(2)));
	}
	
	@Test
	void testProcessFindFormReturnOne() throws Exception {
		List<Owner> returnOwners = new ArrayList<Owner>();
		returnOwners.add(Owner.builder().id(1L).build());
		when(ownerService.findAllByLastNameLike(Mockito.anyString())).thenReturn(returnOwners);
		mockMvc.perform(get("/owners"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/owners/1"));
	}
	
	
	@Test
	void testDisplayOwner() throws Exception {
		when(ownerService.findById(Mockito.anyLong())).thenReturn(Owner.builder().id(1L).build());
		mockMvc.perform(get("/owners/123"))
		.andExpect(status().isOk())
		.andExpect(view().name("/owners/ownerDetails"))
		.andExpect(model().attribute("owner", hasProperty("id", is(1L))));
	}
	
	@Test
	void initCreationForm() throws Exception {
		mockMvc.perform(get("/owners/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/createOrUpdateOwnerForm"))
		.andExpect(model().attributeExists("owner"));
	}
	
	@Test
	void processCreationForm() throws Exception {
		when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());
		mockMvc.perform(post("/owners/new"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/owners/1"))
		.andExpect(model().attributeExists("owner"));
		
		verify(ownerService).save(ArgumentMatchers.any());
	}
	
	@Test
	void initUpdateOwnerForm() throws Exception {
		when(ownerService.findById(Mockito.anyLong())).thenReturn(Owner.builder().id(1L).build());
		
		mockMvc.perform(get("/owners/1/edit"))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/createOrUpdateOwnerForm"))
		.andExpect(model().attributeExists("owner"));
		
		verifyZeroInteractions(ownerService);
	}
	
	@Test
	void processUpdateOwnerForm() throws Exception {
		when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());
		mockMvc.perform(post("/owners/1/edit"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/owners/1"))
		.andExpect(model().attributeExists("owner"));
		
		verify(ownerService).save(ArgumentMatchers.any());
	
	}
	
	@Test
	void processFindFormEmptyReturnMany() throws Exception {
		List<Owner> returnOwners = new ArrayList<Owner>();
		returnOwners.add(Owner.builder().id(1L).build());
		returnOwners.add(Owner.builder().id(2L).build());
		
		when(ownerService.findAllByLastNameLike(ArgumentMatchers.anyString()))
		.thenReturn(returnOwners);
		
		mockMvc.perform(get("/owners").param("lastName", ""))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/ownersList"))
		.andExpect(model().attribute("selections", hasSize(2)));
		
	}
	
}
