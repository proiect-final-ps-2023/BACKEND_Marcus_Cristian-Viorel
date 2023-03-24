package com.hotel.hotelreservationsystem.service.impl;

import com.hotel.hotelreservationsystem.model.RoomType;
import com.hotel.hotelreservationsystem.repository.RoomTypeRepository;
import com.hotel.hotelreservationsystem.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    @Override
    public RoomType createRoomType(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    @Override
    public RoomType findRoomTypeById(Long id) {
        return roomTypeRepository.findById(id).orElse(null);
    }

    @Override
    public List<RoomType> getAllRoomTypes() {
        return (List<RoomType>) roomTypeRepository.findAll();
    }

    @Override
    public RoomType updateRoomType(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    @Override
    public RoomType deleteRoomType(RoomType roomType) {
        roomTypeRepository.delete(roomType);
        return roomType;
    }
}
