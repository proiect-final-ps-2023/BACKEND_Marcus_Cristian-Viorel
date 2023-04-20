package com.hotel.hotelreservationsystem.model;

import com.hotel.hotelreservationsystem.dto.BookingDTO;
import com.hotel.hotelreservationsystem.dto.RoomTypeDTO;
import com.hotel.hotelreservationsystem.mapper.DTOMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class RoomType implements DTOMapper<RoomTypeDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double cost;

    private String description;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "id")
    private List<Room> rooms;

    @Override
    public RoomTypeDTO mapToDTO() {
        return new RoomTypeDTO(
                this.id,
                this.name,
                this.cost,
                this.description
        );
    }
}
