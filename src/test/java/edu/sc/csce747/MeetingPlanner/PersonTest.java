package edu.sc.csce747.MeetingPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person person;

    @BeforeEach
    public void setUp() {
        person = new Person("Sugar");
    }
    @Test
    public void testIsBusy_freeTime() throws TimeConflictException {
        assertFalse(person.isBusy(1, 1, 10, 11));
    }

    @Test
    public void testIsBusy_occupiedTime() throws TimeConflictException {
        Meeting meeting = new Meeting(1, 1, 10, 12);
        person.addMeeting(meeting);
        assertTrue(person.isBusy(1, 1, 10, 12));
    }

    @Test
    public void testGetName_valid() {
        assertEquals("Sugar", person.getName());
    }

    @Test
    public void testAddMeeting_validMeeting() throws TimeConflictException {
        Meeting meeting = new Meeting(1, 1, 10, 12);
        person.addMeeting(meeting);
        assertTrue(person.isBusy(1, 1, 10, 12));
    }

    @Test
    public void testAddMeeting_conflictingMeeting() throws TimeConflictException {
        Meeting meeting1 = new Meeting(1, 1, 10, 12);
        meeting1.setDescription("First Meeting");
        Meeting meeting2 = new Meeting(1, 1, 11, 13);
        meeting2.setDescription("Second Meeting");
        person.addMeeting(meeting1);
        TimeConflictException exception = assertThrows(TimeConflictException.class,
                () -> person.addMeeting(meeting2));
        assertTrue(exception.getMessage().contains("Conflict for attendee Sugar"));
    }

    @Test
    public void testGetMeeting() throws TimeConflictException {
        Meeting meeting = new Meeting(1, 1, 10, 12);
        person.addMeeting(meeting);
        Meeting retrieved = person.getMeeting(1, 1, 0);
        assertNotNull(retrieved);
        assertEquals(1, retrieved.getMonth());
        assertEquals(1, retrieved.getDay());
    }

}
