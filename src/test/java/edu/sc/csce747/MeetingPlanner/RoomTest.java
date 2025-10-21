package edu.sc.csce747.MeetingPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room("101");
    }

    @Test
    public void testIsBusy_freeTime() throws TimeConflictException {
        assertFalse(room.isBusy(1, 1, 10, 11));
    }

    @Test
    public void testIsBusy_occupiedTime() throws TimeConflictException {
        Meeting meeting = new Meeting(1, 1, 10, 12);
        room.addMeeting(meeting);
        assertTrue(room.isBusy(1, 1, 10, 12));
    }

    @Test
    public void testAddMeeting_validMeeting() throws TimeConflictException {
        Meeting meeting = new Meeting(1, 1, 10, 12);
        room.addMeeting(meeting);
        assertTrue(room.isBusy(1, 1, 10, 12));
    }

    @Test
    public void testAddMeeting_conflictingMeeting() throws TimeConflictException {
        Meeting meeting1 = new Meeting(1, 1, 10, 12);
        meeting1.setDescription("First Meeting");
        Meeting meeting2 = new Meeting(1, 1, 11, 13);
        meeting2.setDescription("Second Meeting");
        room.addMeeting(meeting1);
        TimeConflictException exception = assertThrows(TimeConflictException.class,
                () -> room.addMeeting(meeting2));
        assertTrue(exception.getMessage().contains("Conflict for room"));
    }
}
