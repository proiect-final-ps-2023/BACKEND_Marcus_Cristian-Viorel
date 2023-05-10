package com.hotel.hotelreservationsystem.repository;

import com.hotel.hotelreservationsystem.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

}
