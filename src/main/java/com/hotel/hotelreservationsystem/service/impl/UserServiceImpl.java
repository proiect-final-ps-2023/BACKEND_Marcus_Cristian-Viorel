package com.hotel.hotelreservationsystem.service.impl;

import com.hotel.hotelreservationsystem.model.*;
import com.hotel.hotelreservationsystem.repository.*;
import com.hotel.hotelreservationsystem.service.BookingService;
import com.hotel.hotelreservationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User deleteUser(User user) {
        userRepository.delete(user);
        return user;
    }

    @Override
    public User login(String name, String pass) {
        User user = userRepository.findByNameAndPass(name, pass).orElse(null);
        return user;
    }

    @Override
    public Boolean isAdmin(User user) {
        if (user == null) {
            return false;
        }
        return user.getIsAdmin();
    }

    // regular user operations
    @Override
    public Boolean addBooking(Booking booking, BookingRepository bookingRepository) {
        bookingRepository.save(booking);
        return true;
    }

    @Override
    public Boolean removeBooking(Booking booking, BookingRepository bookingRepository) {
        Booking searchedBooking = bookingRepository.findById(booking.getId()).orElse(null);
        if(searchedBooking == null) {
            return false;
        } else {
            bookingRepository.delete(searchedBooking);
            return true;
        }
    }

    @Override
    public Boolean updateBooking(Booking booking, BookingRepository bookingRepository) {
        Booking searchedBooking = bookingRepository.findById(booking.getId()).orElse(null);
        if(searchedBooking == null) {
            return false;
        } else {
            bookingRepository.save(booking);
            return true;
        }
    }

    @Override
    public Boolean addGuest(Guest guest, GuestRepository guestRepository) {
        guestRepository.save(guest);
        return true;
    }

    @Override
    public Boolean updateGuest(Guest guest, GuestRepository guestRepository) {
        Guest searchedGuest = guestRepository.findById(guest.getId()).orElse(null);
        if(searchedGuest == null) {
            return false;
        } else {
            guestRepository.save(guest);
            return true;
        }
    }

    @Override
    public Boolean removeGuest(Guest guest, GuestRepository guestRepository) {
        Guest searchedGuest = guestRepository.findById(guest.getId()).orElse(null);
        if(searchedGuest == null) {
            return false;
        } else {
            guestRepository.delete(searchedGuest);
            return true;
        }
    }

    @Override
    public Boolean addGuestData(GuestData guestData, GuestDataRepository guestDataRepository) {
        guestDataRepository.save(guestData);
        return true;
    }

    @Override
    public Boolean updateGuestData(GuestData guestData, GuestDataRepository guestDataRepository) {
        GuestData searchedGuestData = guestDataRepository.findById(guestData.getId()).orElse(null);
        if(searchedGuestData == null) {
            return false;
        } else {
            guestDataRepository.save(guestData);
            return true;
        }
    }

    @Override
    public Boolean removeGuestData(GuestData guestData, GuestDataRepository guestDataRepository) {
        GuestData searchedGuestData = guestDataRepository.findById(guestData.getId()).orElse(null);
        if(searchedGuestData == null) {
            return false;
        } else {
            guestDataRepository.delete(searchedGuestData);
            return true;
        }
    }

    // additional admin operations
    @Override
    public Boolean addRoom(Room room, RoomRepository roomRepository) {
        roomRepository.save(room);
        return true;
    }

    @Override
    public Boolean removeRoom(Room room, RoomRepository roomRepository) {
        Room searchedRoom = roomRepository.findById(room.getId()).orElse(null);
        if(searchedRoom == null) {
            return false;
        } else {
            roomRepository.delete(searchedRoom);
            return true;
        }
    }

    @Override
    public Boolean updateRoom(Room room, RoomRepository roomRepository) {
        Room searchedRoom = roomRepository.findById(room.getId()).orElse(null);
        if(searchedRoom == null) {
            return false;
        } else {
            roomRepository.save(room);
            return true;
        }
    }

    @Override
    public Boolean addRoomType(RoomType roomType, RoomTypeRepository roomTypeRepository) {
        roomTypeRepository.save(roomType);
        return true;
    }

    @Override
    public Boolean removeRoomType(RoomType roomType, RoomTypeRepository roomTypeRepository) {
        RoomType searchedRoomType = roomTypeRepository.findById(roomType.getId()).orElse(null);
        if(searchedRoomType == null) {
            return false;
        } else {
            roomTypeRepository.delete(searchedRoomType);
            return true;
        }
    }

    @Override
    public Boolean updateRoomType(RoomType roomType, RoomTypeRepository roomTypeRepository) {
        RoomType searchedRoomType = roomTypeRepository.findById(roomType.getId()).orElse(null);
        if(searchedRoomType == null) {
            return false;
        } else {
            roomTypeRepository.save(roomType);
            return true;
        }
    }
}
