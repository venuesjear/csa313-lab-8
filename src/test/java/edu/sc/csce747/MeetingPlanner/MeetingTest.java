package edu.sc.csce747.MeetingPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MeetingTest {
    private Meeting defaultMeeting;
    private Meeting dayMeeting;
    private Meeting dayDescMeeting;
    private Meeting detailedMeeting;
    private Meeting fullMeeting;

    private Person person;
    private Room room;

    @BeforeEach
    public void setUp() {
        defaultMeeting = new Meeting();
        dayMeeting = new Meeting(1, 1);
        dayDescMeeting = new Meeting(2, 2, "Vacation");
        detailedMeeting = new Meeting(3, 3, 10, 12);
        person = new Person("Alice");
        room = new Room("101");
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(person);
        fullMeeting = new Meeting(4, 4, 9, 11, attendees, room, "Team Meeting");
    }

    @Test
    public void testConstructors() {
        assertNotNull(defaultMeeting);
        assertEquals(1, dayMeeting.getMonth());
        assertEquals(1, dayMeeting.getDay());
        assertEquals(0, dayMeeting.getStartTime());
        assertEquals(23, dayMeeting.getEndTime());

        assertEquals(2, dayDescMeeting.getMonth());
        assertEquals("Vacation", dayDescMeeting.getDescription());

        assertEquals(3, detailedMeeting.getMonth());
        assertEquals(10, detailedMeeting.getStartTime());
        assertEquals(12, detailedMeeting.getEndTime());

        assertEquals("Team Meeting", fullMeeting.getDescription());
        assertEquals("Alice", fullMeeting.getAttendees().get(0).getName());
        assertEquals("101", fullMeeting.getRoom().getID());
    }

    @Test
    public void testAddRemoveAttendee() {
        Person bob = new Person("Bob");
        fullMeeting.addAttendee(bob);
        assertTrue(fullMeeting.getAttendees().contains(bob));

        fullMeeting.removeAttendee(bob);
        assertFalse(fullMeeting.getAttendees().contains(bob));
    }

    @Test
    public void testGettersSetters() {
        fullMeeting.setMonth(5);
        fullMeeting.setDay(5);
        fullMeeting.setStartTime(14);
        fullMeeting.setEndTime(15);
        fullMeeting.setDescription("New Meeting");
        Room newRoom = new Room("102");
        fullMeeting.setRoom(newRoom);

        assertEquals(5, fullMeeting.getMonth());
        assertEquals(5, fullMeeting.getDay());
        assertEquals(14, fullMeeting.getStartTime());
        assertEquals(15, fullMeeting.getEndTime());
        assertEquals("New Meeting", fullMeeting.getDescription());
        assertEquals("102", fullMeeting.getRoom().getID());
    }
}
