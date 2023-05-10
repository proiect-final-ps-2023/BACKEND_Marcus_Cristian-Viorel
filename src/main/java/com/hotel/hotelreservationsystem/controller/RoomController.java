package com.hotel.hotelreservationsystem.controller;

import com.hotel.hotelreservationsystem.dto.RoomDTO;
import com.hotel.hotelreservationsystem.dto.RoomWithRoomTypeDTO;
import com.hotel.hotelreservationsystem.model.Room;
import com.hotel.hotelreservationsystem.model.RoomType;
import com.hotel.hotelreservationsystem.service.RoomService;
import com.hotel.hotelreservationsystem.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomTypeService roomTypeService;

    @RequestMapping("/findAll")
    public List<RoomDTO> findAll() {
        return roomService.getAllRooms().stream()
                .map(Room::mapToDTO)
                .toList();
    }

    @RequestMapping("/findAllWithRoomTypes")
    public List<RoomWithRoomTypeDTO> findAllWithRoomTypes() {
        return roomService.getAllRoomsWithRoomTypes().stream()
                .map(RoomWithRoomTypeDTO::mapToDTO)
                .toList();
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        if (roomService.findRoomById(id) == null) {
            throw new IllegalArgumentException("Room with id " + id + " does not exist");
        }

        Room room = roomService.deleteRoom(roomService.findRoomById(id));
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @PutMapping("/add/{number}/{roomTypeId}")
    public void add(@PathVariable Integer number, @PathVariable Long roomTypeId) {
        Room room = new Room();
        room.setNumber(number);

        RoomType roomType = new RoomType();
        roomType.setId(roomTypeId);
        room.setRoomType(roomType);

        if(roomService.findRoomByNumber(room.getNumber()) == null) {
            roomService.createRoom(room);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @PutMapping("/update/{id}/{number}/{roomTypeId}")
    public void update(@PathVariable Long id, @PathVariable Integer number, @PathVariable Long roomTypeId) {
        Room room = roomService.getAllRoomsWithRoomTypes().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);

        room.setNumber(number);

        RoomType roomType = roomTypeService.findRoomTypeById(roomTypeId);
        room.setRoomType(roomType);

        roomService.updateRoom(room);
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @GetMapping("/findById/{id}")
    public RoomDTO findById(@PathVariable Long id) {
        Room room = roomService.findRoomById(id);
        if(room == null) {
            throw new IllegalArgumentException("Room with id " + id + " does not exist");
        }

        return room.mapToDTO();
    }

    @CrossOrigin(origins = "http://localhost:4200") // CORS unblocking
    @GetMapping("/findAllVacantWithRoomTypes")
    public List<RoomWithRoomTypeDTO> findAllVacantWithRoomTypes() {
        return roomService.getAllVacantRooms().stream()
                .map(RoomWithRoomTypeDTO::mapToDTO)
                .toList();
    }
}
