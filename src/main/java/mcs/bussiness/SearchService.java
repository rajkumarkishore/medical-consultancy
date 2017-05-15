package mcs.bussiness;

import java.util.List;

import mcs.model.Appointment;
import mcs.vo.SearchVO;

public interface SearchService {

	public List<Appointment> search(SearchVO vo);
	
	

}
