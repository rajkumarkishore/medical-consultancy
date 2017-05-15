package mcs.bussiness;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import mcs.model.Appointment;
import mcs.vo.TransactionsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AppointmentService service;

	@Override
	public List<Appointment> findTransactions(TransactionsVO vo) {
		List<Appointment> appointments = new ArrayList<Appointment>();
		try {
			if (vo.getDoctor() != null && vo.getDoctor().trim().length() > 0) {
				appointments = service.listAllAppointmentsByDateRangeAndDoctor(
						vo.getFromDate(), vo.getToDate(), vo.getDoctor());
			} else {
				appointments = service.listAllAppointmentsByDateRange(
						vo.getFromDate(), vo.getToDate());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return appointments;
	}

}
