package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.Booking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookingService {

    // crud
    Booking createBooking(Booking booking);

    Booking findBookingById(Long id);

    List<Booking> getAllBookings();

    Booking updateBooking(Booking booking);

    Booking deleteBooking(Booking booking);

}
