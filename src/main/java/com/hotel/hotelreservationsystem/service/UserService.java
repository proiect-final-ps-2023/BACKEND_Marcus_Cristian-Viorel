package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.*;
import com.hotel.hotelreservationsystem.repository.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    // crud
    User createUser(User user);

    User findUserById(Long id);

    List<User> getAllUsers();

    User updateUser(User user);

    User deleteUser(User user);

    User login(String name, String pass);

    Boolean isAdmin(User user);

    // regular user operations

    Boolean addBooking(Booking booking, BookingRepository bookingRepository);
    Boolean removeBooking(Booking booking, BookingRepository bookingRepository);
    Boolean updateBooking(Booking booking, BookingRepository bookingRepository);

    Boolean addGuest(Guest guest, GuestRepository guestRepository);
    Boolean updateGuest(Guest guest, GuestRepository guestRepository);
    Boolean removeGuest(Guest guest, GuestRepository guestRepository);

    Boolean addGuestData(GuestData guestData, GuestDataRepository guestDataRepository);
    Boolean removeGuestData(GuestData guestData, GuestDataRepository guestDataRepository);
    Boolean updateGuestData(GuestData guestData, GuestDataRepository guestDataRepository);

    // additional admin operations
    Boolean addRoom(Room room, RoomRepository roomRepository);
    Boolean removeRoom(Room room, RoomRepository roomRepository);
    Boolean updateRoom(Room room, RoomRepository roomRepository);

    Boolean addRoomType(RoomType roomType, RoomTypeRepository roomTypeRepository);
    Boolean removeRoomType(RoomType roomType, RoomTypeRepository roomTypeRepository);
    Boolean updateRoomType(RoomType roomType, RoomTypeRepository roomTypeRepository);
}
