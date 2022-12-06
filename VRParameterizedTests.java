package testovi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({VRDodajGumuParametrizedTest.class,
	VRPronadjiGumuParemetrizedTest.class})
public class VRParameterizedTests {

}
