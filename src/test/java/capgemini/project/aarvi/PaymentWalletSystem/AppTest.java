package capgemini.project.aarvi.PaymentWalletSystem;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.paymentwallet.service.WalletServiceImpl;

public class AppTest 

{

	private static WalletServiceImpl impl;
	
	@BeforeClass
	public static void initTest() {
		impl=new WalletServiceImpl();
	}
	@Test
	public void testLoginAdmin() {
		String str=impl.loginAdmin(101, "password");
		assertEquals("Logged In",str);
	}
}
