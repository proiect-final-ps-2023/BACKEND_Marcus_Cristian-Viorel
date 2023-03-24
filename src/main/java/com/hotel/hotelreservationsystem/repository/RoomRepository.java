package com.hotel.hotelreservationsystem.repository;

import com.hotel.hotelreservationsystem.model.Guest;
import com.hotel.hotelreservationsystem.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    Optional<Room> findByNumber(int number);
    List<Room> findAllByRoomType_Id(Long id);

}
