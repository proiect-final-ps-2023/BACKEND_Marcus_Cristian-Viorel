package com.hotel.hotelreservationsystem;

import com.hotel.hotelreservationsystem.model.*;
import com.hotel.hotelreservationsystem.repository.*;
import com.hotel.hotelreservationsystem.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.awt.print.Book;
import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EntityScan
@EnableJpaRepositories
@SpringBootApplication
public class HotelReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelReservationSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, RoomTypeRepository roomTypeRepository,
                           RoomRepository roomRepository, GuestRepository guestRepository,
                           GuestDataRepository guestDataRepository, BookingRepository bookingRepository,

                           UserService userService, RoomTypeService roomTypeService,
                           RoomService roomService, GuestService guestService,
                           GuestDataService guestDataService, BookingService bookingService) {
        return args -> {

            // Create users
            User user1 = User.builder().name("marin_alexandra").isAdmin(false).build();
            user1.setPass("pisicute123");

            User user2 = User.builder().name("popcristian13").isAdmin(true).build();
            user2.setPass("haisteaua01");

            List<User> users = List.of(user1, user2);
            userRepository.saveAll(users);

            // Create room types
            RoomType roomType1 = RoomType.builder().name("Standard").cost(100.0).description("1 king bed room with city view + balcony").build();
            RoomType roomType2 = RoomType.builder().name("Executive").cost(500.0).description("1 king bed room with beach view + balcony").build();
            RoomType roomType3 = RoomType.builder().name("Standard Twin Room").cost(150.0).description("2 Twin bed room").build();
            List<RoomType> roomTypes = List.of(roomType1, roomType2, roomType3);
            roomTypeRepository.saveAll(roomTypes);

            // Create rooms
            Room room1 = Room.builder().roomType(roomType1).number(19).build();
            Room room2 = Room.builder().roomType(roomType2).number(13).build();
            Room room3 = Room.builder().roomType(roomType3).number(10).build();
            Room room4 = Room.builder().roomType(roomType2).number(23).build();
            Room room5 = Room.builder().roomType(roomType3).number(50).build();
            List<Room> rooms = List.of(room1, room3, room2, room4, room5);
            roomRepository.saveAll(rooms);

            // Create bookings
            Booking booking1 = Booking.builder().checkInDate(new Date(2021 - 1900, Calendar.JUNE, 1)).checkOutDate(new Date(2021 - 1900, Calendar.JUNE, 5)).total(400.0).isPaid(false).user(user1).build();
            Booking booking2 = Booking.builder().checkInDate(new Date(2013 - 1900, Calendar.DECEMBER, 21)).checkOutDate(new Date(2021 - 1900, Calendar.DECEMBER, 30)).total(530.0).isPaid(false).user(user2).build();
            Booking booking3 = Booking.builder().checkInDate(new Date(2023 - 1900, Calendar.MARCH, 5)).checkOutDate(new Date(2023 - 1900, Calendar.MARCH, 5)).total(700.0).isPaid(false).user(user1).build();
            Booking booking4 = Booking.builder().checkInDate(new Date(2024 - 1900, Calendar.SEPTEMBER, 14)).checkOutDate(new Date(2024 - 1900, Calendar.SEPTEMBER, 24)).total(950.0).isPaid(false).user(user2).build();
            List<Booking> bookings = List.of(booking1, booking2, booking3, booking4);
            bookingRepository.saveAll(bookings);

            // Create guest data
            GuestData guestData1 = GuestData.builder().name("Criste Cosmin").email("criste_cosmin@gmail.com").phone("0724351786").address("Strada Ceahlau").build();
            GuestData guestData2 = GuestData.builder().name("Martis Xonia").email("martisssimona@yahoo.com").phone("0742363685").address("Strada Peana").build();
            GuestData guestData3 = GuestData.builder().name("Popescu Ion").email("ion-pop@hotmal.com").phone("0743928567").address("Strada Mihai Viteazul").build();
            List<GuestData> guestData = List.of(guestData3, guestData1, guestData2);
            guestDataRepository.saveAll(guestData);

            // Create guests
            Guest guest1 = Guest.builder().guestData(guestData1).booking(booking1).room(room1).build();
            Guest guest2 = Guest.builder().guestData(guestData2).booking(booking2).room(room2).build();
            Guest guest3 = Guest.builder().guestData(guestData3).booking(booking2).room(room3).build();
            List<Guest> guests = List.of(guest3, guest1, guest2);
            guestRepository.saveAll(guests);


            // CascadeType.ALL initial


            // list users
            System.out.println("\nUsers:");
            userRepository.findAll().forEach(
                    user -> {
                        System.out.println(user.getName() + " " + user.getHashedPass() + " " + user.getIsAdmin());
                    }
            );

            // list room types
            System.out.println("\nRoom types:");
            roomTypeRepository.findAll().forEach(
                    roomType -> {
                        System.out.println(roomType.getName() + " " + roomType.getCost() + " " + roomType.getDescription());
                    }
            );

            // list rooms
            System.out.println("\nRooms:");
            roomRepository.findAll().forEach(
                    room -> {
                        System.out.println(room.getNumber() + " " + room.getRoomType().getName());
                    }
            );

            // list bookings
            System.out.println("\nBookings:");
            bookingRepository.findAll().forEach(
                    booking -> {
                        System.out.println(booking.getCheckInDate() + " " + booking.getCheckOutDate() + " " + booking.getTotal() + " " + booking.getIsPaid() + " " + booking.getUser().getName());
                    }
            );

            // list guest data
            System.out.println("\nGuest data:");
            guestDataRepository.findAll().forEach(
                    gd -> {
                        System.out.println(gd.getName() + " " + gd.getEmail() + " " + gd.getPhone() + " " + gd.getAddress());
                    }
            );

            // list guests
            System.out.println("\nGuests:");
            guestRepository.findAll().forEach(
                    guest -> {
                        System.out.println(guest.getGuestData().getName() + " " + guest.getBooking().getCheckInDate() + " " + guest.getRoom().getNumber());
                    }
            );


            // regular user operations example
            System.out.println("\nRegular user login operation example:");
            if(userService.login("Marin Alexandra", "pass1") != null) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed");
            }

            System.out.println("\nRegular user create booking operation example:");
            Booking bookingExample = Booking.builder().checkInDate(new Date(2022 - 1900, Calendar.FEBRUARY, 3)).checkOutDate(new Date(2021 - 1900, Calendar.FEBRUARY, 19)).total(1140.0).isPaid(false).user(user1).build();
            if(userService.addBooking(bookingExample, bookingRepository) != null) {
                System.out.println("Booking created:");
                bookingRepository.findAll().forEach(
                        booking -> {
                            System.out.println(booking.getCheckInDate() + " " + booking.getCheckOutDate() + " " + booking.getTotal() + " " + booking.getIsPaid() + " " + booking.getUser().getName());
                        }
                );
            } else {
                System.out.println("Booking not created");
            }

            // admin operations example
            System.out.println("\nAdmin login operation example:");
            if(userService.login("Pop Cristian", "pass2") != null) {
                System.out.println("Login successful (admin)");
            } else {
                System.out.println("Login failed (admin)");
            }

            System.out.println("\nAdmin create room operation example:");
            Room roomExample = Room.builder().roomType(roomType1).number(45).build();
            if(userService.addRoom(roomExample, roomRepository) != null) {
                System.out.println("Room created:");
                roomRepository.findAll().forEach(
                        room -> {
                            System.out.println(room.getNumber() + " " + room.getRoomType().getName());
                        }
                );
            } else {
                System.out.println("Room not created");
            }
        };
    }
}
