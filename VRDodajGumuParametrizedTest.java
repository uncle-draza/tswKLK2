package testovi;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

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
public class VRDodajGumuParametrizedTest 
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
	
	public VRDodajGumuParametrizedTest(AutoGuma ag)
	{
		this.ag = ag;
	}
	
	@Parameters
	public static Collection<Object[]> gume(){
		return Arrays.asList(new Object[][] {
			{new AutoGuma("Michelin", true, 18, 180, 40)},
			{new AutoGuma("Michelin", true, 18, 180, 40)},
			{new AutoGuma("Michelin", true, 18, 180, 40)},
			{new AutoGuma("Michelin", true, 18, 180, 40)}
		});
	}
	
	@Before
	public void init()
	{
		vr = new VulkanizerskaRadnja();
	}
	
	@Test(expected = NullPointerException.class)
	public void dodajGumuTest() 
	{
		ag = null;
		vr.dodajGumu(ag);
	}
	
	@Test(expected = RuntimeException.class)
	public void dodajGumuTest2()
	{
		vr.dodajGumu(ag);
		vr.dodajGumu(ag);
	}
	
	@Test
	public void dodajGumuTest3()
	{
		assertFalse(vr.gume.contains(ag));
		vr.dodajGumu(ag);
		assertTrue(vr.gume.contains(ag));
	}
	
	@After
	public void destroy()
	{
		vr = null;
	}
}
