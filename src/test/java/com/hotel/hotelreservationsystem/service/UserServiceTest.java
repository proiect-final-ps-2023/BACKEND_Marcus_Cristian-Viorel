package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.*;
import com.hotel.hotelreservationsystem.repository.*;
import com.hotel.hotelreservationsystem.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "name";
    private static final String PASS = "pass";
    private static final Boolean IS_ADMIN = false;

    private static final Long ID_NOT = 2L;
    private static final String NAME_NOT = "name_not";
    private static final String PASS_NOT = "pass_not";
    private static final Boolean IS_ADMIN_NOT = true;

    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void init() {
        initMocks(this);
        user = new User();
        user.setId(ID);
        user.setName(NAME);
        user.setPass(PASS);
        user.setIsAdmin(IS_ADMIN);

        userService = new UserServiceImpl(userRepository);

        // Create user
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Find user by id
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        when(userRepository.findById(ID_NOT)).thenReturn(Optional.empty());

        // Get all users
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        // Update user
        when(userRepository.save(user)).thenReturn(user);

        // Delete user
        doNothing().when(userRepository).delete(user);

        // Login
        when(userRepository.findByNameAndPass(NAME, PASS)).thenReturn(Optional.of(user));
        when(userRepository.findByNameAndPass(NAME_NOT, PASS_NOT)).thenReturn(Optional.empty());

        // Is admin
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        when(userRepository.findById(ID_NOT)).thenReturn(Optional.empty());

        // Add booking
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        // Remove booking
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        // Update booking
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        // Add guest
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        // Update guest
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        // Remove guest
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        // Add guest data
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        // Update guest data
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        // Remove guest data
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        // Add room
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Update room
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Remove room
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        // Add room type
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Update room type
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Remove room type
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));
    }

    @Test
    void testCreateUser() {
        User newUser = new User();
        newUser.setName("test");
        newUser.setPass("testpass");
        newUser.setIsAdmin(false);

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User createdUser = userService.createUser(newUser);

        assertEquals(createdUser, newUser);
    }

    @Test
    void testFindUserById() {
        Optional<User> optionalUser = userRepository.findById(ID);
        assertTrue(optionalUser.isPresent());

        User foundUser = userService.findUserById(ID);
        assertEquals(foundUser, user);

        Optional<User> optionalUserNot = userRepository.findById(ID_NOT);
        assertTrue(optionalUserNot.isEmpty());

        User foundUserNot = userService.findUserById(ID_NOT);
        assertNull(foundUserNot);
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = userService.getAllUsers();
        assertEquals(userList.size(), 1);
        assertEquals(userList.get(0), user);
    }

    @Test
    void testUpdateUser() {
        User updatedUser = new User();
        updatedUser.setId(ID);
        updatedUser.setName(NAME);
        updatedUser.setPass(PASS);
        updatedUser.setIsAdmin(true);

        // Update user
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);

        User result = userService.updateUser(updatedUser);

        assertEquals(updatedUser, result);
        verify(userRepository, times(1)).save(updatedUser);
    }


    @Test
    void testDeleteUser() {
        User deletedUser = userService.deleteUser(user);
        assertNotNull(deletedUser);
    }

    @Test
    void testLogin() {
        Optional<User> optionalUser = userRepository.findByNameAndPass(NAME, PASS);
        assertTrue(optionalUser.isPresent());

        User loggedInUser = userService.login(NAME, PASS);
        assertEquals(loggedInUser, user);

        Optional<User> optionalUserNot = userRepository.findByNameAndPass(NAME_NOT, PASS_NOT);
        assertTrue(optionalUserNot.isEmpty());

        User loggedInUserNot = userService.login(NAME_NOT, PASS_NOT);
        assertNull(loggedInUserNot);
    }

    @Test
    void testIsAdmin() {
        Boolean isAdmin = userService.isAdmin(user);
        assertFalse(isAdmin);

        Optional<User> optionalUserNot = userRepository.findById(ID_NOT);
        assertTrue(optionalUserNot.isEmpty());

        User userNot = optionalUserNot.orElse(null);
        Boolean isAdminNot = userService.isAdmin(userNot);
        assertFalse(isAdminNot);
    }

    @Test
    void testAddBooking() {
        Booking booking = new Booking();
        Boolean addedBooking = userService.addBooking(booking, mock(BookingRepository.class));
        assertTrue(addedBooking);
    }

    @Test
    void testRemoveBooking() {
        Booking booking = new Booking();
        Boolean removedBooking = userService.removeBooking(booking, mock(BookingRepository.class));
        assertFalse(removedBooking);
    }

    @Test
    void testUpdateBooking() {
        Booking booking = new Booking();
        booking.setId(1L);

        Booking updatedBooking = new Booking();
        updatedBooking.setId(1L);
        updatedBooking.setTotal(10000.0);

        // mock booking repository
        BookingRepository bookingRepository = mock(BookingRepository.class);
        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
        when(bookingRepository.save(updatedBooking)).thenReturn(updatedBooking);

        assertTrue(userService.updateBooking(updatedBooking, bookingRepository));
        verify(bookingRepository).save(updatedBooking);
    }



    @Test
    void testAddGuest() {
        Guest guest = new Guest();
        Boolean addedGuest = userService.addGuest(guest, mock(GuestRepository.class));
        assertTrue(addedGuest);
    }

    @Test
    void testUpdateGuest() {
        Guest guest = new Guest();
        Boolean updatedGuest = userService.updateGuest(guest, mock(GuestRepository.class));
        assertNotNull(updatedGuest);
    }

    @Test
    void testRemoveGuest() {
        Guest guest = new Guest();
        Boolean removedGuest = userService.removeGuest(guest, mock(GuestRepository.class));
        assertFalse(removedGuest);
    }

    @Test
    void testAddGuestData() {
        GuestData guestData = new GuestData();
        Boolean addedGuestData = userService.addGuestData(guestData, mock(GuestDataRepository.class));
        assertTrue(addedGuestData);
    }
}
