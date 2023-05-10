package com.hotel.hotelreservationsystem.repository;

import com.hotel.hotelreservationsystem.model.Guest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

    @Query("SELECT g FROM Guest g JOIN FETCH g.guestData")
    List<Guest> getAllGuestsWithGuestData();
}
