package testovi;

import java.util.logging.Logger;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class TestRunner 
{
	public static void main(String[] args)
	{
		Result result = JUnitCore.runClasses(AutoGumaTests.class, VRPronadjiGumuParemetrizedTest.class);
		Logger l = Logger.getLogger(TestRunner.class.toString());
		
		
		//opis svih gresaka
		for(Failure f: result.getFailures())
		{
			l.warning(f.toString());
		}
		
		//vreme i broj izvrsenih testova
		l.info("Vreme izvrsavanja: " + result.getRunTime());
		l.info("Broj testova: " + result.getRunCount());
		
		//koliko testovaje uspesnom neuspesno, preskoceno, dinamicki preskoceno
		l.info("Uspesno testova: " + (result.getRunCount()-result.getFailureCount()-result.getIgnoreCount()-result.getAssumptionFailureCount()));
		l.info("Broj palih testova: " + result.getFailureCount());
		l.info("Broj preskocenih: " + result.getIgnoreCount());
		l.info("Broj dinamicki preskocenih: " + result.getAssumptionFailureCount());
		
		
		//da li su svi testovi uspesni izvrseni ili ne
		if(result.wasSuccessful())
			l.info("Svi testovi su uspesno izvrseni!");
		else
			l.warning("Postoje greske u testovima!");
		
	}
}
