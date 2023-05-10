package com.hotel.hotelreservationsystem.controller;

import com.hotel.hotelreservationsystem.dto.GuestDataDTO;
import com.hotel.hotelreservationsystem.model.GuestData;
import com.hotel.hotelreservationsystem.service.GuestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guestdata")
public class GuestDataController {

    @Autowired
    private GuestDataService guestDataService;

    @RequestMapping("/findAll")
    public List<GuestDataDTO> findAll() {
        return guestDataService.getAllGuestData().stream()
                .map(GuestData::mapToDTO)
                .toList();
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @PutMapping("/add")
    public GuestData add(@RequestBody GuestData guestData) {
        return guestDataService.createGuestData(guestData);
    }
}
