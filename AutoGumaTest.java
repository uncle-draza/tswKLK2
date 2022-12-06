package testovi;

import static org.junit.Assert.*;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import gume.AutoGuma;
import gume.VulkanizerskaRadnja;

public class AutoGumaTest {

	public AutoGuma ag;
	
	//vreme izvrsavanja po testu ne sme biti duze od 5 sekundi
	@Rule 
	public final TestRule timeout = Timeout.seconds(5);
	
	//testovi se pokrecu samo na windows operativnom sistemu
	@BeforeClass
	public static void ProveriOS()
	{
		Assume.assumeTrue(System.getProperty("os.name").contains("Windows"));
	}
	
	
	//vrsi inicijalizaciju pre svakog testa
	@Before
	public void init()
	{
		ag = new AutoGuma("Michelin", true, 18, 180, 40);
	}
	
	//definisemo error collector
	@Rule
	public final ErrorCollector ec = new ErrorCollector();
	
	@Test(expected = RuntimeException.class) //ocekujemo izuzetak
	public void setPrecnikTest()
	{
		//ovde regularno testiramo precnik
		int ocekovano = 18;
		int stvarno = ag.getPrecnik();
		assertEquals(ocekovano, stvarno);
		
		ag.setPrecnik(12); //izazivamo izuzetak 1
	}
	
	@Test(expected = RuntimeException.class) //ocekujemo izuzetak
	public void setPrecnikTest2()
	{
		int ocekovano = 18;
		int stvarno = ag.getPrecnik();
		assertEquals(ocekovano, stvarno);
		ag.setPrecnik(23); //izazivamo izuzetak 2
	}
	
	@Test
	public void setPrecnikTest3()
	{ 
		int ocekivano = 18;
		int stvarno = ag.getPrecnik();
		
		try {
			assertEquals(ocekivano, stvarno);
		} catch (Exception e) {
			ec.addError(e);
		}
		
		ag.setPrecnik(15);
		ocekivano = 15;
		stvarno = ag.getPrecnik();
		
		try {
			assertEquals(ocekivano, stvarno);
		} catch (Exception e) {
			ec.addError(e);
		}
	}
	
	
	//primeniti junit pravilo za izuzetke
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void setSirinaTest()
	{
		exception.expect(RuntimeException.class);
		exception.expectMessage("Sirina van opsega");
		int ocekivano = 180;
		int stvarno = ag.getSirina();
		assertEquals(ocekivano, stvarno);
		
		ag.setSirina(134); //izazivamo izuzetak 1
	}
	
	@Test
	public void setSirinaTest2()
	{
		exception.expect(RuntimeException.class);
		exception.expectMessage("Sirina van opsega");
		int ocekivano = 180;
		int stvarno = ag.getSirina();
		assertEquals(ocekivano, stvarno);
		
		ag.setSirina(356); //izazivamo izuzetak 2
	}
	
	@Test
	public void setSirinaTest3()
	{
		int ocekivano = 180;
		int stvarno = ag.getSirina();
		assertEquals(ocekivano, stvarno);
		
		ag.setSirina(300); 
		ocekivano = 300;
		stvarno = ag.getSirina();
		assertEquals(ocekivano, stvarno);
	}
	
	
	//sledeca dva testa se dinamicki preskacu ako dodje do izuzetka
	@Test
	public void setVisinaTest()
	{
		int ocekivano = 40;
		int stvarno = ag.getVisina();
		assertEquals(ocekivano, stvarno);
		
		try {
			ag.setVisina(24);
		} catch (Exception e) {
			Assume.assumeNoException(e); //ako dodje do izuzetka, pravimo se da ga nema
		}
	}
	
	@Test
	public void setVisinaTest2()
	{
		int ocekivano = 40;
		int stvarno = ag.getVisina();
		assertEquals(ocekivano, stvarno);
		
		try {
			ag.setVisina(100);
		} catch (Exception e) {
			Assume.assumeNoException(e); //ako dodje do izuzetka, pravimo se da ga nema
		}
	}
	
	
	@Test
	public void setVisinaTest3()
	{
		int ocekivano = 40;
		int stvarno = ag.getVisina();
		assertEquals(ocekivano, stvarno);
		
		ag.setVisina(64);
		ocekivano = 64;
		stvarno = ag.getVisina();
		assertEquals(ocekivano, stvarno);
	}
	
	//ostale metode-----------------------------------------------
	
	@Rule
	public final TestName name = new TestName();
	
	@Test
	public void getZimskaTest()
	{
		//nzm stvarno zasto ovo ide ovako ali ovako je u pripremi
		//sad ovde iz nekog razloga proveravamo da li se metoda(test) ispravno zove
		//mislim da je ovo nepotrebno
		try {
			assertEquals("getZimskaTest", name.getMethodName());
		} catch (Exception e) {
			ec.addError(e);
		}
		
		//sad tek proveravamo ocekivani rezultat
		boolean ocekivano = true;
		try {
			boolean stvarno = ag.getZimska();
			assertEquals(ocekivano, stvarno);
		} catch (Exception e) {
			ec.addError(e);
		}
	}
	
	@Test
	public void setZimskaTest()
	{
		//ovde necu da koristim ono za proveru imena
		ag.setZimska(false);
		boolean ocekivano = false;
		boolean stvarno = ag.getZimska();
		assertEquals(ocekivano, stvarno);
	}
	
	@Test
	public void getMarkaModelaTest()
	{
		String ocekivano = "Michelin";
		String stvarno = ag.getMarkaModel();
		assertEquals(ocekivano, stvarno);
	}
	
	@Test(expected = RuntimeException.class)
	public void setMarkaModelTest()
	{
		String ocekivano = "Michelin";
		String stvarno = ag.getMarkaModel();
		assertEquals(ocekivano, stvarno);
		
		//izazivamo prvi izuzetak
		ag.setMarkaModel(null);
	}
	
	@Test(expected = RuntimeException.class)
	public void setMarkaModelTest2()
	{
		String ocekivano = "Michelin";
		String stvarno = ag.getMarkaModel();
		assertEquals(ocekivano, stvarno);
		
		//izazivamo drugi izuzetak
		ag.setMarkaModel("gm");
	}
	
	@Test
	public void setMarkaModelTest3()
	{
		String ocekivano = "Michelin";
		String stvarno = ag.getMarkaModel();
		assertEquals(ocekivano, stvarno);
		
		ag.setMarkaModel("Tigar");
		ocekivano = "Tigar";
		stvarno = ag.getMarkaModel();
		assertEquals(ocekivano, stvarno);
	}
	
	@Test
	public void izracunajCenuTest()
	{
		double ocekivano = (ag.getPrecnik()*3+ag.getSirina()+ag.getVisina())*28.53;
		double stvarno = ag.izracunajCenu();
		assertEquals(ocekivano, stvarno, 0.001); //ovde moramo da dodamo toleranciju zbog monozennja realnih brojeva
	}
	
	@Test
	public void povoljnaGumaTest()
	{
		assertFalse(ag.povoljnaGuma());
	}
	
	@Test
	public void povoljnaGumaTest2()
	{
		//ovde postavljamo parametre tako da ispadne da je povoljna guma
		ag.setSirina(135);
		ag.setPrecnik(13);
		ag.setVisina(25);
		
		assertTrue(ag.povoljnaGuma());
	}
	
	@Test
	public void toStringTest()
	{
		String ocekivano = "AutoGuma [markaModel=" + ag.getMarkaModel() + ", precnik=" + ag.getPrecnik()
		+ ", sirina=" + ag.getSirina() + ", visina=" + ag.getVisina() + "]";
		String stvarno = ag.toString();
		assertEquals(ocekivano, stvarno);
	}
	
	@Test 
	public void equalsTest1()
	{
		AutoGuma ag2 = new AutoGuma("Michelin", true, 18, 180, 40);
		assertTrue(ag.equals(ag2));
	}
	
	@Test 
	public void equalsTest2()
	{
		VulkanizerskaRadnja vk = new VulkanizerskaRadnja();
		assertEquals(false, ag.equals(vk));
	}
	
	@Test 
	public void equalsTest3()
	{
		AutoGuma ag2 = ag;
		assertTrue(ag.equals(ag2));
	}
	
	@Test 
	public void equalsTest4()
	{
		AutoGuma ag2 = new AutoGuma(null, true, 18, 180, 40);
		AutoGuma ag3 = new AutoGuma(null, true, 18, 180, 40);
		assertTrue(ag2.equals(ag3));
	}
	
	@Test 
	public void equalsTest5()
	{
		AutoGuma ag2 = new AutoGuma(null, true, 18, 180, 40);
		assertFalse(ag.equals(ag2));
	}
	
	@Test
	public void equalsTest6()
	{
		AutoGuma ag2 = new AutoGuma(null, true, 18, 180, 40);
		AutoGuma ag3 = new AutoGuma("Michelin", true, 18, 180, 40);
		assertFalse(ag2.equals(ag3));
	}
	
	@Test
	public void equalsTest7()
	{
		AutoGuma ag2 = new AutoGuma("Michelin", true, 19, 180, 40);
		assertFalse(ag.equals(ag2));
	}
	
	@Test
	public void equalsTest8()
	{
		AutoGuma ag2 = new AutoGuma("Michelin", true, 18, 185, 40);
		assertFalse(ag.equals(ag2));
	}
	
	@Test
	public void equalsTest9()
	{
		AutoGuma ag2 = new AutoGuma("Michelin", true, 18, 180, 35);
		assertFalse(ag.equals(ag2));
	}
}































