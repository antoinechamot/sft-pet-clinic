package ancm.springframework.sftpetclinic.formatters;

import java.text.ParseException;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import ancm.springframework.sftpetclinic.model.PetType;
import ancm.springframework.sftpetclinic.services.PetTypeService;

@Component
public class PetTypeFormatter implements Formatter<PetType>{
	
	private final PetTypeService petTypeService;
	
	public PetTypeFormatter(PetTypeService petTypeService) {
		super();
		this.petTypeService = petTypeService;
	}

	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}
	
	public PetType parse(String text, Locale locale) throws ParseException{
		Set<PetType> findPetTypes = petTypeService.findAll();
		for(PetType type : findPetTypes) {
			if(type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text,0);
	}

}
