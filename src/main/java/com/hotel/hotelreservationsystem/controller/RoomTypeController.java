package com.hotel.hotelreservationsystem.controller;

import com.hotel.hotelreservationsystem.dto.RoomTypeDTO;
import com.hotel.hotelreservationsystem.model.Room;
import com.hotel.hotelreservationsystem.model.RoomType;
import com.hotel.hotelreservationsystem.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomType")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @RequestMapping("/findAll")
    public List<RoomTypeDTO> findAll() {
        return roomTypeService.getAllRoomTypes().stream()
                .map(RoomType::mapToDTO)
                .toList();
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        if (roomTypeService.findRoomTypeById(id) == null) {
            throw new IllegalArgumentException("RoomType with id " + id + " does not exist");
        }

        RoomType roomType = roomTypeService.deleteRoomType(roomTypeService.findRoomTypeById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @PutMapping("/add")
    public void add(@RequestBody RoomType roomType) {
        roomTypeService.createRoomType(roomType);
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @PutMapping("/update/{id}/{name}/{cost}/{description}")
    public void update(@PathVariable Long id, @PathVariable String name, @PathVariable Double cost, @PathVariable String description) {
        RoomType roomType = roomTypeService.findRoomTypeById(id);
        roomType.setName(name);
        roomType.setCost(cost);
        roomType.setDescription(description);

        roomTypeService.updateRoomType(roomType);
    }
}
