package oms.authetication;

public class RegularExpressionTest {
	public static void main(String[] args) {
		String s = "^\\d{10}$";
		System.out.println("1230000000".matches(s));
		System.out.println("123.0".matches(s));
		System.out.println("123".matches(s));
		System.out.println("123.008".matches(s));
	}
}
