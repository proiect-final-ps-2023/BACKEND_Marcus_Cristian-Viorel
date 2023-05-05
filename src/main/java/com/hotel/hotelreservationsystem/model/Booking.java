package com.hotel.hotelreservationsystem.model;

import com.hotel.hotelreservationsystem.dto.BookingDTO;
import com.hotel.hotelreservationsystem.mapper.DTOMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Booking implements DTOMapper<BookingDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Column(name = "check_in_date", nullable = false)
    private Date checkInDate;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Column(name = "check_out_date", nullable = false)
    private Date checkOutDate;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private User user;

    @Override
    public BookingDTO mapToDTO() {
        return new BookingDTO(
                this.id,
                this.checkInDate,
                this.checkOutDate,
                this.total,
                this.isPaid,
                this.getUser().getId()
        );
    }
}
