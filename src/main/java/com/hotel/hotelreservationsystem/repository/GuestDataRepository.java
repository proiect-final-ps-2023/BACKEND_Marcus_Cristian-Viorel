package com.hotel.hotelreservationsystem.repository;

import com.hotel.hotelreservationsystem.model.GuestData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestDataRepository extends CrudRepository<GuestData, Long> {

}
