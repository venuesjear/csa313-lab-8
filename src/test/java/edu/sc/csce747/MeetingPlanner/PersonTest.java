package edu.sc.csce747.MeetingPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
