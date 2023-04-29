package com.hotel.hotelreservationsystem.controller;

import com.hotel.hotelreservationsystem.dto.BookingDTO;
import com.hotel.hotelreservationsystem.model.Booking;
import com.hotel.hotelreservationsystem.model.Guest;
import com.hotel.hotelreservationsystem.model.RoomType;
import com.hotel.hotelreservationsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/findAll")
    public List<BookingDTO> findAll() {
        return bookingService.getAllBookings().stream()
                .map(Booking::mapToDTO)
                .toList();
    }

    @GetMapping("/findBookingById/{id}")
    public BookingDTO findBookingById(@PathVariable Long id) {
        return bookingService.findBookingById(id).mapToDTO();
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        if(bookingService.findBookingById(id) == null) {
            throw new IllegalArgumentException("Booking with id " + id + " does not exist");
        }

        Booking boooking = bookingService.deleteBooking(bookingService.findBookingById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @PutMapping("/add")
    public void add(@RequestBody Booking booking) {
        // "yyyy-MM-dd" format
        bookingService.createBooking(booking);
    }

/*    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @PutMapping("/update/{id}/{name}/{cost}/{description}")
    public void update(@PathVariable Long id, @PathVariable String name, @PathVariable Double cost, @PathVariable String description) {
        RoomType roomType = roomTypeService.findRoomTypeById(id);
        roomType.setName(name);
        roomType.setCost(cost);
        roomType.setDescription(description);

        roomTypeService.updateRoomType(roomType);
    }*/

    // userId (the creator of the booking) is not updated
    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @PutMapping("/update/{id}/{checkInDate}/{checkOutDate}/{total}/{isPaid}")
    public void update(@PathVariable Long id, @PathVariable String checkInDate, @PathVariable String checkOutDate, @PathVariable Double total, @PathVariable Boolean isPaid) {
        // "yyyy-MM-dd" format
        Booking booking = bookingService.findBookingById(id);
        // convert string checkInDate to date YYYY-MM-DD

        booking.setCheckInDate(new Date(Integer.parseInt(checkInDate.substring(0, 4))-1900, Integer.parseInt(checkInDate.substring(5, 7))-1, Integer.parseInt(checkInDate.substring(8, 10))));
        booking.setCheckOutDate(new Date(Integer.parseInt(checkOutDate.substring(0, 4))-1900, Integer.parseInt(checkOutDate.substring(5, 7))-1, Integer.parseInt(checkOutDate.substring(8, 10))));
        booking.setTotal(total);
        booking.setIsPaid(isPaid);

        bookingService.updateBooking(booking);
    }
}
