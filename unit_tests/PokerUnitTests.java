package unit_tests;

import models.*;
import unit_tests.models.*;
import unit_tests.views.*;

class PokerUnitTests {
	public static void main (String[] args) {
		// Model tests
		CardTests.run();
		PlayerTests.run();
		HandTests.run();

		// View tests
		HandViewerTests.run();

		UnitTests.report();
	}
}
