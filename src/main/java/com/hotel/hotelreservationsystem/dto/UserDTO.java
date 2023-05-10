package com.hotel.hotelreservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO extends DTO {

    private Long id;
    private String name;
    private String hashedPass;
    private Boolean isAdmin;
    private Date lastLogin;

    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", username=" + name + ", password=" + hashedPass + ", isAdmin=" + isAdmin + ", lastLogin=" + lastLogin + "]";
    }
}
