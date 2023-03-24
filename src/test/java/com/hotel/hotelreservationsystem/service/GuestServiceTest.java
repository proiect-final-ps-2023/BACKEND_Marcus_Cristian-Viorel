package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.Guest;
import com.hotel.hotelreservationsystem.repository.GuestRepository;
import com.hotel.hotelreservationsystem.service.impl.GuestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GuestServiceTest {

    private static final Long ID = 1L;

    private static final Long ID_NOT = 2L;

    private GuestServiceImpl guestServiceImpl;

    @Mock
    private GuestRepository guestRepository;

    private Guest guest;

    @BeforeEach
    void init() {
        initMocks(this);
        guest = new Guest();
        guest.setId(ID);

        guestServiceImpl = new GuestServiceImpl(guestRepository);

        // Create guest
        when(guestRepository.save(any(Guest.class))).thenReturn(guest);

        // Find guest by id
        when(guestRepository.findById(ID)).thenReturn(Optional.of(guest));
        when(guestRepository.findById(ID_NOT)).thenReturn(Optional.empty());

        // Get all guests
        when(guestRepository.findAll()).thenReturn(Collections.singletonList(guest));

        // Delete guest
        doNothing().when(guestRepository).deleteById(ID);
        doNothing().when(guestRepository).deleteById(ID_NOT);
    }

    @Test
    void createGuestTest() {
        Guest guest = guestServiceImpl.createGuest(new Guest());
        assertEquals(ID, guest.getId());
    }

    @Test
    void findGuestByIdTest() {
        Guest guest = guestServiceImpl.findGuestById(ID);
        assertEquals(ID, guest.getId());
    }

    @Test
    void findGuestByIdNotFoundTest() {
        Guest guest = guestServiceImpl.findGuestById(ID_NOT);
        assertNull(guest);
    }

    @Test
    void getAllGuestsTest() {
        assertEquals(1, guestServiceImpl.getAllGuests().size());
    }

    @Test
    void deleteGuestTest() {
        guestServiceImpl.deleteGuest(this.guest);
    }

    @Test
    void updateGuestTest() {
        Guest guest = guestServiceImpl.updateGuest(new Guest());
        assertEquals(ID, guest.getId());
    }

}
