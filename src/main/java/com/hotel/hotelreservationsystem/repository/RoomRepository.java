package com.hotel.hotelreservationsystem.repository;

import com.hotel.hotelreservationsystem.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query("SELECT r FROM Room r JOIN FETCH r.roomType WHERE r.id = :id")
    Optional<Room> findById(Long id);

    Optional<Room> findByNumber(int number);

    List<Room> findAllByRoomType_Id(Long id);

    @Query("SELECT r FROM Room r JOIN FETCH r.roomType")
    List<Room> getAllRoomsWithRoomTypes();

    @Query("SELECT r FROM Room r JOIN FETCH r.roomType WHERE r.guests IS EMPTY")
    List<Room> getAllVacantRooms();

    Room findRoomByNumber(Integer roomNumber);
}
