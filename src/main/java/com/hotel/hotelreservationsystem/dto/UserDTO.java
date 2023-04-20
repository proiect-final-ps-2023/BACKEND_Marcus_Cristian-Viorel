package com.hotel.hotelreservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO extends DTO {

    private Long id;
    private String username;
    private String password;
    private Boolean isAdmin;

    @Override
    public String toString() {
        return "UserDTO [id=" + id + ", username=" + username + ", password=" + password + ", isAdmin=" + isAdmin + "]";
    }
}