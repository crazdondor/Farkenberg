package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RunAllTests {

	public static void main(String[] args) {
		testClass(Test_Screens.class);
		testClass(Test_Settings.class);
		testClass(Test_Scoring.class);
		testClass(Test_Misc.class);
	}
	
	public static void testClass(Class<?> cls) {
		Result result = JUnitCore.runClasses(cls);
		
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.getDescription() + ": " + failure.getException());
			System.out.println(failure.getTrace());
		}

		System.out.println((result.wasSuccessful() ? "Success" : "Failure") + " for [" + cls.getName() + "]");
	}
}
