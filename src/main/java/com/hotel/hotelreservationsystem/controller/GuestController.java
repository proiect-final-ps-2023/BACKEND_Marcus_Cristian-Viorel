package com.hotel.hotelreservationsystem.controller;

import com.hotel.hotelreservationsystem.dto.GuestDTO;
import com.hotel.hotelreservationsystem.dto.GuestWithGuestDataDTO;
import com.hotel.hotelreservationsystem.model.Guest;
import com.hotel.hotelreservationsystem.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @GetMapping("/findAll")
    public List<GuestDTO> findAll() {
        return guestService.getAllGuests().stream()
                .map(Guest::mapToDTO)
                .toList();
    }

    @RequestMapping("/findAllWithGuestData")
    public List<GuestWithGuestDataDTO> findAllWithGuestData() {
        return guestService.getAllGuestsWithGuestData().stream()
                .map(GuestWithGuestDataDTO::mapToDTO)
                .toList();
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        if(guestService.findGuestById(id) == null) {
            throw new IllegalArgumentException("Guest with id " + id + " does not exist");
        }

        Guest guest = guestService.deleteGuest(guestService.findGuestById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @PutMapping("/add")
    public void add(@RequestBody Guest guest) {
        guestService.createGuest(guest);
    }
}
