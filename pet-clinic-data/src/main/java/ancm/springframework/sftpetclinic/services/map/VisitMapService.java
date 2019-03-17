package ancm.springframework.sftpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ancm.springframework.sftpetclinic.model.Visit;
import ancm.springframework.sftpetclinic.services.VisitService;

@Service
@Profile({"default","map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService{

	@Override
	public Visit save(Visit visit) {
		
		if(visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null ||
				visit.getPet().getOwner().getId() == null	) {
			throw new RuntimeException("Invalid Visit");
		}
		return super.save(visit);
	}
	
	

}
