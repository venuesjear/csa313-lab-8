package edu.sc.csce747.MeetingPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
