package com.hotel.hotelreservationsystem.model;

import com.hotel.hotelreservationsystem.dto.BookingDTO;
import com.hotel.hotelreservationsystem.dto.GuestDTO;
import com.hotel.hotelreservationsystem.mapper.DTOMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Guest implements DTOMapper<GuestDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "booking_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Booking booking;

    @JoinColumn(name = "room_id")
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Room room;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "guest_data_id", referencedColumnName = "id")
    private GuestData guestData;

    @Override
    public GuestDTO mapToDTO() {
        return new GuestDTO(
                this.id,
                this.booking.getId(),
                this.room.getId(),
                this.guestData.getId()
        );
    }
}
