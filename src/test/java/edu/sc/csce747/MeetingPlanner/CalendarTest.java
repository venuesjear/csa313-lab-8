package edu.sc.csce747.MeetingPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarTest {

    private Calendar calendar;

    @BeforeEach
    public void setUp() {
        calendar = new Calendar();
    }

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
        TimeConflictException exception = assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(1, 0, 0, 1));
        assertEquals("Day does not exist.", exception.getMessage());
    }

    @Test
    public void testCheckTimes_invalidDayUpperBound() {
        TimeConflictException exception = assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(1, 32, 0, 1));
        assertEquals("Day does not exist.", exception.getMessage());
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
        TimeConflictException exception = assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(0, 1, 0, 1));
        assertEquals("Month does not exist.", exception.getMessage());
    }

    @Test
    public void testCheckTimes_invalidMonthUpperBound() {
        TimeConflictException exception = assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(13, 1, 0, 1));
        assertEquals("Month does not exist.", exception.getMessage());
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
        TimeConflictException exception = assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(1, 1, -1, 0));
        assertEquals("Illegal hour.", exception.getMessage());
    }

    @Test
    public void testCheckTimes_invalidStartEndLowerBound() {
        TimeConflictException exception = assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(1, 1, 0, 0));
        assertEquals("Meeting starts before it ends.", exception.getMessage());
    }

    @Test
    public void testCheckTimes_invalidStartEndUpperBound() {
        TimeConflictException exception = assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(1, 1, 23, 23));
        assertEquals("Meeting starts before it ends.", exception.getMessage());
    }

    @Test
    public void testCheckTimes_invalidEndUpperBound() {
        TimeConflictException exception = assertThrows(TimeConflictException.class, () -> Calendar.checkTimes(1, 1, 23, 24));
        assertEquals("Illegal hour.", exception.getMessage());
    }

    @Test
    public void testCheckTimes_startLessThanEnd() {
        assertDoesNotThrow(() -> Calendar.checkTimes(1, 1, 10, 11));
    }

    @Test
    public void testCheckTimes_startGreaterThanOrEqualEnd() {
        TimeConflictException exception = assertThrows(TimeConflictException.class,
                () -> Calendar.checkTimes(1, 1, 12, 12));
        assertEquals("Meeting starts before it ends.", exception.getMessage());
    }

    @Test
    public void testCalendar_dayDoesNotExist() {
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
    public void testCalendar_validDayExists() {
        assertNotEquals("Day does not exist", calendar.getMeeting(11, 30, 0).getDescription());
    }

    @Test
    public void testIsBusy_returnsTrueForFull() throws TimeConflictException {
        assertTrue(calendar.isBusy(2,29,0,23));
    }

    @Test
    public void testIsBusy_freeDayReturnsFalse() throws TimeConflictException {
        assertFalse(calendar.isBusy(1, 1, 1, 2));
    }

    @Test
    public void testIsBusy_endWithinMeetingRange() throws TimeConflictException {
        Meeting meeting = new Meeting(1, 1, 10, 12);
        calendar.addMeeting(meeting);
        assertTrue(calendar.isBusy(1, 1, 9, 11));
    }

    @Test
    public void testAddMeeting_validMeeting() throws TimeConflictException {
        Meeting meeting = new Meeting(1, 1, 1, 2);
        calendar.addMeeting(meeting);
        assertTrue(calendar.isBusy(1, 1, 1, 2));
    }

    @Test
    public void testAddMeeting_conflictingMeeting() throws TimeConflictException {
        Meeting meeting1 = new Meeting(1, 1, 1, 3);
        meeting1.setDescription("First Meeting");
        Meeting meeting2 = new Meeting(1, 1, 2, 4);
        meeting2.setDescription("Second Meeting");
        calendar.addMeeting(meeting1);
        TimeConflictException exception = assertThrows(TimeConflictException.class,
                () -> calendar.addMeeting(meeting2));
        assertTrue(exception.getMessage().contains("Overlap with another item"));
    }

    @Test
    public void testClearSchedule() throws TimeConflictException {
        Meeting meeting = new Meeting(1, 11, 10, 12);
        calendar.addMeeting(meeting);
        assertTrue(calendar.isBusy(1, 11, 10, 12));
        calendar.clearSchedule(1, 11);
        assertFalse(calendar.isBusy(1, 11, 10, 12));
    }

    @Test
    public void testPrintAgenda() throws TimeConflictException {
        Meeting meeting = new Meeting(1, 1, 10, 12, new ArrayList<>(), new Room(), "Test");
        calendar.addMeeting(meeting);
        String agenda = calendar.printAgenda(1, 1);
        assertTrue(agenda.contains("Test"));
        assertTrue(agenda.contains("1/1"));
    }

    @Test
    public void testRemoveMeeting() throws TimeConflictException {
        Meeting meeting1 = new Meeting(1, 2, 9, 10, new ArrayList<>(), new Room(), "Morning Meeting");
        Meeting meeting2 = new Meeting(1, 2, 11, 12, new ArrayList<>(), new Room(), "Noon Meeting");

        calendar.addMeeting(meeting1);
        calendar.addMeeting(meeting2);

        assertEquals(4, calendar.printAgenda(1, 2).split("\n").length - 1);

        calendar.removeMeeting(1, 2, 0);

        String agenda = calendar.printAgenda(1, 2);
        assertFalse(agenda.contains("Morning Meeting"));
        assertTrue(agenda.contains("Noon Meeting"));
    }

    @Test
    public void testPrintAgenda_MonthLevel() throws TimeConflictException {
        Meeting meeting1 = new Meeting(5, 5, 9, 10, new ArrayList<>(), new Room(), "Team Sync");
        Meeting meeting2 = new Meeting(5, 6, 14, 15, new ArrayList<>(), new Room(), "Client Call");

        calendar.addMeeting(meeting1);
        calendar.addMeeting(meeting2);

        String agenda = calendar.printAgenda(5);

        assertTrue(agenda.contains("Agenda for 5:"));
        assertTrue(agenda.contains("Team Sync"));
        assertTrue(agenda.contains("Client Call"));
    }

    @Test
    public void testPrintAgenda_MonthLevelEmpty() {
        String agenda = calendar.printAgenda(7);

        assertTrue(agenda.startsWith("Agenda for 7:"));
        assertFalse(agenda.contains("Meeting"));
    }

    @Test
    public void testAddMeeting_conflictByEndTime() throws TimeConflictException {
        Meeting m1 = new Meeting(4, 4, 9, 11);
        m1.setDescription("Morning");
        calendar.addMeeting(m1);

        Meeting m2 = new Meeting(4, 4, 8, 10);
        m2.setDescription("Overlap by End");
        TimeConflictException ex = assertThrows(TimeConflictException.class, () -> calendar.addMeeting(m2));
        assertTrue(ex.getMessage().contains("Overlap with another item"));
    }
}
