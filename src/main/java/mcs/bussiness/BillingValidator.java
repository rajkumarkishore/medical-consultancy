package mcs.bussiness;

import java.util.HashMap;
import java.util.Map;

import mcs.vo.BillingVO;
import mcs.vo.ServiceVO;

import org.springframework.stereotype.Component;

@Component(value = "BillingValidator")
public class BillingValidator implements FormValidator {

	private final String CURRENCY_REG_EXP = "^\\d+\\.?\\d{0,2}$";

	@Override
	public Map<String, String> validate(Object vo) {

		BillingVO form = null;
		if (vo instanceof BillingVO) {
			form = (BillingVO) vo;
		}

		Map<String, String> errors = new HashMap<String, String>();
		for (ServiceVO ser : form.getServices()) {
			// name validation
			if (ser.getName() == null || ser.getName().trim().length() == 0) {
				errors.put("name" + ser.getIndex(), "cannot be empty!");
			}

			//fee validation
			if (ser.getServiceFee() == null
					|| ser.getServiceFee().trim().length() == 0) {
				errors.put("serviceFee" + ser.getIndex(), "cannot be empty!");
			} else {
				if (!ser.getServiceFee().matches(CURRENCY_REG_EXP)) {
					errors.put("serviceFee" + ser.getIndex(), "Invalid value!");
				}
			}
		}

		return errors;
	}
}
