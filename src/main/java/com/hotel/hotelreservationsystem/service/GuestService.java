package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.Guest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GuestService {

    // crud
    Guest createGuest(Guest guest);

    Guest findGuestById(Long id);

    List<Guest> getAllGuests();

    Guest updateGuest(Guest guest);

    Guest deleteGuest(Guest guest);
}
