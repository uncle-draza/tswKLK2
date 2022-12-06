package testovi;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import gume.AutoGuma;
import gume.VulkanizerskaRadnja;

@RunWith(Parameterized.class)
public class VRPronadjiGumuParemetrizedTest 
{
	AutoGuma ag;
	VulkanizerskaRadnja vr;
	
	@BeforeClass
	public static void proveriOS()
	{
		Assume.assumeTrue(System.getProperty("os.name").contains("Windows"));
	}
	
	@Rule
	public final TestRule timeout = Timeout.seconds(5);
	
	public VRPronadjiGumuParemetrizedTest(AutoGuma ag)
	{
		this.ag = ag;
	}
	
	@Parameters
	public static Collection<Object[]> gume()
	{
		return Arrays.asList(new Object[][] {
			{new AutoGuma("Michelin", true, 18, 180, 40)},
			{new AutoGuma("Michelin", true, 18, 185, 45)},
			{new AutoGuma("Michelin", true, 18, 190, 40)},
			{new AutoGuma("Michelin", false, 19, 170, 30)}
		});
	}
	
	@Before
	public void init()
	{
		vr = new VulkanizerskaRadnja();
	}
	
	@Test
	public void pronadjiGumuTest() {
		String marka = null;
		assertNull(vr.pronadjiGumu(marka));
	}

	@Test
	public void pronadjiGumuTest2() {
		assertFalse(vr.gume.contains(ag));
		vr.dodajGumu(ag);
		LinkedList<AutoGuma> gume = new LinkedList<AutoGuma>();
		gume.add(ag);
		assertEquals(gume, vr.pronadjiGumu("Michelin"));
	}
	
	@Test
	public void pronadjiGumuTest3() {
		assertFalse(vr.gume.contains(ag));
		vr.dodajGumu(ag);
		LinkedList<AutoGuma> gumes = new LinkedList<AutoGuma>();
		gumes.add(ag);
		assertNotEquals(gumes, vr.pronadjiGumu("Pireli - NotExistingModel"));
	}
	
	@After
	public void destroy() {
		vr = null;
	}
	
	
	
	
	
	

}
