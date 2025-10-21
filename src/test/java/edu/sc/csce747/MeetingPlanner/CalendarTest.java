package edu.sc.csce747.MeetingPlanner;

//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//import org.junit.Test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalendarTest {
	// Add test methods here. 
	// You are not required to write tests for all classes.

    @Test
    public void testCheckTimes_validDayLowerBound() {
        assertDoesNotThrow(() -> Calendar.checkTimes(1, 1, 0, 1));
    }

    @Test
    public void testCheckTimes_validDayUpperBound() {
        assertDoesNotThrow(() -> Calendar.checkTimes(1, 31, 0, 1));
    }

    @Test
    public void testCheckTimes_invalidDayLowerBound() {
        assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(1, 0, 0, 1));
    }

    @Test
    public void testCheckTimes_invalidDayUpperBound() {
        assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(1, 32, 0, 1));
    }
	
//	@Test
	public void testAddMeeting_holiday() {
		// Create Midsommar holiday
//		Calendar calendar = new Calendar();
//		// Add to calendar object.
//		try {
//			Meeting midsommar = new Meeting(6, 26, "Midsommar");
//			calendar.addMeeting(midsommar);
//			// Verify that it was added.
//			Boolean added = calendar.isBusy(6, 26, 0, 23);
//			assertTrue("Midsommar should be marked as busy on the calendar",added);
//		} catch(TimeConflictException e) {
//			fail("Should not throw exception: " + e.getMessage());
//		}
	}
}
