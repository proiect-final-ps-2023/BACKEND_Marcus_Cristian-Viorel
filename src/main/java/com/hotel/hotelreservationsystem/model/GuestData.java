package com.hotel.hotelreservationsystem.model;

import com.hotel.hotelreservationsystem.dto.BookingDTO;
import com.hotel.hotelreservationsystem.dto.GuestDTO;
import com.hotel.hotelreservationsystem.dto.GuestDataDTO;
import com.hotel.hotelreservationsystem.mapper.DTOMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class GuestData implements DTOMapper<GuestDataDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "id")
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "guestData")
    private Guest guest;

    @Column(name = "name", nullable = false)
    private String name;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
    @Column(name = "email", nullable = false)
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number format")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Override
    public GuestDataDTO mapToDTO() {
        return new GuestDataDTO(
                this.id,
                this.name,
                this.email,
                this.phone,
                this.address,
                this.guest.getId()
        );
    }
}
