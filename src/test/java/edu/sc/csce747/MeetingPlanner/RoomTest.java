package edu.sc.csce747.MeetingPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    public void testPrintAgenda_specificDay() throws TimeConflictException {
        Meeting meeting = new Meeting(3, 15, 9, 10, new ArrayList<>(), new Room(), "Morning Brief");
        room.addMeeting(meeting);

        String agenda = room.printAgenda(3, 15);
        assertTrue(agenda.contains("Agenda for 3/15:"));
        assertTrue(agenda.contains("Morning Brief"));
    }

    @Test
    public void testPrintAgenda_month() throws TimeConflictException {
        Meeting meeting1 = new Meeting(3, 15, 9, 10, new ArrayList<>(), new Room(), "Morning Brief");
        Meeting meeting2 = new Meeting(3, 20, 14, 15, new ArrayList<>(), new Room(), "Afternoon Call");
        room.addMeeting(meeting1);
        room.addMeeting(meeting2);

        String agenda = room.printAgenda(3);
        assertTrue(agenda.contains("Agenda for 3:"));
        assertTrue(agenda.contains("Morning Brief"));
        assertTrue(agenda.contains("Afternoon Call"));
    }

    @Test
    public void testRemoveMeeting() throws TimeConflictException {
        Meeting meeting1 = new Meeting(5, 5, 10, 11, new ArrayList<>(), new Room(), "Team Sync");
        Meeting meeting2 = new Meeting(5, 5, 12, 13, new ArrayList<>(), new Room(), "Lunch Review");

        room.addMeeting(meeting1);
        room.addMeeting(meeting2);

        String before = room.printAgenda(5, 5);
        assertTrue(before.contains("Team Sync"));
        assertTrue(before.contains("Lunch Review"));

        room.removeMeeting(5, 5, 0);

        String after = room.printAgenda(5, 5);
        assertFalse(after.contains("Team Sync"));
        assertTrue(after.contains("Lunch Review"));
    }

    @Test
    public void testGetMeeting() throws TimeConflictException {
        Meeting meeting = new Meeting(4, 10, 11, 12, new ArrayList<>(), new Room(), "Planning Session");
        room.addMeeting(meeting);

        Meeting fetched = room.getMeeting(4, 10, 0);
        assertEquals("Planning Session", fetched.getDescription());
        assertEquals(11, fetched.getStartTime());
        assertEquals(12, fetched.getEndTime());
    }
}
