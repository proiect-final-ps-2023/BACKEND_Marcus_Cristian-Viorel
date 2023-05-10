package com.hotel.hotelreservationsystem.model;

import com.hotel.hotelreservationsystem.dto.BookingDTO;
import com.hotel.hotelreservationsystem.dto.UserDTO;
import com.hotel.hotelreservationsystem.mapper.DTOMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
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

    @Pattern(regexp = "^[^\\s]+$", message = "Name must not contain spaces")
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String hashedPass;

    public void setPass(String pass) {
        if (pass.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.hashedPass = encoder.encode(pass);
        } else {
            throw new IllegalArgumentException("Password must contain at least 1 letter, and be at least 6 characters long");
        }
    }

    public boolean checkPass(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(pass, this.hashedPass);
    }


/*    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must contain at least 1 letter, and be at least 6 characters long")
    @Column(nullable = false)
    private String pass;*/

    @Column(nullable = false)
    private Boolean isAdmin;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "id")
    private List<Booking> bookings;

    private Date lastLogin;

    @Override
    public UserDTO mapToDTO() {
        return new UserDTO(
                this.id,
                this.name,
                this.hashedPass,
                this.isAdmin,
                this.lastLogin
        );
    }
}
