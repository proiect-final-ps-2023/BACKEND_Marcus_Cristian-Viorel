package com.hotel.hotelreservationsystem.dto;

import com.hotel.hotelreservationsystem.model.Guest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuestWithGuestDataDTO extends DTO {

    // guest specific fields
    private Long id;
    private Long bookingId;
    private Long roomId;
    private Long guestDataId;

    // guestData specific fields
    private String name;
    private String email;
    private String phone;
    private String address;

    @Override
    public String toString() {
        return "GuestWithGuestDataDTO [id=" + id + ", bookingId=" + bookingId + ", roomId=" + roomId + ", guestDataId=" + guestDataId + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address=" + address + ", guestId=" + "]";
    }

    public static GuestWithGuestDataDTO mapToDTO(Guest guest) {
        return new GuestWithGuestDataDTO(guest.getId(), guest.getBooking().getId(), guest.getRoom().getId(),
                guest.getGuestData().getId(), guest.getGuestData().getName(), guest.getGuestData().getEmail(),
                guest.getGuestData().getPhone(), guest.getGuestData().getAddress());
    }
}
