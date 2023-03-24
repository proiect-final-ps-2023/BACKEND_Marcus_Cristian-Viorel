package com.hotel.hotelreservationsystem.service.impl;

import com.hotel.hotelreservationsystem.model.Guest;
import com.hotel.hotelreservationsystem.repository.GuestRepository;
import com.hotel.hotelreservationsystem.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Guest findGuestById(Long id) {
        return guestRepository.findById(id).orElse(null);
    }

    @Override
    public List<Guest> getAllGuests() {
        return (List<Guest>) guestRepository.findAll();
    }

    @Override
    public Guest updateGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Guest deleteGuest(Guest guest) {
        guestRepository.delete(guest);
        return guest;
    }
}
