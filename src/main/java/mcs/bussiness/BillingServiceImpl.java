package mcs.bussiness;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import mcs.model.Appointment;
import mcs.model.Bill;
import mcs.model.Fee;
import mcs.model.ServiceItem;
import mcs.vo.BillingVO;
import mcs.vo.ServiceVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	private AppointmentRepository repository;

	@Override
	public void saveBill(BillingVO vo) {

		Appointment appointment = repository.findOne(Long.parseLong(vo
				.getAppointmentId()));

		appointment.setIsBilled(true);

		Set<ServiceItem> services = new HashSet<ServiceItem>();
		for (ServiceVO svo : vo.getServices()) {

			ServiceItem s = new ServiceItem();
			s.setName(svo.getName());
			s.setType(svo.getType());

			Fee fee = new Fee();
			fee.setPaid(Double.parseDouble(svo.getServiceFee()));
			fee.setTotal(Double.parseDouble(svo.getServiceFee()));
			fee.setServiceItem(s);

			s.setFee(fee);

			services.add(s);
		}

		if (appointment.getBill() == null) {
			Bill bill = new Bill();
			bill.setAppointment(appointment);
			bill.setGeneratedDate(new Date());
			appointment.setBill(bill);
		}

		appointment.getBill().setItems(services);

		repository.save(appointment);

	}

}
