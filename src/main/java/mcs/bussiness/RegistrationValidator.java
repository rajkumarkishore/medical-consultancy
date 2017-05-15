package mcs.bussiness;

import java.util.HashMap;
import java.util.Map;

import mcs.vo.RegistrationVO;

import org.springframework.stereotype.Component;

@Component(value="RegistrationValidator")
public class RegistrationValidator implements FormValidator {

	private final String NUMERIC_REG_EXP = "\\d+(\\.\\d+)*";
	private final String CURRENCY_REG_EXP = "^\\d+\\.?\\d{0,2}$";
	private final String MOBILE_REG_EXP = "^\\d{10}$";

	@Override
	public Map<String, String> validate(Object vo) {

		RegistrationVO form = null;
		if (vo instanceof RegistrationVO) {
			form = (RegistrationVO) vo;
		}

		Map<String, String> errors = new HashMap<String, String>();

		if (form.getNameOfPatient() == null
				|| form.getNameOfPatient().trim().length() == 0) {
			errors.put("nameOfPatient", "cannot be empty!");
		}

		if (form.getAddressOfPatient() == null
				|| form.getAddressOfPatient().trim().length() == 0) {
			errors.put("addressOfPatient", "cannot be empty!");
		}

		if (form.getAgeOfPatientStr() == null
				|| form.getAgeOfPatientStr().trim().length() == 0) {
			errors.put("ageOfPatientStr", "cannot be empty!");
		} else {// validate numeric here --- work needed!

			if (!form.getAgeOfPatientStr().matches(NUMERIC_REG_EXP)) {
				errors.put("ageOfPatientStr", "numeric value required!");
			}
		}

		if (form.getRegnFee() == null || form.getRegnFee().length() == 0) {
			errors.put("regnFee", "cannot be empty!");
		} else {
			if (!form.getRegnFee().matches(CURRENCY_REG_EXP)) {
				errors.put("regnFee", "Invalid value!");
			}
		}

		if ((form.getMobile() != null && form.getMobile().length() > 0)
				&& !form.getMobile().matches(MOBILE_REG_EXP)) {
			errors.put("mobile", "Invalid mobile number!");
		}

		return errors;
	}
}
