package com.hotel.hotelreservationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuestDataDTO extends DTO{

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Long guestId;

    @Override
    public String toString() {
        return "GuestDataDTO [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address + ", guestId=" + guestId + "]";
    }
}
