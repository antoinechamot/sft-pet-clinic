package ancm.springframework.sftpetclinic.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="owners")
public class Owner extends Person{
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy= "owner")
	private Set<Pet> pets = new HashSet<Pet>();
	@Column(name="address")
	private String address;
	@Column(name="city")
	private String city;
	@Column(name="telephone")
	private String telephone;
	
	@Builder
	public Owner(Long id,String firstName, String lastName,
			Set<Pet> pets, String address, String city, String telephone) {
		super(id,firstName,lastName);
		this.pets = Optional.ofNullable(pets).orElse(this.pets);
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}
	
	

}
