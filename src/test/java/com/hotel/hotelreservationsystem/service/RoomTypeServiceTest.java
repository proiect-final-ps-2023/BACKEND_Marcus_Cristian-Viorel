package com.hotel.hotelreservationsystem.service;

import com.hotel.hotelreservationsystem.model.Room;
import com.hotel.hotelreservationsystem.model.RoomType;
import com.hotel.hotelreservationsystem.repository.RoomTypeRepository;
import com.hotel.hotelreservationsystem.service.impl.RoomTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RoomTypeServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "Single";
    private static final Double COST = 100.0;
    private static final String DESCRIPTION = "Single room";

    private static final Long ID_NOT = 2L;
    private static final String NAME_NOT = "Double";
    private static final Double COST_NOT = 200.0;
    private static final String DESCRIPTION_NOT = "Double room";

    private RoomTypeServiceImpl roomTypeServiceImpl;

    @Mock
    private RoomTypeRepository roomTypeRepository;

    private RoomType roomType;

    @BeforeEach
    void init() {
        initMocks(this);
        roomType = new RoomType();
        roomType.setId(ID);
        roomType.setName(NAME);
        roomType.setCost(COST);
        roomType.setDescription(DESCRIPTION);

        roomTypeServiceImpl = new RoomTypeServiceImpl(roomTypeRepository);

        // Create room type
        when(roomTypeRepository.save(any(RoomType.class))).thenReturn(roomType);

        // Find room type by id
        when(roomTypeRepository.findById(ID)).thenReturn(Optional.of(roomType));
        when(roomTypeRepository.findById(ID_NOT)).thenReturn(Optional.empty());

        // Get all room types
        when(roomTypeRepository.findAll()).thenReturn(Collections.singletonList(roomType));

        // Update room type
        when(roomTypeRepository.save(any(RoomType.class))).thenReturn(roomType);

        // Delete room type
        doNothing().when(roomTypeRepository).delete(any(RoomType.class));
    }

    @Test
    void createRoomTypeTest() {
        RoomType roomType = new RoomType();
        RoomType createdRoomType = roomTypeServiceImpl.createRoomType(roomType);

        assertEquals(createdRoomType.getName(), NAME);
        assertEquals(createdRoomType.getCost(), COST);
        assertEquals(createdRoomType.getDescription(), DESCRIPTION);
    }

    @Test
    void findRoomTypeByIdTest() {
        RoomType roomType = roomTypeServiceImpl.findRoomTypeById(ID);
        assertEquals(roomType.getName(), NAME);
        assertEquals(roomType.getCost(), COST);
        assertEquals(roomType.getDescription(), DESCRIPTION);
    }

    @Test
    void findRoomTypeByIdNotFoundTest() {
        RoomType roomType = roomTypeServiceImpl.findRoomTypeById(ID_NOT);
        assertNull(roomType);
    }

    @Test
    void getAllRoomTypesTest() {
        assertEquals(roomTypeServiceImpl.getAllRoomTypes().size(), 1);
    }

    @Test
    void updateRoomTypeTest() {
        RoomType roomType = new RoomType();
        RoomType updatedRoomType = roomTypeServiceImpl.updateRoomType(roomType);
        assertEquals(updatedRoomType.getName(), NAME);
        assertEquals(updatedRoomType.getCost(), COST);
        assertEquals(updatedRoomType.getDescription(), DESCRIPTION);
    }

    @Test
    void deleteRoomTypeTest() {
        roomTypeServiceImpl.deleteRoomType(this.roomType);
    }
}
