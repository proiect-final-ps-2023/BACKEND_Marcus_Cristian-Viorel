package com.hotel.hotelreservationsystem.model;

import com.hotel.hotelreservationsystem.dto.BookingDTO;
import com.hotel.hotelreservationsystem.dto.UserDTO;
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
public class User implements DTOMapper<UserDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String pass;

    @Column(nullable = false)
    private Boolean isAdmin;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "id")
    private List<Booking> bookings;

    @Override
    public UserDTO mapToDTO() {
        return new UserDTO(
                this.id,
                this.name,
                this.pass,
                this.isAdmin
        );
    }
}
