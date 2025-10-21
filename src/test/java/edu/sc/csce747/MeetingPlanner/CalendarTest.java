package edu.sc.csce747.MeetingPlanner;

//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//import org.junit.Test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testCheckTimes_validMonthLowerBound() {
        assertDoesNotThrow(() -> Calendar.checkTimes(1, 1, 0, 1));
    }

    @Test
    public void testCheckTimes_validMonthUpperBound() {
        assertDoesNotThrow(() -> Calendar.checkTimes(12, 1, 0, 1));
    }

    @Test
    public void testCheckTimes_invalidMonthLowerBound() {
        assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(0, 1, 0, 1));
    }

    @Test
    public void testCheckTimes_invalidMonthUpperBound() {
        assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(13, 1, 0, 1));
    }

    @Test
    public void testCheckTimes_validStartLowerBound() {
        assertDoesNotThrow(() -> Calendar.checkTimes(1, 1, 0, 1));
    }

    @Test
    public void testCheckTimes_validStartUpperBound() {
        assertDoesNotThrow(() -> Calendar.checkTimes(1, 1, 22, 23));
    }

    @Test
    public void testCheckTimes_invalidStartLowerBound() {
        assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(1, 1, -1, 0));
    }

    @Test
    public void testCheckTimes_invalidStartUpperBound() {
        assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(1, 1, 23, 1));
    }

    @Test
    public void testCheckTimes_invalidEndUpperBound() {
        assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(1, 1, 23, 24));
    }

    @Test
    public void testCalendar_dayDoesNotExist() {
        Calendar calendar = new Calendar();

        String[] occupied = new String[]{
                calendar.getMeeting(2, 29, 0).getDescription(),
                calendar.getMeeting(2, 30, 0).getDescription(),
                calendar.getMeeting(2, 31, 0).getDescription(),
                calendar.getMeeting(4, 31, 0).getDescription(),
                calendar.getMeeting(6, 31, 0).getDescription(),
                calendar.getMeeting(9, 31, 0).getDescription(),
                calendar.getMeeting(11, 31, 0).getDescription()
        };

        for (String desc : occupied) {
            assertEquals("Day does not exist", desc);
        }
    }

    @Test
    public void testCalendar_dayExists() {
        Calendar calendar = new Calendar();

        assertNotEquals("Day does not exist", calendar.getMeeting(11, 30, 0).getDescription());
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
