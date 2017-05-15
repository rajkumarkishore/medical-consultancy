package mcs.util;

import java.util.Calendar;
import java.util.Date;

import mcs.model.ItemType;

public class Utility {

	public static String buildReferenceNumber(Long billId) {
		int numLength = 10;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < (numLength - String.valueOf(billId).length()); i++) {
			sb.append("0");
		}
		sb.append(billId);
		return sb.toString();
	}

	public static Date calculateDate(Date from, Integer days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(from);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Boolean isRegistrationValid(Date from, Integer days) {
		Date currDate = new Date();
		Date validityDate = calculateDate(from, days);
		return currDate.after(validityDate) ? false : true;
	}

	public static void main(String[] args) {
/*		System.out.println(buildReferenceNumber((long) 530030980));
		try {
			Date d = new SimpleDateFormat().parse("2017-04-27 08:34:09.0");
			System.out.println(calculateDate(d, 360));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
*/		
				System.out.println(ItemType.NEW_REGISTRATION);
				System.out.println(ItemType.NEW_REGISTRATION.toString());
				System.out.println(String.valueOf(ItemType.NEW_REGISTRATION.toString()));

	}
}
