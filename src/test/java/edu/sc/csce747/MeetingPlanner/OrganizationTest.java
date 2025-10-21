package edu.sc.csce747.MeetingPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizationTest {
    private Organization organization;

    @BeforeEach
    public void setUp() {
        organization = new Organization();
    }

    @Test
    public void testGetRoom_existingRoom() throws Exception {
        Room room = organization.getRoom("2A01");
        assertNotNull(room);
        assertEquals("2A01", room.getID());
    }

    @Test
    public void testGetRoom_nonExistingRoom() {
        Exception exception = assertThrows(Exception.class,
                () -> organization.getRoom("NonExistentRoom"));
        assertEquals("Requested room does not exist", exception.getMessage());
    }

}
