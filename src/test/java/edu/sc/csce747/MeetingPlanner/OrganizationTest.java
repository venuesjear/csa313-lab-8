package edu.sc.csce747.MeetingPlanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizationTest {
    private Organization organization;

    @BeforeEach
    public void setUp() {
        organization = new Organization();
    }

    @Test
    public void testGetRooms() {
        ArrayList<Room> rooms = organization.getRooms();
        assertNotNull(rooms);
        assertTrue(rooms.size() >= 5);
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

    @Test
    public void testGetEmployees() {
        ArrayList<Person> employees = organization.getEmployees();
        assertNotNull(employees);
        assertTrue(employees.size() >= 5);
    }

    @Test
    public void testGetEmployee_existingEmployee() throws Exception {
        Person employee = organization.getEmployee("Greg Gay");
        assertNotNull(employee);
        assertEquals("Greg Gay", employee.getName());
    }

    @Test
    public void testGetEmployee_nonExistingEmployee() {
        Exception exception = assertThrows(Exception.class,
                () -> organization.getEmployee("NonExistent Employee"));
        assertEquals("Requested employee does not exist", exception.getMessage());
    }

}
