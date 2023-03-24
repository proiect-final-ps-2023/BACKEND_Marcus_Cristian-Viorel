package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.GuestData;
import com.hotel.hotelreservationsystem.repository.GuestDataRepository;
import com.hotel.hotelreservationsystem.service.impl.GuestDataServiceImpl;
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

public class GuestDataServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "John";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String ADDRESS = "address";

    private static final Long ID_NOT = 2L;
    private static final String NAME_NOT = "Alex";
    private static final String EMAIL_NOT = "not email";
    private static final String PHONE_NOT = "not phone";
    private static final String ADDRESS_NOT = "not address";

    private GuestDataServiceImpl guestDataServiceImpl;

    @Mock
    private GuestDataRepository guestDataRepository;

    private GuestData guestData;

    @BeforeEach
    void init() {
        initMocks(this);
        guestData = new GuestData();
        guestData.setId(ID);
        guestData.setName(NAME);
        guestData.setEmail(EMAIL);
        guestData.setPhone(PHONE);
        guestData.setAddress(ADDRESS);

        guestDataServiceImpl = new GuestDataServiceImpl(guestDataRepository);

        // Create guest data
        when(guestDataRepository.save(any(GuestData.class))).thenReturn(guestData);

        // Find guest data by id
        when(guestDataRepository.findById(ID)).thenReturn(Optional.of(guestData));
        when(guestDataRepository.findById(ID_NOT)).thenReturn(Optional.empty());

        // Get all guest data
        when(guestDataRepository.findAll()).thenReturn(Collections.singletonList(guestData));

        // Update guest data
        when(guestDataRepository.save(any(GuestData.class))).thenReturn(guestData);

        // Delete guest data
        doNothing().when(guestDataRepository).delete(any(GuestData.class));
    }

    @Test
    void createGuestDataTest() {
        GuestData guestData = guestDataServiceImpl.createGuestData(this.guestData);
        assertEquals(guestData, this.guestData);
    }

    @Test
    void findGuestDataByIdTest() {
        GuestData guestData = guestDataServiceImpl.findGuestDataById(ID);
        assertEquals(guestData, this.guestData);
    }

    @Test
    void findGuestDataByIdTest_NotFound() {
        GuestData guestData = guestDataServiceImpl.findGuestDataById(ID_NOT);
        assertNull(guestData);
    }

    @Test
    void getAllGuestDataTest() {
        Iterable<GuestData> guestData = guestDataServiceImpl.getAllGuestData();
        assertEquals(guestData, Collections.singletonList(this.guestData));
    }

    @Test
    void updateGuestDataTest() {
        GuestData guestData = guestDataServiceImpl.updateGuestData(this.guestData);
        assertEquals(guestData, this.guestData);
    }

    @Test
    void deleteGuestDataTest() {
        guestDataServiceImpl.deleteGuestData(this.guestData);
    }
}
