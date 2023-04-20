package com.hotel.hotelreservationsystem.model;

import com.hotel.hotelreservationsystem.dto.BookingDTO;
import com.hotel.hotelreservationsystem.dto.RoomDTO;
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
@Builder
@Entity
public class Room implements DTOMapper<RoomDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int number;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "id")
    private List<Guest> guests;

    @JoinColumn(name = "room_type_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private RoomType roomType;

    @Override
    public RoomDTO mapToDTO() {
        return new RoomDTO(
                this.id,
                this.number,
                this.roomType.getId()
        );
    }
}
