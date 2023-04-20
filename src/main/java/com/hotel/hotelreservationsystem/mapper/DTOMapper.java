package com.hotel.hotelreservationsystem.mapper;

import com.hotel.hotelreservationsystem.dto.DTO;

public interface DTOMapper<T extends DTO> {

    T mapToDTO();
}
