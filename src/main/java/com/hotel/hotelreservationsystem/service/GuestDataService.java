package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.GuestData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GuestDataService {

    // crud
    GuestData createGuestData(GuestData guestData);

    GuestData findGuestDataById(Long id);

    List<GuestData> getAllGuestData();

    GuestData updateGuestData(GuestData guestData);

    GuestData deleteGuestData(GuestData guestData);
}
