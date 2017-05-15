package mcs.bussiness;

import java.util.HashMap;
import java.util.Map;

import mcs.vo.AppointmentVO;

import org.springframework.stereotype.Component;

@Component(value = "AppointmentValidator")
public class AppointmentValidator implements FormValidator {

	private final String CURRENCY_REG_EXP = "^\\d+\\.?\\d{0,2}$";

	@Override
	public Map<String, String> validate(Object vo) {

		AppointmentVO form = null;
		if (vo instanceof AppointmentVO) {
			form = (AppointmentVO) vo;
		}

		Map<String, String> errors = new HashMap<String, String>();

		if (form.getDateOfAppointment() == null
				|| form.getDateOfAppointment().trim().length() == 0) {
			errors.put("dateOfAppointment", "cannot be empty!");
		}

		if (form.getReferredBy() == null
				|| form.getReferredBy().trim().length() == 0) {
			errors.put("referredBy", "cannot be empty!");
		}

		if (form.getNameOfDoctor() == null
				|| form.getNameOfDoctor().trim().length() == 0) {
			errors.put("nameOfDoctor", "cannot be empty!");
		}

		if (!Boolean.parseBoolean(form.getValidRegistration())) {
			if (form.getRegnRenewalFee() == null
					|| form.getRegnRenewalFee().length() == 0) {
				errors.put("regnRenewalFee", "cannot be empty!");
			} else {
				if (!form.getRegnRenewalFee().matches(CURRENCY_REG_EXP)) {
					errors.put("regnRenewalFee", "Invalid value!");
				}
			}
		}

		return errors;
	}
}
