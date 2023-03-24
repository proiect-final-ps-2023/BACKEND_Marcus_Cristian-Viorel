package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.Booking;
import com.hotel.hotelreservationsystem.repository.BookingRepository;
import com.hotel.hotelreservationsystem.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BookingServiceTest {

    private static final Long ID = 1L;
    private static final Date DATE = new Date(2000, Calendar.JANUARY, 12);
    private static final Double TOTAL = 100.0;
    private static final Boolean IS_PAID = false;

    private static final Long ID_NOT = 2L;
    private static final Date DATE_NOT = new Date(2001, Calendar.FEBRUARY, 13);
    private static final Double TOTAL_NOT = 200.0;
    private static final Boolean IS_PAID_NOT = true;

    private BookingServiceImpl bookingServiceImpl;

    @Mock
    private BookingRepository bookingRepository;

    private Booking booking;

    @BeforeEach
    void init() {
        initMocks(this);
        booking = new Booking();
        booking.setCheckInDate(DATE);
        booking.setCheckOutDate(DATE);
        booking.setTotal(TOTAL);
        booking.setIsPaid(IS_PAID);

        bookingServiceImpl = new BookingServiceImpl(bookingRepository);

        // Create booking
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // Find booking by id
        when(bookingRepository.findById(ID)).thenReturn(Optional.of(booking));
        when(bookingRepository.findById(ID_NOT)).thenReturn(Optional.empty());

        // Get all bookings
        when(bookingRepository.findAll()).thenReturn(Collections.singletonList(booking));

        // Update booking
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        // Delete booking
        doNothing().when(bookingRepository).delete(any(Booking.class));
    }

    @Test
    void createBooking() {
        Booking booking = bookingServiceImpl.createBooking(this.booking);
        assertEquals(booking, this.booking);
    }

    @Test
    void findBookingById() {
        Optional<Booking> booking = Optional.ofNullable(bookingServiceImpl.findBookingById(ID));
        assertEquals(booking.get(), this.booking);
    }

    @Test
    void findBookingByIdNotFound() {
        Optional<Booking> booking = Optional.ofNullable(bookingServiceImpl.findBookingById(ID_NOT));
        assertEquals(booking, Optional.empty());
    }

    @Test
    void getAllBookings() {
        List<Booking> bookings = bookingServiceImpl.getAllBookings();
        assertEquals(bookings.get(0), this.booking);
    }

    @Test
    void updateBooking() {
        Booking booking = bookingServiceImpl.updateBooking(this.booking);
        assertEquals(booking, this.booking);
    }

    @Test
    void deleteBooking() {
        bookingServiceImpl.deleteBooking(this.booking);
    }
}
