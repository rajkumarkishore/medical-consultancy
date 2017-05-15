package oms.authetication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptionTest {
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//admin
		String encrypt1 = bCryptPasswordEncoder().encode("admin1010");
		//superuser
		String encrypt2 = bCryptPasswordEncoder().encode("super1010");
		
		System.out.println(encrypt1);
		System.out.println(encrypt2);
		
	}

}
